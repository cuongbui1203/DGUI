package com.dictionary.database;

import com.dictionary.base.Word;

import java.sql.*;
import java.util.ArrayList;

/**
 * class thuc hien viec giao tiep va cac ham can thiet de giao tiep voi database.<br/>
 * database dc su dung la: SQLite.
 */
public class DatabaseController {
    private final String dbname = "av";
    private final String c1 = "word";
    private final String c2 = "html";
    private final String c3 = "description";
    private final String c4 = "pronounce";
    private String querty;           // Cau truy van.
    private Connection dbconect;     //Connect den database.
    private Statement stmt;          // co tra ve du lieu.
    private PreparedStatement pstmt; // k tra ve du lieu.

    public DatabaseController() {
        try {
            String url = "jdbc:sqlite:.\\database\\dict_hh.db";
//            String url = "jdbc:sqlite:C:\\Users\\Kuon\\Downloads\\New folder\\dict_hh.db";
            dbconect = DriverManager.getConnection(url);
            System.out.println("Connected to Database");
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        }
    }

    public static void main(String[] args) throws SQLException {
        DatabaseController databaseController = new DatabaseController();
        databaseController.querty = "SELECT " + databaseController.c1 + "," + databaseController.c2 + "," + databaseController.c3 + " FROM " + databaseController.dbname + ";";
        databaseController.stmt = databaseController.dbconect.createStatement();
        ResultSet res = databaseController.stmt.executeQuery(databaseController.querty);
        System.out.println(res.getString(1));
        System.out.println(res.getString(2));
        System.out.println(res.getString(3));
        //        {
//            ArrayList<Word> a = databaseController.getDictionariesFromDatabase();
//            for (Word w : a) {
//                System.out.println(w.word_target + " " + w.word_explain);
//            }
//        }
//        System.out.println("----");
//        ArrayList<Word> ar = databaseController.searcherWord("e");
//        for (Word w : ar) {
//            System.out.println(w.word_target + " " + w.word_explain);
//        }
//        System.out.println("----");
//        databaseController.repairWord(new Word("he", "123123123"));
//        {
//            ArrayList<Word> a = databaseController.getDictionariesFromDatabase();
//            for (Word w : a) {
//                System.out.println(w.word_target + " " + w.word_explain);
//            }
//        }
    }

