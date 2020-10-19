package com.ruoyi.project.badminton;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.constant.ScheduleConstants;
import com.ruoyi.common.utils.DataEntry;
import com.ruoyi.common.utils.MsgTemplatePojo;
import com.ruoyi.common.utils.WxSendUtils;
import com.ruoyi.project.badminton.arena.domain.Arena;
import com.ruoyi.project.badminton.arena.service.IArenaService;
import com.ruoyi.project.badminton.reserve.domain.ArenaReserve;
import com.ruoyi.project.badminton.reserve.service.IArenaReserveService;
import com.ruoyi.project.monitor.job.domain.Job;
import com.ruoyi.project.monitor.job.service.IJobService;

@Component("QttSpider")
public class QttSpider {

	//0020C062910

	@Autowired private IJobService jobService;
	
	 @Autowired
	    private IArenaReserveService arenaReserveService;
	 
	 @Autowired
	 private IArenaService arenaService;

	public void executeJob(Long arenareserveid,Long jobid){
		ArenaReserve selectArenaReserveById = arenaReserveService.selectArenaReserveById(arenareserveid);
		//1.把dateperoid分成几个日期,构造list<map>
		String arenaNo = selectArenaReserveById.getArenaNo();
		String period = selectArenaReserveById.getPeriod();
		List<Map<String, String>> listmap = getMapList(arenaNo,period);
		//
		for (int i = 0; i < listmap.size(); i++) {
			Map<String, String> map2 = listmap.get(i);
			Object openDate = map2.get("openDate");
			JSONArray customerInfo = QttAPI.getCustomerInfo(map2);
			for (int k = 0; k < customerInfo.size(); k++) {
				JSONObject jsonObject = customerInfo.getJSONObject(k);
				String fieldName = jsonObject.getJSONObject("stadiumField").getString("fieldName");//场地编号
				JSONArray jsonArray = jsonObject.getJSONArray("storeList");//时间段
				for (int j = 0; j < jsonArray.size(); j++) {
					// System.out.println(jsonArray.getJSONObject(0).get("startTime"));
					JSONObject jsonObject2 = jsonArray.getJSONObject(j);
					String startTime = jsonObject2.getString("startTime");
					int orderNum = jsonObject2.getInteger("orderNum");
					BigDecimal price = jsonArray.getJSONObject(j).getBigDecimal("price0");
					String starttime = selectArenaReserveById.getStarttime();
					if(startTime.equals(starttime) && orderNum==1){
						//System.out.println(openDate+" "+ startTime+fieldName+"有场，价格为："+price);
						try {	
							changeJobStatus(jobid);
							sendWxMsg(openDate,startTime,arenaNo,fieldName,price);
							return;
						} catch (SchedulerException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

	public List<Map<String, String>> getMapList(String cgno, String dateperoid){
		String[] split = dateperoid.split("~");
		split[0]=getBeginDate(split[0]);
		
		List<String> days = getDays(split[0],split[1]);
		
		List<Map<String, String>> listmap = new ArrayList<Map<String, String>>();
		
		for (String day : days) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("cgCode", cgno);
			map.put("sportCode", "002");
			map.put("openDate", day);
			map.put("booking", "Y");
			listmap.add(map);
		}
		return listmap;
	}
	
	
	private String getBeginDate(String begindate) {
		 Date date=new Date();//取时间
		 Calendar calendar = new GregorianCalendar();
		 calendar.setTime(date);
		 calendar.add(calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动
		 date=calendar.getTime(); //这个时间就是日期往后推一天的结果 
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		 String format = formatter.format(date);
		 begindate = begindate.compareTo(format)>0?begindate:format;
		return begindate;
	}


	/**
	 * 获取两个日期之间的所有日期(获取今天到截止日期)
	 * @param startTime 开始日期
	 * @param endTime 结束日期
	 * @return
	 */
	public static List<String> getDays(String startTime, String endTime) {

	    // 返回的日期集合
	    List<String> days = new ArrayList<String>();

	    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    try {
	        Date start = dateFormat.parse(startTime);
	        Date end = dateFormat.parse(endTime);

	        Calendar tempStart = Calendar.getInstance();
	        tempStart.setTime(start);

	        Calendar tempEnd = Calendar.getInstance();
	        tempEnd.setTime(end);
	        tempEnd.add(Calendar.DATE, +1);// 日期加1(包含结束)
	        while (tempStart.before(tempEnd)) {
	            days.add(dateFormat.format(tempStart.getTime()));
	            tempStart.add(Calendar.DAY_OF_YEAR, 1);
	        }

	    } catch (ParseException e) {
	        e.printStackTrace();
	    }

	    return days;
	}
	
	
	/**
	 * 关闭job
	 * @param jobid 
	 * @throws SchedulerException
	 */
	public void changeJobStatus(Long jobid) throws SchedulerException{
		Job job = jobService.selectJobById(jobid);
		job.setStatus(ScheduleConstants.Status.PAUSE.getValue());
		jobService.changeStatus(job);
	}
	
	/**
	 * 微信提醒
	 * @param openDate
	 * @param startTime
	 * @param endTime
	 * @param cgno
	 * @param fieldName
	 * @param price 
	 */
	public void sendWxMsg(Object openDate, String startTime, String cgno, String fieldName, BigDecimal price){
		Arena arena = new Arena();
		arena.setNumber(cgno);
		List<Arena> selectArenaList = arenaService.selectArenaList(arena);
		
		MsgTemplatePojo msgTemplatePojo = new MsgTemplatePojo();
		msgTemplatePojo.setFirst(new DataEntry("订场通知"));
		msgTemplatePojo.setKeyword1(new DataEntry(openDate.toString()));
		msgTemplatePojo.setKeyword2(new DataEntry(startTime));
		msgTemplatePojo.setKeyword3(new DataEntry(price.toString()));
		msgTemplatePojo.setRemark(new DataEntry(selectArenaList.get(0).getName()+fieldName));
		WxSendUtils.SendMes(msgTemplatePojo);
	}
}
