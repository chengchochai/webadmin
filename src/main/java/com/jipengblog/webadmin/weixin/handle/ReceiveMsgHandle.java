package com.jipengblog.webadmin.weixin.handle;

import com.jipengblog.webadmin.weixin.bean.ReceiveMsgParent;


/**
 * @desc 处理逻辑的接口
 * @author penn
 * @date 2013-06-27 11:11
 * 
 */
public interface ReceiveMsgHandle {

	 
	/**
	 * 具体的逻辑处理方法
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String handle(ReceiveMsgParent msg);
}