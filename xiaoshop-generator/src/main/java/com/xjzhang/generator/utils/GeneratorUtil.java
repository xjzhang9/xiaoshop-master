package com.xjzhang.generator.utils;

import com.xjzhang.common.exception.BusinessException;
import com.xjzhang.generator.model.ColumnInfo;
import com.xjzhang.generator.model.TableInfo;
import com.xjzhang.utils.DateUtils;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成器工具类
 *
 * @author xjzhang
 * @version 1.0
 * @date 2021/9/4 11:29
 */
public class GeneratorUtil {
    /**
     * 获得
     *
     * @return
     */
    public List<String> getCodeTemplateList() {
        List<String> templatList = new ArrayList<>();
        templatList.add("Controller.java.vm");
        templatList.add("Dao.java.vm");
        templatList.add("Dao.xml.vm");
        templatList.add("Entity.java.vm");
        templatList.add("list.html.vm");
        templatList.add("list.js.vm");
        templatList.add("menu.sql.vm");
        templatList.add("Service.java.vm");
        templatList.add("ServiceImpl.java.vm");

        return templatList;
    }

    public static Configuration getConfig() {
        try {
            return new PropertiesConfiguration("generator.properties");
        } catch (ConfigurationException e) {
            throw new BusinessException("获得生成代码配置文件失败：", e);
        }
    }

    public void generatorCode(TableInfo tableInfo, List<ColumnInfo> columnInfoList, ZipOutputStream zip) {
        Configuration config = getConfig();

        String className = DbNameToJava(tableInfo.getTableName());
        tableInfo.setClassName(className);

        final boolean[] hasBigDecimal = {false};

        columnInfoList.stream().map(columnInfo -> {
            // 列名转换为java属性名
            columnInfo.setPropertyName(DbNameToJava(columnInfo.getColumnName()));

            // 列属性转换为java属性类型
            String propertyType = config.getString(columnInfo.getDataType(), "unKnowType");
            columnInfo.setPropertyType(propertyType);
            if (!hasBigDecimal[0] && "BigDecimal".equals(propertyType)) {
                hasBigDecimal[0] = true;
            }

            if ("PRI".equalsIgnoreCase(columnInfo.getColumnType()) && tableInfo.getPk() == null) {
                tableInfo.setPk(columnInfo);
            }

            return columnInfo;
        }).collect(Collectors.toList());

        tableInfo.setColumnInfoList(columnInfoList);

        if (tableInfo.getPk() == null) {
            tableInfo.setPk(tableInfo.getColumnInfoList().get(0));
        }

        // 设置velocity 资源加载器
        Properties prop = new Properties();
        prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(prop);

        String mainPath = config.getString("main.path");
        mainPath = StringUtils.isBlank(mainPath) ? "com.xjzhang" : mainPath;

        // 封装模板数据
        Map<String, Object> map = new HashMap<>();
        map.put("tableName", tableInfo.getTableName());
        map.put("comments", tableInfo.getTableComment());
        map.put("pk", tableInfo.getPk());
        map.put("className", tableInfo.getClassName());
        map.put("pathName", tableInfo.getClassName().toLowerCase());
        map.put("columns", tableInfo.getColumnInfoList());
        map.put("hasBigDecimal", hasBigDecimal);
        map.put("mainPath", mainPath);
        map.put("packageName", config.getString("package"));
        map.put("moduleName", config.getString("moduleName"));
        map.put("author", config.getString("author"));
        map.put("email", config.getString("email"));
        map.put("datetime", DateUtils.format(new Date(), DateUtils.DATE_TIME_FORMAT));

        VelocityContext context = new VelocityContext(map);
        List<String> templateList = getCodeTemplateList();
        templateList.forEach(template -> {
            StringWriter stringWriter = new StringWriter();
            Template tpl = Velocity.getTemplate(template, "utf-8");
            tpl.merge(context, stringWriter);

            try {
                //添加到zip文件中
                zip.putNextEntry(new ZipEntry(getTemplatePath(template, tableInfo.getClassName(), config.getString("package"), config.getString("moduleName"))));
                IOUtils.write(stringWriter.toString(), zip, "utf-8");
                IOUtils.closeQuietly(stringWriter);
                zip.closeEntry();
            } catch (IOException e) {
                throw new BusinessException("渲染模板失败，表名：" + tableInfo.getTableName(), e);
            }
        });
    }


    /**
     * 数据库里面的表名或者列明转换为Java 的驼峰命名法
     *
     * @param name
     * @return
     */
    public String DbNameToJava(String name) {
        return WordUtils.capitalizeFully(name, new char[]{'_'}).replace("_", "");
    }

    /**
     * 获取模块生成路径
     *
     * @param template
     * @param className
     * @param packageName
     * @param moduleName
     * @return
     */
    public String getTemplatePath(String template, String className, String packageName, String moduleName) {
        String packagePath = "main" + File.separator + "java" + File.separator;
        if (StringUtils.isNoneBlank(packageName)) {
            packagePath += packageName.replace(".", File.separator) + File.separator + moduleName + File.separator;
        }

        if (template.contains("Controller.java.vm")) {
            return packagePath + "controller" + File.separator + className + "Controller.java";
        }

        if (template.contains("Dao.java.vm")) {
            return packagePath + "dao" + File.separator + className + "Dao.java";
        }

        if (template.contains("Dao.xml.vm")) {
            return packagePath + "resources" + File.separator + "mapper" + File.separator + moduleName + File.separator + className + "Dao.xml";
        }

        if (template.contains("Entity.java.vm")) {
            return packagePath + "model" + File.separator + className + "Entity.java";
        }

        if (template.contains("Service.java.vm")) {
            return packagePath + "service" + File.separator + className + "Service.java";
        }

        if (template.contains("ServiceImpl.java.vm")) {
            return packagePath + "service" + File.separator + "impl" + File.separator + className + "ServiceImpl.java";
        }

        if (template.contains("menu.sql.vm")) {
            return className.toLowerCase() + "_menu.sql";
        }

        return null;
    }


}
