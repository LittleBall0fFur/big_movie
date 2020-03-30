-- Question #4

SELECT
    title.start_year,
    title.end_year,
    title.runtime,
    title.is_adult,
    title$genres.genre,
    title$rating.average_rating,
    title$akas.region,
    title$akas.language
FROM
    title$rating INNER JOIN title ON title$rating.title_id = title.id
    INNER JOIN title$genres ON title.id = title$genres.title_id
    INNER JOIN title$akas ON title$genres.title_id = title$akas.title_id;
