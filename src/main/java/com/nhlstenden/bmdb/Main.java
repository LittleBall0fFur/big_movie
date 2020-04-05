package com.nhlstenden.bmdb;

import com.nhlstenden.bmdb.database.DatabaseConnection;
import com.nhlstenden.bmdb.gui.SceneFactory;
import com.nhlstenden.bmdb.gui.SceneManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static MyTopic topic = new MyTopic();

    public static void main(String args[]) {
        System.out.println("Start application!");
        DatabaseConnection.getInstance().connect();
        if(DatabaseConnection.getInstance().isConnected())
            System.out.println("[DATABASE] Connection true");
        else
            System.out.println("[DATABASE] Connection false");

        //Map<String, String[]> list = DatabaseConnection.getInstance().query("");

        //create observers
        Observer obj1 = new MyTopicSubscriber("Obj1");
        Observer obj2 = new MyTopicSubscriber("Obj2");
        Observer obj3 = new MyTopicSubscriber("Obj3");

        //register observers to the subject
        topic.register(obj1);
        topic.register(obj2);
        topic.register(obj3);

        //attach observer to subject
        obj1.setSubject(topic);
        obj2.setSubject(topic);
        obj3.setSubject(topic);

        //check if any update is available
        obj1.update();

        //now send message to subject
        topic.postMessage("New Message");

        launch(args);
    }

    @Override
    public void start(Stage _stage) {
        SceneManager.getInstance().init(_stage, "Windows Title", 800, 600, false);

        Scene sc1 = SceneFactory.createTempScene("Scene 1");
        Scene scq2 = SceneFactory.createQuestionScreen("Question Screen", "graph.png", "Alot of text...");
        Scene sc3 = SceneFactory.createTempScene("Scene 3");
        Scene sc4 = SceneFactory.createTempScene("Scene 4");

        SceneManager.getInstance().addScenes(sc1, scq2, sc3, sc4);
        SceneManager.getInstance().show();
    }

    @Override
    public void stop() throws Exception {
        DatabaseConnection.getInstance().disconnect();
        System.out.println("Exit application!");
        super.stop();
    }
}