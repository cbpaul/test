package com.fxpgy.fetch.vo;

import java.io.Serializable;

/**
 * 违章vo
 * @author paul
 * @version 2013-5-8 下午4:15:23
 * 类说明
 */
public class IllegalVo implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id ="";				//违章记录id
	private String carType="";			//车类型
	private String time="";				//违章时间
	private String place="" ;			//违章地点 
	private String fineMoney="";		//罚款金额
	private String score="" ;			//记分
	private String party="";			//当事人
	private String enforcementUnit="";	//执法单位
	private String behavior="";			//违章行为
	private String infoParamStr = "";  	//参数字符串
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getFineMoney() {
		return fineMoney;
	}
	public void setFineMoney(String fineMoney) {
		this.fineMoney = fineMoney;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getParty() {
		return party;
	}
	public void setParty(String party) {
		this.party = party;
	}
	public String getEnforcementUnit() {
		return enforcementUnit;
	}
	public void setEnforcementUnit(String enforcementUnit) {
		this.enforcementUnit = enforcementUnit;
	}
	public String getBehavior() {
		return behavior;
	}
	public void setBehavior(String behavior) {
		this.behavior = behavior;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInfoParamStr() {
		return infoParamStr;
	}
	public void setInfoParamStr(String infoParamStr) {
		this.infoParamStr = infoParamStr;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "车类型:"+this.carType+"\t违章时间:"+time+"\t违章地点:"+place+
				"\n罚款金额:"+fineMoney+"\t记分:"+score+"\t当事人:"+party+
				"\n执法单位:"+enforcementUnit+"\t违章行为:"+behavior;
	}

}
