SELECT
    COUNT(*)
FROM
    ARTICLE
WHERE
    TITLE LIKE '%거래%';

SELECT
    *
FROM
    (
        SELECT
            ROWNUM,
            RNUM,
            NO,
            PHOTO_NO,
            USER_NO,
            ORIGINAL_FILENAME,
            RENAMED_FILENAME,
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
                    PHOTO_NO,
                    USER_NO,
                    ORIGINAL_FILENAME,
                    RENAMED_FILENAME,
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
                    TITLE LIKE '%거래%'
                ORDER BY
                    NO DESC
            )
    )
WHERE
    ROWNUM BETWEEN 1 AND 15;