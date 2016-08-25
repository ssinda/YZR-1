package net.nigne.yzrproject.domain;

public class TempReservationInfo {
	String theater_area = "";
	String theater_id = "";
	String theater_name = "";
	String movie_id = "";
	String movie_title = "";
	String plex_num = "";
	String start_time = "";
	int dataFlag = 0;
	
	public String getTheater_area() {
		return theater_area;
	}
	public void setTheater_area(String theater_area) {
		this.theater_area = theater_area;
	}
	public String getTheater_id() {
		return theater_id;
	}
	public void setTheater_id(String theater_id) {
		this.theater_id = theater_id;
	}
	public String getTheater_name() {
		return theater_name;
	}
	public void setTheater_name(String theater_name) {
		this.theater_name = theater_name;
	}
	public String getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(String movie_id) {
		this.movie_id = movie_id;
	}
	public String getMovie_title() {
		return movie_title;
	}
	public void setMovie_title(String movie_title) {
		this.movie_title = movie_title;
	}
	public String getPlex_num() {
		return plex_num;
	}
	public void setPlex_num(String plex_num) {
		this.plex_num = plex_num;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public int getDataFlag() {
		return dataFlag;
	}
	public void setDataFlag(int dataFlag) {
		this.dataFlag = dataFlag;
	}
}
