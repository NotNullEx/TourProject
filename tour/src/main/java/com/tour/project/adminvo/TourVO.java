package com.tour.project.adminvo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TourVO {

	// 팩스 번호
	private String CMMN_FAX;
	// 주소
	private String ADDRESS;
	// 신주소
	private String NEW_ADDRESS;
	// 교통정보
	private String SUBWAY_INFO;
	// 웹사이트
	private String CMMN_HMPG_URL;
	// 전화번호
	private String CMMN_TELNO;
	// 운영요일
	private String CMMN_BSNDE;
	// 장애인 편의시설
	private String BF_DESC;
	// 휴무일
	private String CMMN_RSTDE;
	// 운영 시간
	private String CMMN_USE_TIME;
	// 상호명
	private String POST_SJ;
	// 고유 번호
	private String POST_SN;

}
