DataVraag10 <- read.csv(file.choose()) #read file

colnames(DataVraag10) <- c("Title", "Rating", "Total_Actors") #set column names

plot(DataVraag10)

plot(DataVraag10$Total_Actors, DataVraag10$Rating)

cor(DataVraag10$Total_Actors, DataVraag10$Rating)
