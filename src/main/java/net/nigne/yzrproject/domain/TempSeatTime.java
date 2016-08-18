package net.nigne.yzrproject.domain;

public class TempSeatTime {
	String startTime = "";
	long extraSeatCount = 0;
	String plexNumber = "";
	
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public long getExtraSeatCount() {
		return extraSeatCount;
	}
	public void setExtraSeatCount(long extraSeatCount) {
		this.extraSeatCount = extraSeatCount;
	}
	public String getPlexNumber() {
		return plexNumber;
	}
	public void setPlexNumber(String plexNumber) {
		this.plexNumber = plexNumber;
	}
	

}
