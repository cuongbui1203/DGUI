package com.dictionary.controller;

import com.dictionary.base.Form;
import javafx.scene.control.TextField;
import com.dictionary.database.DatabaseController;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class TimTu implements Form {
    TextField input;
    WebEngine engine;
    DatabaseController db;

    public TimTu(DatabaseController db, TextField input, WebView webView) {
        this.input = input;
        this.db = db;
        this.engine = webView.getEngine();
    }

    @Override
    public void reset() {
        input.setText("");
        engine.loadContent("");
    }

    public void tim(){
        String s = input.getText();
        if(s.length() <= 1){
            engine.loadContent("<html><h2>Từ bạn vừa nhập quá ngắn.<br/>Vui lòng nhập từ khác dài hơn</h2></html>");
        }else{
            engine.loadContent("<html>"+db.searcherWord(input.getText())+"</html>");
        }
    }
}
