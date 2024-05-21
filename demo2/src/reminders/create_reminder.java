package reminders;
import com.mysql.cj.protocol.Resultset;
import databases.pd;
import databases.ul;
import databases.un;
import users.login_user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class create_reminder {
    public static int create(int user_id,String title,String description,String reminder_date) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = ul.url_get();
        String urn = un.urn_get();
        String pwd = pd.pwd_get();
        Connection conn = (Connection) DriverManager.getConnection(url,urn,pwd);
        try{
            String sql = "insert into reminders (user_id,title,description,reminder_date) values (?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,user_id);
            pstmt.setString(2,title);
            pstmt.setString(3,description);
            pstmt.setString(4,reminder_date);
            pstmt.executeUpdate();//查询数据要用stmt.executeQuery();
            pstmt.close();
            int reminder_id = get_reminder_id.getinf(user_id,title,description);
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
