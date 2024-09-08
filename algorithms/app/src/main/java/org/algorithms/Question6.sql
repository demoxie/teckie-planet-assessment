#LEFT JOIN: Includes all records from the left table (games), even if there's no match in the right table (city).
# Missing matches in the right table are filled with NULL.
SELECT g.yr, g.city, c.country
FROM games g
         LEFT JOIN city c ON g.city = c.name;


#RIGHT JOIN: Includes all records from the right table (city), even if there's no match in the left table (games).
# Missing matches in the left table are filled with NULL.

SELECT g.yr, g.city, c.country
FROM games g
         RIGHT JOIN city c ON g.city = c.name;



