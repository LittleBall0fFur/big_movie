SELECT
  episode.season_number,
  AVG(title$genres.genre) AS avr_seasons_genre
FROM
  episode INNER JOIN title$genres ON episode.parent_id = title$genres.title_id
GROUP BY
    title$genres.genre
ORDER BY avr_seasons_genre DESC;