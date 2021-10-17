package com.dictionary.ui;

import com.dictionary.base.Status;
import com.dictionary.controller.ThemTu;
import com.dictionary.controller.TimTu;
import com.dictionary.controller.TraTu;
import com.dictionary.controller.XoaTu;
import com.dictionary.database.DatabaseController;
import com.dictionary.tts.TTSController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private DatabaseController db;
    private TraTu traTu;
    private TimTu timTu;
    private ThemTu themTu;
    private XoaTu xoaTu;
    private Status tt = Status.huongDan;

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
    private TextField timInput;
    @FXML
    private TextField enAdd;
    @FXML
    private TextField plAdd;
    @FXML
    private TextField paAdd;
    @FXML
    private TextArea nghiaAdd;
    @FXML
    private TextField xoaInput;
    @FXML
    private VBox formTraTu;
    @FXML
    private AnchorPane formTimTu;
    @FXML
    private AnchorPane formThemTu;
    @FXML
    private AnchorPane formXoaTu;
    @FXML
    private AnchorPane formHuongDan;
    @FXML
    private AnchorPane formSuaTu;
    @FXML
    private Label checkAdd;
    @FXML
    private Label labelOut;

    /**
     * khởi tạo các thành phần cần thiết.
     *
     * @param agr0 tham so 1.
     * @param agr1 tham so 2.
     */
    @Override
    public void initialize(URL agr0, ResourceBundle agr1) {
        db = new DatabaseController();
        traTu = new TraTu(db, webViewTra, enInput, wordEn, wordPA);
        timTu = new TimTu(db,timInput,webViewTim);
        themTu = new ThemTu(db,enAdd,plAdd,paAdd,nghiaAdd,checkAdd);
        xoaTu = new XoaTu(db,xoaInput,labelOut);
        chanett(Status.huongDan);
    }
//--------------------------------------------
    /**
     * chuyển trạng thái hiện tại của chương trình.<br/>
     * Hướng Dẫn
     */
    @FXML
    public void HuongDan() {
        chanett(Status.huongDan);

        System.out.println("hd");
    }

    /**
     * chuyển trạng thái hiện tại của chương trình.<br/>
     * Thêm từ mới
     */
    @FXML
    public void them() {
        chanett(Status.them);
    }

    /**
     * chuyển trạng thái hiện tại của chương trình.<br/>
     * Xóa 1 từ.
     */
    @FXML
    public void remove() {
        chanett(Status.xoaTu);
    }

    /**
     * chuyển trạng thái hiện tại của chương trình.<br/>
     * Sửa 1 từ.
     */
    @FXML
    public void repair() {
        chanett(Status.suaTu);
        System.out.println("sua");
    }

    /**
     * chuyển trạng thái hiện tại của chương trình.<br/>
     * Tìm kiếm 1 từ.
     */
    @FXML
    public void search() {
        chanett(Status.timKiem);
    }

    /**
     * chuyển trạng thái hiện tại của chương trình.<br/>
     * Tra 1 từ.
     */
    @FXML
    public void tra() {
        chanett(Status.traTu);
    }

    /**
     * chuyển trạng thái hiện tại của chương trình.<br/>
     * Sửa 1 từ.
     */
    @FXML
    public void sua(){
        chanett(Status.suaTu);
    }
//--------------------------------------------
    private void setVisibleFalse(){
        formThemTu.setVisible(false);
        formTimTu.setVisible(false);
        formTraTu.setVisible(false);
        formXoaTu.setVisible(false);
        formHuongDan.setVisible(false);
    }

    /**
     * chuyển trạng thái của chương trình.
     *
     * @param tt Trạng Thái dc chuyển.
     */
    public void chanett(Status tt) {
        setVisibleFalse();
        traTu.reset();
        timTu.reset();
        xoaTu.reset();
        themTu.reset();
        switch (tt) {
            case huongDan:
                formHuongDan.setVisible(true);
                tt = Status.huongDan;
                System.out.println("none");
                break;
            case them:
                formThemTu.setVisible(true);
                tt = Status.them;
                System.out.println("them");
                break;
            case suaTu:
                tt = Status.suaTu;
                System.out.println("sua");
                break;
            case traTu:
                formTraTu.setVisible(true);
                tt = Status.traTu;
                System.out.println("tra");
                break;
            case xoaTu:
                formXoaTu.setVisible(true);
                tt = Status.xoaTu;
                System.out.println("xoa");
                break;
            case timKiem:
                formTimTu.setVisible(true);
                tt = Status.timKiem;
                System.out.println("tim");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + tt);
        }
    }
/**************************************************************/
    /**
     * tra từ được nhập từ bàn phím(En).
     */
    @FXML
    public void Tra() {
        traTu.Tra();
    }

    /**
     * tìm kiếm các từ chứa từ dc nhập từ bàn phím.
     */
    @FXML
    public void seacher() {
        timTu.tim();
    }

    @FXML
    public void add(){
        themTu.them();
    }

    @FXML
    public void xoa(){
        xoaTu.xoa();
    }

    @FXML
    public void phat(){
        TTSController.phatam(wordEn.getText());
    }

    @FXML
    public void enter(KeyEvent e){
        if(e.getCode() == KeyCode.ENTER){
            System.out.println("e");
            switch (tt){
                case timKiem:
                    seacher();
                    break;
                case xoaTu:
                    xoa();
                    break;
                case traTu:
                    traTu.Tra();
                    break;
                case suaTu:
                    break;
                case them:
                    add();
                    break;
            }
        }
    }
}
/***
 * ------------
 * id en vn
 * ---------
 *
 * id| en |code_html |mở rộng | cách phát âm
 */