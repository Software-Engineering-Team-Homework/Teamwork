package frame;

import databases.pd;
import databases.ul;
import databases.un;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import databases.pd;
import databases.ul;
import databases.un;
import reminders.mark_complete;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class ReminderListFrame extends JFrame {
    private JList<String> reminderList;
    private DefaultListModel<String> listModel;
    private JButton createButton;
    private JButton backButton;
    private List<Reminder> reminders;

    public ReminderListFrame(int user_id)  {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = ul.url_get();
            String urn = un.urn_get();
            String pwd = pd.pwd_get();
            Connection conn = (Connection) DriverManager.getConnection(url,urn,pwd);
            reminders = new ArrayList<>();
            String sql = "select * from reminders where user_id = ? order by reminder_date";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,user_id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next())
            {
                if (rs.getInt(6)==1)//事件已完成则不再显示
                    continue;
                reminders.add(new Reminder(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(5),rs.getString(4),rs.getString(7),rs.getString(8)));
            }
            rs.close();
            pstmt.close();
            conn.close();
        }
            catch (Exception e) {
            throw new RuntimeException(e);
        }

        listModel = new DefaultListModel<>();
        for (Reminder reminder : reminders) {
            listModel.addElement(reminder.getTitle() + " - 截止时间: " + reminder.getDeadline());
        }

        reminderList = new JList<>(listModel);
        reminderList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        createButton = new JButton("创建注意事项");
        backButton = new JButton("退出登录");

        setLayout(new BorderLayout());
        add(new JScrollPane(reminderList), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(createButton);
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateReminderFrame(reminders, listModel,user_id).setVisible(true);
                ReminderListFrame.this.dispose();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginFrame().setVisible(true);
                ReminderListFrame.this.dispose();
            }
        });

        reminderList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = reminderList.locationToIndex(e.getPoint());
                    System.out.println(index);
                    Reminder selectedReminder = reminders.get(index);
                    try {
                        new ReminderDetailFrame(selectedReminder,user_id).setVisible(true);
                    }
                     catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    ReminderListFrame.this.dispose();
                }
                else if (SwingUtilities.isRightMouseButton(e)) {
                    int index = reminderList.locationToIndex(e.getPoint());
                    Reminder selectedReminder = reminders.get(index);

                    int option = JOptionPane.showConfirmDialog(ReminderListFrame.this, "该事项已完成？", "确认", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        // 将提醒事项标记为已完成
                        try {
                            mark_complete.finish(selectedReminder.getUser_id(),selectedReminder.getreminder_id());
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                        selectedReminder.setComplete(1);

                        // 更新列表
                        listModel.remove(index);
            }
            }
            }
        });
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}


