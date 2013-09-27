package com.fxpgy.fetch.servlet;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.fxpgy.fetch.service.IllegalHTMLParser;
import com.fxpgy.fetch.util.HttpClientUtil;
import com.fxpgy.fetch.util.JsonUtil;
import com.fxpgy.fetch.util.RequestUtil;
import com.fxpgy.fetch.vo.IllegalVo;

public class IllegalQuery extends BaseServlet {
	private static final String CQWZQ_LIST_URL="http://www.cqjg.gov.cn/netcar/FindOne1.aspx";
	private static final String CQWZQ_INFO_URL="http://www.cqjg.gov.cn/netcar/wfxw.aspx";
	private static final long serialVersionUID = 1L;

	
	/**
	 * 重庆车辆违章列表
	 * @param req
	 * @param resp
	 */
	public void cqwzqList(){
		String httpParam = paramConvert(request);
		try {
			String html = HttpClientUtil.httpRequest(CQWZQ_LIST_URL, httpParam, "POST","gb2312");
			List<IllegalVo> vios = IllegalHTMLParser.illegalList(html,RequestUtil.getAppURL(request)+"/illegal?method=cqwzqInfo&");
			String viosJson = JsonUtil.object2json(vios);
//			viosJson = new String(viosJson.getBytes(),"UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(viosJson);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 重庆车辆违章详情
	 * @param req
	 * @param resp
	 */
	public void cqwzqInfo(){
		String paramStr = paramConvert(request);
		try {
			String html = HttpClientUtil.httpRequest(CQWZQ_INFO_URL+"?"+paramStr, "", "GET","gb2312");
			IllegalVo vio = IllegalHTMLParser.illegalInfo(html);
			String vioJson = JsonUtil.object2json(vio);
//			vioJson = new String(vioJson.getBytes(),"UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(vioJson);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 参数转换
	 * e.g.:License=***&type=***
	 * 
	 */
	private String paramConvert(HttpServletRequest req){
		StringBuffer sb = new StringBuffer();
		@SuppressWarnings("unchecked")
		Enumeration<String> en = ((Enumeration<String>)req.getParameterNames());
		while(en.hasMoreElements()){
			String paramName = (String)en.nextElement();
			if(null != paramName){
				if(!paramName.equals("method")){
					try {
						sb.append(paramName+"="+URLEncoder.encode(new String(req.getParameter(paramName).getBytes("iso-8859-1"),"GB2312"),"GBK")+"&");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return sb.substring(0,sb.length()-1);
	}

	@Override
	public BaseServlet getCurrenServlet() {
		// TODO Auto-generated method stub
		return this;
	}
}
