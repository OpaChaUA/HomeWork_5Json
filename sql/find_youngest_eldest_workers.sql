SELECT 'YOUNGEST' AS TYPE, name, birthday
  FROM worker
  WHERE birthday IN(
    SELECT MAX(birthday)
    FROM worker
)
  UNION
    SELECT 'OLD' AS TYPE, name, birthday
    FROM worker
    WHERE birthday IN(
      SELECT MIN(birthday)
      FROM worker
);
