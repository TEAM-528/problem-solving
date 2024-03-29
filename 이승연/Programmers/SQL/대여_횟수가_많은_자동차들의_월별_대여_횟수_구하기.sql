SELECT MONTH(START_DATE) AS MONTH, TOTAL_RENTAL.CAR_ID, COUNT(HISTORY_ID) AS RECORDS
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY,
    (SELECT CAR_ID, COUNT(HISTORY_ID) AS TOTAL_RENTAL
    FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
    WHERE YEAR(START_DATE) = 2022 AND MONTH(START_DATE) >= 8 AND MONTH(START_DATE) <= 10
    GROUP BY CAR_ID
    HAVING TOTAL_RENTAL >= 5) AS TOTAL_RENTAL
WHERE CAR_RENTAL_COMPANY_RENTAL_HISTORY.CAR_ID = TOTAL_RENTAL.CAR_ID AND MONTH(START_DATE) >= 8 AND MONTH(START_DATE) <= 10
GROUP BY MONTH, CAR_ID
HAVING RECORDS != 0 
ORDER BY MONTH, CAR_ID DESC