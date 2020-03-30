-- Question #3

SELECT
    title.primary_title,
    COUNT(title$genres.genre)
FROM
    title LEFT JOIN episode ON title.id = episode.id
    INNER JOIN title$genres ON title.id = title$genres.title_id
WHERE
    title.primary_title LIKE '%beer%' AND title.start_year BETWEEN 1990 AND date_part('year', CURRENT_DATE)
GROUP BY
    title$genres.genre, title.primary_title;

