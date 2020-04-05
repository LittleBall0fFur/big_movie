package com.nhlstenden.bmdb;

import com.nhlstenden.bmdb.database.DatabaseConnection;
import com.nhlstenden.bmdb.gui.SceneFactory;
import com.nhlstenden.bmdb.gui.SceneManager;
import com.nhlstenden.bmdb.observerpattern.MyTopic;
import com.nhlstenden.bmdb.observerpattern.MyTopicSubscriber;
import com.nhlstenden.bmdb.observerpattern.Observer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main extends Application {

    public static MyTopic topic = new MyTopic();
    public static String[] facts = new String[10];

    public static void main(String args[]) {
        System.out.println("Start application!");
        DatabaseConnection.getInstance().connect();
        if(DatabaseConnection.getInstance().isConnected())
            System.out.println("[DATABASE] Connection true");
        else
            System.out.println("[DATABASE] Connection false");

        //Map<String, String[]> list = DatabaseConnection.getInstance().query("");

        launch(args);
    }

    @Override
    public void start(Stage _stage) throws IOException, URISyntaxException {
        SceneManager.getInstance().init(_stage, "Windows Title", 800, 600, false);

        Scene sc1 = SceneFactory.createTempScene("Welcome!");

        /*Scene scq0 = SceneFactory.createQuestionScreen("Question Screen", "question_0");
        Scene scq1 = SceneFactory.createQuestionScreen("Question Screen", "question_1");
        Scene scq2 = SceneFactory.createQuestionScreen("Question Screen", "question_2");
        Scene scq3 = SceneFactory.createQuestionScreen("Question Screen", "question_3");
        Scene scq4 = SceneFactory.createQuestionScreen("Question Screen", "question_4");
        Scene scq5 = SceneFactory.createQuestionScreen("Question Screen", "question_5");
        Scene scq6 = SceneFactory.createQuestionScreen("Question Screen", "question_6");
        Scene scq7 = SceneFactory.createQuestionScreen("Question Screen", "question_7");
        Scene scq8 = SceneFactory.createQuestionScreen("Question Screen", "question_8");
        Scene scq9 = SceneFactory.createQuestionScreen("Question Screen", "question_9");*/

        Scene sc2 = SceneFactory.createTempScene("End");

        SceneManager.getInstance().addScenes(sc1, /*scq0, scq1, scq2, scq3, scq4, scq5, scq6, scq7, scq8, scq9,*/ sc2);
        SceneManager.getInstance().show();

        // create observer
        Observer obj = new MyTopicSubscriber("obj1");

        // register observer to the subject
        topic.register(obj);

        // attach observer to subject
        obj.setSubject(topic);
    }

    @Override
    public void stop() throws Exception {
        DatabaseConnection.getInstance().disconnect();
        System.out.println("Exit application!");
        super.stop();
    }
}
