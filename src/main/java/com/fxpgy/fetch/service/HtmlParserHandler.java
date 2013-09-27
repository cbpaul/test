package com.fxpgy.fetch.service;
/**
 * @author paul
 * @version 创建时间：2013-5-8 下午4:58:00
 * 类说明
 */
public class HtmlParserHandler {
	public static  <T>T excuteParser(HtmlParser<T> parser,String html){
		return (T)parser.parser(html);
	}
}
