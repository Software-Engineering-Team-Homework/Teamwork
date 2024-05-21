import databases.create_reminders_database;
import databases.create_users_database;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws Exception{
        //new LoginFrame1().setVisible(true);
        //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        create_users_database.create();
        create_reminders_database.create();
        //create_user.create("bx", "666");
        //reminder_create.create(1,"今日作业","1 f4a 2 software_teamwork","20240520172000");
    }
}