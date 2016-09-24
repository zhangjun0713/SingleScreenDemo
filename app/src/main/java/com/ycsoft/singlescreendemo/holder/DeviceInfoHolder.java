package com.ycsoft.singlescreendemo.holder;

import android.os.IBinder;
import android.os.ServiceManager;
import android.os.storage.IMountService;

import com.ycsoft.singlescreendemo.entity.MountEntity;
import com.ycsoft.singlescreendemo.entity.MountEntity.MountType;

import java.util.ArrayList;
import java.util.List;


/**
 * 设备信息(存储,mac等)管理类
 *
 * @author HomgWu
 */
public class DeviceInfoHolder {
	private List<android.os.storage.ExtraInfo> mountList;

	public DeviceInfoHolder() {
	}

	public List<MountEntity> initMountList() {
		List<MountEntity> mountEntities = new ArrayList<>();
		try {
			// support for DevType
			IBinder service = ServiceManager.getService("mount");
			if (service != null) {
				IMountService mountService = IMountService.Stub
						.asInterface(service);
				mountList = mountService.getAllExtraInfos();
				int index = mountList.size();
				for (int i = 0; i < index; i++) {
					android.os.storage.ExtraInfo extraInfo = mountList.get(i);
					String path = mountList.get(i).mMountPoint;
					String label;
					String partition;
					String type = null;
					if (extraInfo.mLabel != null) {
						label = mountList.get(i).mDiskLabel + ": "
								+ mountList.get(i).mLabel;
					} else {
						label = mountList.get(i).mDiskLabel;
					}
					partition = label;
					String typeStr = mountList.get(i).mDevType;
					if (path.contains("/mnt/nand")) {
						type = MountType.CARD.getValue();
					} else if (typeStr.equals("SDCARD")) {
						type = MountType.CARD.getValue();
					} else if (typeStr.equals("SATA")) {
						type = MountType.SATA.getValue();
					} else if (typeStr.equals("USB2.0")) {
						type = MountType.USB2.getValue();
					} else if (typeStr.equals("USB3.0")) {
						type = MountType.USB3.getValue();
					} else if (typeStr.equals("UNKOWN")) {
						type = MountType.UNKOWN.getValue();
					}
					MountEntity mountEntity = new MountEntity();
					mountEntity.label = label;
					mountEntity.partition = partition;
					mountEntity.path = path;
					mountEntity.type = type;
					mountEntities.add(mountEntity);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mountEntities;
	}
}
