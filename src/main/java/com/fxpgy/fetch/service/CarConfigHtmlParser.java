package com.fxpgy.fetch.service;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


/**
 * @author paul
 * @version 创建时间：2013-5-21 上午11:10:20
 * 类说明
 */
public class CarConfigHtmlParser implements HtmlParser<Map<String,String>> {

	@Override
	public Map<String, String> parser(String html) {
		// TODO Auto-generated method stub
		return null;
	}
//
//	@Override
//	public Map<String,String> parser(String html) {
//		Map<String,String> map = new HashMap<String, String>();
//		Document doc = Jsoup.parse(html);
//		Elements keys = doc.select("td.bg3");
//		Elements values = doc.select("td.bg4");
//		for(int i=0 ; i<keys.size() ; i++){
//			String key = keys.get(i).text().trim();
//			map.put(key.substring(0,key.lastIndexOf("：")).trim(), values.get(i).text().trim());
//		}
//		return map;
//	}

}
