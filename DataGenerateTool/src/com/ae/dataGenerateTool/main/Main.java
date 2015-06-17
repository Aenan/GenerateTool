package com.ae.dataGenerateTool.main;

import com.ae.dataGenerateTool.data.ConfigHelper;
import com.ae.dataGenerateTool.data.DataGenerateTool;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//ConfigHelper.getConfigHelper().CONFIGPATH=args[0];
		DataGenerateTool tool = new DataGenerateTool();
		tool.executeGenerate();
	}

}