    /**
     * <p>
     * close connect to database.
     * </p>
     */
    public void close() {
        try {
            dbconect.close();
            System.out.println("Disconnected to Database");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Load thu vien tu database ve.
     *
     * @return mang cac Dictionary.Word || null neu loi.
     */
    public ArrayList<Word> getDictionariesFromDatabase() {
        querty = "SELECT " + c1 + "," + c2 + "," + c3 + "," + c4 + " FROM " + dbname + ";";
        try {
            stmt = dbconect.createStatement();
            ResultSet res = stmt.executeQuery(querty);
            ArrayList<Word> out = new ArrayList<>();
            while (res.next()) {
                Word tem = new Word();
                tem.word_target = res.getString(c1);
                tem.code_html = res.getString(c2);
                tem.word_pa = res.getString(c4);
                tem.word_explain = res.getString(c3);
                out.add(tem);
            }
            return out;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    /**
     * Them 1 tu moi vao database.
     *
     * @param w tu can them.
     * @return <p> true neu thuc hien thanh cong.<br/>False neu that bai.</p>
     */
    public boolean addNewWordToDatabase(Word w) {
        querty = "INSERT INTO " + dbname + "(" + c1 + "," + c2 + "," + c3 + "," + c4 + ") VALUES(?,?,?,?);";
        try {
            pstmt = dbconect.prepareStatement(querty);
            pstmt.setString(1, w.word_target);
            pstmt.setString(2, w.code_html);
            pstmt.setString(4, w.word_pa);
            pstmt.setString(3, w.word_explain);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    /**
     * them mang nhieu tu vao dataBase.
     *
     * @param arr mang chua cac tu can them.
     * @return <p> true neu thuc hien thanh cong.<br/>False neu that bai.</p>
     */
    public boolean addWordsToDatabase(ArrayList<Word> arr) {
        for (Word w : arr) {
            if (!addNewWordToDatabase(w)) return false;
        }
        return true;
    }

    /**
     * xoa 1 tu khoi database.
     *
     * @param s tu can xoa (EN).
     * @return <p> true neu thuc hien thanh cong.<br/>False neu that bai.</p>
     */
    public boolean removeWord(String s) {
        querty = "DELETE FROM " + dbname + " WHERE " + c1 + " = ?;";
        try {
            pstmt = dbconect.prepareStatement(querty);
            pstmt.setString(1, s);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    /**
     * chinh sua 1 tu da co tu truoc.
     *
     * @param w tu dc sua.
     * @return <p> true neu thuc hien thanh cong.<br/>False neu that bai.</p>
     */
    public boolean repairWord(Word w) {
        querty = "UPDATE " + dbname
                + " SET " + c2 + " = ?"
                + " WHERE " + c1 + " = ?;";

        try {
            pstmt = dbconect.prepareStatement(querty);
            pstmt.setString(1, w.word_explain);
            pstmt.setString(2, w.word_target);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    /**
     * Sửa html hiển thị cho chữ có từ trước.
     * @param en từ muốn sửa.
     * @param code_html html sửa.
     * @return true nếu thành công.<br/>false nếu thất bại.
     */
    public boolean repairHtml(String en,String code_html){
        querty = "UPDATE " + dbname
                + " SET " + c2 + " = ?"
                + " WHERE " + c1 + " = ?;";
        try{
            pstmt = dbconect.prepareStatement(querty);
            pstmt.setString(1,en);
            pstmt.setString(2,code_html);
            pstmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * sửa phần mở rộng cho từ có từ trc.
     * @param en từ muốn sửa.
     * @param nghia phần mở rộng sửa lại.
     * @return true nếu thành công.<br/>false nếu thất bại.
     */
    public boolean repairNghia(String en,String nghia){
        querty = "UPDATE " + dbname
                + " SET " + c3 + " = ?"
                + " WHERE " + c1 + " = ?;";
        try{
            pstmt = dbconect.prepareStatement(querty);
            pstmt.setString(1,en);
            pstmt.setString(2,nghia);
            pstmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * sửa phát âm của 1 từ cho trc.
     * @param en từ muốn sửa.
     * @param pa cách phát âm sửa lại (VD: hə'lou).
     * @return true nếu thành công.<br/>false nếu thất bại.
     */
    public boolean repairPA(String en,String pa){
        querty = "UPDATE " + dbname
                + " SET " + c4 + " = ?"
                + " WHERE " + c1 + " = ?;";
        try{
            pstmt = dbconect.prepareStatement(querty);
            pstmt.setString(1,en);
            pstmt.setString(2,pa);
            pstmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * tim tu tieng anh.
     *
     * @param en tu can tim bang EN.
     * @return Word tim dc hoac null neu k.
     */
    public Word searchWord(String en) {
        querty = "select " + c1 + "," + c2 + "," + c3 + "," + c4 + " from " + dbname + " where " + c1 + " = '" + en + "';";
        System.out.println(querty);
        try {
            stmt = dbconect.createStatement();
            ResultSet res = stmt.executeQuery(querty);
            Word o = new Word();
            o.word_target = res.getString(c1);
            o.code_html = res.getString(c2);
            o.word_pa = res.getString(c4);
            o.word_explain = res.getString(c3);
            return o;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    /**
     * tim tat ca can tu cos chua w.
     *
     * @param w tu goi y muon tim.
     * @return mang cac tu tim dc.
     */
    public ArrayList<String> searcherWord2(String w) {
        querty = "select " + c1 + " from " + dbname + " where " + c1 + " like '%" + w + "%';";
        try {
            stmt = dbconect.createStatement();
            ResultSet out = stmt.executeQuery(querty);
            ArrayList<String> res = new ArrayList<>();
            while (out.next()) {
                res.add(out.getString(1));

            }
            return res;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * tra từ có sẵn.
     *
     * @param w từ cần tra.
     * @return trả về các từ tìm dc theo html<br/>hoặc null nếu k tìm thấy
     */
    public String searcherWord(String w) {
        querty = "select " + c1 + " from " + dbname + " where " + c1 + " like '%" + w + "%';";
        try {
            stmt = dbconect.createStatement();
            ResultSet out = stmt.executeQuery(querty);
            String res = "<h1>Các từ tìm dc:</h1></br><ul>";
            while (out.next()) {
                res += "<li><h3>" + out.getString(c1) + "</h3></li>";
            }
            res += "</ul>";
            return res;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

}
