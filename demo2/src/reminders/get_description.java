package reminders;

import databases.pd;
import databases.ul;
import databases.un;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class get_description {
    public static String getinf(int user_id,int reminder_id) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = ul.url_get();
        String urn = un.urn_get();
        String pwd = pd.pwd_get();
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
            conn.close();
            System.out.println("出现异常，请检查你的输入");
        }
        return null;
    }
}
