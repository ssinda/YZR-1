package net.nigne.yzrproject.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="genre")
public class GenreVO {
	@Id
	@GeneratedValue(generator="NoSequence", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="NoSequence", sequenceName="GENRE_NO_SEQ", allocationSize=1)
	@Column(name="no")
	int no;
	
	@Column(name="movie_id")
	String movie_id;
	
	@Column(name="movie_genre")
	String movie_genre;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getMovie_id() {
		return movie_id;
	}

	public void setMovie_id(String movie_id) {
		this.movie_id = movie_id;
	}

	public String getMovie_genre() {
		return movie_genre;
	}

	public void setMovie_genre(String movie_genre) {
		this.movie_genre = movie_genre;
	}
}
