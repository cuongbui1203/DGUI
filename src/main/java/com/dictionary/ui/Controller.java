package com.dictionary.ui;

import base.Word;
import com.gluonhq.charm.glisten.control.TextField;
import database.DatabaseController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private WebEngine engineTra,engineTim;
    private DatabaseController db;
    private Status tt = Status.none;

    @FXML
    private WebView webViewTra;
    @FXML
    private WebView webViewTim;
    @FXML
    private Label wordEn;
    @FXML
    private Label wordPA;
    @FXML
    private TextField enInput;
    @FXML
    private Button btn;
    @FXML
    private VBox formTraTu;
    @FXML
    private AnchorPane formTimTu;
    @FXML
    private AnchorPane formThemTu;
    @FXML
    private AnchorPane formXoaTu;

    public void initFormTraTu(){
        if(engineTra == null)
            engineTra = webViewTra.getEngine();
    }

    public void initFormTimTu(){
        if(engineTim == null)
        engineTim = webViewTim.getEngine();
    }

    @Override
    public void initialize(URL agr0, ResourceBundle agr1) {
        db = new DatabaseController();
//        tt = Status.timKiem;
//        chanett();
    }


    @FXML
    public void them() {
        tt = Status.them;
        chanett();
    }

    @FXML
    public void remove() {
        tt = Status.xoaTu;
        chanett();
    }

    @FXML
    public void repair() {
        tt = Status.suaTu;
        chanett();
        System.out.println("sua");
    }

    @FXML
    public void btnClicked(){

    }

    public void chanett(){
        switch (tt){
            case none -> {
                formThemTu.setVisible(false);
                formTimTu.setVisible(false);
                formTraTu.setVisible(false);
                formXoaTu.setVisible(false);
                System.out.println("none");
            }
            case them -> {
                formThemTu.setVisible(true);
                formTimTu.setVisible(false);
                formTraTu.setVisible(false);
                formXoaTu.setVisible(false);
                System.out.println("them");
            }
            case suaTu -> {
                System.out.println("sua");
            }
            case traTu -> {
                formThemTu.setVisible(false);
                formTimTu.setVisible(false);
                formTraTu.setVisible(true);
                formXoaTu.setVisible(false);
                initFormTraTu();
                System.out.println("tra");
            }
            case xoaTu -> {
                formThemTu.setVisible(false);
                formTimTu.setVisible(false);
                formTraTu.setVisible(false);
                formXoaTu.setVisible(true);
                System.out.println("xoa");
            }
            case timKiem -> {
                formThemTu.setVisible(false);
                formTimTu.setVisible(true);
                formTraTu.setVisible(false);
                formXoaTu.setVisible(false);
                initFormTimTu();
                System.out.println("tim");
            }
        }
    }

    @FXML
    public void search(){
        tt = Status.timKiem;
        chanett();
    }

    @FXML
    public void tra() {
        tt = Status.traTu;
        chanett();
        System.out.println("tra");
    }

    @FXML
    public void show() {
        wordEn.setText("a");
        wordPA.setText(enInput.getText());
        engineTra.loadContent("<html><h1>\uFEFFa</h1><h3><i>/ei, ə/</i></h3><h2>danh từ,  số nhiều as,  a's</h2><ul><li>(thông tục) loại a, hạng nhất, hạng tốt nhất hạng rất tốt<ul style=\"list-style-type:circle\"><li>his health is a:<i> sức khoẻ anh ta vào loại a</i></li></ul></li><li>(âm nhạc) la<ul style=\"list-style-type:circle\"><li>a sharp:<i> la thăng</i></li><li>a flat:<i> la giáng</i></li></ul></li><li>người giả định thứ nhất; trường hợp giả định thứ nhất<ul style=\"list-style-type:circle\"><li>from a to z:<i> từ đầu đến đuôi, tường tận</i></li><li>not to know a from b:<i> không biết tí gì cả; một chữ bẻ đôi cũng không biết</i></li></ul></li></ul><h2>mạo từ</h2><ul><li>một; một (như kiểu); một (nào đó)<ul style=\"list-style-type:circle\"><li>a very cold day:<i> một ngày rất lạnh</i></li><li>a dozen:<i> một tá</i></li><li>a few:<i> một ít</i></li><li>all of a size:<i> tất cả cùng một cỡ</i></li><li>a Shakespeare:<i> một (văn hào như kiểu) Sếch-xpia</i></li><li>a Mr Nam:<i> một ông Nam (nào đó)</i></li></ul></li><li>cái, con, chiếc, cuốn, người, đứa...;<ul style=\"list-style-type:circle\"><li>a cup:<i> cái chén</i></li><li>a knife:<i> con dao</i></li><li>a son of the Party:<i> người con của Đảng</i></li><li>a Vietnamese grammar:<i> cuốn ngữ pháp Việt Nam</i></li></ul></li></ul><h2>giới từ</h2><ul><li>mỗi, mỗi một<ul style=\"list-style-type:circle\"><li>twice a week:<i> mỗi tuần hai lần</i></li></ul></li></ul></html>");
    }
    @FXML
    public String createHtml(String s){
        return "<h1>"+s+"</h1>";
    }

    @FXML
    public void timKiem(){
        String s = enInput.getText();
        s = s.toLowerCase();
        Word w = db.searchWord(s);

        if(w != null){
            Show(w);
        }else {
            Show2(createHtml("Không Tìm Thấy: "+s));
        }
    }

    @FXML
    public void enterPressed(KeyEvent e){
        if(e.getCode() == KeyCode.ENTER){
            timKiem();
        }
    }

    @FXML
    public void Show(Word w) {
        wordEn.setText(w.word_target);
        wordPA.setText("/"+w.word_pa+"/");
        System.out.println(w.code_html);
        engineTra.loadContent("<html>" + w.code_html + "</html>");
    }
    @FXML
    public void Show2(String s) {
        wordEn.setText("");
        wordPA.setText("");
        engineTra.loadContent("<html>" + s + "</html>");
    }
}
/***
 * ------------
 * id en vn
 * ---------
 *
 * id| en |code_html |mở rộng | cách phát âm
 */