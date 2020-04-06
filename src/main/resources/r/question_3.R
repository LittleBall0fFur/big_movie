library(ROCR) #library for ROC curve

#librarys for cart tree
library(rpart)
library(rpart.plot)

library(caTools) #library for data spliting

library(e1071) #library for cart tree
library(caret)

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
      title.start_year,
      title.end_year,
      title.runtime,
      title.is_adult,
      title$genres.genre,
      title$rating.average_rating,
      title$akas.region,
      title$akas.language
  FROM
      title$rating INNER JOIN title ON title$rating.title_id = title.id
      INNER JOIN title$genres ON title.id = title$genres.title_id
      INNER JOIN title$akas ON title$genres.title_id = title$akas.title_id;
");


#DataVraag4 <- read.csv(file.choose()) #read file

colnames(data()) <- c("start_year", "end_year", "runtime", "is_adult", "genre", "avg_rating", "region", "language") #set column names

data$end_year <- NULL #end_year is for al records NULL/NA so the colom gets removed
data$language <- NULL

data <- data[complete.cases(data),] #clears NA rows 

data$avg_rating <- as.factor(data$avg_rating >= 6) #make avg_rating a factor with everything above or equal to 6 TRUE



#split data
set.seed(123)
split <- sample.split(data$genre, SplitRatio = 0.7)
TrainDataVraag4 <- subset(data, split==TRUE)
TestDataVraag4 <- subset(data, split==FALSE)

#cross validation
fitControl <- trainControl(method="cv", number= 10)
cartGrid <- expand.grid(.cp=(1:50) * 0.01)
CVData <- train(avg_rating ~ runtime, data = TrainDataVraag4, method="rpart", trControl = fitControl, tuneGrid=cartGrid)
CVData

#Makes a cart tree with cp = 0.24
png(filename='./plots/question_3.png');
tree <- rpart(avg_rating~., data=TrainDataVraag4, method="class", control=rpart.control(cp=0.24))
dev.off();

prp(tree)

#Build confusionMatrix using test data
TreeCPPredict <- predict(tree, newdata = TestDataVraag4, type = "class")
confusionMatrix <- table(TestDataVraag4$avg_rating, TreeCPPredict)

confusionMatrix

#calculates specificity
specificityTree <- confusionMatrix[1,1]/(confusionMatrix[1,1] + confusionMatrix[1,2])

#caculates sensitivity
sensitivityTree <- confusionMatrix[2,2]/(confusionMatrix[2,2] + confusionMatrix[2,1])

#caculates accuracy
accuracyTree <- (confusionMatrix[1,1] + confusionMatrix[2,2])/ (confusionMatrix[1,1] + confusionMatrix[1,2] + confusionMatrix[2,1] + confusionMatrix[2,2])

specificityTree
sensitivityTree
accuracyTree


#build ROC curve
PredictRocTree<- predict(tree, newdata = TestDataVraag4)
ROCTree <- prediction(PredictRocTree[,2], TestDataVraag4$avg_rating)
ROCPerformanceTree <- performance(ROCTree, "tpr", "fpr")

png(filename='./plots/question_3ROC.png');
plot(ROCPerformanceTree, colorize=TRUE)
abline(0,1)
dev.off();

#caculates AUC
AUCTree <- as.numeric(performance(ROCTree, "auc") @y.values)
AUCTree




