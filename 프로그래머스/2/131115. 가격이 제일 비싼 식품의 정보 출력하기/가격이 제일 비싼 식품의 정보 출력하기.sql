-- 코드를 입력하세요
SELECT PRODUCT_ID, PRODUCT_NAME, PRODUCT_CD, CATEGORY, PRICE
FROM FOOD_PRODUCT ORIGIN JOIN
    (SELECT MAX(PRICE) MAX_PRICE 
     FROM FOOD_PRODUCT) MAX_TABLE
    ON ORIGIN.PRICE = MAX_TABLE.MAX_PRICE;
