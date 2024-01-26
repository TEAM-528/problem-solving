SELECT NAME, COUNT(ANIMAL_ID) AS COUNT
FROM ANIMAL_INS
WHERE NAME IS NOT NULL
GROUP BY NAME
HAVING COUNT(ANIMAL_ID) > 1
ORDER BY NAME