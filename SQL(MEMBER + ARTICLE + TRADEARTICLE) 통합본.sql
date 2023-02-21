DROP TABLE TRADE_ARTICLE;
DROP TABLE REPLY;
DROP TABLE ARTICLE;
DROP TABLE MEMBER;

DROP SEQUENCE SEQ_REPLY_NO;
DROP SEQUENCE SEQ_ARTICLE_NO;
DROP SEQUENCE SEQ_UNO;

-- MEMBER 테이블 생성 및 코멘트, 시퀀스 작성 (CLOSETOU 계정에서 작업하세요.)
CREATE TABLE MEMBER (
    NO NUMBER PRIMARY KEY,
    USER_ID VARCHAR2(20) NOT NULL UNIQUE,
    PASSWORD VARCHAR2(30) NOT NULL,
    ROLE VARCHAR2(12) DEFAULT 'ROLE_USER',
    USER_NAME VARCHAR2(20) NOT NULL,
    NICKNAME VARCHAR2(30) NOT NULL,
    PHONE VARCHAR2(13) NOT NULL,
    EMAIL VARCHAR2(50),
    ADDRESS VARCHAR2(200),
    ADDRESS_DETAIL VARCHAR2(50),
    STATUS VARCHAR2(1) DEFAULT 'Y' CHECK(STATUS IN('Y', 'N')),
    ENROLL_DATE DATE DEFAULT SYSDATE
);

COMMENT ON COLUMN Member.NO IS '회원번호';
COMMENT ON COLUMN Member.USER_ID IS '회원ID';
COMMENT ON COLUMN Member.PASSWORD IS '회원PWD';
COMMENT ON COLUMN Member.ROLE IS '회원권한';
COMMENT ON COLUMN Member.USER_NAME IS '회원명';
COMMENT ON COLUMN Member.NICKNAME IS '닉네임';
COMMENT ON COLUMN Member.PHONE IS '연락처';
COMMENT ON COLUMN Member.EMAIL IS '이메일';
COMMENT ON COLUMN Member.ADDRESS IS '주소';
COMMENT ON COLUMN Member.ADDRESS_DETAIL IS '상세주소';
COMMENT ON COLUMN Member.STATUS IS '회원상태';
COMMENT ON COLUMN Member.ENROLL_DATE IS '등록일자';

CREATE SEQUENCE SEQ_UNO;

-- 테스트용 관리자 계정 생성 (CLOSETOU 계정에서 작업하세요.)
INSERT INTO MEMBER (
    NO, 
    USER_ID, 
    PASSWORD,
    ROLE,
    USER_NAME,
    NICKNAME, 
    PHONE,
    EMAIL, 
    ADDRESS, 
    ADDRESS_DETAIL, 
    STATUS, 
    ENROLL_DATE
) VALUES(
    SEQ_UNO.NEXTVAL, 
    'admin', 
    '1234', 
    'ROLE_ADMIN',
    '관리자',
    '관리자',
    '010-1234-5678',
    'admin@iei.or.kr', 
    '서울시 강남구 역삼동',
    '남도빌딩 4층 R강의장',
    DEFAULT,
    DEFAULT
);

COMMIT;

-- 테스트용 일반 회원 계정 생성 (CLOSETOU 계정에서 작업하세요.)
INSERT INTO MEMBER (
    NO, 
    USER_ID, 
    PASSWORD,
    ROLE,
    USER_NAME,
    NICKNAME, 
    PHONE,
    EMAIL, 
    ADDRESS,
    ADDRESS_DETAIL,
    STATUS, 
    ENROLL_DATE
) VALUES(
    SEQ_UNO.NEXTVAL, 
    'user1', 
    '1234', 
    DEFAULT,
    '문인수',
    '강사님짱',
    '010-2345-6789',
    'user1@iei.or.kr', 
    '서울시 강남구 역삼동',
    '남도빌딩 어딘가',
    DEFAULT,
    DEFAULT
);

