package com.clarity.beans;

public enum AirCraftSize implements IAirCraftSize {
	Small,
	Large;
	
	String type;
	String priority;
	
	AirCraftSize(){}

	@Override
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String getType() {
		return this.type;
	}

	@Override
	public String getPriority() {
		return this.priority;
	}

	public void setPriority(String highest) {
		this.priority = highest;
	}

}
