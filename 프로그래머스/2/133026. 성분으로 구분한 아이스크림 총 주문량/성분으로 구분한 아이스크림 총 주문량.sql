-- 코드를 입력하세요
SELECT INGREDIENT_TYPE, SUM(TOTAL_ORDER) TOTAL_ORDER
FROM FIRST_HALF NATURAL JOIN ICECREAM_INFO
GROUP BY INGREDIENT_TYPE