INSERT INTO MEMBER (
    NO, 
    USER_ID, 
    PASSWORD,
    ROLE,
    USER_NAME,
    NICKNAME, 
    PHONE,
    EMAIL, 
    ADDRESS,
    ADDRESS_DETAIL,
    STATUS, 
    ENROLL_DATE
) VALUES(
    SEQ_UNO.NEXTVAL, 
    'user2', 
    '1234', 
    DEFAULT,
    '공민지',
    '콩밍디',
    '010-1111-2222',
    'user2@iei.or.kr', 
    '서울특별시 강서구 화곡동',
    '화곡역과 까치산역 사이 어딘가',
    DEFAULT,
    DEFAULT
);

INSERT INTO MEMBER (
    NO, 
    USER_ID, 
    PASSWORD,
    ROLE,
    USER_NAME,
    NICKNAME, 
    PHONE,
    EMAIL, 
    ADDRESS,
    ADDRESS_DETAIL,
    STATUS, 
    ENROLL_DATE
) VALUES(
    SEQ_UNO.NEXTVAL, 
    'user3', 
    '1234', 
    DEFAULT,
    '천승준',
    '블랙워그레이몬',
    '010-2222-3333',
    'user3@iei.or.kr', 
    '서울특별시 강동구 명일동',
    '무슨 아파트',
    DEFAULT,
    DEFAULT
);

INSERT INTO MEMBER (
    NO, 
    USER_ID, 
    PASSWORD,
    ROLE,
    USER_NAME,
    NICKNAME, 
    PHONE,
    EMAIL, 
    ADDRESS,
    ADDRESS_DETAIL,
    STATUS, 
    ENROLL_DATE
) VALUES(
    SEQ_UNO.NEXTVAL, 
    'user4', 
    '1234', 
    DEFAULT,
    '이정준',
    '땅콩이형',
    '010-3333-4444',
    'user4@iei.or.kr', 
    '서울특별시 강동구 명일동',
    '땅콩이집',
    DEFAULT,
    DEFAULT
);

INSERT INTO MEMBER (
    NO, 
    USER_ID, 
    PASSWORD,
    ROLE,
    USER_NAME,
    NICKNAME, 
    PHONE,
    EMAIL, 
    ADDRESS, 
    ADDRESS_DETAIL,
    STATUS, 
    ENROLL_DATE
) VALUES(
    SEQ_UNO.NEXTVAL, 
    'user5', 
    '1234', 
    DEFAULT,
    '김영근',
    '주안동불주먹',
    '010-4444-5555',
    'user5@iei.or.kr', 
    '인천광역시 미추홀구 주안동',
    '주안역 근처 어딘가',
    DEFAULT,
    DEFAULT
);

INSERT INTO MEMBER (
    NO, 
    USER_ID, 
    PASSWORD,
    ROLE,
    USER_NAME,
    NICKNAME, 
    PHONE,
    EMAIL, 
    ADDRESS, 
    ADDRESS_DETAIL,
    STATUS, 
    ENROLL_DATE
) VALUES(
    SEQ_UNO.NEXTVAL, 
    'user6', 
    '1234', 
    DEFAULT,
    '이주희',
    '성남여신',
    '010-5555-6666',
    'user6@iei.or.kr', 
    '경기도 성남시 중원구',
    '산성역 근처 어딘가',
    DEFAULT,
    DEFAULT
);

INSERT INTO MEMBER (
    NO, 
    USER_ID, 
    PASSWORD,
    ROLE,
    USER_NAME,
    NICKNAME, 
    PHONE,
    EMAIL, 
    ADDRESS, 
    ADDRESS_DETAIL,
    STATUS, 
    ENROLL_DATE
) VALUES(
    SEQ_UNO.NEXTVAL, 
    'user7', 
    '1234', 
    DEFAULT,
    '이정훈',
    '동두천빈지노',
    '010-6666-7777',
    'user7@iei.or.kr', 
    '경기도 동두천시 동두천동',
    '동두천 어딘가',
    DEFAULT,
    DEFAULT
);

