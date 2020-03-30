-- Question #7

SELECT
    title$directors.person_id,
    title$rating.average_rating,
    person.birth_year,
    person.death_year
FROM
    title$rating INNER JOIN title ON title$rating.title_id = title.id
    INNER JOIN title$directors ON title.id = title$directors.title_id
    INNER JOIN person ON title$directors.person_id = person.id;
