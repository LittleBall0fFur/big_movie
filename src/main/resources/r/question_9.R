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

png(filename='./plots/question_9.png');
ggplot(data, aes(x=total_actors, y=average_rating)) + geom_point();
dev.off();
