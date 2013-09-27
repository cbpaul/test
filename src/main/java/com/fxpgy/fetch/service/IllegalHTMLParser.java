package com.fxpgy.fetch.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.fxpgy.fetch.util.HttpClientUtil;
import com.fxpgy.fetch.vo.IllegalVo;

public abstract class IllegalHTMLParser {
	private static final String CQWZ_WEB_URL="http://www.cqjg.gov.cn/netcar/";
	/**
	 * 车辆违章列表
	 * 
	 * @param html
	 * @return
	 */
	public static List<IllegalVo> illegalList(String html, String appUrl) {
		List<IllegalVo> obs = new ArrayList<IllegalVo>();
		Document doc = Jsoup.parse(html);
		Elements elements = doc.select("table tr");
		for (int i = 1; i < elements.size(); i++) {
			Element element = elements.get(i);
			IllegalVo vio = new IllegalVo();
			Elements es = element.children();
			if (es.size() >= 7) {
				vio.setCarType(es.get(0).text());
				vio.setTime(es.get(1).text());
				vio.setPlace(es.get(2).text());
				vio.setFineMoney(es.get(3).text());
				vio.setScore(es.get(4).text());
				vio.setParty(es.get(5).text());
				String href = es.get(6).children().get(0).attr("href");
				vio.setInfoParamStr(appUrl
						+ href.substring(href.indexOf("?") + 1, href.length()));
				obs.add(vio);
			}
		}
		return obs;
	}
	
	/**
	 * 车辆违章详情
	 * 
	 * @param html
	 * @return
	 */
	public static IllegalVo illegalInfo(String html) {
		IllegalVo vio = new IllegalVo();
		Document doc = Jsoup.parse(html);
		Elements elements = doc.select("tr td");
		if (elements.size() > 13) {
			vio.setCarType(elements.get(1).text());
			vio.setTime(elements.get(3).text());
			vio.setPlace(elements.get(5).text());
			vio.setEnforcementUnit(elements.get(7).text());
			vio.setScore(elements.get(9).text());
			vio.setFineMoney(elements.get(11).text());
			vio.setBehavior(elements.get(13).text());
		}
		return vio;
	}
	/**
	 * 得到违章时间与请求url对应列表
	 * @param html
	 * @return
	 */
	public static Map<String,String> illegalTimeUrlMaps(String html){
		Document doc = Jsoup.parse(html);
		Elements elements = doc.select("table tr");
		Map<String,String> resultMap = new HashMap<String,String>();
		if(elements.size()>0){
			for (int i = 1; i < elements.size(); i++) {
				String timeStr = elements.get(i).children().get(1).text();
				String href = elements.get(i).children().get(6).children().get(0).attr("href");
				String regex="([\u4e00-\u9fa5]+)";
				Matcher matcher = Pattern.compile(regex).matcher(href);
				StringBuffer sb = new StringBuffer();
				while(matcher.find()){
					try {
						System.out.println(URLEncoder.encode(matcher.group(), "gb2312"));
						matcher.appendReplacement(sb, URLEncoder.encode(matcher.group(), "gb2312"));
						System.out.println(sb.toString());
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}
				resultMap.put(timeStr, CQWZ_WEB_URL+matcher.appendTail(sb));
			}
			return resultMap;
		}
		return null;
	}
	/**
	 * 返回违章详情列表
	 * @param timeUrlMap 时间与详情url对应（illegalTimeUrlMaps方法可以返回）
	 * @param illegalVos 存取违章vo列表
	 * @return
	 * @throws Exception
	 */
	public  synchronized static List<IllegalVo>  illegalInfos(Map<String,String> timeUrlMap,List<IllegalVo> illegalVos) throws Exception{
		if(illegalVos == null){
			illegalVos = new ArrayList<IllegalVo>();
		}
		for(Entry<String, String> entry : timeUrlMap.entrySet()){
			String html = HttpClientUtil.httpRequest(entry.getValue(), "", "GET", "gb2312");
			if(null != html){
				IllegalVo illegalVo = illegalInfo(html);
				illegalVos.add(illegalVo);
			}
		}
		return illegalVos;
	}
}
