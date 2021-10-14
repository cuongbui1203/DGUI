package com.dictionary.controler;

import com.dictionary.base.Form;
import com.dictionary.base.Word;
import com.dictionary.database.DatabaseController;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class TraTu implements Form {
    DatabaseController db;
    WebEngine engine;
    Label en;
    Label pa;
    TextField input;

    public TraTu(DatabaseController db, WebView webViewTra, TextField input, Label en, Label pa) {
        this.db = db;
        this.engine = webViewTra.getEngine();
        this.en = en;
        this.pa = pa;
        this.input = input;
    }

    @Override
    public void reset() {
        engine.loadContent("");
        en.setText("");
        pa.setText("");
        input.setText("");

    }

    public void Tra() {
        String s = input.getText();
        s = s.toLowerCase();
        Word w = db.searchWord(s);
        reset();
        if (w != null) {
            show(w);
        } else {
            show("<h2>Không Tìm Thấy: " + s + "</h2>");
        }
    }

    public void show(Word w) {
        en.setText(w.word_target);
        pa.setText("/" + w.word_pa + "/");
        System.out.println(w.code_html);
        engine.loadContent("<html>" + w.code_html + "</html>");

    }

    public void show(String s) {
        engine.loadContent("<html>" + s + "</html>");
    }

}
