-- Question #9

SELECT
  title$genres.genre,
  AVG(episode.season_number) AS avg_seasons_genre
FROM
  episode INNER JOIN title$genres ON episode.parent_id = title$genres.title_id
GROUP BY
    title$genres.genre
ORDER BY avg_seasons_genre DESC;
