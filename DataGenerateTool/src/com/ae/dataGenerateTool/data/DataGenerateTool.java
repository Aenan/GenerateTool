package com.ae.dataGenerateTool.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.dom4j.Attribute;
import org.dom4j.Element;

import com.ae.dataGenerateTool.data.XMLHelper;

public class DataGenerateTool {

	static final public String XMLFilePath = ConfigHelper.getConfigHelper().getXMLFilePath();
	static final public String PICTPath = ConfigHelper.getConfigHelper().getPICTPath();
	private List<Parameter> list = new ArrayList<Parameter>();

	public DataGenerateTool() {

	}

	public void executeGenerate() {
		Iterator iterator = XMLHelper.getXMLHelper().getParameters();
		while (iterator.hasNext()) {
			Element element = (Element) iterator.next();
			List<Attribute> alist = element.attributes();
			ParameterOfXML parameterOfXML = new ParameterOfXML(alist.get(0)
					.getValue(), alist.get(1).getValue(), alist.get(2)
					.getValue(), alist.get(3).getValue(), alist.get(4)
					.getValue());
			// System.out.println(parameterOfXML.toString());
			Parameter parameter = equivalenceAndBoundary(parameterOfXML);
			this.list.add(parameter);
			System.out.println(parameter.toString());

		}
		TxtHelper.getTxtHelper().generateTxt();
		String content = "";
		for (int i = 0; i < list.size(); i++) {
			content = content + list.get(i).toString();
		}
		// System.out.print(content);
		TxtHelper.getTxtHelper().writeTxt(content);
		PICT.executePICT();

	}

	private Parameter equivalenceAndBoundary(ParameterOfXML p) {
		List<String> list = new ArrayList();
		if (p.get_type().equals(Type.STRING.getText())) {
			list.add("空");
			if (!p.get_default().equals("")) {
				list.add(p.get_default());
			}
			dealScopeString(p, list);

		} else if (p.get_type().equals(Type.INT.getText())) {
			list.add("空");
			if (!p.get_default().equals("")) {
				list.add(p.get_default());
			}
			dealScopeNumber(p, list);
			list.add("" + 0);
			list.add(Integer.MAX_VALUE+"(整形最大值)");
			list.add(-1+Integer.MAX_VALUE+"(整形最大值减1)");
			list.add(1+Integer.MAX_VALUE+"(整形最大值加1)");
			list.add(Integer.MIN_VALUE+"(整形最小值)");
			list.add(-1+Integer.MIN_VALUE+"(整形最小值减1)");
			list.add(1+Integer.MIN_VALUE+"(整形最小值加1)");
		} else if (p.get_type().equals(Type.BOOLEAN.getText())) {
			list.add("空");
			list.add("true");
			list.add("false");

		} else if (p.get_type().equals(Type.DOUBLE.getText())) {
			list.add("空");
			if (!p.get_default().equals("")) {
				list.add(p.get_default());
			}
			dealScopeFNumber(p, list);
			list.add(0.0+"");
			list.add(Double.MAX_VALUE+"(双精度最大值)");
			list.add("-1+"+Double.MAX_VALUE+"(双精度最大值减1)");
			list.add("1+"+Double.MAX_VALUE+"(双精度最大值加1)");
			list.add(Double.MIN_VALUE+"(双精度最小值)");
			list.add("-1+"+Double.MIN_VALUE+"(双精度最小值减1)");
			list.add("1+"+Double.MIN_VALUE+"(双精度最小值加1)");
			// list.add("" + Double.MAX_VALUE);
			// list.add(Double.MAX_VALUE - 1 + "");
			// list.add(Double.MAX_VALUE + 1 + "");
			// list.add("" + Double.MIN_VALUE);
			// list.add("-1+" + Double.MIN_VALUE);
			// list.add("1+" + Double.MIN_VALUE);

		} else if (p.get_type().equals(Type.LONG.getText())) {
			list.add("空");
			if (!p.get_default().equals("")) {
				list.add(p.get_default());
			}
			dealScopeNumber(p, list);
			list.add("" + 0);
			list.add(Long.MAX_VALUE+"(长整型最大值)");
			list.add(-1+Long.MAX_VALUE+"(长整型最大值减1)");
			list.add(1+Long.MAX_VALUE+"(长整型最大值加1)");
			list.add(Long.MIN_VALUE+"(长整型最小值_");
			list.add(-1+Long.MIN_VALUE+"(长整型最小值减1)");
			list.add(1+Long.MIN_VALUE+"(长整型最小值加1)");
			// list.add("" + Long.MAX_VALUE);
			// list.add(Long.MAX_VALUE - 1 + "");
			// list.add(Long.MAX_VALUE + 1 + "");
			// list.add("" + Long.MIN_VALUE);
			// list.add(Long.MIN_VALUE - 1 + "");
			// list.add(Long.MIN_VALUE + 1 + "");

		}
		Parameter parameter = new Parameter(p.get_name(), list);
		parameter.removeRepeat();
		return parameter;
	}

