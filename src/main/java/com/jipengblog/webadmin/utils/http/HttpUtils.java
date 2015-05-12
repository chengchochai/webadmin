package com.jipengblog.webadmin.utils.http;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.jipengblog.webadmin.utils.PennUtils;
import com.jipengblog.webadmin.utils.security.SignatureUtils;

public class HttpUtils extends PennUtils {

	public static String sendGet(String url) throws Exception {
		String content = "";
		logger.info("url:::"+url);
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet httpGet = new HttpGet(url);
			CloseableHttpResponse response = httpclient.execute(httpGet);
			try {
				HttpEntity entity = response.getEntity();
				InputStream instream = entity.getContent();
				content = IOUtils.toString(instream);
				EntityUtils.consume(entity);
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}
		return content;
	}

	public static String sendPost(String url, Map<String, String> params)
			throws Exception {
		String content = null;
		logger.info("url:::"+url);
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpPost httpPost = new HttpPost(url);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			for (Map.Entry<String, String> entry : params.entrySet()) {
				nvps.add(new BasicNameValuePair(entry.getKey(), entry
						.getValue()));
			}
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			CloseableHttpResponse response = httpclient.execute(httpPost);
			try {
				HttpEntity entity = response.getEntity();
				content = IOUtils.toString(entity.getContent());
				EntityUtils.consume(entity);
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}
		return content;
	}

	public static void main(String[] args) throws Exception {
//		String url = "http://ip.taobao.com/service/getIpInfo.php?ip=113.90.222.234";
//		String json = HttpUtils.sendGet(url);
//		JSONObject jsonObj = JSONObject.fromObject(json);
//		System.out.println(jsonObj.get("code"));
//		System.out.println(jsonObj.get("data"));
		
		SignatureUtils signatureUtils = new SignatureUtils();
		String url = "http://112.231.23.2:9610/api/pay/";
		String key = "duduniu123321#$";
		String spId = "duduniu";
		String amount = "100";
		String spOrderId = String.valueOf(System.currentTimeMillis());
		String userId = "124996813";
		String tel = "";
		String time = String.valueOf(System.currentTimeMillis());
		//String toValid = "amount"+amount+"spId"+spId+"spOrderId"+spOrderId+"time"+time+"userId"+userId+key;
		//String toValid = amount+spId+spOrderId+time+userId+key;
		String toValid = "spId="+spId+"&amount="+amount+"&spOrderId="+spOrderId+"&userId="+userId+"&time="+time+key;
		System.out.println("加密前:::"+toValid);
		String sign = signatureUtils.encrypt(toValid, "");
		System.out.println("加密后:::"+sign);
		url = url+"?spId="+spId+"&amount="+amount+"&spOrderId="+spOrderId+"&userId="+userId+"&time="+time+"&sign="+sign+tel;
		System.out.println(url);
		//System.out.println(HttpUtils.sendGet(url));
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("wd", "遍历");
//		System.out.println(HttpUtils.sendPost("http://www.baidu.com/s",map));
	}
}
