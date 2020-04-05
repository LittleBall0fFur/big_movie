library(ggplot2);

source('./bmdb.R');

data <- executeQuery("
    SELECT
        COUNT(episode.episode_number) AS total_episodes,
        title$rating.average_rating
    FROM
        episode INNER JOIN title$rating ON episode.parent_id = title$rating.title_id
    GROUP BY
        episode.parent_id, title$rating.average_rating
    ORDER BY
        random()
    LIMIT
        5000;
");

data$total_episodes <- as.numeric(data$total_episodes);

png(filename='./plots/question_5.png');
ggplot(data, aes(x=total_episodes, y=average_rating)) + geom_point();
dev.off();