	private void dealScopeString(ParameterOfXML p, List list) {
		if (!p.get_scope().equals("")) {
			String scope = p.get_scope();
			if (scope.indexOf('|') != -1) {
				String scopes[] = scope.split("\\|");
				for (int i = 0; i < scopes.length; i++) {
					// System.out.println(scopes[i].toString());
					if (scopes[i].indexOf(',') != -1
							&& (scopes[i].indexOf('(') != -1 || scopes[i]
									.indexOf('[') != -1)) {

						int min = Integer.parseInt(scopes[i].substring(1,
								scopes[i].indexOf(',')));
						int max = Integer.parseInt(scopes[i].substring(
								scopes[i].indexOf(',') + 1,
								scopes[i].length() - 1));
						if (min > 0) {
							if (min > 1) {
								list.add("小于" + min + "个字符");
							}
							list.add(min - 1 + "个字符");// 防止出現-1位的情況
							list.add(min + "个字符");
						}
						list.add(min + 1 + "个字符");
						list.add(min + "至" + max + "个字符");
						list.add(max - 1 + "个字符");
						list.add(max + "个字符");
						list.add(max + 1 + "个字符");
						list.add("大于" + max + "个字符");

					} else {
						list.add(scopes[i]);
					}
				}
			} else {
				if (scope.indexOf(',') != -1
						&& (scope.indexOf('(') != -1 || scope.indexOf('[') != -1)) {

					int min = Integer.parseInt(scope.substring(1,
							scope.indexOf(',')));
					int max = Integer.parseInt(scope.substring(
							scope.indexOf(',') + 1, scope.length() - 1));
					if (min > 0) {
						if (min > 1) {
							list.add("小于" + min + "个字符");
						}
						list.add(min - 1 + "个字符");// 防止出現-1位的情況
						list.add(min + "个字符");
					}
					list.add(min + 1 + "个字符");
					list.add(min + "至" + max + "个字符");
					list.add(max - 1 + "个字符");
					list.add(max + "个字符");
					list.add(max + 1 + "个字符");
					list.add("大于" + max + "个字符");

				} else {
					list.add(scope);
				}
			}
		}
	}

	void dealScopeNumber(ParameterOfXML p, List list) {
		if (!p.get_scope().equals("")) {
			String scope = p.get_scope();
			if (scope.indexOf('|') != -1) {
				String scopes[] = scope.split("\\|");
				for (int i = 0; i < scopes.length; i++) {
					// System.out.println(scopes[i].toString());
					if (scopes[i].indexOf(',') != -1
							&& (scopes[i].indexOf('(') != -1 || scopes[i]
									.indexOf('[') != -1)) {

						long min = Long.parseLong(scopes[i].substring(1,
								scopes[i].indexOf(',')));
						long max = Long.parseLong(scopes[i].substring(
								scopes[i].indexOf(',') + 1,
								scopes[i].length() - 1));

						list.add("小于" + min);
						list.add(min - 1 + "");
						list.add(min + "");
						list.add(min + 1 + "");
						list.add(min + "至" + max);
						list.add(max - 1 + "");
						list.add(max + "");
						list.add(max + 1 + "");
						list.add("大于" + max);

					} else if (scopes[i].indexOf(',') != -1) {
						String numbers[] = scopes[i].split(",");
						for (int j = 0; j < numbers.length; j++) {
							long number = Long.parseLong(numbers[j]);
							list.add(number + "");
							list.add(number - 1 + "");
							list.add(number + 1 + "");
						}
					} else {
						long number = Long.parseLong(scopes[i]);
						list.add(number + "");
						list.add(number - 1 + "");
						list.add(number + 1 + "");
					}
				}
			} else {
				if (scope.indexOf(',') != -1
						&& (scope.indexOf('(') != -1 || scope.indexOf('[') != -1)) {

					long min = Long.parseLong(scope.substring(1,
							scope.indexOf(',')));
					long max = Long.parseLong(scope.substring(
							scope.indexOf(',') + 1, scope.length() - 1));
					list.add("小于" + min);
					list.add(min - 1 + "");
					list.add(min + "");
					list.add(min + 1 + "");
					list.add(min + "至" + max);
					list.add(max - 1 + "");
					list.add(max + "");
					list.add(max + 1 + "");
					list.add("大于" + max);

				} else if (scope.indexOf(',') != -1) {
					String numbers[] = scope.split(",");
					for (int i = 0; i < numbers.length; i++) {
						// System.out.println(numbers[i]);
						long number = Long.parseLong(numbers[i]);
						list.add(number + "");
						list.add(number - 1 + "");
						list.add(number + 1 + "");
					}
				} else {
					long number = Long.parseLong(scope);
					list.add(number + "");
					list.add(number - 1 + "");
					list.add(number + 1 + "");
				}
			}
		}
	}

