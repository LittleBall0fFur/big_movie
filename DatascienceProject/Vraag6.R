DataVraag6 <- read.csv(file.choose()) #read file

colnames(DataVraag6) <- c("total_episodes", "Popularity") #set column names

plot(DataVraag6$total_episodes, DataVraag6$Popularity, xlab = "Totaal aantal episodes", ylab = "Populariteit") #scatterplot

cor(DataVraag6$total_episodes, DataVraag6$Popularity) #correlatie
