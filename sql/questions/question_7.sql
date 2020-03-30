-- Question #8

SELECT
    title.start_year,
    COUNT(title$genres.genre) AS total_in_genre
FROM
    title INNER JOIN title$genres ON title.id = title$genres.title_id
WHERE
    title.start_year BETWEEN 1990 AND date_part('year', CURRENT_DATE)
GROUP BY
    title.start_year,
    title$genres.genre
ORDER BY total_in_genre DESC
LIMIT 1;
