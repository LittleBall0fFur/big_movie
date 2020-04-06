package com.nhlstenden.bmdb;

import com.nhlstenden.bmdb.database.DatabaseConnection;
import com.nhlstenden.bmdb.gui.GuiFactory;
import com.nhlstenden.bmdb.gui.SceneFactory;
import com.nhlstenden.bmdb.gui.SceneManager;
import com.nhlstenden.bmdb.observerpattern.MyTopic;
import com.nhlstenden.bmdb.observerpattern.MyTopicSubscriber;
import com.nhlstenden.bmdb.observerpattern.Observer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends Application {

    public static MyTopic topic = new MyTopic();
    private Timer tm = new Timer();;

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
        // create observer
        Observer obj = new MyTopicSubscriber("obj1");

        // register observer to the subject
        topic.register(obj);

        // attach observer to subject
        obj.setSubject(topic);

        // create progress bar
        ProgressIndicator progressIndicator = GuiFactory.createProgressIndicator(0.0f, 100, 100);

        TimerTask task = new TimerTask() {
            @Override
            public void run()  {
                try{
                    Scene sc1 = SceneFactory.createTempScene("Welcome!");

                    Scene scq0 = SceneFactory.createQuestionScreen("Question Screen", "question_0");
                    progressIndicator.setProgress(0.05);
                    Scene scq1 = SceneFactory.createQuestionScreen("Question Screen", "question_1");
                    progressIndicator.setProgress(0.1);
                    Scene scq2 = SceneFactory.createQuestionScreen("Question Screen", "question_2");
                    progressIndicator.setProgress(0.2);
                    Scene scq3 = SceneFactory.createQuestionScreen("Question Screen", "question_3");
                    progressIndicator.setProgress(0.3);
                    Scene scq4 = SceneFactory.createQuestionScreen("Question Screen", "question_4");
                    progressIndicator.setProgress(0.4);
                    Scene scq5 = SceneFactory.createQuestionScreen("Question Screen", "question_5");
                    progressIndicator.setProgress(0.5);
                    Scene scq6 = SceneFactory.createQuestionScreen("Question Screen", "question_6");
                    progressIndicator.setProgress(0.6);
                    Scene scq7 = SceneFactory.createQuestionScreen("Question Screen", "question_7");
                    progressIndicator.setProgress(0.7);
                    Scene scq8 = SceneFactory.createQuestionScreen("Question Screen", "question_8");
                    progressIndicator.setProgress(0.8);
                    Scene scq9 = SceneFactory.createQuestionScreen("Question Screen", "question_9");
                    progressIndicator.setProgress(0.9);

                    Scene sc2 = SceneFactory.createTempScene("End");

                    SceneManager.getInstance().addScenes(sc1, scq0, scq1, scq2, scq3, scq4, scq5, scq6, scq7, scq8, scq9, sc2);
                    Platform.runLater(new Runnable() {
                        @Override public void run() {
                            SceneManager.getInstance().show();
                        }
                    });
                    tm.cancel();
                }catch (Exception e){
                    System.out.println(e);
                }
            }
        };
        tm.schedule(task, 0, 1000);

        // init scene manager and add loading scene
        SceneManager.getInstance().init(_stage, "Windows Title", 800, 800, false);
        Scene scloading = SceneFactory.createLoadingScreen("Loading...", progressIndicator);

        SceneManager.getInstance().activateLoading(scloading);
    }

    @Override
    public void stop() throws Exception {
        DatabaseConnection.getInstance().disconnect();
        System.out.println("Exit application!");
        super.stop();
    }
}
