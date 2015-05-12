package com.jipengblog.webadmin.weixin.handle;

import com.jipengblog.webadmin.weixin.bean.ReceiveMsgParent;

/**
 * @desc 没有找到对应的handle时的处理
 * @author penn
 * @2013-06-27 15:40
 *
 */
public class ReceviceMsgDefaultHandleImpl implements ReceiveMsgHandle {

	public String handle(ReceiveMsgParent msg) {

		return "";//默认处理
	}
}
