library(ggplot2) #library for plot

source('./bmdb.R');

data <- executeQuery("
    SELECT genre, ROUND(AVG(seasons), 0) AS average_seasons
    FROM (SELECT genre, MAX(season_number) AS seasons
          FROM episode INNER JOIN title$genres ON episode.parent_id = title$genres.title_id
          GROUP BY genre, episode.parent_id) AS episode$seasons
    GROUP BY genre;
");

png(filename='./plots/question_8.png');
ggplot(data, aes(x=genre, y=average_seasons, fill=genre)) + geom_bar(stat='identity');
dev.off();
