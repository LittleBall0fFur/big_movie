-- Question 6

SELECT 
    COUNT(episode.episode_number) AS total_number_episodes,
    title$rating.average_rating
FROM
    episode INNER JOIN title$rating ON episode.parent_id = title$rating.title_id
GROUP BY
    episode.parent_id, title$rating.average_rating;
