package com.ycsoft.singlescreendemo.entity;

import java.io.Serializable;

/**
 * Created by Jeremy on 2016/9/24.
 * MV实体
 */
public class MvEntity implements Serializable {

	/**
	 * MV名字
	 */
	public String mvName;
	/**
	 * MV本地绝对路径
	 */
	public String path;

	public MvEntity() {
	}

	public MvEntity(String mvName, String path) {
		this.mvName = mvName;
		this.path = path;
	}

	@Override
	public String toString() {
		return "MvEntity{" +
				"mvName='" + mvName + '\'' +
				", path='" + path + '\'' +
				'}';
	}
}
