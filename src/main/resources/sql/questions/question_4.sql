-- Question #5

SELECT 
    title.type,
    title.start_year,
    title$genres.genre,
    title$rating.average_rating
FROM
    title LEFT JOIN episode ON title.id = episode.id
    INNER JOIN title$genres ON title.id = title$genres.title_id
    INNER JOIN title$rating ON title.id = title$rating.title_id;
