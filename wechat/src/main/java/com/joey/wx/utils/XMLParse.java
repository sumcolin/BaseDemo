/**
 * 对公众平台发送给公众账号的消息加解密示例代码.
 * 
 * @copyright Copyright (c) 1998-2014 Tencent Inc.
 */

// ------------------------------------------------------------------------

package com.joey.wx.utils;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.joey.wx.enums.MsgType;
import com.joey.wx.model.AcceptMsg;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * XMLParse class
 *
 * 提供提取消息格式中的密文及生成回复消息格式的接口.
 */
class XMLParse {
//	public static Object[] extract(String xmltext) throws Exception {
//		Object[] result = new Object[3];
//		try {
//			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//			dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
//			dbf.setFeature("http://xml.org/sax/features/external-general-entities", false);
//			dbf.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
//			dbf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
//			dbf.setXIncludeAware(false);
//			dbf.setExpandEntityReferences(false);
//			DocumentBuilder db = dbf.newDocumentBuilder();
//			StringReader sr = new StringReader(xmltext);
//			InputSource is = new InputSource(sr);
//			Document document = db.parse(is);
//
//			Element root = document.getDocumentElement();
//			NodeList nodelist1 = root.getElementsByTagName("Encrypt");
//			NodeList nodelist2 = root.getElementsByTagName("ToUserName");
//			result[0] = 0;
//			result[1] = nodelist1.item(0).getTextContent();
//			result[2] = nodelist2.item(0).getTextContent();
//			return result;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

//	public static String generate(String encrypt, String signature, String timestamp, String nonce) {
//
//		String format = "<xml>\n" + "<Encrypt><![CDATA[%1$s]]></Encrypt>\n"
//				+ "<MsgSignature><![CDATA[%2$s]]></MsgSignature>\n"
//				+ "<TimeStamp>%3$s</TimeStamp>\n" + "<Nonce><![CDATA[%4$s]]></Nonce>\n" + "</xml>";
//		return String.format(format, encrypt, signature, timestamp, nonce);
//
//	}


	// 解析输入流 封装消息体
	public static Object parseXML(InputStream inputStream) throws Exception {
		// 1、解析字节流
		Document document = new SAXReader().read(inputStream);
		Element rootElement = document.getRootElement();
		return parsDomToObj(rootElement, new AcceptMsg());
	}


	private static Object parsDomToObj(Element rootElement, Object obj) throws Exception {
		Field[] fields = obj.getClass().getDeclaredFields();
		Class<?> superclass = obj.getClass().getSuperclass();
		List<Field> fieldList = new ArrayList(Arrays.asList(fields));
		if (superclass != null) {
			Field[] superFields = superclass.getDeclaredFields();
			fieldList.addAll(Arrays.asList(superFields));
		}
		for (Field field : fieldList) {
			String name = upperCase(field.getName());
			Element element = rootElement.element(name);
			if (element != null) {
				String value = element.getTextTrim();
				field.setAccessible(true);
				field.set(obj, value);
			}
		}
		return obj;
	}


	private static String upperCase(String str) {
		char[] ch = str.toCharArray();
		if (ch[0] >= 'a' && ch[0] <= 'z') {
			ch[0] = (char) (ch[0] - 32);
		}
		return new String(ch);
	}
}
