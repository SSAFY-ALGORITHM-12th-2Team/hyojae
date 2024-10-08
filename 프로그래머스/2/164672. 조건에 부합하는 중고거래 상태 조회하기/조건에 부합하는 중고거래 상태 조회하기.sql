-- 코드를 입력하세요
SELECT BOARD_ID, WRITER_ID, TITLE, PRICE, 
    CASE 
        WHEN STATUS = "DONE" THEN "거래완료"
        WHEN STATUS = "SALE" THEN "판매중"
        ELSE "예약중"
    END STATUS
FROM USED_GOODS_BOARD
WHERE DATEDIFF(CREATED_DATE, "2022-10-05") = 0
ORDER BY BOARD_ID DESC;