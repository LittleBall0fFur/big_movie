//Question 1

//woody allen as director
SELECT 
  COUNT(Title.primary_title) AS Total_series 
FROM 
  Title, 
  Person, 
  Person$titles, 
  Title$directors 
WHERE Person.name = 'Woody Allen' AND (Person$titles.title_id = Title.id) AND (Person$titles.person_id = Person.id) AND (Title$directors.title_id = Title.id) AND (Title$directors.person_id = Person.id);

//woody allen as actor
SELECT 
  COUNT(Title.primary_title) AS Total_series 
FROM 
  Title,
  Person,
  Person$titles 
WHERE Person.name = 'Woody Allen' AND Person.professions LIKE '%act%' AND (Person$titles.title_id = Title.id) AND (Person$titles.person_id = Person.id);

//woody allen all
SELECT
  COUNT(Title.primary_title) AS Total_series 
FROM 
  Title, 
  Person, 
  Person$titles 
WHERE Person.name = 'Woody Allen' AND (Person$titles.title_id = Title.id) AND (Person$titles.person_id = Person.id); 

//Question 2

SELECT
  le.person_id,
  re.person_id
FROM
  title$principals AS le INNER JOIN title$principals AS re ON le.title_id = re.title_id
WHERE
  le.job LIKE '%act%' AND re.job LIKE '%act%' AND le.person_id <> re.person_id;
  
//Question 3

SELECT
  title.primary_title,
  COUNT(title$genres.genre)
FROM
  title LEFT JOIN episode ON title.id = episode.id
  INNER JOIN title$genres ON title.id = title$genres.title_id
WHERE title.primary_title LIKE '%beer%' AND title.start_year BETWEEN 1990 AND date_part('year', CURRENT_DATE)
GROUP BY
    title$genres.genre, title.primary_title;
  
//Question 4

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
  INNER JOIN title$akas ON title$genres.title_id = title$akas.title_id

//Question 5

SELECT 
  title.type,
  title.start_year,
  title$genres.genre,
  title$rating.average_rating
FROM
  title LEFT JOIN episode ON title.id = episode.id
  INNER JOIN title$genres ON title.id = title$genres.title_id
  INNER JOIN title$rating ON title.id = title$rating.title_id

//Question 6

SELECT 
    COUNT(episode.episode_number) AS total_number_episodes,
    title$rating.average_rating
FROM
  episode INNER JOIN title$rating ON episode.parent_id = title$rating.title_id
GROUP BY
    episode.parent_id, title$rating.average_rating;
  
//Question 7

SELECT
  title$directors.person_id,
  title$rating.average_rating,
  person.birth_year,
  person.death_year
FROM
  title$rating INNER JOIN title ON title$rating.title_id = title.id
  INNER JOIN title$directors ON title.id = title$directors.title_id
  INNER JOIN person ON title$directors.person_id = person.id;

//Question 8

SELECT
  title.start_year,
  COUNT(title$genres.genre) AS total_in_genre
FROM
  title INNER JOIN title$genres ON title.id = title$genres.title_id
WHERE title.start_year BETWEEN 1990 AND date_part('year', CURRENT_DATE)
GROUP BY
    title.start_year,
    title$genres.genre
ORDER BY total_in_genre DESC
LIMIT 1;

//Question 9

SELECT
  title$genres.genre,
  AVG(episode.season_number) AS avg_seasons_genre
FROM
  episode INNER JOIN title$genres ON episode.parent_id = title$genres.title_id
GROUP BY
    title$genres.genre
ORDER BY avg_seasons_genre DESC;

//Question 10

SELECT 
  Title.primary_title, 
  Title$rating.average_rating,
  COUNT(Person.id) AS Total_actors 
FROM 
  Title, 
  Person, 
  Person$titles, 
  Title$rating 
WHERE Person.professions LIKE '%act%' AND (Person$titles.title_id = Title.id) AND (Person$titles.person_id = Person.id) AND (Title$rating.title_id = Title.id) 
GROUP BY 
  Title.primary_title, 
  Title$rating.average_rating;
