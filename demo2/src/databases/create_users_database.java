package databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class create_users_database {
    public static void create() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = ul.url_get();
        String urn = un.urn_get();
        String pwd = pd.pwd_get();
        Connection conn = (Connection) DriverManager.getConnection(url,urn,pwd);
        String sql = "CREATE TABLE users (user_id INT AUTO_INCREMENT PRIMARY KEY,username VARCHAR(30) NOT NULL UNIQUE comment '账号',password VARCHAR(30) NOT NULL comment '密码',created_at DATETIME DEFAULT CURRENT_TIMESTAMP comment '创建时间')comment '用户表';";//创建用户表数据库
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }
}
