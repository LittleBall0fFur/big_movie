library(ggplot2);

# Include 'bmdb.R' - BEGIN
library(DBI);

executeQuery <- function (query) {
  connection <- DBI::dbConnect(odbc::odbc(),
                               driver = 'PostgreSQL ANSI(x64)',
                               server = '84.86.32.123',
                               port   =  5432,
                               uid    = 'bmdb',
                               pwd    = 'B1g_M0v13_D4t4b4s3_P455w0rd_F0r_5ch00l');

  result <- DBI::dbSendQuery(connection, query);
  data <- DBI::dbFetch(result);
  DBI::dbClearResult(result);

  DBI::dbDisconnect(connection);

  return(data);
};
# Include 'bmdb.R' - END

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
