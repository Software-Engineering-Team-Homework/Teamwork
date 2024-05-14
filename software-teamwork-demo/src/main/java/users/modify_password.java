package users;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class modify_password {
    public static void modify(int id,String psw) throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1:3306/itcast?useUnicode=true&characterEncoding=UTF-8&userSSL=false&serverTimezone=GMT%2B8";
        String urn = "root";
        String pwd = "123456";
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
        System.out.println("出现异常，请检查你的输入");
    }
    }
}
