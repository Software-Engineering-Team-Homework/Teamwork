package users;
import databases.pd;
import databases.ul;
import databases.un;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class modify_password {
    public static void modify(int id,String psw) throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = ul.url_get();
        String urn = un.urn_get();
        String pwd = pd.pwd_get();
        Connection conn = (Connection) DriverManager.getConnection(url,urn,pwd);
        try{
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
        catch (Exception e)
    {
        conn.close();
        System.out.println("出现异常，请检查你的输入");
    }
    }
}
