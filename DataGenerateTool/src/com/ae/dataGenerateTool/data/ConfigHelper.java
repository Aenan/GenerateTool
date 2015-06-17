package com.ae.dataGenerateTool.data;

import java.io.File;
import java.io.InputStream;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ConfigHelper {
	private static ConfigHelper configHelper = new ConfigHelper();
	public final static String CONFIGPATH = ".\\config.xml";
	private InputStream in = null;
	private SAXReader reader = null;
	private Document doc = null;
	private Element root = null;

	private ConfigHelper() {
		reader = new SAXReader();
		try {
			doc = reader.read(new File(CONFIGPATH));
		} catch (Exception e) {
			System.out.println("文件加载问题,请确定文件路径正确。");
		}
		root = doc.getRootElement();

	}

	public static ConfigHelper getConfigHelper() {
		return configHelper;
	}

	public String getXMLFilePath() {
		Iterator iterator = root.elementIterator();
		while (iterator.hasNext()) {
			Element element = (Element) iterator.next();
			if (element.getName().equals("XMLFilePath")) {
				System.out.println(element.getText());
				return element.getText();
			}
		}
		System.out.println("config.xml文件错误");
		return "";
	}
	public String getPICTPath() {
		Iterator iterator = root.elementIterator();
		while (iterator.hasNext()) {
			Element element = (Element) iterator.next();
			if (element.getName().equals("PICTPath")) {
				System.out.println(element.getText());
				return element.getText();
			}
		}
		System.out.println("config.xml文件错误");
		return "";
	}

}
