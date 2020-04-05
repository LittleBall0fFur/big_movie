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
