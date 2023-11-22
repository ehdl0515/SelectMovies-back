package com.projectFilm.demo.movies.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class DailyBoxOfficeId implements Serializable {
	private static final long serialVersionUID = -3073081628715340209L;
	@Column(name = "dateStamp", nullable = false)
	private Integer dateStamp;

	@Column(name = "movieRank", nullable = false)
	private Integer movieRank;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		DailyBoxOfficeId entity = (DailyBoxOfficeId) o;
		return Objects.equals(this.dateStamp, entity.dateStamp) &&
				Objects.equals(this.movieRank, entity.movieRank);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dateStamp, movieRank);
	}

}