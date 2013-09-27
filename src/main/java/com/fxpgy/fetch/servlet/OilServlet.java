package com.fxpgy.fetch.servlet;

import com.fxpgy.fetch.service.HtmlParserHandler;
import com.fxpgy.fetch.service.OilPriceHtmlParser;
import com.fxpgy.fetch.util.HttpClientUtil;
import com.fxpgy.fetch.util.JsonUtil;
import com.fxpgy.fetch.vo.OilVo;

/**
 * @author paul
 * @version 创建时间：2013-5-8 下午5:34:33
 * 类说明
 */
public class OilServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private static final String OIL_PRICE_URL = "http://www.bitauto.com/youjia/";

	@Override
	public BaseServlet getCurrenServlet() {
		// TODO Auto-generated method stub
		return this;
	}
	/**
	 * 城市油价查询
	 */
	public void oilPriceQuery(){
		String city = request.getParameter("city");
		try {
			String html = HttpClientUtil.httpRequest(OIL_PRICE_URL+city, "", "GET", "utf-8");
			OilVo oilVo = HtmlParserHandler.excuteParser(new OilPriceHtmlParser(), html);
			oilVo.setCity(city);
			response.getWriter().write(JsonUtil.object2json(oilVo));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
