package users;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
public class create_user {//注册
    public static void create(String name,String psw) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1:3306/itcast?useUnicode=true&characterEncoding=UTF-8&userSSL=false&serverTimezone=GMT%2B8";
        String urn = "root";
        String pwd = "123456";
        Connection conn = (Connection) DriverManager.getConnection(url,urn,pwd);
        try{
        String sql = "insert into users (username,password) values (?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,name);
        pstmt.setString(2,psw);
        int count = pstmt.executeUpdate();//查询数据要用stmt.executeQuery();
        if(count > 0)
            System.out.println("注册成功");
        else
            System.out.println("注册失败");
        pstmt.close();
        conn.close();
    }
        catch (Exception e)
    {
        System.out.println("出现异常，请检查你的输入");
    }
    }
}
