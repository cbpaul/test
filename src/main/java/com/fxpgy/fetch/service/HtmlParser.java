package com.fxpgy.fetch.service;
/**
 * @author paul
 * @version 创建时间：2013-5-8 下午4:46:12
 * 类说明
 */
public interface  HtmlParser<T> {
	/**
	 * 页面解析返回bean
	 * @return
	 */
	T parser(String html);
}
