library(ggplot2);

source('./bmdb.R');

data <- executeQuery("
    SELECT
        COUNT(title$principals.person_id) AS total_actors,
        title$rating.average_rating
    FROM
        title$principals INNER JOIN title$rating ON title$principals.title_id = title$rating.title_id
    WHERE
        title$principals.category LIKE 'act%'
    GROUP BY
        title$principals.title_id, title$rating.average_rating
    ORDER BY
        random()
    LIMIT
        5000;
");

data$total_actors <- as.numeric(data$total_actors);

ggplot(data, aes(x=total_actors, y=average_rating)) + geom_point();
