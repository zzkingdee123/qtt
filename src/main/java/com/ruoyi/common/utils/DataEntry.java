/**
  * Copyright 2020 bejson.com 
  */
package com.ruoyi.common.utils;
import java.util.Date;

/**
 * Auto-generated: 2020-08-28 19:13:36
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class DataEntry {

	public DataEntry(String value ,String color){
		this.value = value;
		this.color = color;
	}
	
	public DataEntry(String value){
		this.value = value;
		this.color = "#173177";
	}
	
    private String value;
    private String color;
    public void setValue(String value) {
         this.value = value;
     }
     public String getValue() {
         return value;
     }

    public void setColor(String color) {
         this.color = color;
     }
     public String getColor() {
         return color;
     }

}