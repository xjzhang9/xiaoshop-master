/*
 * Copyright (c) 2018. paascloud.net All Rights Reserved.
 * 项目名称：paascloud快速搭建企业级分布式微服务平台
 * 类名称：BaseTree.java
 * 创建人：刘兆明
 * 联系方式：paascloud.net@gmail.com
 * 开源地址: https://github.com/paascloud
 * 博客地址: http://blog.paascloud.net
 * 项目官网: http://paascloud.net
 */
package com.xjzhang.base.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * The class Base tree.
 * @author THTF
 */
@Data
public class BaseTree<E, ID> implements Serializable {


	/**
	 * ID
	 */
	private ID id;

	/**
	 * 父ID
	 */
	private ID pid;

	/**
	 * 是否含有子节点
	 */
	private boolean hasChild = false;

	/**
	 * 子节点集合
	 */
	private List<E> children;
}