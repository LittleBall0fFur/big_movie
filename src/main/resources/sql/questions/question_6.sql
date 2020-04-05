-- Question #7

SELECT
    (title.start_year - person.birth_year) AS directors_age,
    title$rating.average_rating,
FROM
    title INNER JOIN title$rating    ON title.id = title$rating.title_id
          INNER JOIN title$directors ON title.id = title$directors.title_id
          INNER JOIN person          ON title$directors.person_id = person.id
ORDER BY
    random()
LIMIT
    5000;
