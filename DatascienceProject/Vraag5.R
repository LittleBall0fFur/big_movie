library(ggplot2) #library for plot

DataVraag5 <- read.csv(file.choose()) #read file

colnames(DataVraag5) <- c("type", "start_year", "genre", "average_rating") #set column names

DataVraag5$type <- NULL #verwijderd de eerste kolom (is mischien niet meer nodig sinds we een andere query hebben)

ggplot(DataVraag5, aes(x=start_year, y=average_rating, colour = genre)) + geom_line() + 
    scale_x_continuous(breaks = c(1880,1900,1920,1940,1960,1980, 2000, 2020)) + 
    scale_y_continuous(breaks = c(1, 2, 3, 4, 5, 6, 7, 8,9,10))


