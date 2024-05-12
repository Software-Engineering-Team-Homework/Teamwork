package reminders;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class createreminder {
    public static void create() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1:3306/itcast?useUnicode=true&characterEncoding=UTF-8&userSSL=false&serverTimezone=GMT%2B8";
        String urn = "root";
        String pwd = "123456";
        Connection conn = (Connection) DriverManager.getConnection(url,urn,pwd);

        int id = 1;//登录后进行操作时传当前账户的id
        String title = "software-teamwork1.0";//标题，用户输入
        String txt = "注意事项表增";//注意事项信息，由用户输入
        String ddl = "20240518235959";//截止时间，由用户输入
        String sql = "insert into reminders (user_id,title,description,reminder_date,completed) values (?,?,?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,id);
        pstmt.setString(2,title);
        pstmt.setString(3,txt);
        pstmt.setString(4,ddl);
        pstmt.setInt(5,0);
        int count = pstmt.executeUpdate();//查询数据要用stmt.executeQuery();
        if(count > 0)//返回记录结果
            System.out.println("记录成功");
        else
            System.out.println("记录失败");
        pstmt.close();
        conn.close();
    }
}
