SELECT userId, AVG(duration) AS AverageDuration
FROM sessions
GROUP BY userId
HAVING COUNT(id) > 1;
