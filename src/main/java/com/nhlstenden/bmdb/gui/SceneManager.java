package com.nhlstenden.bmdb.gui;

import com.nhlstenden.bmdb.observerpattern.Observer;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

import static com.nhlstenden.bmdb.Main.topic;

public class SceneManager {
    public static SceneManager _instance = null;

    private Stage _stage;

    private ArrayList<Scene> _scenes;
    private int _currentScene;

    private int _width;
    private int _height;

    private SceneManager(){
        this._scenes = new ArrayList<Scene>();
        this._currentScene = 0;
    }

    public static SceneManager getInstance(){
        if(_instance == null)
            _instance = new SceneManager();
        return _instance;
    }

    public void init(Stage _stage, String _title, int _width, int _height, boolean _sizeToScene){
        this._stage = _stage;
        this._width = _width;
        this._height = _height;

        _stage.setTitle(_title);

        if(_sizeToScene) {
            _stage.sizeToScene();
        }else{
            _stage.setWidth(this._width);
            _stage.setHeight(this._height);
        }
    }

    public void addScene(Scene _scene){
        _scenes.add(_scene);
    }

    public void addScenes(Scene... _scenes){
        for(int i = 0; i < _scenes.length; i++){
            this._scenes.add(_scenes[i]);
        }
    }

    public void next(){
        _currentScene = _currentScene+1 >= _scenes.size() ? 0 : _currentScene+1;
        _stage.setScene(_scenes.get(_currentScene));
        topic.postMessage(Integer.toString(_currentScene));
    }

    public void previous(){
        _currentScene = _currentScene-1 < 0 ? _scenes.size()-1 : _currentScene-1;
        _stage.setScene(_scenes.get(_currentScene));
        topic.postMessage(Integer.toString(_currentScene));
    }

    public void show(){
        if(_scenes.isEmpty()) {
            System.out.println("[SCENE-MANAGER] Can't show scene if _scenes list is empty");
            return;
        }

        _stage.setScene(_scenes.get(_currentScene));
        _stage.show();
    }

    /**
     * Get current scene
     * @return scene id
     */
    public int getCurScene() {
        return this._currentScene;
    }
}