INSERT INTO MEMBER (
    NO, 
    USER_ID, 
    PASSWORD,
    ROLE,
    USER_NAME,
    NICKNAME, 
    PHONE,
    EMAIL, 
    ADDRESS, 
    ADDRESS_DETAIL,
    STATUS, 
    ENROLL_DATE
) VALUES(
    SEQ_UNO.NEXTVAL, 
    'user8', 
    '1234', 
    DEFAULT,
    '이정수',
    '현정아잘지내니',
    '010-7777-8888',
    'user8@iei.or.kr', 
    '서울특별시 동작구 사당동',
    '사당역과 이수역 사이 어딘가',
    DEFAULT,
    DEFAULT
);

COMMIT;

-- 아티클 생성
DROP TABLE ARTICLE;

CREATE TABLE ARTICLE
    (NO	        NUMBER		        PRIMARY KEY,
	PHOTO_NO	NUMBER,
	USER_NO	    NUMBER		        NOT NULL,
	TYPE	    VARCHAR2(10)		NOT NULL,
	TITLE	    VARCHAR2(100)		NOT NULL,
	CONTENT	    VARCHAR2(2000)		NOT NULL,
	READ_COUNT	NUMBER	        DEFAULT 0	NOT NULL,
	VISABLE	    CHAR(1)	        DEFAULT 'Y'	NOT NULL,
	POST_DATE	DATE	        DEFAULT SYSDATE	NOT NULL,
	EDITED	    VARCHAR2(500)		NULL,
	EDIT_DATE	DATE		        NULL);

COMMENT ON COLUMN "ARTICLE"."NO" IS '게시물번호';

COMMENT ON COLUMN "ARTICLE"."TYPE" IS '공지, 문의, 거래, 자유';

ALTER TABLE "ARTICLE" ADD CONSTRAINT "FK_ARTICLE_MEMBER" FOREIGN KEY (
	"USER_NO"
)
REFERENCES "MEMBER" (
	"NO"
);

COMMIT;

DROP TABLE "TRADE_ARTICLE";

CREATE TABLE "TRADE_ARTICLE" (
	"ARTICLE_ID"	NUMBER		NOT NULL,
	"CLOTH_NO"	    NUMBER		NOT NULL,
	"PRICE"	        NUMBER		        NOT NULL,
	"CLOTH_INFO"	VARCHAR2(1000)		NOT NULL,
	"TRADE_ENDED"	CHAR(1)	DEFAULT 'N'	NOT NULL,
	"FREE"      	CHAR(1)	DEFAULT 'N'	NOT NULL,
	"TRADE_METHOD"	VARCHAR2(10)		NOT NULL,
	"LOCATION"	    VARCHAR2(200)		NOT NULL
);

COMMENT ON COLUMN "TRADE_ARTICLE"."ARTICLE_ID" IS '게시물번호';

COMMENT ON COLUMN "TRADE_ARTICLE"."CLOTH_NO" IS '의류NO';

COMMENT ON COLUMN "TRADE_ARTICLE"."PRICE" IS '가격';

COMMENT ON COLUMN "TRADE_ARTICLE"."CLOTH_INFO" IS '의류설명';

COMMENT ON COLUMN "TRADE_ARTICLE"."TRADE_ENDED" IS '판매완료';

COMMENT ON COLUMN "TRADE_ARTICLE"."FREE" IS '나눔여부';

COMMENT ON COLUMN "TRADE_ARTICLE"."TRADE_METHOD" IS '희망거래방법';

COMMENT ON COLUMN "TRADE_ARTICLE"."LOCATION" IS '지역';

ALTER TABLE "TRADE_ARTICLE" ADD CONSTRAINT "PK_TRADE_ARTICLE" PRIMARY KEY (
	"ARTICLE_ID"
);

ALTER TABLE "TRADE_ARTICLE" ADD CONSTRAINT "FK_TRART_ARTNO" FOREIGN KEY (
	"ARTICLE_ID"
)
REFERENCES "ARTICLE" (
	"NO"
);

ALTER TABLE "TRADE_ARTICLE" ADD CONSTRAINT "FK_TRART_CLNO" FOREIGN KEY (
	"CLOTH_NO"
)
REFERENCES "CLOTH" (
	"NO"
);

COMMIT;


-- 예시 아티클 생성기

CREATE SEQUENCE SEQ_ARTICLE_NO;

DROP SEQUENCE SEQ_TRADE_ARTICLE_NO;

