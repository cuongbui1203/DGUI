package com.dictionary.controller;

import com.dictionary.base.Form;
import javafx.scene.control.TextField;
import com.dictionary.database.DatabaseController;
import javafx.scene.control.Label;

public class XoaTu implements Form {
    TextField input;
    Label output;
    DatabaseController db;

    public XoaTu(DatabaseController db, TextField input, Label output){
        this.input = input;
        this.output = output;
        this.db = db;
    }

    @Override
    public void reset() {
        input.setText("");
        output.setText("");
    }

    public void xoa(){
        String s = input.getText();
        s = db.removeWord(s) ? "Đã xóa thành công":"Lỗi: Không tìm thấy từ bạn muốn xóa. kiểm tra lại.";
        output.setText(s);
    }
}
