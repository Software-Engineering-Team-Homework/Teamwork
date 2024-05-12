package reminders;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
public class modifydescription {//修改内容
    public static void modify() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1:3306/itcast?useUnicode=true&characterEncoding=UTF-8&userSSL=false&serverTimezone=GMT%2B8";
        String urn = "root";
        String pwd = "123456";
        Connection conn = (Connection) DriverManager.getConnection(url,urn,pwd);
        int id = 1;//登录后进行操作时传当前账户的id
        int r_id = 1;//传入要更改内容的reminder_id
        String text = "豪仔66";//由用户输入新的内容
        String sql = "update reminders set description = ? where user_id = ? and reminder_id = ?";//更改内容
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,text);
        pstmt.setInt(2,id);
        pstmt.setInt(3,r_id);
        int count = pstmt.executeUpdate();
        if(count > 0)
            System.out.println("修改成功");
        else
            System.out.println("修改失败");
        pstmt.close();
        conn.close();
    }
}
