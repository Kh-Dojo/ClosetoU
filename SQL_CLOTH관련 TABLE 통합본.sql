--테이블 생성 & 제약조건 추가
DROP TABLE "CLOTH_PHOTO";

DROP TABLE "CATEGORY_PER_CLOTH";

DROP TABLE "CLOTH_CATEGORY";

DROP SEQUENCE SEQ_CLNO;

DROP SEQUENCE SEQ_CLPH;

CREATE TABLE "CLOTH_CATEGORY" (
    "CLOTH_CODE"     VARCHAR2(5) PRIMARY KEY NOT NULL,
    "CLOTH_CATEGORY" VARCHAR2(50) NOT NULL
);

COMMENT ON COLUMN "CLOTH_CATEGORY"."CLOTH_CODE" IS
    '분류코드';

COMMENT ON COLUMN "CLOTH_CATEGORY"."CLOTH_CATEGORY" IS
    '종류명';

CREATE TABLE "CATEGORY_PER_CLOTH" (
    "CLOTH_NO"   NUMBER NOT NULL,
    "CLOTH_CODE" VARCHAR2(5) NOT NULL
);

COMMENT ON COLUMN "CATEGORY_PER_CLOTH"."CLOTH_NO" IS
    '의류ID';

COMMENT ON COLUMN "CATEGORY_PER_CLOTH"."CLOTH_CODE" IS
    '분류코드';

ALTER TABLE CATEGORY_PER_CLOTH ADD CONSTRAINT FK_CATAPERCL_CN FOREIGN KEY ( CLOTH_NO )
    REFERENCES CLOTH;

ALTER TABLE CATEGORY_PER_CLOTH ADD CONSTRAINT FK_CATAPERCL_CC FOREIGN KEY ( CLOTH_CODE )
    REFERENCES CLOTH_CATEGORY;

CREATE TABLE "CLOTH_PHOTO" (
    "PHOTO_ID"      VARCHAR2(200) NOT NULL,
    "NO"            NUMBER NOT NULL,
    "CLOTH_NO"      NUMBER NOT NULL,
    "ORIGINAL_NAME" VARCHAR2(100) NOT NULL,
    "CREATED_DATE"  DATE DEFAULT SYSDATE NOT NULL
);

COMMENT ON COLUMN "CLOTH_PHOTO"."NO" IS
    '의류사진번호';

COMMENT ON COLUMN "CLOTH_PHOTO"."CLOTH_NO" IS
    '의류NO';

COMMENT ON COLUMN "CLOTH_PHOTO"."ORIGINAL_NAME" IS
    '파일원래이름';

COMMENT ON COLUMN "CLOTH_PHOTO"."PHOTO_ID" IS
    '파일변경이름(RENAMED)';

COMMENT ON COLUMN "CLOTH_PHOTO"."CREATED_DATE" IS
    '생성일자';

ALTER TABLE "CLOTH_PHOTO" ADD CONSTRAINT "PK_CLOTH_PHOTO" PRIMARY KEY ( "PHOTO_ID" );

CREATE SEQUENCE SEQ_CLPH;

-- 제약조건

-- 데이터 입력

-- 의류 카테고리 분류
-- 대분류  : 상의(T0) / 하의(P0) / 잡화(S0) / 악세서리(AO) 

INSERT INTO CLOTH_CATEGORY (
    CLOTH_CODE,
    CLOTH_CATEGORY
) VALUES (
    '00',
    '지정안함'
);

INSERT INTO CLOTH_CATEGORY (
    CLOTH_CODE,
    CLOTH_CATEGORY
) VALUES (
    'T0',
    '상의'
);

INSERT INTO CLOTH_CATEGORY (
    CLOTH_CODE,
    CLOTH_CATEGORY
) VALUES (
    'P0',
    '하의'
);

INSERT INTO CLOTH_CATEGORY (
    CLOTH_CODE,
    CLOTH_CATEGORY
) VALUES (
    'S0',
    '잡화'
);

INSERT INTO CLOTH_CATEGORY (
    CLOTH_CODE,
    CLOTH_CATEGORY
) VALUES (
    'A0',
    '악세서리'
);

INSERT INTO CLOTH_CATEGORY (
    CLOTH_CODE,
    CLOTH_CATEGORY
) VALUES (
    'T1',
    '아우터'
);

INSERT INTO CLOTH_CATEGORY (
    CLOTH_CODE,
    CLOTH_CATEGORY
) VALUES (
    'P1',
    '바지'
);

INSERT INTO CLOTH_CATEGORY (
    CLOTH_CODE,
    CLOTH_CATEGORY
) VALUES (
    'T2',
    '원피스'
);

