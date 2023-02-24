SELECT
    *
FROM
    (
        SELECT
            ROWNUM AS RNUM,
            NO,
            USER_NO,
            TYPE,
            TITLE,
            CONTENT,
            READ_COUNT,
            VISABLE,
            POST_DATE,
            EDITED,
            EDIT_DATE
        FROM
            (
                SELECT
                    ROWNUM AS RNUM,
                    NO,
                    USER_NO,
                    TYPE,
                    TITLE,
                    CONTENT,
                    READ_COUNT,
                    VISABLE,
                    POST_DATE,
                    EDITED,
                    EDIT_DATE
                FROM
                    ARTICLE
                WHERE
                    TYPE IN ( '거래' )
                ORDER BY
                    NO DESC
            )
    )
WHERE RNUM BETWEEN ? AND ?;