package users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class login_user {//登录
    public static void login(String name,String psw) throws Exception {//传入用户名和密码
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1:3306/itcast?useUnicode=true&characterEncoding=UTF-8&userSSL=false&serverTimezone=GMT%2B8";
        String urn = "root";
        String pwd = "123456";
        Connection conn = (Connection) DriverManager.getConnection(url, urn, pwd);
        try{
        String sql = "select * from users where username = ? and password = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, name);
        pstmt.setString(2, psw);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next())//有结果返回登录成功否则返回登录失败
            System.out.println("登陆成功");
        else
            System.out.println("登录失败");
        rs.close();
        pstmt.close();
        conn.close();
    }
        catch (Exception e)
    {
        System.out.println("出现异常，请检查你的输入");
    }
    }
}
