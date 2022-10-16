# TourProject



CREATE TABLE `member` (
	`mem_seq`	INT(11)	NOT NULL,
	`mem_name`	VARCHAR(20)	NOT NULL,
	`mem_password`	VARCHAR(500)	NOT NULL,
	`mem_phone_num`	VARCHAR(20)	NOT NULL,
	`mem_status`	INT(1)	NOT NULL	DEFAULT 0	COMMENT '0- 무료회원 1- 유료회원',
	`mem_regist_day`	TIMESTAMP	NOT NULL	COMMENT '로컬날짜',
	`mem_email`	VARCHAR(20)	NOT NULL,
	`mem_upd_date`	TIMESTAMP	NOT NULL
);

CREATE TABLE `event` (
	`even_code`	VARCHAR(10)	NOT NULL,
	`tour_seq`	INT(11)	NOT NULL,
	`tour_post_sn`	INT(5)	NULL,
	`mem_seq`	INT(11)	NOT NULL,
	`even_name`	VARCHAR(50)	NOT NULL,
	`even_start_date`	TIMESTAMP	NOT NULL,
	`even_end_date`	TIMESTAMP	NOT NULL,
	`even_adress`	VARCHAR(100)	NOT NULL,
	`even_status`	INT(1)	NOT NULL	COMMENT '0-이벤트종료 1-이벤트시작',
	`even_desc`	VARCHAR(200)	NOT NULL
);

CREATE TABLE `tour` (
	`tour_seq`	INT(11)	NOT NULL,
	`tour_post_sn`	INT(5)	NULL,
	`mem_seq`	INT(11)	NOT NULL,
	`tour_cmmn_fax`	VARCHAR(20)	NULL,
	`tour_address`	VARCHAR(100)	NULL,
	`tour_new_address`	VARCHAR(100)	NULL,
	`tour_subway_info`	VARCHAR(200)	NULL,
	`tour_cmmn_hmpg_url`	VARCHAR(50)	NULL,
	`tour_cmmn_telno`	VARCHAR(15)	NULL,
	`tour_cmmn_bsnde`	VARCHAR(50)	NULL,
	`toour_bf_desc`	VARCHAR(200)	NULL,
	`tour_cmmn_rstde`	VARCHAR(15)	NULL,
	`tour_cmmn_use_time`	VARCHAR(150)	NULL,
	`tour_post_sj`	VARCHAR(100)	NULL
);

CREATE TABLE `lodgment` (
	`lod_code`	VARCHAR(20)	NOT NULL,
	`tour_seq`	INT(11)	NOT NULL,
	`tour_post_sn`	INT(5)	NULL,
	`mem_seq`	INT(11)	NOT NULL,
	`lod_name`	VARCHAR(20)	NOT NULL,
	`lod_telnum`	VARCHAR(20)	NULL	COMMENT '출력할때: 문의 및 안내',
	`lod_adress`	VARCHAR(50)	NOT NULL,
	`lod_checkin_date`	VARCHAR(20)	NULL,
	`lod_checkout_date`	VARCHAR(20)	NULL,
	`lod_ reser_telnum`	VARCHAR(20)	NULL	COMMENT '출력할때: 예약안내',
	`lod_image`	VARCHAR(150)	NULL,
	`lod_status`	INT(1)	NOT NULL	COMMENT '0-full 1-empty'
);

CREATE TABLE `restaurant` (
	`res_code`	INT	NOT NULL,
	`tour_seq`	INT(11)	NOT NULL,
	`tour_post_sn`	INT(5)	NULL,
	`mem_seq`	INT(11)	NOT NULL,
	`res_telnum`	VARCHAR(20)	NOT NULL,
	`res_adress`	VARCHAR(50)	NOT NULL,
	`res_open_time`	VARCHAR(50)	NOT NULL,
	`res_rest_day`	VARCHAR(10)	NULL,
	`res_head_menu`	VARCHAR(30)	NULL,
	`res_image`	VARCHAR(200)	NULL,
	`res_kind`	INT(1)	NULL	COMMENT '0-한식 1-중식 2-일식 3-양식',
	`res_desc`	VARCHAR(200)	NOT NULL
);