INSERT INTO CLOTH_CATEGORY (
    CLOTH_CODE,
    CLOTH_CATEGORY
) VALUES (
    'P3',
    '스커트'
);

INSERT INTO CLOTH_CATEGORY (
    CLOTH_CODE,
    CLOTH_CATEGORY
) VALUES (
    'S1',
    '가방'
);

INSERT INTO CLOTH_CATEGORY (
    CLOTH_CODE,
    CLOTH_CATEGORY
) VALUES (
    'S2',
    '신발'
);

INSERT INTO CLOTH_CATEGORY (
    CLOTH_CODE,
    CLOTH_CATEGORY
) VALUES (
    'S3',
    '스니커즈'
);

INSERT INTO CLOTH_CATEGORY (
    CLOTH_CODE,
    CLOTH_CATEGORY
) VALUES (
    'A1',
    '시계'
);

INSERT INTO CLOTH_CATEGORY (
    CLOTH_CODE,
    CLOTH_CATEGORY
) VALUES (
    'S4',
    '모자'
);

INSERT INTO CLOTH_CATEGORY (
    CLOTH_CODE,
    CLOTH_CATEGORY
) VALUES (
    'S5',
    '양말/레그웨어'
);

INSERT INTO CLOTH_CATEGORY (
    CLOTH_CODE,
    CLOTH_CATEGORY
) VALUES (
    'A2',
    '안경'
);

COMMIT;

INSERT INTO CLOTH_PHOTO VALUES (
    '1_background-red-retro-model-tennis.jpg',
    SEQ_CLPH.NEXTVAL,
    1,
    '1_background-red-retro-model-tennis.jpg', DEFAULT
);

INSERT INTO CLOTH_PHOTO VALUES (
    '2_blue-backpack-wooden-table.jpg',
    SEQ_CLPH.NEXTVAL,
    1,
    '2_blue-backpack-wooden-table.jpg', DEFAULT
);

INSERT INTO CLOTH_PHOTO VALUES (
    '3_blue-visor-hat-cloth-baseball.jpg',
    SEQ_CLPH.NEXTVAL,
    3,
    '3_blue-visor-hat-cloth-baseball.jpg', DEFAULT
);

INSERT INTO CLOTH_PHOTO VALUES (
    '4_couple-ghosts-posing-brick-wall-halloween-party.jpg',
    SEQ_CLPH.NEXTVAL,
    4,
    '4_couple-ghosts-posing-brick-wall-halloween-party.jpg', DEFAULT
);

INSERT INTO CLOTH_PHOTO VALUES (
    '5_fashion-woman-with-clothes.jpg',
    SEQ_CLPH.NEXTVAL,
    5,
    '5_fashion-woman-with-clothes.jpg', DEFAULT
);

INSERT INTO CLOTH_PHOTO VALUES (
    '6_isolated-opened-black-t-shirt.jpg',
    SEQ_CLPH.NEXTVAL,
    6,
    '6_isolated-opened-black-t-shirt.jpg', DEFAULT
);

INSERT INTO CLOTH_PHOTO VALUES (
    '7_jeans.jpg',
    SEQ_CLPH.NEXTVAL,
    7,
    '7_jeans.jpg', DEFAULT
);

INSERT INTO CLOTH_PHOTO VALUES (
    '8_luxury-woman-handbag.jpg',
    SEQ_CLPH.NEXTVAL,
    8,
    '8_luxury-woman-handbag.jpg', DEFAULT
);

INSERT INTO CLOTH_PHOTO VALUES (
    '9_man-in-black-sweater-and-black-bucket-hat-youth-apparel-shoot.jpg',
    SEQ_CLPH.NEXTVAL,
    9,
    '9_man-in-black-sweater-and-black-bucket-hat-youth-apparel-shoot.jpg', DEFAULT
);

INSERT INTO CLOTH_PHOTO VALUES (
    '10_man-wearing-brown-pants-close-up.jpg',
    SEQ_CLPH.NEXTVAL,
    10,
    '10_man-wearing-brown-pants-close-up.jpg', DEFAULT
);

INSERT INTO CLOTH_PHOTO VALUES (
    '11_open-knapsack-with-stationery-watercolors.jpg',
    SEQ_CLPH.NEXTVAL,
    11,
    '11_open-knapsack-with-stationery-watercolors.jpg', DEFAULT
);

INSERT INTO CLOTH_PHOTO VALUES (
    '12_red-cap-protection-background-clothes.jpg',
    SEQ_CLPH.NEXTVAL,
    12,
    '12_red-cap-protection-background-clothes.jpg', DEFAULT
);

