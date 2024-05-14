package reminders;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class mark_complete {//标记完成
    public static void finish(int user_id,int reminder_id) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1:3306/itcast?useUnicode=true&characterEncoding=UTF-8&userSSL=false&serverTimezone=GMT%2B8";
        String urn = "root";
        String pwd = "123456";
        Connection conn = (Connection) DriverManager.getConnection(url,urn,pwd);
        try{
        String sql = "update reminders set completed = ? where user_id = ? and reminder_id = ?";//更改内容
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,1);
        pstmt.setInt(2,user_id);
        pstmt.setInt(3,reminder_id);
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }
        catch (Exception e)
    {
        System.out.println("出现异常，请检查你的输入");
    }
    }
}
