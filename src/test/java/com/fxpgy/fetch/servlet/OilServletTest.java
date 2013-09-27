package com.fxpgy.fetch.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.easymock.EasyMock;
import org.junit.Test;

/**
 * @author paul
 * @version 创建时间：2013-5-8 下午6:35:58
 * 类说明
 */
public class OilServletTest {
	private HttpServletRequest request;
	private HttpServletResponse response;

//	@Test
//	public void testOilPriceQuery() {
//		request = EasyMock.createMock(HttpServletRequest.class);
//		response = EasyMock.createMock(HttpServletResponse.class);
//		EasyMock.expect(request.getParameter("method")).andReturn("oilPriceQuery").times(1);
//		EasyMock.expect(request.getParameter("city")).andReturn("chengdu").times(1);
//		EasyMock.replay(request,response);
//		OilServlet os = new OilServlet();
//		try {
//			os.doGet(request, response);
//		} catch (ServletException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

}
