package net.nigne.yzrproject.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="faq")
public class FaqVO {
	@Id
	@GeneratedValue(generator="NoSequence", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="NoSequence", sequenceName="FAQ_NO_SEQ", allocationSize=1)
	@Column(name="no")
	int no;
	
	@Column(name="category")
	String category;
	
	@Column(name="title")
	String title;
	
	@Column(name="content")
	String content;

	@Column(name="reg_date")
	String reg_date;
	
	@Column(name="view_cnt")
	int view_cnt;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public int getView_cnt() {
		return view_cnt;
	}

	public void setView_cnt(int view_cnt) {
		this.view_cnt = view_cnt;
	}
}