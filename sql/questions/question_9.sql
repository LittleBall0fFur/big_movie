-- Question #10

SELECT 
    Title.primary_title, 
    Title$rating.average_rating,
    COUNT(Person.id) AS Total_actors 
FROM 
    Title, 
    Person, 
    Person$titles,
    Title$rating 
WHERE
    Person.professions LIKE '%act%'         AND
    (Person$titles.title_id = Title.id)     AND
    (Person$titles.person_id = Person.id)   AND
    (Title$rating.title_id = Title.id) 
GROUP BY 
    Title.primary_title, 
    Title$rating.average_rating;
