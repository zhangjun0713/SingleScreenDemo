package com.ycsoft.singlescreendemo.entity;

import java.io.Serializable;

/**
 * 挂载设备类
 * 
 * @author HomgWu
 *
 */
public class MountEntity implements Serializable {
	public String path;
	public String type;
	public String label;
	public String partition;

	/**
	 * 
	 * @author HomgWu
	 *
	 */
	public enum MountType {
		CARD("CARD"), SATA("SATA"), USB2("USB2.0"), USB3("USB3.0"), UNKOWN(
				"UNKOWN");

		private String type;

		MountType(String type) {
			this.type = type;
		}

		@Override
		public String toString() {
			return String.valueOf(this.type);
		}

		public String getValue() {
			return type;
		}
	}
}