INSERT INTO CLOTH_PHOTO VALUES (
    '13_shirt-mockup-concept-with-plain-clothing (1).jpg',
    SEQ_CLPH.NEXTVAL,
    13,
    '13_shirt-mockup-concept-with-plain-clothing (1).jpg', DEFAULT
);

INSERT INTO CLOTH_PHOTO VALUES (
    '14_shirt-mockup-concept-with-plain-clothing.jpg',
    SEQ_CLPH.NEXTVAL,
    13,
    '14_shirt-mockup-concept-with-plain-clothing.jpg', DEFAULT
);

INSERT INTO CLOTH_PHOTO VALUES (
    '15_still-life-rendering-jackets-display.jpg',
    SEQ_CLPH.NEXTVAL,
    14,
    '15_still-life-rendering-jackets-display.jpg', DEFAULT
);

INSERT INTO CLOTH_PHOTO VALUES (
    '16_white-shirts-hanging-in-room.jpg',
    SEQ_CLPH.NEXTVAL,
    15,
    '16_white-shirts-hanging-in-room.jpg', DEFAULT
);

INSERT INTO CLOTH_PHOTO VALUES (
    '17_woman-gray-crop-top-fashion-shoot.jpg',
    SEQ_CLPH.NEXTVAL,
    16,
    '17_woman-gray-crop-top-fashion-shoot.jpg', DEFAULT
);

INSERT INTO CLOTH_PHOTO VALUES (
    '18_woman-wearing-blue-skirt.jpg',
    SEQ_CLPH.NEXTVAL,
    17,
    '18_woman-wearing-blue-skirt.jpg', DEFAULT
);

INSERT INTO CLOTH_PHOTO VALUES (
    '19_young-man-portrait (1).jpg',
    SEQ_CLPH.NEXTVAL,
    18,
    '19_young-man-portrait (1).jpg', DEFAULT
);

INSERT INTO CLOTH_PHOTO VALUES (
    '20_young-man-portrait (2).jpg',
    SEQ_CLPH.NEXTVAL,
    18,
    '19_young-man-portrait (2).jpg', DEFAULT
);

INSERT INTO CLOTH_PHOTO VALUES (
    '21_young-man-portrait.jpg',
    SEQ_CLPH.NEXTVAL,
    18,
    '21_young-man-portrait.jpg', DEFAULT
);

UPDATE CLOTH
SET
    PHOTO_ID = '1_background-red-retro-model-tennis.jpg',
    CLOTH_NAME = '붉은 컨버스',
    CREATED_DATE = SYSDATE
WHERE
    NO = 1;

DROP SEQUENCE SEQ_CLOTH_NO;

CREATE SEQUENCE SEQ_CLOTH_NO START WITH 2;

INSERT INTO CLOTH VALUES (
    SEQ_CLOTH_NO.NEXTVAL,
    '2_blue-backpack-wooden-table.jpg',
    '파란 가방', DEFAULT,
    '00'
);

INSERT INTO CLOTH VALUES (
    SEQ_CLOTH_NO.NEXTVAL,
    '3_blue-visor-hat-cloth-baseball.jpg',
    '검은 모자', DEFAULT,
    '00'
);

INSERT INTO CLOTH VALUES (
    SEQ_CLOTH_NO.NEXTVAL,
    '4_couple-ghosts-posing-brick-wall-halloween-party.jpg',
    '할로윈 코스튬', DEFAULT,
    '00'
);

INSERT INTO CLOTH VALUES (
    SEQ_CLOTH_NO.NEXTVAL,
    '5_fashion-woman-with-clothes.jpg',
    '파란 원피스', DEFAULT,
    '00'
);

INSERT INTO CLOTH VALUES (
    SEQ_CLOTH_NO.NEXTVAL,
    '6_isolated-opened-black-t-shirt.jpg',
    '검은 티셔츠', DEFAULT,
    '00'
);

INSERT INTO CLOTH VALUES (
    SEQ_CLOTH_NO.NEXTVAL,
    '7_jeans.jpg',
    '청바지', DEFAULT,
    '00'
);

INSERT INTO CLOTH VALUES (
    SEQ_CLOTH_NO.NEXTVAL,
    '8_luxury-woman-handbag.jpg',
    '분홍 핸드백', DEFAULT,
    '00'
);

INSERT INTO CLOTH VALUES (
    SEQ_CLOTH_NO.NEXTVAL,
    '9_man-in-black-sweater-and-black-bucket-hat-youth-apparel-shoot.jpg',
    '검은 맨투맨', DEFAULT,
    '00'
);

INSERT INTO CLOTH VALUES (
    SEQ_CLOTH_NO.NEXTVAL,
    '10_man-wearing-brown-pants-close-up.jpg',
    '갈색 바지', DEFAULT,
    '00'
);