CREATE SEQUENCE SEQ_TRADE_ARTICLE_NO
    START WITH 31;

BEGIN
    FOR N IN 1..15
    LOOP
        INSERT INTO 
            ARTICLE 
        VALUES
            (SEQ_ARTICLE_NO.NEXTVAL,
            1, -- PHOTO
            1, -- USER
            '공지',
            '공지글 ' || SEQ_ARTICLE_NO.CURRVAL , 
            '이 게시글은 영국에서 시작해서..' ||  SEQ_ARTICLE_NO.CURRVAL, 
            DEFAULT, 
            'Y', 
            DEFAULT, 
            NULL, 
            NULL);
    END LOOP;


    FOR N IN 1..15
    LOOP
        INSERT INTO 
            ARTICLE 
        VALUES
            (SEQ_ARTICLE_NO.NEXTVAL,
            1, -- PHOTO
            2, -- USER
            '문의',
            '문의글 ' || SEQ_ARTICLE_NO.CURRVAL , 
            '이 게시글은 영국에서 시작해서..' ||  SEQ_ARTICLE_NO.CURRVAL, 
            DEFAULT, 
            'Y', 
            SYSDATE, 
            NULL, 
            NULL);
    END LOOP;

    FOR N IN 1..30
    LOOP
        INSERT INTO 
            ARTICLE 
        VALUES
            (SEQ_ARTICLE_NO.NEXTVAL,
            1, -- PHOTO
            3, -- USER
            '거래',
            '거래글 ' || SEQ_ARTICLE_NO.CURRVAL , 
            '이 게시글은 영국에서 시작해서..' ||  SEQ_ARTICLE_NO.CURRVAL, 
            DEFAULT, 
            'Y', 
            SYSDATE, 
            NULL, 
            NULL);
    END LOOP;

    FOR N IN 1..30
    LOOP
        INSERT INTO 
            ARTICLE 
        VALUES
            (SEQ_ARTICLE_NO.NEXTVAL,
            1, -- PHOTO
            4, -- USER
            '거래',
            '거래글 ' || SEQ_ARTICLE_NO.CURRVAL , 
            '이 게시글은 영국에서 시작해서..' ||  SEQ_ARTICLE_NO.CURRVAL, 
            DEFAULT, 
            'Y', 
            SYSDATE, 
            NULL, 
            NULL);
    END LOOP;
    
     FOR N IN 1..30
    LOOP
        INSERT INTO 
            ARTICLE 
        VALUES
            (SEQ_ARTICLE_NO.NEXTVAL,
            1, -- PHOTO
            4, -- USER
            '자유',
            '거래글 ' || SEQ_ARTICLE_NO.CURRVAL , 
            '이 게시글은 영국에서 시작해서..' ||  SEQ_ARTICLE_NO.CURRVAL, 
            DEFAULT, 
            'Y', 
            SYSDATE, 
            NULL, 
            NULL);
    END LOOP;
    
    
         FOR N IN 1..5
    LOOP
        INSERT INTO 
            ARTICLE 
        VALUES
            (SEQ_ARTICLE_NO.NEXTVAL,
            1, -- PHOTO
            4, -- USER
            '자유',
            '거래글 ' || SEQ_ARTICLE_NO.CURRVAL , 
            '이 게시글은 영국에서 시작해서..' ||  SEQ_ARTICLE_NO.CURRVAL, 
            DEFAULT, 
            'N', 
            SYSDATE, 
            NULL, 
            NULL);
    END LOOP;
    
    -- 거래게시글 추가 입력
    
         FOR N IN 1..50
    LOOP
        INSERT INTO 
            TRADE_ARTICLE 
        VALUES
            (SEQ_TRADE_ARTICLE_NO.NEXTVAL,
            5, -- CLOTH_NO
            150000, -- PRICE
            '옷정보 ' || SEQ_TRADE_ARTICLE_NO.CURRVAL,
            DEFAULT , 
            DEFAULT ,
            '직거래',
            '서울');
    END LOOP;
    
    
    
COMMIT;
   
EXCEPTION
    WHEN OTHERS THEN ROLLBACK;
END;






