package com.fxpgy.fetch.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.helper.StringUtil;

import com.fxpgy.fetch.service.IllegalHTMLParser;
import com.fxpgy.fetch.util.HttpClientUtil;
import com.fxpgy.fetch.vo.CarVo;
import com.fxpgy.fetch.vo.IllegalVo;

/**
 * @author paul
 * @version 创建时间：2013-5-10 上午10:38:18 类说明
 */
public class BatchIllegalRunnable {
	private static final String CQWZQ_LIST_URL = "http://www.cqjg.gov.cn/netcar/FindOne1.aspx";
	public static Runnable getIllegalRunnable(final List<CarVo> carList) {
		return new Runnable() {
			@Override
			public void run() {
				List<IllegalVo> illegals = new ArrayList<IllegalVo>();
				for (CarVo car : carList) {
					if (car != null && !StringUtil.isBlank(car.getPlateNo())
							&& !StringUtil.isBlank(car.getFrameNo())) {
						StringBuffer sb = new StringBuffer();
						sb.append("VIN="+car.getFrameNo()).append("&")
						.append("LicenseTxt="+car.getPlateNo());
						try {
							String html = HttpClientUtil.httpRequest(CQWZQ_LIST_URL, sb.toString(), "POST", "gb2312");
							if(!StringUtil.isBlank(html)){
								Map<String,String> timeUrlMap = IllegalHTMLParser.illegalTimeUrlMaps(html);
								if(timeUrlMap!= null && timeUrlMap.size()>0){
									//数据库过滤
									
									IllegalHTMLParser.illegalInfos(timeUrlMap, illegals);
									if(illegals!= null && illegals.size()>0){
										System.out.println(illegals.get(0).toString());
									}
									//数据库保存代码
								}
							}
							
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}

			}

		};
	}

}
