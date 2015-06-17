package com.ae.dataGenerateTool.data;

import java.awt.print.Printable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PICT {
	static Runtime r = null;
	private static PICT pict = new PICT();

	private PICT() {
		r = Runtime.getRuntime();
	}
    public static PICT getPICT(){
    	return pict;
    }
    
    public static void executePICT(){
    	File file=new File(DataGenerateTool.PICTPath+"\\temp.bat");
    	if (!file.exists()){
			try {
				file.createNewFile();
				String content=DataGenerateTool.PICTPath.toString().charAt(0)+":\r\n"+"cd "+DataGenerateTool.PICTPath+"\r\npict temp.txt>temp.xls";
				FileOutputStream o = new FileOutputStream(file);
				o.write(content.getBytes("GBK"));           //必须写入“GBK”
				o.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("创建并写入bat文件错误");
			}
			
		}
    	try {
    		r.exec("cmd /c start "+DataGenerateTool.PICTPath+"\\temp.bat");
			//System.out.println("执行pict完毕");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("执行pict问题");
		}
    }
}
