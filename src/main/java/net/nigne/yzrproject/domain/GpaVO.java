package net.nigne.yzrproject.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

@Entity
@Table(name="gpa")
public class GpaVO {
	@Formula("(direction + ost + story + acting + beauty) / 5")
	public double avg;
	
	@Id
	@Column(name="movie_id")
	String movie_id;
	
	@Column(name="direction")
	int direction;
	
	@Column(name="ost")
	int ost;
	
	@Column(name="story")
	int story;
	
	@Column(name="acting")
	int acting;
	
	@Column(name="beauty")
	int beauty;
	
	@Column(name="male")
	int male;
	
	@Column(name="female")
	int female;
	
	@Column(name="teenager")
	int teenager;
	
	@Column(name="twenties")
	int twenties;
	
	@Column(name="thirties")
	int thirties;
	
	@Column(name="forties")
	int forties;

	public String getMovie_id() {
		return movie_id;
	}

	public void setMovie_id(String movie_id) {
		this.movie_id = movie_id;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getOst() {
		return ost;
	}

	public void setOst(int ost) {
		this.ost = ost;
	}

	public int getStory() {
		return story;
	}

	public void setStory(int story) {
		this.story = story;
	}

	public int getActing() {
		return acting;
	}

	public void setActing(int acting) {
		this.acting = acting;
	}

	public int getBeauty() {
		return beauty;
	}

	public void setBeauty(int beauty) {
		this.beauty = beauty;
	}

	public int getMale() {
		return male;
	}

	public void setMale(int male) {
		this.male = male;
	}

	public int getFemale() {
		return female;
	}

	public void setFemale(int female) {
		this.female = female;
	}

	public int getTeenager() {
		return teenager;
	}

	public void setTeenager(int teenager) {
		this.teenager = teenager;
	}

	public int getTwenties() {
		return twenties;
	}

	public void setTwenties(int twenties) {
		this.twenties = twenties;
	}

	public int getThirties() {
		return thirties;
	}

	public void setThirties(int thirties) {
		this.thirties = thirties;
	}

	public int getForties() {
		return forties;
	}

	public void setForties(int forties) {
		this.forties = forties;
	}

	public double getAvg() {
		return avg;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}

}
