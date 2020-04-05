package com.nhlstenden.bmdb;

import com.nhlstenden.bmdb.database.DatabaseConnection;
import com.nhlstenden.bmdb.gui.SceneFactory;
import com.nhlstenden.bmdb.gui.SceneManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String args[]) {
        System.out.println("Start application!");
        DatabaseConnection.getInstance().connect();
        if(DatabaseConnection.getInstance().isConnected())
            System.out.println("[DATABASE] Connection true");
        else
            System.out.println("[DATABASE] Connection false");

        //Map<String, String[]> list = DatabaseConnection.getInstance().query("");

        ConcreteObservable observable = new ConcreteObservable();
        ConcreteObserver observer1 = new ConcreteObserver(observable);
        ConcreteObserver observer2 = new ConcreteObserver(observable);

        observable.notifier();

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