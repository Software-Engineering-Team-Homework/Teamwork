package org.example;
import user.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class createuser {
    public static void create() throws Exception {
        //Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1:3306/user?useUnicode=true&characterEncoding=UTF-8&userSSL=false&serverTimezone=GMT%2B8";
        String username = "root";
        String password = "123456";
        Connection conn = (Connection) DriverManager.getConnection(url,username,password);
        String name = "";
        String psw = "";
        String sql = "insert into user(username,password) values (name,psw)";
        Statement stmt = conn.createStatement();
        int count = stmt.executeUpdate(sql);//查询数据要用stmt.executeQuery();
        if(count > 0)
            System.out.println("注册成功");
        else
            System.out.println("注册失败");
        stmt.close();
        conn.close();
    }
}
