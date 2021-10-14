package com.dictionary.controller;

import com.dictionary.base.Word;
import com.dictionary.base.Form;
import javafx.scene.control.TextField;
import com.dictionary.database.DatabaseController;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

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
    }

    private void createHtml() {
        word.code_html = "";
        word.code_html += "<h1>" + word.word_target + "</h1>"
                + "<h3><i>" + word.word_pa + "</i></h3>"
                + "<h2>" + word.word_loai + "</h2>";
        if (word.word_explain.length() != 0) {
            word.code_html += "<ul>";
            nghia = word.word_explain.split("\n");
            for (String t : nghia) {
                word.code_html += "<li>" + t + "</li>";
            }
            word.code_html += "</ul>";
        }
    }

    private void add() {
        boolean t = db.addNewWordToDatabase(word);
        String s = t ? "Thêm thành công!":"Thất bại!!!";
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
