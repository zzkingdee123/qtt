package com.ruoyi.common.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


import com.alibaba.fastjson.JSONObject;
import com.wangfengta.api.WftClient;
import com.wangfengta.api.exception.ApiException;
import com.wangfengta.api.model.Result;

public class WxSendUtils {
	
	public static final String URL = "http://jy.erpit.cn/api/message/send";
	
//	public static final String SECRETKEY = "6c0ede7461d2baba09d5a8beb73582cb";
//	public static final String APP_KEY = "4a112be43fe45990389472996e8d6e37";
	
	public static final String SECRETKEY = "1b9069aa5198f4010197071a7e51c51a";
	public static final String APP_KEY = "631e2d74e991794bed7f9bf18af66768";
	
	
	public static final String TEMPLATE_ID = "_DGvdAmOf82CZO5jPkM-u7SM53t2JFbj_n5j00w8Q5Y";

	public static void main(String[] args) throws ApiException {
		
		MsgTemplatePojo msgTemplatePojo = new MsgTemplatePojo();
		msgTemplatePojo.setFirst(new DataEntry("订场通知"));
		msgTemplatePojo.setKeyword1(new DataEntry("111"));
		msgTemplatePojo.setKeyword2(new DataEntry("222"));
		msgTemplatePojo.setKeyword3(new DataEntry("333"));
		msgTemplatePojo.setRemark(new DataEntry("remark"));
		WxSendUtils.SendMes(msgTemplatePojo);
		
		
		//告警内容中你的业务参数，是可选的。
//		Map param = Collections.singletonMap("orderCode", "");
//		//secret是密钥,建议WftClient以单例模式使用。
//		WftClient client = WftClient.init(SECRETKEY);
//		//调用告警接口
//		Result alarmResult =client.alarm(APP_KEY,TEMPLATE_ID,param);
//		//当code为200时表示成功
//		System.out.println(alarmResult.getCode());
//		//Assert.assertNotEquals(alarmResult.getCode(),"200");
	}
	
	public static String SendMes(MsgTemplatePojo msgTemplatePojo ){
		//System.out.println(shopName+","+ordersn+","+orderDetail+","+amount+","+remarkString+","+appKey);
		String url = URL;
		Map<String, Object> map = new HashMap<>();
		map.put("secret", SECRETKEY);
		map.put("app_key", APP_KEY);
		map.put("template_id", TEMPLATE_ID);
		
		
		
//		DataEntry first = new DataEntry(shopName);
//		DataEntry keyword1 = new DataEntry(ordersn);
//		DataEntry keyword2 = new DataEntry(orderDetail);
//		DataEntry keyword3 = new DataEntry(amount);
//		DataEntry remark = new DataEntry(remarkString);
//		data = msgTemplatePojo;
//		data.setFirst(first);
//		data.setKeyword1(keyword1);
//		data.setKeyword2(keyword2);
//		data.setKeyword3(keyword3);
//		data.setRemark(remark);
		map.put("data", msgTemplatePojo);
		
		
		JSONObject result = HttpsUtil.sendByHttp(map, url);
		
		System.out.println(result);
		if (result!=null) {
			return result.getString("code");
		}
		else {
			return "";
		}
	}
}
