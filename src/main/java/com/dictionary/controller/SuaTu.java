package com.dictionary.controller;

import com.dictionary.base.Form;
import com.dictionary.base.Word;
import com.dictionary.database.DatabaseController;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SuaTu implements Form {
    private TextField input;
    private TextField enFix;
    private TextField paFix;
    private TextArea nghiaFix;
    private Label lbCheck;
    private DatabaseController db;
    private Word word;

    SuaTu(DatabaseController db,TextField input,TextField enFix,TextField paFix,TextArea nghiaFix,Label lbCheck){
        this.input = input;
        this.enFix = enFix;
        this.lbCheck = lbCheck;
        this.nghiaFix = nghiaFix;
        this.paFix = paFix;

        this.db = db;
        word = null;
    }

    public boolean timSua(){
        word = db.searchWord(input.getText());
        if(word != null){
            return true;
        }
        return false;
    }

    public void sua(){
        enFix.setText(word.word_target);
//        enFix
    }

    @Override
    public void reset() {
        input.setText("");
        enFix.setText("");
        lbCheck.setText("");
        nghiaFix.setText("");
        paFix.setText("");
        word = null;
    }
}
