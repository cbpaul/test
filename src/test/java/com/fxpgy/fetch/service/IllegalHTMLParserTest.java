package com.fxpgy.fetch.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.fxpgy.fetch.thread.BatchIllegalRunnable;
import com.fxpgy.fetch.util.HttpClientUtil;
import com.fxpgy.fetch.vo.CarVo;
import com.fxpgy.fetch.vo.IllegalVo;

/**
 * @author paul
 * @version 创建时间：2013-5-9 下午2:52:36 类说明
 */
public class IllegalHTMLParserTest {
//
//	@Test
//	public void testIllegalTimes() {
//		try {
//			String html = HttpClientUtil.httpRequest(
//					"http://www.cqjg.gov.cn/netcar/FindOne1.aspx",
//					"VIN=LBEMDAFC6CZ052159&LicenseTxt=ASJ970", "POST", "gb2312");
//			System.out.println(html);
//			Map<String,String> times = IllegalHTMLParser.illegalTimeUrlMaps(html);
//			System.out.println(times.get("2013.01.12 16:31"));
//			List<IllegalVo> illegals = IllegalHTMLParser.illegalInfos(times,null);
//			System.out.println(illegals.get(0).toString());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	@Test
//	public void testIllegalRunnable(){
//		CarVo car1 =  new CarVo();
//		car1.setFrameNo("LBEMDAFC6CZ052159");
//		car1.setPlateNo("ASJ970");
//		List<CarVo> cars = new ArrayList<CarVo>();
//		cars.add(car1);
//		Thread thread = new Thread(BatchIllegalRunnable.getIllegalRunnable(cars));
//		 
//		thread.start();
//		thread.run();
//		
//	}
}
