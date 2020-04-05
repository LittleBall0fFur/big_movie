-- Question #5

SELECT
    title$genres.genre,
    title.start_year,
    AVG(title$rating.average_rating) AS average_rating
FROM
    title INNER JOIN title$genres ON title.id = title$genres.title_id
          INNER JOIN title$rating ON title.id = title$rating.title_id
GROUP BY
    title$genres.genre,
    title.start_year;
