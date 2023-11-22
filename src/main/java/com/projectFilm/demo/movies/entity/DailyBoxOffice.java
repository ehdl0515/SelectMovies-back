package com.projectFilm.demo.movies.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "Daily_Box_Office")
public class DailyBoxOffice {
	@EmbeddedId
	private DailyBoxOfficeId id;

	@Column(name = "rankInten")
	private Integer rankInten;

	@Column(name = "rankOldAndNew", length = 5)
	private String rankOldAndNew;

	@Column(name = "movieCd", length = 10)
	private String movieCd;

	@Column(name = "movieNm", length = 200)
	private String movieNm;

	@Column(name = "openDt")
	private Integer openDt;

	@Column(name = "salesAmt")
	private Long salesAmt;

	@Column(name = "salesShare", precision = 8, scale = 3)
	private BigDecimal salesShare;

	@Column(name = "salesInten")
	private Long salesInten;

	@Column(name = "salesChange", precision = 8, scale = 3)
	private BigDecimal salesChange;

	@Column(name = "salesAcc")
	private Long salesAcc;

	@Column(name = "audiCnt")
	private Integer audiCnt;

	@Column(name = "audiInten")
	private Integer audiInten;

	@Column(name = "audiChange", precision = 8, scale = 3)
	private BigDecimal audiChange;

	@Column(name = "audiAcc")
	private Long audiAcc;

	@Column(name = "scrnCnt")
	private Integer scrnCnt;

	@Column(name = "showCnt")
	private Integer showCnt;

}