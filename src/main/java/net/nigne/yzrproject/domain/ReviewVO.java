package net.nigne.yzrproject.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="review")
public class ReviewVO {
	@Id
	@GeneratedValue(generator="NoSequence", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="NoSequence", sequenceName="REVIEW_NO_SEQ", allocationSize=1)
	@Column(name="no")
	int no;
	@Column(name="member_id")
	String member_id;	
	@Column(name="movie_id")
	String movie_id;
	@Column(name="review_title")
	String review_title;
	@Column(name="review_content")
	String review_content;
	@Column(name="review_date")
	String review_date;
	@Column(name="review_cnt")
	int review_cnt;
	@Column(name="review_file")
	String review_file;
	@Column(name="review_like")
	String review_like;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(String movie_id) {
		this.movie_id = movie_id;
	}
	public String getReview_title() {
		return review_title;
	}
	public void setReview_title(String review_title) {
		this.review_title = review_title;
	}
	public String getReview_content() {
		return review_content;
	}
	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}
	public String getReview_date() {
		return review_date;
	}
	public void setReview_date(String review_date) {
		this.review_date = review_date;
	}
	public int getReview_cnt() {
		return review_cnt;
	}
	public void setReview_cnt(int review_cnt) {
		this.review_cnt = review_cnt;
	}
	public String getReview_file() {
		return review_file;
	}
	public void setReview_file(String review_file) {
		this.review_file = review_file;
	}
	public String getReview_like() {
		return review_like;
	}
	public void setReview_like(String review_like) {
		this.review_like = review_like;
	}
	
}