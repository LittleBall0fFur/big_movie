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

#data <- executeQuery("
#    SELECT
#        le.person_id,
#        re.person_id
#    FROM
#        title$principals AS le INNER JOIN title$principals AS re ON le.title_id = re.title_id
#    WHERE
#        le.job LIKE '%act%' AND re.job LIKE '%act%' AND le.person_id <> re.person_id;
#");