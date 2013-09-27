package com.fxpgy.fetch.service;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author paul
 * @version 创建时间：2013-5-13 下午5:45:22 类说明
 */
public class XmlParser {
	private static final String WEB_URL = "PicLib";
	private static int oneClassId = 0;
	private static int twoClassId = 0;
	private static int seriesId = 0;
	public static XPath xpath;
	private static StringBuffer oneClassBrand = new StringBuffer();
	private static StringBuffer towClassBrand = new StringBuffer();
	private static StringBuffer series = new StringBuffer();
	
	public static void main(String[] args) throws Exception {
		int i = 0;
		oneClassBrand.append("insert into bsd_auto_brand (id,name,letter,icon) values ");
		towClassBrand.append("insert into bsd_auto_brand (id,name,parentId)");
		series.append("insert into bsd_auto_series (id,brandId,name,icon,parentId,price) ");
		DocumentBuilderFactory domFactory = DocumentBuilderFactory
				.newInstance();
		domFactory.setNamespaceAware(true); // never forget this!
		DocumentBuilder builder = domFactory.newDocumentBuilder();
		Document doc = builder.parse("src/main/java/Brand_Info.plist");
		XPathFactory factory = XPathFactory.newInstance();
		xpath = factory.newXPath();
		NodeList letterNodes = (NodeList) xpath.compile("//plist/array/dict/array/dict/string").evaluate(doc,XPathConstants.NODESET);
		NodeList oneclassBrandNodes = (NodeList) xpath.compile("//plist/array/dict/array/dict/array/dict").evaluate(doc,XPathConstants.NODESET);
		System.out.println(oneclassBrandNodes.getLength());
		twoClassId = oneclassBrandNodes.getLength();
		for (i = 0; i < letterNodes.getLength(); i++) {
			Node node = letterNodes.item(i).getParentNode();
			NodeList list = (NodeList) xpath.compile("array/dict").evaluate(node, XPathConstants.NODESET);
			NodeList strList = (NodeList) xpath.compile("array/dict/string").evaluate(node, XPathConstants.NODESET);
			if (strList.getLength() >= 3) {
				int b = strList.getLength() / 3;
				for (int j = 1; j <= b; j++) {
					oneClassId++;
					oneClassBrand.append("("+oneClassId+",'"+ strList.item(j * 3 - 1).getTextContent() + "','"+ letterNodes.item(i).getTextContent() + "','"+ urlSub(strList.item(j * 3 - 2).getTextContent())+ "'),\n");
					NodeList towClassNodes = (NodeList) xpath.compile("array/dict").evaluate(list.item(j - 1),XPathConstants.NODESET);
					twoClassSqlStr(towClassNodes);
				}
			}
		}
		System.out.println(oneClassBrand.substring(0,oneClassBrand.lastIndexOf(",")));
		System.out.println(towClassBrand.substring(0,towClassBrand.lastIndexOf(",")));
		System.out.println(series.substring(0,series.lastIndexOf(",")));
	}

	private static String urlSub(String url) {
		return url.substring(url.indexOf(WEB_URL), url.length());
	}

	public static void twoClassSqlStr(NodeList towClassNodes) throws Exception {	
		for (int i = 0; i < towClassNodes.getLength(); i++) {
			twoClassId++;
			NodeList nodes = (NodeList)xpath.compile("string").evaluate(towClassNodes.item(i), XPathConstants.NODESET);
			towClassBrand.append("("+twoClassId+",'" + nodes.item(1).getTextContent() + "',"+oneClassId + ") ,\n");
			NodeList seriesNodes = (NodeList)xpath.compile("array/dict").evaluate(towClassNodes.item(i), XPathConstants.NODESET);
			seriesSqlStr(seriesNodes);
		}

	}
	public static void seriesSqlStr(NodeList seriesNodes) throws Exception{
		for(int i=0;i<seriesNodes.getLength();i++){
			seriesId++;
			Node seriesNode = seriesNodes.item(i);
			NodeList seriesValue = (NodeList)xpath.compile("string").evaluate(seriesNode, XPathConstants.NODESET);
			series.append("("+seriesId+","+twoClassId+",'"+seriesValue.item(1).getTextContent()+"','"+urlSub(seriesValue.item(0).getTextContent())+"',null,'"+seriesValue.item(2).getTextContent()+"'),\n");
		}
	}
}
