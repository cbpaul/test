package com.fxpgy.fetch.service;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.fxpgy.fetch.util.DataBaseConnection;
import com.fxpgy.fetch.util.HttpClientUtil;
import com.fxpgy.fetch.util.XmlLoad;

/**
 * @author paul
 * @version 创建时间：2013-5-21 上午11:51:22
 * 类说明
 */
public class CarConfigService {
	public StringBuffer carConfigSqlBuffer() throws Exception{
		List<Map<String,Integer>> typeMaps =  XmlLoad.typeMaps;
		StringBuffer sb = new StringBuffer("insert into bsd_auto_setting_value (carId,paramValue,typeId) values \n");
		DataBaseConnection conn = new DataBaseConnection(DataBaseConnection.MYSQL_DRIVER,"jdbc:mysql://10.89.100.250:3306/BSD", "root", "123456");
		List<Map<String,Object>> carList = conn.queryForList("select car.id,car.xcarid from bsd_auto_car car ", null);
		List<Long> existCarIds = existCarValue(conn);
		for(Map<String,Object> car : carList){
			Long carId = (Long)car.get("id");
			if(!existCarIds.contains(carId)){
				System.out.println("汽车Id"+carId);
				String xcar_web = "http://newcar.xcar.com.cn/m";
				String xcarId = String.valueOf(car.get("xcarId"));
				xcar_web = xcar_web+xcarId+"/config.htm";
				String html = HttpClientUtil.httpRequest(xcar_web, null, "GET", "gb2312");
				Map<String,String> map = HtmlParserHandler.excuteParser(new CarConfigHtmlParser(), html);
				for(Map<String,Integer> typeMap:typeMaps){
					for(Entry<String,Integer> entry :typeMap.entrySet()){
						String paramValue = map.get(entry.getKey());
						if( paramValue == null){
							paramValue = "";
							System.out.println(entry.getKey());
						}
						sb.append(" ("+carId+",'"+ paramValue +"',"+entry.getValue()+"),\n");
					}
				}
			}
		}
		return sb;
	}
	public List<Long> existCarValue(DataBaseConnection conn) throws SQLException{
		ResultSet resultSet = conn.query("select v.carId from bsd_auto_setting_value v group by v.carId", null);
		List<Long> ids = new ArrayList<Long>();
		while(resultSet.next()){
			ids.add(resultSet.getLong(1));
		}
		return ids;
		
	}
	public static void main(String[] args) throws Exception{
		CarConfigService carService = new CarConfigService();
		StringBuffer sb = carService.carConfigSqlBuffer();
		File file = new File("F://carConfigSql.sql");
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(sb.toString().getBytes());
		System.out.println("生成汽车配置sql成功");
//		List<Long> ids = new ArrayList<Long>();
//		ids.add(new Long(100));
//		ids.add(new Long(200));
//		System.out.println(ids.contains(new Long(100)));
	}
}
