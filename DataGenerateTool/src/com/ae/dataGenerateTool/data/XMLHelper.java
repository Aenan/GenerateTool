package com.ae.dataGenerateTool.data;

import java.io.File;
import java.io.InputStream;
import java.util.Iterator;

import org.dom4j.*;
import org.dom4j.io.SAXReader;

public class XMLHelper {

	private static XMLHelper xmlHelper = new XMLHelper();
	public String XMLFILEPATH = DataGenerateTool.XMLFilePath;
	public static final String ArgusName = "Parameters";
	private InputStream in = null;
	private SAXReader reader = null;
	private Document doc = null;
	private Element root = null;

	private XMLHelper() {
		reader = new SAXReader();
		try {
			doc = reader.read(new File(XMLFILEPATH));
		} catch (Exception e) {
			System.out.println("文件加载问题,请确定文件路径正确。");
		}
		root = doc.getRootElement();

	}

	public static XMLHelper getXMLHelper() {
		return xmlHelper;
	}

	public Iterator getParameters() {
		Iterator iterator = root.elementIterator();
		while (iterator.hasNext()) {
			Element element = (Element) iterator.next();
			if (element.getName().equals(ArgusName)) {
				Iterator it = element.elementIterator();
				return it;
			} else {
				return null;
			}
		}
		return null;
	}

	public void printXML() {
		Iterator iterator = root.elementIterator();
		while (iterator.hasNext()) {
			Element element = (Element) iterator.next();
			if (element.getName().equals("parameters")) {

			}
			element.getText();
			System.out.println(element.getName());
		}
	}

}
