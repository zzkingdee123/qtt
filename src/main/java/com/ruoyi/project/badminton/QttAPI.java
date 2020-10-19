package com.ruoyi.project.badminton;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.http.HttpUtils;

public class QttAPI {
	
	private static final String STORE ="http://tytapp.quntitong.cn/sportinterNew/androidstadium/queryStoreByType.do";
	private static final String ARENA ="http://tytapp.quntitong.cn/sportinterNew/androidstadium/queryStadiumAll.do";
	
	/**
	 * 群体通接口,获取场地信息
	 * @param map
	 * @return
	 */
	public static JSONArray getCustomerInfo(Map<String, String> map)
	{
		String sendGet = HttpUtils.sendGet(STORE, buildMap(map));
		return JSONArray.parseArray(sendGet);
	}
	
	/**
	 * 群体通接口,获取场馆信息
	 * @param map
	 * @return
	 */
	public static JSONArray getArenaInfo(Map<String, String> map)
	{
		String sendGet = HttpUtils.sendGet(ARENA, buildMap(map));
		return JSONArray.parseArray(sendGet);
	}
	
	/**
	 * 群体通接口,获取场馆个数
	 * @param map
	 * @return
	 */
	public static Integer getArenaCount(Map<String, String> map)
	{
		String sendGet = HttpUtils.sendGet(ARENA, buildMap(map));
	    JSONArray parseArray = JSONArray.parseArray(sendGet);
	    Integer integer = parseArray.getJSONObject(0).getInteger("totalRecord");
	    return integer;
	}
	
	public static String buildMap(Map<String, String> map) {
        StringBuffer sb = new StringBuffer();
        if (map.size() > 0) {
            for (String key : map.keySet()) {
                sb.append(key + "=");
                if (StringUtils.isEmpty(map.get(key))) {
                    sb.append("&");
                } else {
                    String value = map.get(key);
                    try {
                        value = URLEncoder.encode(value, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    sb.append(value + "&");
                }
            }
        }
        return sb.toString();
    }
}
	
