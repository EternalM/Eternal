package com.devsun.eternal.common.mxd2.macro;

public enum KeyValue {

	UP("UP"),
	DOWN("DOWN"),
	LEFT("LEFT"),
	RIGHT("RIGHT"),
	SPACEBAR("SPACEBAR");
	
	private KeyValue(String text){
		this.text = text;
	}
	
	private String text;

	public String getText() {
		return text;
	}

}
