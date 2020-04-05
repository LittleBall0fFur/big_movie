library(ggplot2) #library for plot

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
    SELECT genre, ROUND(AVG(seasons), 0) AS average_seasons
    FROM (SELECT genre, MAX(season_number) AS seasons
          FROM episode INNER JOIN title$genres ON episode.parent_id = title$genres.title_id
          GROUP BY genre, episode.parent_id) AS episode$seasons
    GROUP BY genre;
");

png(filename='./plots/question_8.png', width = 480, height = 480);
ggplot(data, aes(x=genre, y=average_seasons, fill=genre)) + geom_bar(stat='identity');
dev.off();
