library(ggplot2) #library for plot

DataVraag9 <- read.csv(file.choose()) #read file

colnames(DataVraag9) <- c("Genre", "avg_seasons_genre") #set column names

ggplot(DataVraag9, aes(x=Genre, y=avg_seasons_genre, fill=Genre)) + geom_bar(stat= "identity") #maakt bar plot
