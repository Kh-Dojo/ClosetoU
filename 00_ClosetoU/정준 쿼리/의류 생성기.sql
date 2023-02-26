CREATE SEQUENCE SEQ_CLOTH_NO;

BEGIN
    FOR N IN 4..52
    LOOP
        INSERT INTO 
            CLOTH 
        VALUES
            (SEQ_CLOTH_NO.NEXTVAL, 
            'CL' || SEQ_CLOTH_NO.CURRVAL, -- 있는 MEMBERNO으로
            'PH' || SEQ_CLOTH_NO.CURRVAL,
            '의류명 ' || SEQ_CLOTH_NO.CURRVAL , 
            DEFAULT,
            NULL );
    END LOOP;

    COMMIT;
EXCEPTION
    WHEN OTHERS THEN ROLLBACK;
END;

