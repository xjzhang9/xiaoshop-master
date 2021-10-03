package com.xjzhang.generator.utils;

import com.xjzhang.common.exception.BusinessException;
import com.xjzhang.generator.model.ColumnInfo;
import com.xjzhang.generator.model.TableInfo;
import com.xjzhang.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class GeneratorUtil {
    /**
     * 获得
     *
     * @return
     */
    public static List<String> getCodeTemplateList() {
        List<String> templatList = new ArrayList<>();
        templatList.add("template/Controller.java.vm");
        templatList.add("template/Dao.java.vm");
        templatList.add("template/Dao.xml.vm");
        templatList.add("template/Entity.java.vm");
//        templatList.add("template/list.html.vm");
//        templatList.add("template/list.js.vm");
//        templatList.add("template/menu.sql.vm");
        templatList.add("template/Service.java.vm");
        templatList.add("template/ServiceImpl.java.vm");
        templatList.add("template/Vo.java.vm");
        templatList.add("template/Dto.java.vm");
        templatList.add("template/BeanCopy.java.vm");

        return templatList;
    }

    public static Configuration getConfig() {
        try {
            return new PropertiesConfiguration("generator.properties");
        } catch (ConfigurationException e) {
            throw new BusinessException("获得生成代码配置文件失败：", e);
        }
    }

    public static void generatorCode(TableInfo tableInfo, List<ColumnInfo> columnInfoList, ZipOutputStream zip) {
        Configuration config = getConfig();

        // 表名转换为java类名
        String className = tableToJava(tableInfo.getTableName(), config.getString("tablePrefix"));
        tableInfo.setClassName(className);
        tableInfo.setClassObjectName(StringUtils.uncapitalize(className));

        final boolean[] hasBigDecimal = {false};

        columnInfoList.stream().map(columnInfo -> {
            // 列名转换为java属性名
            String propertyName = DbNameToJava(columnInfo.getColumnName());
            columnInfo.setPropertyName(propertyName);
            columnInfo.setPropertyObjectName(StringUtils.uncapitalize(propertyName));

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

        String mainPath = config.getString("mainPath");
        mainPath = StringUtils.isBlank(mainPath) ? "com.xjzhang" : mainPath;

        // 封装模板数据
        Map<String, Object> map = new HashMap<>();
        map.put("tableName", tableInfo.getTableName());
        map.put("comments", tableInfo.getTableComment());
        map.put("pk", tableInfo.getPk());
        map.put("className", tableInfo.getClassName());
        map.put("classObjectName", tableInfo.getClassObjectName());
        map.put("pathName", tableInfo.getClassName().toLowerCase());
        map.put("columns", tableInfo.getColumnInfoList());
        map.put("hasBigDecimal", hasBigDecimal);
        map.put("mainPath", mainPath);
        map.put("package", config.getString("package"));
        map.put("moduleName", config.getString("moduleName"));
        map.put("author", config.getString("author"));
        map.put("email", config.getString("email"));
        map.put("datetime", DateUtil.format(new Date(), DateUtil.DATE_TIME_FORMAT));

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
               log.error("渲染模板失败，表名：" + tableInfo.getTableName(), e);
            }
        });
    }


    /**
     * 表名转换成Java类名
     */
    public static String tableToJava(String tableName, String tablePrefix) {
        if(org.apache.commons.lang.StringUtils.isNotBlank(tablePrefix)){
            tableName = tableName.replace(tablePrefix, "");
        }
        return DbNameToJava(tableName);
    }


    /**
     * 数据库里面的表名或者列明转换为Java 的驼峰命名法
     *
     * @param name
     * @return
     */
    public static String DbNameToJava(String name) {
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
    public static String getTemplatePath(String template, String className, String packageName, String moduleName) {
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
            return packagePath + "model" + File.separator + className + ".java";
        }

        if (template.contains("Dto.java.vm")) {
            return packagePath + "model" + File.separator + className + "Dto.java";
        }

        if (template.contains("Vo.java.vm")) {
            return packagePath + "model" + File.separator + className + "Vo.java";
        }

        if (template.contains("Service.java.vm")) {
            return packagePath + "service" + File.separator + className + "Service.java";
        }

        if (template.contains("ServiceImpl.java.vm")) {
            return packagePath + "service" + File.separator + "impl" + File.separator + className + "ServiceImpl.java";
        }

        if (template.contains("BeanCopy.java.vm")) {
            return packagePath + "convert" + File.separator  + className + "Convert.java";
        }

        return null;
    }
}
