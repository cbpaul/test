package com.fxpgy.fetch.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.fxpgy.fetch.util.DataBaseConnection;

/**
 * @author paul
 * @version 创建时间：2013-5-28 下午5:24:30
 * 类说明
 */
public class SeriesPhotoService {
	private static Integer id=7000;
	public StringBuffer seriesImgSql() throws Exception{
		StringBuffer sb = new StringBuffer();
		sb.append("insert into bsd_img (type,fkid,imgPath) values \n ");
		DataBaseConnection conn = new DataBaseConnection(DataBaseConnection.MYSQL_DRIVER,"jdbc:mysql://10.89.100.250:3306/BSD", "root", "123456");
//		List<Map<String,Object>> seriesList = conn.queryForList("select s.id,s.xcarImgId from bsd_auto_series s where s.parentId is  null and s.isbsd=1 ", null);
		List<Map<String,Object>> seriesList = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", 428L);
		map.put("xcarImgId", 2701L);
		seriesList.add(map);
		Map<String,Object> map2 = new HashMap<String,Object>();
		map2.put("id", 436L);
		map2.put("xcarImgId", 2407L);
		seriesList.add(map2);
		for(Map<String,Object> series : seriesList){
			Long id = (Long)series.get("id");
			Long xcarImgId = (Long)series.get("xcarImgId");
			File file = findByXcarImgId(xcarImgId);
			if(file != null && file.exists()){
				File saveFile = new File("F://seriesImg/"+id);
				if(!saveFile.exists()){
					saveFile.mkdirs();
				}
				saveFileAndSql(file,sb,saveFile.getAbsolutePath(),id);
			}
		}
		return sb;
	}
	
	 public static void saveFileAndSql(File dir,StringBuffer sb,String savePathFile,Long fkId) throws Exception{
		  File[] fs = dir.listFiles();
		  for(int i=0; i<fs.length; i++){
		   System.out.println(fs[i].getAbsolutePath());
		   if(fs[i].isDirectory()){
		    try{
		    	saveFileAndSql(fs[i],sb,savePathFile,fkId);
		    }catch(Exception e){
		    	e.printStackTrace();
		    }
		   }else{
			   id++;
			   	FileUtils.copyFile(fs[i], new File(savePathFile,fs[i].getName()));
				sb.append("("+2+","+fkId+",'"+"seriesImg/"+fkId+"/"+fs[i].getName()+"'),\n");
		   }
		}
	}
	public static File findByXcarImgId(Long xcarImgId){
		File file = new File("F:/新增车系图片3");
		File findFile = null;
		File[] files = file.listFiles();
		for(File tempFile:files){
			String fileName = tempFile.getName();
			if(fileName.contains(String.valueOf(xcarImgId))){
				findFile = tempFile;
				break;
			}
		}
		return findFile;
	}
	
	public static void main(String[] args) throws Exception{
		SeriesPhotoService ps = new SeriesPhotoService();
//		System.out.println(ps.seriesImgSql());
		File file = new File("F://seriesPhoto.sql");
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(ps.seriesImgSql().toString().getBytes());
		System.out.println("生成车系图片sql成功");
	}
}
