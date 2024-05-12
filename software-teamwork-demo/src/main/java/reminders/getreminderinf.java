package reminders;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class getreminderinf {
    public static void getinf() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1:3306/itcast?useUnicode=true&characterEncoding=UTF-8&userSSL=false&serverTimezone=GMT%2B8";
        String urn = "root";
        String pwd = "123456";
        Connection conn = (Connection) DriverManager.getConnection(url,urn,pwd);
        //String name = "bx";//用户输入，用于line 18
        //String psw = "666";//用户输入,用于line 19
        int id = 1;
        String sql = "select * from reminders where user_id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,id);
        ResultSet rs = pstmt.executeQuery();
        while(rs.next())//有结果返回登录成功否则返回登录失败
        {
            System.out.print(rs.getString(3));
            System.out.print(rs.getString(4));
            System.out.print(rs.getString(5));
            System.out.println(rs.getString(6));
        }
        rs.close();
        pstmt.close();
        conn.close();
    }
}
