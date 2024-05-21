package users;

import databases.pd;
import databases.ul;
import databases.un;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class delete_user {
    public static void delete(int user_id) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = ul.url_get();
        String urn = un.urn_get();
        String pwd = pd.pwd_get();
        Connection conn = (Connection) DriverManager.getConnection(url,urn,pwd);
        try{
        String sql = "delete from users where user_id = ?";//删除内容
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,user_id);
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
        conn.close();
        System.out.println("出现异常，请检查你的输入");
    }
    }
}
