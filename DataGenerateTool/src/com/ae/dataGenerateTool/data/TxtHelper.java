package com.ae.dataGenerateTool.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TxtHelper {
	public static String txtPath = DataGenerateTool.PICTPath+"\\temp.txt";
	private static TxtHelper txtHelper = new TxtHelper();
	File file = new File(txtPath);

	public static TxtHelper getTxtHelper() {
		return txtHelper;
	}

	void generateTxt() {
		if (file.exists()) {
			System.out.println("已存在txt文件");
		} else {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("创建txt文件错误");
			}
		}
	}

	void writeTxt(String content) {
		if (!file.exists()) {
			System.out.println("txt文件不存在");
		} else {

			try {
				FileOutputStream o = new FileOutputStream(file);
				o.write(content.getBytes("GBK"));           //必须写入“GBK”
				o.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("写入文件时错误");
			}

		}
	}
}
