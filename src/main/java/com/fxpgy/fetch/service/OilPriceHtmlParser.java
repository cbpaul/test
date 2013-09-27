package com.fxpgy.fetch.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.fxpgy.fetch.vo.OilVo;

/**
 * @author paul
 * @version 创建时间：2013-5-8 下午4:44:29 类说明
 */
public class OilPriceHtmlParser implements HtmlParser<OilVo> {

	@Override
	public OilVo parser(String html) {
		Document doc = Jsoup.parse(html);
		OilVo oilVo = new OilVo();
		Elements prices = doc.select("span.todayPrice > strong");
		oilVo.setPrice90(prices.get(0).text());
		oilVo.setPrice93(prices.get(1).text());
		oilVo.setPrice97(prices.get(2).text());
		oilVo.setPrice0(prices.get(3).text());
		return oilVo;
	}
}
