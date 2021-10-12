package com.dictionary.ui;

import base.Word;
import database.DatabaseController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class test extends Application implements Initializable {
    private Stage window;
    private Scene scene1,scene2;

    @FXML
    private WebView webView;
    private WebEngine engine;
    private DatabaseController db;
    @FXML
    Label wordEn;
    @FXML
    Label wordPA;
    @FXML
    Button btn;
    @FXML
    Button btn2;

    @Override
    public void initialize(URL agr0, ResourceBundle agr1){
        engine = webView.getEngine();
        db = new DatabaseController();
    }

    @FXML
    public void chane1(Stage stage,Scene scene){
        stage.setScene(scene);
    }
    @FXML
    public void chane2(Stage stage,Scene scene){
        stage.setScene(scene);
    }

    @FXML
    public void show(){
        wordEn.setText("a");
        engine.loadContent("<html><h1>\uFEFFa</h1><h3><i>/ei, ə/</i></h3><h2>danh từ,  số nhiều as,  a's</h2><ul><li>(thông tục) loại a, hạng nhất, hạng tốt nhất hạng rất tốt<ul style=\"list-style-type:circle\"><li>his health is a:<i> sức khoẻ anh ta vào loại a</i></li></ul></li><li>(âm nhạc) la<ul style=\"list-style-type:circle\"><li>a sharp:<i> la thăng</i></li><li>a flat:<i> la giáng</i></li></ul></li><li>người giả định thứ nhất; trường hợp giả định thứ nhất<ul style=\"list-style-type:circle\"><li>from a to z:<i> từ đầu đến đuôi, tường tận</i></li><li>not to know a from b:<i> không biết tí gì cả; một chữ bẻ đôi cũng không biết</i></li></ul></li></ul><h2>mạo từ</h2><ul><li>một; một (như kiểu); một (nào đó)<ul style=\"list-style-type:circle\"><li>a very cold day:<i> một ngày rất lạnh</i></li><li>a dozen:<i> một tá</i></li><li>a few:<i> một ít</i></li><li>all of a size:<i> tất cả cùng một cỡ</i></li><li>a Shakespeare:<i> một (văn hào như kiểu) Sếch-xpia</i></li><li>a Mr Nam:<i> một ông Nam (nào đó)</i></li></ul></li><li>cái, con, chiếc, cuốn, người, đứa...;<ul style=\"list-style-type:circle\"><li>a cup:<i> cái chén</i></li><li>a knife:<i> con dao</i></li><li>a son of the Party:<i> người con của Đảng</i></li><li>a Vietnamese grammar:<i> cuốn ngữ pháp Việt Nam</i></li></ul></li></ul><h2>giới từ</h2><ul><li>mỗi, mỗi một<ul style=\"list-style-type:circle\"><li>twice a week:<i> mỗi tuần hai lần</i></li></ul></li></ul></html>");
    }
    @FXML
    public void Show(Word w){
        wordEn.setText(w.word_target);
        wordPA.setText(w.word_pa);
        engine.loadContent("<html>"+w.code_html+"</html>");
    }

    @Override
    public void start(Stage stage) throws IOException {
        window = stage;
        window.setTitle("Từ Điển");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        scene1 = new Scene(fxmlLoader.load(), 800, 600);

        scene2 = new Scene(new FXMLLoader(getClass().getResource("q.fxml")).load(), 800, 600);
        btn2.setOnAction(Event -> {
            window.setScene(scene2);
        });
        window.setScene(scene1);
        window.setResizable(false);

        btn.setOnAction(Event ->{
            window.setScene(scene1);
        });

        window.show();

    }



    public static void main(String[] args) {
        launch(args);
    }
}
