package com.example.tobyspring3.db;

import java.sql.*;
import java.util.Map;

import static java.lang.System.getenv;

public class ConnectChecker {

    public void select() throws ClassNotFoundException, SQLException {
        Map<String, String> env = getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(dbHost, dbUser, dbPassword); //db에 연결

        Statement st = con.createStatement(); //쿼리를 날릴 수 있는 명령
        ResultSet rs = st.executeQuery("select * from users"); //쿼리를 보내서 ResultSet으로 결과를 받겠다

        while (rs.next()) {
            String col1 = rs.getString(1);
            String col2 = rs.getString(2);
            String col3 = rs.getString(3);
            System.out.printf("%s %s %s\n", col1, col2, col3);
        }
    }
    public void check() throws SQLException, ClassNotFoundException {
        Map<String, String> env = getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(dbHost, dbUser, dbPassword); //db에 연결

        Statement st = con.createStatement(); //쿼리를 날릴 수 있는 명령
        ResultSet rs = st.executeQuery("SHOW DATABASES"); //쿼리를 보내서 ResultSet으로 결과를 받겠다

        while (rs.next()) {
            String str = rs.getString(1);
            System.out.println(str);
        }
    }

    public void add() throws ClassNotFoundException, SQLException {
        Map<String, String> env = getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD"); //보안을 위해 이렇게 처리해야 한다.(+environment variables 수정) 아니면 DB 털림

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(dbHost, dbUser, dbPassword); //db에 연결

        PreparedStatement pstmt = con.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
        pstmt.setString(1, "1");
        pstmt.setString(2, "kyeongrok");
        pstmt.setString(3, "12345678");
        pstmt.executeUpdate();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ConnectChecker cc = new ConnectChecker();
        cc.check();
    }
}