library(ggplot2);

source('./bmdb.R');

data <- executeQuery("
    SELECT
        title$genres.genre,
        title.start_year,
        AVG(title$rating.average_rating) AS average_rating
    FROM
        title INNER JOIN title$genres ON title.id = title$genres.title_id
              INNER JOIN title$rating ON title.id = title$rating.title_id
    GROUP BY
        title$genres.genre,
        title.start_year;
");

png(filename='./plots/question_4.png');
ggplot(data, aes(x=start_year, y=average_rating, colour = genre)) + geom_line();
dev.off();
