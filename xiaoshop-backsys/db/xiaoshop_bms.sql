create table tb_sys_user (
	user_id BIGINT NOT NULL AUTO_INCREMENT,
	user_name VARCHAR(50) NOT NULL COMMENT '用户名',
    dept_id BIGINT COMMENT '部门编号',
	password VARCHAR(100) COMMENT '密码',
	mobile VARCHAR(11) COMMENT '手机号',
	salt VARCHAR(20) COMMENT '盐',
	email VARCHAR(100) COMMENT '电子邮箱',
	status TINYINT COMMENT '状态 0:禁用 1:正常',
	create_time VARCHAR(11) COMMENT '创建时间',
	PRIMARY KEY (user_id),
	UNIQUE INDEX (user_name)
) ENGINE = 'INNODB' DEFAULT CHARACTER SET utf8mb4 COMMENT='系统用户';

create table tb_sys_role (
    role_id BIGINT NOT NULL AUTO_INCREMENT,
    role_name VARCHAR(50) NOT NULL COMMENT '角色名称',
    dept_id BIGINT COMMENT '部门编号',
    remark VARCHAR(100) COMMENT '备注',
    create_time VARCHAR(11) COMMENT '创建时间',
   	PRIMARY KEY (role_id)
) ENGINE = 'INNODB' DEFAULT CHARACTER SET utf8mb4 COMMENT='角色';

create table tb_user_role (
   id BIGINT NOT NULL AUTO_INCREMENT,
   user_id BIGINT NOT NULL COMMENT '用户编号',
   role_id BIGINT NOT NULL COMMENT '角色编号',
    PRIMARY KEY(id)
) ENGINE = 'INNODB' DEFAULT CHARACTER SET utf8mb4 COMMENT = '用户与角色对应关系';

create table tb_sys_menu (
   menu_id BIGINT NOT NULL AUTO_INCREMENT,
   parent_id BIGINT COMMENT '父菜单编号',
   name VARCHAR(50) COMMENT '菜单名称',
   url VARCHAR(200) COMMENT '菜单url',
   perms VARCHAR(5OO) COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
   type int COMMENT '类型 0：目录 1：菜单 2：按钮',
   icon VARCHAR(50) COMMENT '菜单图标',
   order_num int COMMENT '排序',
   PRIMARY KEY(menu_id)
) ENGINE='InnoDB' DEFAULT CHARACTER SET utf8mb4 COMMENT='菜单管理';

CREATE TABLE tb_role_menu (
   id bigint NOT NULL AUTO_INCREMENT,
   role_id bigint COMMENT '角色ID',
   menu_id bigint COMMENT '菜单ID',
  PRIMARY KEY (id)
) ENGINE= 'InnoDB'  DEFAULT CHARACTER SET utf8mb4 COMMENT='角色与菜单对应关系';

create table tb_sys_dept (
   dept_id BIGINT NOT NULL AUTO_INCREMENT,
   parent_id BIGINT COMMENT '父部门编号',
   dep_name VARCHAR(50) COMMENT '部门名称',
   order_num int COMMENT '排序',
   del_flag TINYINT DEFAULT 0  COMMENT '是否删除 0：正常 -1：已删除',
   PRIMARY KEY (dept_id)
) ENGINE='INNODB' DEFAULT CHARACTER SET utf8mb4 COMMENT='部门信息';


