SELECT RNUM, NO, USER_NO, TYPE, TITLE, CONTENT, READCOUNT, VISABLE, POST_DATE, EDITED, EDIT_DATE
FROM
    (SELECT 
        ROWNUM AS RNUM,
        NO, 
        USER_NO, 
        TYPE, 
        TITLE, 
        CONTENT, 
        READCOUNT, 
        VISABLE, 
        POST_DATE, 
        EDITED, 
        EDIT_DATE
    FROM
        ARTICLE
    WHERE
        TYPE IN ('거래')
    ORDER BY
        NO DESC)
WHERE RNUM BETWEEN 1 AND 1;