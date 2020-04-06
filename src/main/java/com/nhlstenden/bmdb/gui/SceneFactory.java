package com.nhlstenden.bmdb.gui;

import com.nhlstenden.bmdb.database.DatabaseConnection;
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
        if(_plotName != "question_0" && _plotName != "question_1" && _plotName != "question_2") {
            try {
                Image imageDisplay = RManager.getRPlot(_plotName);
                graphImage = new ImageView(imageDisplay);

                graphImage.setFitHeight(450);
                graphImage.setFitWidth(350);
                graphImage.setPreserveRatio(true);
            } catch (Exception e) {
                System.out.println(e);
            }
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

        if(_plotName != "question_0" && _plotName != "question_1" && _plotName != "question_2"){
            content.setLeft(graphImage);
        }else{
            String value = "";
            switch (_plotName){
                case "question_0":
                    value = DatabaseConnection.getInstance().query("SELECT \n" +
                            "    COUNT(Title.primary_title) AS Total_series \n" +
                            "FROM \n" +
                            "    Title,\n" +
                            "    Person,\n" +
                            "    Person$titles \n" +
                            "WHERE\n" +
                            "    Person.name = 'Woody Allen'         AND\n" +
                            "    Person.professions LIKE '%act%'     AND\n" +
                            "    (Person$titles.title_id = Title.id) AND\n" +
                            "    (Person$titles.person_id = Person.id);").get("total_series")[0].toString();
                    content.setLeft(GuiFactory.createText(value, Color.BLACK, 40));
                    break;
                case "question_1":
                    value = DatabaseConnection.getInstance().query("SELECT\n" +
                            "    le.person_id,\n" +
                            "    re.person_id\n" +
                            "FROM\n" +
                            "    title$principals AS le INNER JOIN title$principals AS re ON le.title_id = re.title_id\n" +
                            "WHERE\n" +
                            "    le.job LIKE '%act%' AND re.job LIKE '%act%' AND le.person_id <> re.person_id;").get("person_id")[0].toString();
                    content.setLeft(GuiFactory.createText(value, Color.BLACK, 40));
                    break;
                case "question_2":
                    value = DatabaseConnection.getInstance().query("SELECT\n" +
                            "    title.primary_title,\n" +
                            "    COUNT(title$genres.genre)\n" +
                            "FROM\n" +
                            "    title LEFT JOIN episode ON title.id = episode.id\n" +
                            "    INNER JOIN title$genres ON title.id = title$genres.title_id\n" +
                            "WHERE\n" +
                            "    title.primary_title LIKE '%beer%' AND title.start_year BETWEEN 1990 AND date_part('year', CURRENT_DATE)\n" +
                            "GROUP BY\n" +
                            "    title$genres.genre, title.primary_title;").get("count")[0].toString();
                    content.setLeft(GuiFactory.createText(value, Color.BLACK, 40));
                    break;
                default:
                    break;
            }
            System.out.println("value: " + value);
        }
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
