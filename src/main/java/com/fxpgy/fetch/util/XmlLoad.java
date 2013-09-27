package com.fxpgy.fetch.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * @author paul
 * @version 创建时间：2013-5-21 上午11:57:50
 * 类说明
 */
public class XmlLoad {
	public static List<Map<String,Integer>> typeMaps = null;
	static{
		typeMaps = new ArrayList<Map<String,Integer>>();
		File file = new File("src/main/resources/bsd_auto_setting_type.xml");
		DocumentBuilderFactory domFactory = DocumentBuilderFactory
				.newInstance();
		domFactory.setNamespaceAware(true); // never forget this!
		DocumentBuilder builder;
		try {
			builder = domFactory.newDocumentBuilder();
			XPathFactory factory = XPathFactory.newInstance();
			Document doc = builder.parse(file);
			XPath xpath = factory.newXPath();
			NodeList typeNames = (NodeList)xpath.compile("//mysql/row/typename").evaluate(doc, XPathConstants.NODESET);
			NodeList ids = (NodeList)xpath.compile("//mysql/row/id").evaluate(doc, XPathConstants.NODESET);
			for(int i=0 ; i<typeNames.getLength();i++){
				Map<String,Integer> map = new HashMap<String, Integer>(); 
				map.put(typeNames.item(i).getTextContent().trim(),Integer.parseInt(ids.item(i).getTextContent().trim()));
				typeMaps.add(map);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
