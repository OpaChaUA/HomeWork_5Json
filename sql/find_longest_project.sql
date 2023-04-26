SELECT name, DATEDIFF(MONTH, start_date, finish_date)  AS month_count
  FROM project
  GROUP BY name
  HAVING DATEDIFF(MONTH, start_date, finish_date) IN(
    SELECT DATEDIFF(MONTH, start_date, finish_date)
    FROM project
    GROUP BY name
    ORDER BY DATEDIFF(MONTH, start_date, finish_date) DESC
    LIMIT 1
);