INSERT INTO CLOTH VALUES (
    SEQ_CLOTH_NO.NEXTVAL,
    '11_open-knapsack-with-stationery-watercolors.jpg',
    '파란 가방', DEFAULT,
    '00'
);

INSERT INTO CLOTH VALUES (
    SEQ_CLOTH_NO.NEXTVAL,
    '12_red-cap-protection-background-clothes.jpg',
    '검은 모자', DEFAULT,
    '00'
);

INSERT INTO CLOTH VALUES (
    SEQ_CLOTH_NO.NEXTVAL,
    '13_shirt-mockup-concept-with-plain-clothing (1).jpg',
    '파란 가방', DEFAULT,
    '00'
);

INSERT INTO CLOTH VALUES (
    SEQ_CLOTH_NO.NEXTVAL,
    '15_still-life-rendering-jackets-display.jpg',
    '파란 야구잠바', DEFAULT,
    '00'
);

INSERT INTO CLOTH VALUES (
    SEQ_CLOTH_NO.NEXTVAL,
    '16_white-shirts-hanging-in-room.jpg',
    '하얀 셔츠', DEFAULT,
    '00'
);

INSERT INTO CLOTH VALUES (
    SEQ_CLOTH_NO.NEXTVAL,
    '17_woman-gray-crop-top-fashion-shoot.jpg',
    '회색 크롭티', DEFAULT,
    '00'
);

INSERT INTO CLOTH VALUES (
    SEQ_CLOTH_NO.NEXTVAL,
    '18_woman-wearing-blue-skirt.jpg',
    '하늘색 치마', DEFAULT,
    '00'
);

INSERT INTO CLOTH VALUES (
    SEQ_CLOTH_NO.NEXTVAL,
    '19_young-man-portrait (1).jpg',
    '베이지 코트', DEFAULT,
    '00'
);

INSERT INTO CATEGORY_PER_CLOTH VALUES (
    1,
    'S0'
);

INSERT INTO CATEGORY_PER_CLOTH VALUES (
    1,
    'S2'
);

INSERT INTO CATEGORY_PER_CLOTH VALUES (
    1,
    'S3'
);

INSERT INTO CATEGORY_PER_CLOTH VALUES (
    2,
    'S0'
);

INSERT INTO CATEGORY_PER_CLOTH VALUES (
    2,
    'S1'
);

INSERT INTO CATEGORY_PER_CLOTH VALUES (
    3,
    'S0'
);

INSERT INTO CATEGORY_PER_CLOTH VALUES (
    3,
    'S4'
);

INSERT INTO CATEGORY_PER_CLOTH VALUES (
    4,
    'S0'
);

INSERT INTO CATEGORY_PER_CLOTH VALUES (
    4,
    'T1'
);

INSERT INTO CATEGORY_PER_CLOTH VALUES (
    5,
    'T0'
);

INSERT INTO CATEGORY_PER_CLOTH VALUES (
    5,
    'T2'
);

INSERT INTO CATEGORY_PER_CLOTH VALUES (
    6,
    'T0'
);

INSERT INTO CATEGORY_PER_CLOTH VALUES (
    7,
    'P0'
);

INSERT INTO CATEGORY_PER_CLOTH VALUES (
    7,
    'P1'
);

INSERT INTO CATEGORY_PER_CLOTH VALUES (
    8,
    'S0'
);

INSERT INTO CATEGORY_PER_CLOTH VALUES (
    8,
    'S1'
);

INSERT INTO CATEGORY_PER_CLOTH VALUES (
    9,
    'T0'
);

INSERT INTO CATEGORY_PER_CLOTH VALUES (
    10,
    'P0'
);

INSERT INTO CATEGORY_PER_CLOTH VALUES (
    11,
    'S0'
);

INSERT INTO CATEGORY_PER_CLOTH VALUES (
    12,
    'S0'
);

INSERT INTO CATEGORY_PER_CLOTH VALUES (
    13,
    'T0'
);

INSERT INTO CATEGORY_PER_CLOTH VALUES (
    14,
    'T0'
);

INSERT INTO CATEGORY_PER_CLOTH VALUES (
    15,
    'T0'
);

INSERT INTO CATEGORY_PER_CLOTH VALUES (
    16,
    'T0'
);

INSERT INTO CATEGORY_PER_CLOTH VALUES (
    17,
    'P0'
);

INSERT INTO CATEGORY_PER_CLOTH VALUES (
    17,
    'P3'
);

INSERT INTO CATEGORY_PER_CLOTH VALUES (
    18,
    'T0'
);

INSERT INTO CATEGORY_PER_CLOTH VALUES (
    18,
    'T1'
);

COMMIT;