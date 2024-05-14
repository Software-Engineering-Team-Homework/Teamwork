package reminders;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class create_reminder {
    public static void create(int user_id,String title,String description,String reminder_date) throws Exception {//传入用户id
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1:3306/itcast?useUnicode=true&characterEncoding=UTF-8&userSSL=false&serverTimezone=GMT%2B8";
        String urn = "root";
        String pwd = "123456";
        Connection conn = (Connection) DriverManager.getConnection(url,urn,pwd);
        try {
        String sql = "insert into reminders (user_id,title,description,reminder_date,completed) values (?,?,?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,user_id);
        pstmt.setString(2,title);
        pstmt.setString(3,description);
        pstmt.setString(4,reminder_date);
        pstmt.setInt(5,0);
        int count = pstmt.executeUpdate();
        if(count > 0)//返回记录结果
            System.out.println("记录成功");
        else
            System.out.println("记录失败");
        pstmt.close();
        conn.close();
    }
        catch (Exception e)
    {
        System.out.println("出现异常，请检查你的输入");
    }
    }
}
