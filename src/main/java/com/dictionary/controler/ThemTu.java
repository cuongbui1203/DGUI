package com.dictionary.controler;

import com.dictionary.base.Form;
import com.dictionary.base.Word;
import com.dictionary.database.DatabaseController;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ThemTu implements Form {
    private final DatabaseController db;
    private final TextField enAdd;
    private final TextField plAdd;
    private final TextField paAdd;
    private final TextArea nghiaAdd;
    private final Label checkAdd;
    private final Word word;
    private String[] nghia;

    public ThemTu(DatabaseController db, TextField enAdd, TextField plAdd, TextField paAdd, TextArea nghiaAdd, Label checkAdd) {
        this.db = db;
        this.enAdd = enAdd;
        this.paAdd = paAdd;
        this.plAdd = plAdd;
        this.nghiaAdd = nghiaAdd;
        this.checkAdd = checkAdd;
        word = new Word();
        nghia = null;
    }

    private void createHtml() {
        word.code_html = "";
        StringBuilder s = new StringBuilder();
        s.append("<h1>").append(word.word_target).append("</h1>");
        s.append("<h3><i>").append(word.word_pa).append("</i></h3>");
        s.append("<h2>").append(word.word_loai).append("</h2>");
        if (word.word_explain.length() != 0) {
            s.append("<ul>");
            word.code_html += "<ul>";
            nghia = word.word_explain.split("\n");
            for (String t : nghia) s.append("<li>").append(t).append("</li>");
           s.append( "</ul>");
        }
        word.code_html = s.toString();
    }

    private void add() {
        boolean t = db.addNewWordToDatabase(word);
        String s = t ? "Thêm thành công!" : "Thất bại!!!";
        checkAdd.setText(s);
    }

    public void them() {
        word.word_target = enAdd.getText();
        word.word_pa = paAdd.getText();
        word.word_loai = plAdd.getText();
        word.word_explain = nghiaAdd.getText();
        createHtml();
//        add();
        System.out.println(word.code_html);
    }


    @Override
    public void reset() {
        enAdd.setText("");
        plAdd.setText("");
        paAdd.setText("");
        nghiaAdd.setText("");
        checkAdd.setText("");
        word.reset();
    }
}
