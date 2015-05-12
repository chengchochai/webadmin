package com.jipengblog.webadmin.weixin.factory;

import com.jipengblog.webadmin.weixin.handle.ReceiveMsgHandle;
import com.jipengblog.webadmin.weixin.handle.ReceiveMsgSiteAccessHandleImpl;
import com.jipengblog.webadmin.weixin.handle.ReceiveMsgTextHandleImpl;
import com.jipengblog.webadmin.weixin.handle.ReceviceMsgDefaultHandleImpl;

/**
 * @desc 公众号工厂类，根据需要生成实际处理的handle
 * @author penn
 * @date 2013-06-27 14:08
 */
public class MpReceiveMsgHandleFactory {

	private String type;

	public MpReceiveMsgHandleFactory(String type) {
		this.type = type;
	}

	/**
	 * 
	 * @param type
	 *            目前仅为text，site，分别为对应的处理模式
	 * @return IHandleService
	 */
	public ReceiveMsgHandle createHandle() {
		if (this.type.trim().equalsIgnoreCase("site")) {
			return new ReceiveMsgSiteAccessHandleImpl();
		} else if (this.type.trim().equalsIgnoreCase("text")) {
			return new ReceiveMsgTextHandleImpl();
		} else {
			return new ReceviceMsgDefaultHandleImpl();
		}
	}

	public String getType() {
		return type;
	}

}
