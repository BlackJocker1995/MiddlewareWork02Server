package com.nwsuaf.rain;

import java.sql.*;

/**
 * Created by rain on 2017/3/30.
 */
public class DBmanager  {
    private Connection conn;
    private String url="jdbc:mysql://localhost:3306/mysql";    //JDBC-URL
    private Statement stmt;
    public static String table_name;
    public DBmanager() {
        try{
            //Class.forName() loading mysql driver
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Loading MySQL driver!");
        }catch(ClassNotFoundException e1){
            System.out.println("Can not find MySQL driver!");
            e1.printStackTrace();
        }
    }
    protected void Connect(){
        try {
            conn = DriverManager.getConnection(url,"rain","022410");
            stmt = conn.createStatement(); //create Statement to control the sql
            System.out.println("Linked successful");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    protected   void ConClose(){
        try {
            stmt.close();
            conn.close();
            System.out.println("close!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void CreateTable(String in_table_name){
        table_name = in_table_name;
        DBmanager db = new DBmanager();
        db.Connect();
        String sql = "CREATE TABLE "+in_table_name+" (name varchar(255),score INT(11));";
        try {
            db.stmt.executeUpdate(sql);
            System.out.println("Successful!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Table has already exist or something wrong!");
            System.exit(1);
        }
        db.ConClose();
    }
    public  static void Insert(String name,int score){
        DBmanager db = new DBmanager();
        String sql = "insert stu values('"+name+"',"+score+");";
        db.Connect();
        try {
            db.stmt.executeUpdate(sql);
            System.out.println("Successful!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error!");
            System.exit(1);
        }
        db.ConClose();
    }
    public  static int Search(String name){
        DBmanager db = new DBmanager();
        db.Connect();
        String sql = "select score from stu where name = '"+name+"';";
        ResultSet result;
        int score =  0;
        try {
            result = db.stmt.executeQuery(sql);
            while (result.next()) {
                score =result.getInt("score");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error!");
            System.exit(1);
        }

        db.ConClose();
        return score;
    }

}
