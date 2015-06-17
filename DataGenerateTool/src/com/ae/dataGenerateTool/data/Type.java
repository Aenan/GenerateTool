package com.ae.dataGenerateTool.data;

public enum Type {
	STRING("String", 1), INT("int", 2), DOUBLE("double", 3), LONG("long", 4), CHAR(
			"char", 5), BOOLEAN("boolean", 6), BYTE("byte", 7);
	public String text;
	public int index;

	Type(String text, int index) {
		this.text = text;
		this.index = index;
	}

	public String getText() {
		return this.text;
	}
	public int getIndex() {
		return this.index;
	}
}
