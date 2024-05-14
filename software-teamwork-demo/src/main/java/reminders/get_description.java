package reminders;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class get_description {
    public static String getinf(int user_id,int reminder_id) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1:3306/itcast?useUnicode=true&characterEncoding=UTF-8&userSSL=false&serverTimezone=GMT%2B8";
        String urn = "root";
        String pwd = "123456";
        Connection conn = (Connection) DriverManager.getConnection(url,urn,pwd);
        try{
        String sql = "select * from reminders where user_id = ? and reminder_id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,user_id);
        pstmt.setInt(2,reminder_id);
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        String temp = rs.getString(4);
        rs.close();
        pstmt.close();
        conn.close();
        return temp;
        }
        catch (Exception e)
        {
            System.out.println("出现异常，请检查你的输入");
        }
        return null;
    }
}
