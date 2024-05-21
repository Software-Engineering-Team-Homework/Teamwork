package reminders;

import databases.pd;
import databases.ul;
import databases.un;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class get_reminder_id {
    public static int getinf(int user_id,String title,String description) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = ul.url_get();
        String urn = un.urn_get();
        String pwd = pd.pwd_get();
        Connection conn = (Connection) DriverManager.getConnection(url,urn,pwd);
        try{
            int reminder_id = 0;
            String sql = "select * from reminders where user_id=? and title = ? and description = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,user_id);
            pstmt.setString(2,title);
            pstmt.setString(3,description);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            reminder_id = rs.getInt(1);
            conn.close();
            return reminder_id;
        }
        catch (Exception e)
        {
            conn.close();
            System.out.println("出现异常，请检查你的输入");
        }
        return 0;
    }
}
