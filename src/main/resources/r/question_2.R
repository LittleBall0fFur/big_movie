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

# data <- executeQuery("
# SELECT
#    title.primary_title,
#    COUNT(title$genres.genre)
# FROM
#    title LEFT JOIN episode ON title.id = episode.id
#    INNER JOIN title$genres ON title.id = title$genres.title_id
# WHERE
#    title.primary_title LIKE '%beer%' AND title.start_year BETWEEN 1990 AND date_part('year', CURRENT_DATE)
# GROUP BY
#    title$genres.genre, title.primary_title;
#
# ");