SELECT client.name, count(project.id) AS PROJECT_COUNT
FROM client
JOIN project ON client.id=project.client_id
  GROUP BY client_id
  HAVING count(project.id) IN (
    SELECT count(id)
    FROM project
    GROUP BY client_id
    ORDER BY count(id) DESC
    LIMIT 1
  );
