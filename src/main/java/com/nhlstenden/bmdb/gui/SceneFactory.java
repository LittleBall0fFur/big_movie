package com.nhlstenden.bmdb.gui;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.kordamp.bootstrapfx.scene.layout.Panel;

import java.io.FileInputStream;

public class SceneFactory {

    public static Scene createTempScene(String _title){
        // create panel
        Panel panel = GuiFactory.createPanel(GuiFactory.PanelStyle.panel_primary);

        // create content menus
        BorderPane contentHeader = GuiFactory.createBorderPane(5);
        BorderPane content = GuiFactory.createBorderPane(5);
        BorderPane contentFooter = GuiFactory.createBorderPane(5);

        // create header text
        Text headerText = GuiFactory.createText(_title, Color.BLACK, 20);

        // create footer buttons and text
        Button buttonPrevious  = GuiFactory.createButton("Previous", GuiFactory.ButtonStyle.btn_primary, new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                SceneManager.getInstance().previous();
            }
        });
        Text footerText = GuiFactory.createText("Move though the scene's by using these buttons!", Color.BLACK, 18);
        Button buttonNext = GuiFactory.createButton("Next", GuiFactory.ButtonStyle.btn_primary, new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                SceneManager.getInstance().next();
            }
        });


        // add objects to contents
        contentHeader.setCenter(headerText);
        contentFooter.setLeft(buttonPrevious);
        contentFooter.setCenter(footerText);
        contentFooter.setRight(buttonNext);

        // add content menus to panel
        panel.setHeading(contentHeader);
        panel.setBody(content);
        panel.setFooter(contentFooter);

        Scene sc = new Scene(panel);
        sc.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
        return sc;
    }

    public static Scene createQuestionScreen(String _title, String _imageLocation, String _text){
        // create panel
        Panel panel = GuiFactory.createPanel(GuiFactory.PanelStyle.panel_primary);

        // create content menus
        BorderPane contentHeader = GuiFactory.createBorderPane(5);
        BorderPane content = GuiFactory.createBorderPane(5);
        BorderPane contentFooter = GuiFactory.createBorderPane(5);

        // create header text
        Text headerText = GuiFactory.createText(_title, Color.BLACK, 20);

        ImageView graphImage = null;
        try{
            Image imageDisplay = new Image(new FileInputStream("graph.png"));
            graphImage = new ImageView(imageDisplay);

            graphImage.setFitHeight(450);
            graphImage.setFitWidth(350);
            graphImage.setPreserveRatio(true);

        }catch(Exception e){
            System.out.println(e);
        }

        // TODO: add this to GuiFactory with proper size
        TextArea cssEditorFld = new TextArea("Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        cssEditorFld.setPrefRowCount(10);
        cssEditorFld.setPrefColumnCount(100);
        cssEditorFld.setWrapText(true);
        cssEditorFld.setPrefWidth(350);
        cssEditorFld.setEditable(false);
        cssEditorFld.setBorder(null);

        // create footer buttons and text
        Button buttonPrevious  = GuiFactory.createButton("Previous", GuiFactory.ButtonStyle.btn_primary, new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                SceneManager.getInstance().previous();
            }
        });
        Text footerText = GuiFactory.createText("Move though the scene's by using these buttons!", Color.BLACK, 18);
        Button buttonNext = GuiFactory.createButton("Next", GuiFactory.ButtonStyle.btn_primary, new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                SceneManager.getInstance().next();
            }
        });


        // add objects to contents
        contentHeader.setCenter(headerText);

        content.setLeft(graphImage);
        content.setRight(cssEditorFld);

        contentFooter.setLeft(buttonPrevious);
        contentFooter.setCenter(footerText);
        contentFooter.setRight(buttonNext);

        // add content menus to panel
        panel.setHeading(contentHeader);
        panel.setBody(content);
        panel.setFooter(contentFooter);

        Scene sc = new Scene(panel);
        sc.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
        return sc;
    }

}
