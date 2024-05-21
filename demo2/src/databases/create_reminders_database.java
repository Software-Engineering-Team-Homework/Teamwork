package databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class create_reminders_database {
    public static void create() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = ul.url_get();
        String urn = un.urn_get();
        String pwd = pd.pwd_get();
        Connection conn = (Connection) DriverManager.getConnection(url,urn,pwd);
        String sql = "CREATE TABLE reminders (\n" +
                "    reminder_id INT AUTO_INCREMENT PRIMARY KEY comment '事项id',\n" +
                "    user_id INT NOT NULL comment '用户id',\n" +
                "    title VARCHAR(255) NOT NULL comment '事项标题',\n" +
                "    description TEXT comment '事项描述',\n" +
                "    reminder_date DATETIME NOT NULL comment '截止日期和时间',\n" +
                "    completed BOOLEAN DEFAULT FALSE comment '标记事项是否已完成',\n" +
                "    created_at DATETIME DEFAULT CURRENT_TIMESTAMP comment '创建时间',\n" +
                "    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更改时间',\n" +
                "    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE\n" +
                ")comment '事项查询表';";//创建提醒事项数据库
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }
}