	void dealScopeFNumber(ParameterOfXML p, List list) {
		if (!p.get_scope().equals("")) {
			String scope = p.get_scope();
			if (scope.indexOf('|') != -1) {
				String scopes[] = scope.split("\\|");
				for (int i = 0; i < scopes.length; i++) {
					// System.out.println(scopes[i].toString());
					if (scopes[i].indexOf(',') != -1
							&& (scopes[i].indexOf('(') != -1 || scopes[i]
									.indexOf('[') != -1)) {

						double min = Double.parseDouble(scopes[i].substring(1,
								scopes[i].indexOf(',')));
						double max = Double.parseDouble(scopes[i].substring(
								scopes[i].indexOf(',') + 1,
								scopes[i].length() - 1));
						list.add("小于" + min + "双精度");
						list.add(min - 1 + "双精度");// 防止出現-1位的情況
						list.add(min + "双精度");
						list.add(min + 1 + "双精度");
						list.add(min + "至" + max + "双精度");
						list.add(max - 1 + "双精度");
						list.add(max + "双精度");
						list.add(max + 1 + "双精度");
						list.add("大于" + max + "双精度");

					} else if (scopes[i].indexOf(',') != -1) {
						String numbers[] = scopes[i].split(",");
						for (int j = 0; j < numbers.length; j++) {
							double number = Double.parseDouble(numbers[j]);
							list.add(number + "双精度");
							list.add(number - 1 + "双精度");
							list.add(number + 1 + "双精度");
						}
					} else {
						double number = Double.parseDouble(scopes[i]);
						list.add(number + "双精度");
						list.add(number - 1 + "双精度");
						list.add(number + 1 + "双精度");
					}
				}
			} else {
				if (scope.indexOf(',') != -1
						&& (scope.indexOf('(') != -1 || scope.indexOf('[') != -1)) {

					double min = Double.parseDouble(scope.substring(1,
							scope.indexOf(',')));
					double max = Double.parseDouble(scope.substring(
							scope.indexOf(',') + 1, scope.length() - 1));

					list.add("小于" + min + "双精度");
					list.add(min - 1 + "双精度");// 防止出現-1位的情況
					list.add(min + "双精度");
					list.add(min + 1 + "双精度");
					list.add(min + "至" + max + "双精度");
					list.add(max - 1 + "双精度");
					list.add(max + "双精度");
					list.add(max + 1 + "双精度");
					list.add("大于" + max + "双精度");

				} else if (scope.indexOf(',') != -1) {
					String numbers[] = scope.split(",");
					for (int i = 0; i < numbers.length; i++) {
						double number = Double.parseDouble(numbers[i]);
						list.add(number + "双精度");
						list.add(number - 1 + "双精度");
						list.add(number + 1 + "双精度");
					}
				} else {
					double number = Double.parseDouble(scope);
					list.add(number + "双精度");
					list.add(number - 1 + "双精度");
					list.add(number + 1 + "双精度");
				}
			}
		}
	}
	
