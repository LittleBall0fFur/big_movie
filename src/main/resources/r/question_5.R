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
