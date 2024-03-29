SELECT CATEGORY, SUM(TOTAL_SALES) AS TOTAL_SALES
FROM BOOK, (SELECT BOOK_ID, SUM(SALES) AS TOTAL_SALES
            FROM BOOK_SALES
            WHERE SALES_DATE LIKE "2022-01%"
            GROUP BY BOOK_ID) AS SALES
WHERE BOOK.BOOK_ID = SALES.BOOK_ID
GROUP BY CATEGORY
ORDER BY CATEGORY