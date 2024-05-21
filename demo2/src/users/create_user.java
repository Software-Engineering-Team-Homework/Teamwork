package users;
import databases.pd;
import databases.ul;
import databases.un;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
public class create_user {//注册
    public static int create(String name,String psw) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = ul.url_get();
        String urn = un.urn_get();
        String pwd = pd.pwd_get();
        Connection conn = (Connection) DriverManager.getConnection(url,urn,pwd);
        try{
        String sql = "insert into users (username,password) values (?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,name);
        pstmt.setString(2,psw);
        pstmt.executeUpdate();//查询数据要用stmt.executeQuery();
        pstmt.close();
        conn.close();
        int user_id = login_user.login(name,psw);
        return user_id;
    }
        catch (Exception e)
    {
        conn.close();
        System.out.println("出现异常，请检查你的输入");
    }
        return 0;
    }
}
