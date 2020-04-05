library(ggplot2);

source('./bmdb.R');

data <- executeQuery("
    SELECT
        (title.start_year - person.birth_year) AS directors_age,
        title$rating.average_rating
    FROM
        title INNER JOIN title$rating    ON title.id = title$rating.title_id
              INNER JOIN title$directors ON title.id = title$directors.title_id
              INNER JOIN person          ON title$directors.person_id = person.id
    WHERE
        title.start_year IS NOT NULL AND person.birth_year IS NOT NULL
    ORDER BY
        random()
    LIMIT
        5000;
");

png(filename='./plots/question_6.png');
ggplot(data, aes(x=directors_age, y=average_rating)) + geom_point();
dev.off();