	@Deprecated
	void dealScopeFNumberBackup(ParameterOfXML p, List list) {
		if (!p.get_scope().equals("")) {
			String scope = p.get_scope();
			if (scope.indexOf('|') != -1) {
				String scopes[] = scope.split("\\|");
				for (int i = 0; i < scopes.length; i++) {
					// System.out.println(scopes[i].toString());
					if (scopes[i].indexOf(',') != -1
							&& (scopes[i].indexOf('(') != -1 || scopes[i]
									.indexOf('[') != -1)) {

						long min = Long.parseLong(scopes[i].substring(1,
								scopes[i].indexOf(',')));
						long max = Long.parseLong(scopes[i].substring(
								scopes[i].indexOf(',') + 1,
								scopes[i].length() - 1));
						if(min<0){
							list.add("小于负" + (-min) + "双精度");
							list.add("负" + (-min) + "双精度");
							list.add("负" + (-min) + "至" + max + "双精度");
						}else {
							list.add("小于" + min + "双精度");
							list.add(min + "双精度");
							list.add(min + "至" + max + "双精度");
						}
						if(min-1<0){
							list.add("负"+(-(min - 1)) + "双精度");
						}else{
							list.add(min - 1 + "双精度");
						}
						if(min+1<0){
							list.add("负"+(-(min + 1)) + "双精度");
						}else{
							list.add(min + 1 + "双精度");
						}
						if(max<0){
							list.add("小于负" + (-max) + "双精度");
							list.add("负" + (-max) + "双精度");
						}else {
							list.add("小于" + max + "双精度");
							list.add(max + "双精度");
						}
						if(max-1<0){
							list.add("负"+(-(max - 1)) + "双精度");
						}else{
							list.add(max - 1 + "双精度");
						}
						if(max+1<0){
							list.add("负"+(-(max + 1)) + "双精度");
						}else{
							list.add(max + 1 + "双精度");
						}


					} else if (scopes[i].indexOf(',') != -1) {
						String numbers[] = scopes[i].split(",");
						for (int j = 0; j < numbers.length; j++) {
							long number = Long.parseLong(numbers[j]);
							if(number<0){
								list.add("负"+(-number) + "双精度");
							}else {
								list.add(number + "双精度");
							}
							if(number-1<0){
								list.add("负"+(-(number-1)) + "双精度");
							}else {
								list.add(number - 1 + "双精度");
							}
							if(number+1<0){
								list.add("负"+(-(number+1)) + "双精度");
							}else {
								list.add(number + 1 + "双精度");
							}
							
							
						}
					} else {
						long number = Long.parseLong(scopes[i]);
						if(number<0){
							list.add("负"+(-number) + "双精度");
						}else {
							list.add(number + "双精度");
						}
						if(number-1<0){
							list.add("负"+(-(number-1)) + "双精度");
						}else {
							list.add(number - 1 + "双精度");
						}
						if(number+1<0){
							list.add("负"+(-(number+1)) + "双精度");
						}else {
							list.add(number + 1 + "双精度");
						}
					}
				}
			} else {
				if (scope.indexOf(',') != -1
						&& (scope.indexOf('(') != -1 || scope.indexOf('[') != -1)) {

					long min = Long.parseLong(scope.substring(1,
							scope.indexOf(',')));
					long max = Long.parseLong(scope.substring(
							scope.indexOf(',') + 1, scope.length() - 1));

					if(min<0){
						list.add("小于负" + (-min) + "双精度");
						list.add("负" + (-min) + "双精度");
						list.add("负" + (-min) + "至" + max + "双精度");
					}else {
						list.add("小于" + min + "双精度");
						list.add(min + "双精度");
						list.add(min + "至" + max + "双精度");
					}
					if(min-1<0){
						list.add("负"+(-(min - 1)) + "双精度");
					}else{
						list.add(min - 1 + "双精度");
					}
					if(min+1<0){
						list.add("负"+(-(min + 1)) + "双精度");
					}else{
						list.add(min + 1 + "双精度");
					}
					if(max<0){
						list.add("小于负" + (-max) + "双精度");
						list.add("负" + (-max) + "双精度");
					}else {
						list.add("小于" + max + "双精度");
						list.add(max + "双精度");
					}
					if(max-1<0){
						list.add("负"+(-(max - 1)) + "双精度");
					}else{
						list.add(max - 1 + "双精度");
					}
					if(max+1<0){
						list.add("负"+(-(max + 1)) + "双精度");
					}else{
						list.add(max + 1 + "双精度");
					}

				} else if (scope.indexOf(',') != -1) {
					String numbers[] = scope.split(",");
					for (int i = 0; i < numbers.length; i++) {
						long number = Long.parseLong(numbers[i]);
						if(number<0){
							list.add("负"+(-number) + "双精度");
						}else {
							list.add(number + "双精度");
						}
						if(number-1<0){
							list.add("负"+(-(number-1)) + "双精度");
						}else {
							list.add(number - 1 + "双精度");
						}
						if(number+1<0){
							list.add("负"+(-(number+1)) + "双精度");
						}else {
							list.add(number + 1 + "双精度");
						}
					}
				} else {
					long number = Long.parseLong(scope);
					if(number<0){
						list.add("负"+(-number) + "双精度");
					}else {
						list.add(number + "双精度");
					}
					if(number-1<0){
						list.add("负"+(-(number-1)) + "双精度");
					}else {
						list.add(number - 1 + "双精度");
					}
					if(number+1<0){
						list.add("负"+(-(number+1)) + "双精度");
					}else {
						list.add(number + 1 + "双精度");
					}
				}
			}
		}
	}
}
