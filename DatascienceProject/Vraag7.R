install.packages(dplyr) #library for filter fcuntie (used it for debugging so can erase if you want)
library(dplyr)

DataVraag7 <- read.csv(file.choose()) #read file
head(DataVraag7)

colnames(DataVraag7) <- c("director_id", "avg_rating", "birth_year", "deadth_year") #set column names

DataVraag7 <- DataVraag7[complete.cases(DataVraag7),] #clears NA rows (rows where dead and birth year are missing)

DataVraag7$age= DataVraag7$deadth_year - DataVraag7$birth_year #add age column to DataVraag7

filter(DataVraag7, age < 0) #checks if age i below 0 (used for debugging can erase if you want)

DataVraag7<-DataVraag7[!(DataVraag7$deadth_year < DataVraag7$birth_year),] #erase columns where death_year < birth_year

plot(DataVraag7$avg_rating, DataVraag7$age, xlab = "gemiddelde rating", ylab = "leeftijd") #scatterplot

cor(DataVraag7$avg_rating, DataVraag7$age) #correlatie 

