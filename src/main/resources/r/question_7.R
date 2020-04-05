library(ggplot2);

source('./bmdb.R');

data <- executeQuery("
    SELECT
        title$genres.genre,
        AVG(title$rating.average_rating) AS average_rating
    FROM
        title INNER JOIN title$genres ON title.id = title$genres.title_id
        INNER JOIN title$rating ON title$genres.title_id = title$rating.title_id
    WHERE
        title.start_year BETWEEN 1990 AND date_part('year', CURRENT_DATE)
    GROUP BY
        title$genres.genre
    ORDER BY
         title$genres.genre DESC;
");

png(file='./plots/question_7.png');
ggplot(data, aes(x=genre, y=average_rating, fill=genre)) + geom_bar(stat='identity');
dev.off();
