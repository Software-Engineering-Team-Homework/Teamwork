package reminders;

import databases.pd;
import databases.ul;
import databases.un;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class modify_date {
    public static void modify(int user_id,int reminder_id,String new_reminderdate) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = ul.url_get();
        String urn = un.urn_get();
        String pwd = pd.pwd_get();
        Connection conn = (Connection) DriverManager.getConnection(url,urn,pwd);
        try{
        String sql = "update reminders set reminder_date = ? where user_id = ? and reminder_id = ?";//更改内容
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,new_reminderdate);
        pstmt.setInt(2,user_id);
        pstmt.setInt(3,reminder_id);
        int count = pstmt.executeUpdate();
        if(count > 0)
            System.out.println("修改成功");
        else
            System.out.println("修改失败");
        pstmt.close();
        conn.close();
    }
        catch (Exception e)
    {
        conn.close();
        System.out.println("出现异常，请检查你的输入");
    }
    }
}
