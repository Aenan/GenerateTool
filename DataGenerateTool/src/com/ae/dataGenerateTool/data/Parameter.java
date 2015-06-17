package com.ae.dataGenerateTool.data;

import java.util.ArrayList;
import java.util.List;

public class Parameter {
	private String parameterName;
	private List<String> values;

	public Parameter(String parameterName, List<String> list) {
		this.parameterName = parameterName;
		this.values = list;
	}

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	public List getValues() {
		return values;
	}

	public void setValues(List values) {
		this.values = values;
	}
    public void removeRepeat(){
    	for (int i = 0; i < values.size() - 1; i++) {  
            for (int j = values.size() - 1; j > i; j--) {  
                if(values.get(j).toString().equals(values.get(i).toString())){  
                	values.remove(j);  
                }  
            }  
        }
    }
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String myString=this.parameterName+":";
		myString=myString+values.get(0).toString();
		for(int i=1;i<values.size();i++){
			myString=myString+","+values.get(i).toString();
		}
		myString=myString+"\r\n";
		return myString;
	}
}
