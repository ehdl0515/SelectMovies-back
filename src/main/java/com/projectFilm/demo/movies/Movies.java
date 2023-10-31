package com.projectFilm.demo.movies;

import lombok.*;

import javax.persistence.*;



@NoArgsConstructor
@Entity
@Getter
public class Movies {

	@Id
	public String movieCd;

	public String movieNm;
	public String movieNmEn;
	public short prdtYear;
	public int openDt;
	public String typeNm;
	public String prdtStatNm;
	public String nationAlt;
	public String genreAlt;
	public String repNationNm;
	public String repGenreNm;

	public Movies(String movieCd_,String movieNm_,String movieNmEn_,short prdtYear_,int openDt_,String typeNm_,String prdtStatNm_,String nationAlt_,String genreAlt_,String repNationNm_,String repGenreNm_)
	{
		this.movieCd = movieCd_;
		this.movieNm = movieNm_;
		this.movieNmEn = movieNmEn_;
		this.prdtYear = prdtYear_;
		this.openDt = openDt_;
		this.typeNm = typeNm_;
		this.prdtStatNm = prdtStatNm_;
		this.nationAlt = nationAlt_;
		this.genreAlt = genreAlt_;
		this.repNationNm = repNationNm_;
		this.repGenreNm = repGenreNm_;
	}
}

