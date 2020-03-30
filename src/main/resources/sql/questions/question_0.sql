-- Question #1

-- Woody Allen as director
SELECT 
    COUNT(Title.primary_title) AS Total_series 
FROM 
    Title, 
    Person, 
    Person$titles, 
    Title$directors 
WHERE
    Person.name = 'Woody Allen'             AND
    (Person$titles.title_id = Title.id)     AND
    (Person$titles.person_id = Person.id)   AND
    (Title$directors.title_id = Title.id)   AND
    (Title$directors.person_id = Person.id);

-- Woody Allen as actor
SELECT 
    COUNT(Title.primary_title) AS Total_series 
FROM 
    Title,
    Person,
    Person$titles 
WHERE
    Person.name = 'Woody Allen'         AND
    Person.professions LIKE '%act%'     AND
    (Person$titles.title_id = Title.id) AND
    (Person$titles.person_id = Person.id);

-- Woody Allen all
SELECT
    COUNT(Title.primary_title) AS Total_series 
FROM 
    Title, 
    Person, 
    Person$titles 
WHERE
    Person.name = 'Woody Allen'         AND
    (Person$titles.title_id = Title.id) AND
    (Person$titles.person_id = Person.id);
