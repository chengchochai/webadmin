package com.jipengblog.webadmin.web.controller.webservice;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jipengblog.webadmin.web.controller.ParentController;
import com.jipengblog.webadmin.weixin.bean.ReceiveMsgParent;
import com.jipengblog.webadmin.weixin.factory.MpReceiveMsgBeanFactory;
import com.jipengblog.webadmin.weixin.factory.MpReceiveMsgHandleFactory;
import com.jipengblog.webadmin.weixin.handle.ReceiveMsgHandle;

@Controller
public class WeixinController extends ParentController {

	@RequestMapping(value = "/interface/wx", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String wx(HttpServletRequest request) {
		String result = "";
		try {
			ReceiveMsgParent pmsg = MpReceiveMsgBeanFactory.packageReqInfo(request);
			MpReceiveMsgHandleFactory factory = new MpReceiveMsgHandleFactory(pmsg.getMsgType());
			ReceiveMsgHandle handleService = factory.createHandle();
			result = handleService.handle(pmsg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}