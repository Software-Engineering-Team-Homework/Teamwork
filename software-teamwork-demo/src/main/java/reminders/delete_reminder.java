package reminders;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class delete_reminder {
    public static void delete(int user_id,int reminder_id) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1:3306/itcast?useUnicode=true&characterEncoding=UTF-8&userSSL=false&serverTimezone=GMT%2B8";
        String urn = "root";
        String pwd = "123456";
        Connection conn = (Connection) DriverManager.getConnection(url,urn,pwd);
        try {
        String sql = "delete from reminders where user_id = ? and reminder_id = ?";//删除内容
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,user_id);
        pstmt.setInt(2,reminder_id);
        int count = pstmt.executeUpdate();
        if(count > 0)
            System.out.println("删除成功");
        else
            System.out.println("删除失败");
        pstmt.close();
        conn.close();
    }
        catch (Exception e)
    {
        System.out.println("出现异常，请检查你的输入");
    }
    }
}
