package com.devsun.eternal.common.mxd2.macro;

public enum KeyDirection {

	UP("UP"),
	DOWN("DOWN");
	
	private KeyDirection(String text){
		this.text = text;
	}
	
	private String text;

	public String getText() {
		return text;
	}

}
