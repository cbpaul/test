<%@ page language="java" import="java.util.*,com.fxpgy.www.common.SpringMvcTools" pageEncoding="utf-8"%>
<%

    //访问的页面不存在。
	Exception exception = (Exception)request.getAttribute("exception");
	
	String userAgent = request.getHeader("User-Agent").toLowerCase();
	
	
	if(userAgent.contains("ipad")   || userAgent.contains("iphone") ||  userAgent.contains("java")  || 
		 userAgent.contains("mobile") || userAgent.contains("android") ){
			
			response.getWriter().write("访问的页面不存在。");
		
	}else{
	
		 response.getWriter().write("访问的页面不存在。");
		
	}	
%>