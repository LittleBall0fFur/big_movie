package com.nhlstenden.bmdb.gui;

import com.nhlstenden.bmdb.observerpattern.Observer;
import com.nhlstenden.bmdb.rcaller.RManager;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.kordamp.bootstrapfx.scene.layout.Panel;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.nhlstenden.bmdb.Main.topic;

public class SceneFactory {

    private static Text funText;

    static{
        funText = GuiFactory.createText("test", Color.BLACK, 18);
    }

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

                displayMessageObserver(headerText);
            }
        });
        Text footerText = GuiFactory.createText("Move though the scene's by using these buttons!", Color.BLACK, 18);

        Button buttonNext = GuiFactory.createButton("Next", GuiFactory.ButtonStyle.btn_primary, new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                SceneManager.getInstance().next();

                displayMessageObserver(headerText);
            }
        });

        // add objects to contents
        contentHeader.setCenter(headerText);
        contentFooter.setLeft(buttonPrevious);
        contentFooter.setCenter(funText);
        contentFooter.setRight(buttonNext);

        // add content menus to panel
        panel.setHeading(contentHeader);
        panel.setBody(content);
        panel.setFooter(contentFooter);

        Scene sc = new Scene(panel);
        sc.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
        return sc;
    }

    public static Scene createLoadingScreen(String _title, ProgressIndicator _progressIndicator){
        // create panel
        Panel panel = GuiFactory.createPanel(GuiFactory.PanelStyle.panel_primary);

        // create content menus
        BorderPane contentHeader = GuiFactory.createBorderPane(5);
        BorderPane content = GuiFactory.createBorderPane(5);
        BorderPane contentFooter = GuiFactory.createBorderPane(5);

        // create header text
        Text headerText = GuiFactory.createText(_title, Color.BLACK, 20);

        // add objects to contents
        contentHeader.setCenter(headerText);
        content.setCenter(_progressIndicator);

        // add content menus to panel
        panel.setHeading(contentHeader);
        panel.setBody(content);
        panel.setFooter(contentFooter);

        Scene sc = new Scene(panel);
        sc.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
        return sc;
    }

    public static Scene createQuestionScreen(String _title, String _plotName) throws URISyntaxException, IOException {
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
            Image imageDisplay = RManager.getRPlot(_plotName);
            graphImage = new ImageView(imageDisplay);

            graphImage.setFitHeight(450);
            graphImage.setFitWidth(350);
            graphImage.setPreserveRatio(true);
        }catch(Exception e){
            System.out.println(e);
        }

        // TODO: add this to GuiFactory with proper size
        //Read the analysis text file and set it in the text object of the scene
        String question_text = "blah"; //Files.readString(Paths.get(SceneFactory.class.getClassLoader().getResource("scene_texts/" + _plotName + ".txt").toURI()), StandardCharsets.UTF_8);
        TextArea cssEditorFld = new TextArea(question_text);
        cssEditorFld.setPrefRowCount(5);
        cssEditorFld.setPrefColumnCount(100);
        cssEditorFld.setWrapText(true);
        cssEditorFld.setPrefWidth(300);
        cssEditorFld.setMaxHeight(300);
        cssEditorFld.setEditable(false);
        cssEditorFld.setBorder(null);


        // create footer buttons and text
        Button buttonPrevious  = GuiFactory.createButton("Previous", GuiFactory.ButtonStyle.btn_primary, new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                SceneManager.getInstance().previous();

                displayMessageObserver(headerText);
            }
        });
        Text footerText = GuiFactory.createText("Move though the scene's by using these buttons!", Color.BLACK, 18);
        Button buttonNext = GuiFactory.createButton("Next", GuiFactory.ButtonStyle.btn_primary, new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                SceneManager.getInstance().next();

                displayMessageObserver(headerText);
            }
        });


        // add objects to contents
        contentHeader.setCenter(headerText);

        content.setLeft(graphImage);
        content.setRight(cssEditorFld);

        contentFooter.setLeft(buttonPrevious);
        contentFooter.setCenter(funText);
        contentFooter.setRight(buttonNext);

        // add content menus to panel
        panel.setHeading(contentHeader);
        panel.setBody(content);
        panel.setFooter(contentFooter);

        Scene sc = new Scene(panel);
        sc.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
        return sc;
    }

    /**
     * Displays message of observer
     * @param Text text component
     */
    private static void displayMessageObserver(Text txt) {
        for (Observer obj : topic.observers) {
            if (obj.getName() == "obj1") {
                System.out.println("Message from observer = " + obj.getMessage());
                funText.setText(obj.getMessage());
            }
        }
    }
}
