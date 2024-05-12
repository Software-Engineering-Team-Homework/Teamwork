package users;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class modifypassword {
    public static void modify() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1:3306/itcast?useUnicode=true&characterEncoding=UTF-8&userSSL=false&serverTimezone=GMT%2B8";
        String urn = "root";
        String pwd = "123456";
        Connection conn = (Connection) DriverManager.getConnection(url,urn,pwd);
        int id = 1;//登录后进行操作时传当前账户的id
        String psw = "123456";//由用户输入新的密码
        String sql = "update users set password = ? where user_id = ?";//更改密码
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,psw);
        pstmt.setInt(2,id);
        int count = pstmt.executeUpdate();
        if(count > 0)
            System.out.println("修改成功");
        else
            System.out.println("修改失败");
        pstmt.close();
        conn.close();
    }
}