CREATE TABLE `board` (
	`board_seq`	INT	NOT NULL,
	`mem_seq`	INT(11)	NOT NULL,
	`board_title`	VARCHAR(100)	NOT NULL,
	`board_contents`	VARCHAR(1000)	NULL,
	`board_status`	INT(1)	NOT NULL	COMMENT '0-자유 1-질문 2-답변 3-숙박후기 4-음식점후기 5-축제후기',
	`board_reg_date`	TIMESTAMP	NOT NULL,
	`board_upd_date`	TIMESTAMP	NOT NULL
);

CREATE TABLE `coments` (
	`coment_seq`	INT(10)	NOT NULL,
	`board_seq`	INT	NOT NULL,
	`mem_email2`	VARCHAR(20)	NOT NULL,
	`mem_seq`	INT(11)	NOT NULL,
	`coment_contents`	VARCHAR(100)	NOT NULL,
	`coment_like`	INT(11)	NOT NULL,
	`coment_reg_date`	TIMESTAMP	NOT NULL,
	`coment_upd_date`	TIMESTAMP	NOT NULL
);

CREATE TABLE `notification` (
	`noti_seq`	INT	NOT NULL,
	`admin_seq`	INT(11)	NOT NULL,
	`noti_title`	VARCHAR(100)	NOT NULL,
	`noti_contents`	VARCHAR(1000)	NOT NULL,
	`noti_reg_date`	TIMESTAMP	NOT NULL,
	`noti_upd_date`	TIMESTAMP	NOT NULL,
	`noti_del_date`	TIMESTAMP	NOT NULL,
	`noti_status`	INT(1)	NOT NULL	COMMENT '0-비표시 1-표시'
);

CREATE TABLE `admin` (
	`admin_seq`	INT(11)	NOT NULL,
	`admin_name`	VARCHAR(20)	NOT NULL,
	`admin_password`	VARCHAR(500)	NOT NULL,
	`admin_phone_num`	VARCHAR(20)	NOT NULL,
	`admin_status`	INT(1)	NOT NULL	DEFAULT 0	COMMENT '0- 무료회원 1- 유료회원',
	`admin_regist_day`	TIMESTAMP	NOT NULL	COMMENT '로컬날짜',
	`admin_email`	VARCHAR(20)	NOT NULL
);

ALTER TABLE `member` ADD CONSTRAINT `PK_MEMBER` PRIMARY KEY (
	`mem_seq`
);

ALTER TABLE `event` ADD CONSTRAINT `PK_EVENT` PRIMARY KEY (
	`even_code`,
	`tour_seq`,
	`tour_post_sn`,
	`mem_seq`
);

ALTER TABLE `tour` ADD CONSTRAINT `PK_TOUR` PRIMARY KEY (
	`tour_seq`,
	`tour_post_sn`,
	`mem_seq`
);

ALTER TABLE `lodgment` ADD CONSTRAINT `PK_LODGMENT` PRIMARY KEY (
	`lod_code`,
	`tour_seq`,
	`tour_post_sn`,
	`mem_seq`
);

ALTER TABLE `restaurant` ADD CONSTRAINT `PK_RESTAURANT` PRIMARY KEY (
	`res_code`,
	`tour_seq`,
	`tour_post_sn`,
	`mem_seq`
);

ALTER TABLE `board` ADD CONSTRAINT `PK_BOARD` PRIMARY KEY (
	`board_seq`,
	`mem_seq`
);

ALTER TABLE `coments` ADD CONSTRAINT `PK_COMENTS` PRIMARY KEY (
	`coment_seq`,
	`board_seq`,
	`mem_email2`,
	`mem_seq`
);

ALTER TABLE `notification` ADD CONSTRAINT `PK_NOTIFICATION` PRIMARY KEY (
	`noti_seq`,
	`admin_seq`
);

ALTER TABLE `admin` ADD CONSTRAINT `PK_ADMIN` PRIMARY KEY (
	`admin_seq`
);

