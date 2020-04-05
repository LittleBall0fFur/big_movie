package com.nhlstenden.bmdb.gui;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.kordamp.bootstrapfx.scene.layout.Panel;

public class GuiFactory {

    public enum ButtonStyle{ // btn-default, btn-primary, btn-success, btn-info, btn-warning, btn-danger
        btn_default, btn_primary, btn_success, btn_info, btn_warning, btn_danger
    }

    public enum PanelStyle{ // panel-default, panel-primary, panel-success, panel-info, panel-warning, panel-danger
        panel_default, panel_primary, panel_success, panel_info, panel_warning, panel_danger
    }

    private static String _normalFont = "Arial";

    public static void setNormalFont(String _fontName){
        GuiFactory._normalFont = _fontName;
    }

    public static Button createButton(String _text, ButtonStyle _style){
        Button bt = new Button(_text);
        bt.getStyleClass().setAll("btn", _style.toString());

        return bt;
    }

    public static Button createButton(String _text, ButtonStyle _style, EventHandler<MouseEvent> _clickEvent){
        Button bt = new Button(_text);
        bt.addEventHandler(MouseEvent.MOUSE_CLICKED, _clickEvent);
        bt.getStyleClass().setAll("btn", _style.toString().replace('_', '-'));

        return bt;
    }

    public static Text createText(String _text, Color _color, int _fontSize){
        Text t = new Text(_text);
        t.setFill(_color);
        t.setFont(Font.font(GuiFactory._normalFont, _fontSize));

        return t;
    }

    public static Text createText(String _text, Color _color, String _fontName, int _fontSize){
        Text t = new Text(_text);
        t.setFill(_color);
        t.setFont(Font.font (_fontName, _fontSize));

        return t;
    }

    public static ProgressIndicator createProgressIndicator(float _value, int _w, int _h){
        ProgressIndicator pi = new ProgressIndicator(_value);
        pi.setMinWidth(_w);
        pi.setMinHeight(_h);

        return pi;
    }

    public static BorderPane createBorderPane(int _padding){
        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(_padding));

        return bp;
    }

    public static Panel createPanel(PanelStyle _style){
        Panel pl = new Panel();
        pl.getStyleClass().setAll("btn", _style.toString().replace('_', '-'));

        return pl;
    }

    public static Panel createPanel(String _title, PanelStyle _style){
        Panel pl = new Panel(_title);
        pl.getStyleClass().add(_style.toString().replace('_', '-'));

        return pl;
    }

}
