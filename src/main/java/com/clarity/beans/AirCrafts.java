package com.clarity.beans;

import java.time.LocalDateTime;

public class AirCrafts {
	
	AirCraftType airCraftType;
	AirCraftSize airCraftSizes;
	LocalDateTime timeQueued;
	static int x = 0;
	
	String process;

	public int id;
	
	public AirCrafts(AirCraftType airCraftType, AirCraftSize airCraftSizes, String process) {
		this.airCraftType = airCraftType;
		this.airCraftSizes = airCraftSizes;
		this.process = process;
		this.timeQueued = LocalDateTime.now();
		setId(++x);
	}
	
	
	
	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public AirCrafts() {}

	public AirCraftType getAirCraftType() {
		return airCraftType;
	}

	public void setAirCraftType(AirCraftType airCraftType) {
		this.airCraftType = airCraftType;
	}

	public AirCraftSize getAirCraftSizes() {
		return airCraftSizes;
	}

	public void setAirCraftSizes(AirCraftSize airCraftSizes) {
		this.airCraftSizes = airCraftSizes;
	}

	public LocalDateTime getTimeQueued() {
		return timeQueued;
	}

	public void setTimeQueued(LocalDateTime timeQueued) {
		this.timeQueued = timeQueued;
	}

	@Override
	public String toString() {
		return "AirCrafts [airCraftType=" + airCraftType + ", airCraftSizes=" + airCraftSizes + ", timeQueued="
				+ timeQueued + "]";
	}
	
	
}
