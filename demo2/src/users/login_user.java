package users;

import databases.pd;
import databases.ul;
import databases.un;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class login_user {//登录
    public static int login(String name,String psw) throws Exception {//传入用户名和密码
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = ul.url_get();
        String urn = un.urn_get();
        String pwd = pd.pwd_get();
        Connection conn = (Connection) DriverManager.getConnection(url, urn, pwd);
        try{
        String sql = "select * from users where username = ? and password = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, name);
        pstmt.setString(2, psw);
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        int user_id = rs.getInt(1);
        rs.close();
        pstmt.close();
        conn.close();
        return user_id;
    }
        catch (Exception e)
    {
        conn.close();
        System.out.println("出现异常，请检查你的输入");
    }
        return 0;
    }
}
