SELECT c.country
FROM games g
         JOIN cities c ON g.city = c.name;
