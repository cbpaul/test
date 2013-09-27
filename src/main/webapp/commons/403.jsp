<%@ page language="java" import="java.util.*,com.fxpgy.www.common.SpringMvcTools" pageEncoding="utf-8"%>

<%

    //系统故障，请稍后再试。
	Exception exception = (Exception)request.getAttribute("exception");
	
	String userAgent = request.getHeader("User-Agent").toLowerCase();
	
	
	if(userAgent.contains("ipad")   || userAgent.contains("iphone") ||  userAgent.contains("java")  || 
		 userAgent.contains("mobile") || userAgent.contains("android") ){
			
			response.getWriter().write("系统故障，请稍后再试。");
		
	}else{
		
		
		 SpringMvcTools.writeFail(response.getWriter(), "系统故障，请稍后再试。");
		
	}	
%>