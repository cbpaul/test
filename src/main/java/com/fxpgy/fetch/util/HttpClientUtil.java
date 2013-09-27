package com.fxpgy.fetch.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class HttpClientUtil {
	
	public static String httpRequest(String urlStr, String data,
			String requestMethod,String charSet) throws Exception {
		URL url = new URL(urlStr);
		// 建立连接
		URLConnection uconn = url.openConnection();
		// 强转 ： URLConnection是HttpURLConnection的父类。
		HttpURLConnection conn = (HttpURLConnection) uconn;
		conn.setRequestMethod(requestMethod);
		// conn.setRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows 2000)");
		conn.setDoOutput(true);
		// //2.获取服务器的响应
		if (null != data && !"".equals(data)) {
			OutputStreamWriter out = new OutputStreamWriter(
					conn.getOutputStream(),charSet);
			out.write(data);
			out.flush();
			out.close();
		}
		StringBuffer sb = new StringBuffer();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				conn.getInputStream(), charSet));
		String line = null;
		while ((line = reader.readLine()) != null) {
			sb.append(line).append("\r\n");
		}
		reader.close();
		return sb.toString();
	}
}