package com.fxpgy.fetch.vo;

import java.io.Serializable;

/**
 * @author paul
 * @version 创建时间：2013-5-10 上午10:41:01
 * 类说明
 */
public class CarVo implements Serializable{
	private static final long serialVersionUID = 1L;
	private String carId;
	private String plateNo;
	private String frameNo;
	public String getCarId() {
		return carId;
	}
	public void setCarId(String carId) {
		this.carId = carId;
	}
	public String getPlateNo() {
		return plateNo;
	}
	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}
	public String getFrameNo() {
		return frameNo;
	}
	public void setFrameNo(String frameNo) {
		this.frameNo = frameNo;
	}
	
}
