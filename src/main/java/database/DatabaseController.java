package database;

import base.Word;

import java.sql.*;
import java.util.ArrayList;

/**
 * class thuc hien viec giao tiep va cac ham can thiet de giao tiep voi database.<br/>
 * database dc su dung la: SQLite.
 */
public class DatabaseController {
    public final String dbname = "av";
    public final String c1 = "word";
    public final String c2 = "html";
    public final String c3 = "pronounce";
    public String querty;           // Cau truy van.
    public Connection dbconect;     //Connect den database.
    public Statement stmt;          // co tra ve du lieu.
    public PreparedStatement pstmt; // k tra ve du lieu.

    public DatabaseController() {
        try {
//            String url = "jdbc:sqlite:.\\database\\sanbox.db";
            String url = "jdbc:sqlite:C:\\Users\\Kuon\\Downloads\\New folder\\dict_hh.db";
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
        querty = "SELECT " + c1 + "," + c2 + "," + c3 + " FROM " + dbname + ";";
        try {
            stmt = dbconect.createStatement();
            ResultSet res = stmt.executeQuery(querty);
            ArrayList<Word> out = new ArrayList<>();
            while (res.next()) {
                Word tem = new Word();
                tem.word_target = res.getString(c1);
                tem.code_html = res.getString(c2);
                tem.word_pa = res.getString(c3);
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
        querty = "INSERT INTO " + dbname + "(" + c1 + "," + c2 + "," + c3 + ") VALUES(?,?,?);";
        try {
            pstmt = dbconect.prepareStatement(querty);
            pstmt.setString(1, w.word_target);
            pstmt.setString(2, w.code_html);
            pstmt.setString(3, w.word_pa);
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
     * tim tu tieng anh.
     * @param en tu can tim bang EN.
     * @return Word tim dc hoac null neu k.
     */
    public Word searchWord(String en) {
        querty = "select " + c1 + "," + c2 +","+c3 +" from " + dbname + " where " + c1 + " = '" + en + "';";
        System.out.println(querty);
        try {
            stmt = dbconect.createStatement();
            ResultSet res = stmt.executeQuery(querty);
            Word o = new Word();
            o.word_target = res.getString(c1);
            o.code_html = res.getString(c2);
            o.word_pa = res.getString(c3);
            return o;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * tim tat ca can tu cos chua w.
     * @param w tu goi y muon tim.
     * @return mang cac tu tim dc.
     */
    public ArrayList<String> searcherWord(String w) {
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
    public String searcherWords(String w) {
        querty = "select " + c1 + " from " + dbname + " where " + c1 + " like '%" + w + "%';";
        try {
            stmt = dbconect.createStatement();
            ResultSet out = stmt.executeQuery(querty);
            String res = "<h1>Các từ tìm dc:</h1></br><ul>";
            while (out.next()) {
                res += "<li><h3>"+out.getString(c1)+"</h3></li>";
            }
            res += "</ul>";
            return res;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
