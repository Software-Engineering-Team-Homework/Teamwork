package frame;

import javax.swing.*;
import java.awt.*;
import users.*;
import reminders.*;

public class ReminderDetailFrame extends JFrame {
    private JTextArea titleArea;
    private JTextArea detailTextArea;
    private JLabel createdLabel;
    private JLabel modifiedLabel;
    private JTextArea deadlineArea;
    private JButton backButton;
    private JButton editButton;

    public ReminderDetailFrame(Reminder reminder, int user_id) {
        titleArea = new JTextArea();
        detailTextArea = new JTextArea();
        createdLabel = new JLabel();
        modifiedLabel = new JLabel();
        deadlineArea = new JTextArea();
        backButton = new JButton("返回");
        editButton = new JButton("修改");

        setTitle("Reminder Detail");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel infoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        infoPanel.add(new JLabel("标题:"), gbc);
        gbc.gridx++;
        infoPanel.add(new JScrollPane(titleArea), gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        infoPanel.add(new JLabel("具体内容:"), gbc);
        gbc.gridx++;
        infoPanel.add(new JScrollPane(detailTextArea), gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        infoPanel.add(new JLabel("创建时间:"), gbc);
        gbc.gridx++;
        infoPanel.add(createdLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        infoPanel.add(new JLabel("修改时间:"), gbc);
        gbc.gridx++;
        infoPanel.add(modifiedLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        infoPanel.add(new JLabel("截止时间:"), gbc);
        gbc.gridx++;
        infoPanel.add(new JScrollPane(deadlineArea), gbc);

        add(infoPanel, BorderLayout.CENTER);
        add(backButton, BorderLayout.SOUTH);
        add(editButton, BorderLayout.NORTH);

        backButton.addActionListener(e -> {
            new ReminderListFrame(user_id).setVisible(true);
            ReminderDetailFrame.this.dispose();
        });

        editButton.addActionListener(e -> {
            String updatetitle = JOptionPane.showInputDialog("请输入修改后的标题:", titleArea.getText());
            String updatedDetail = JOptionPane.showInputDialog("请输入修改后的内容:", detailTextArea.getText());
            String updatedddl = JOptionPane.showInputDialog("请输入修改后的截止时间:", deadlineArea.getText());
            try {
                modify_title.modify(user_id, reminder.getreminder_id(), updatetitle);
                modify_description.modify(user_id, reminder.getreminder_id(), updatedDetail);
                modify_date.modify(user_id, reminder.getreminder_id(), updatedddl);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            if (updatedDetail != null) {
                try {
                    titleArea.setText(get_title.getinf(user_id, reminder.getreminder_id()));
                    detailTextArea.setText(get_description.getinf(user_id, reminder.getreminder_id()));
                    deadlineArea.setText(get_date.getinf(user_id, reminder.getreminder_id()));

                    reminder.setTitle(updatetitle);
                    reminder.setDetail(updatedDetail);
                    reminder.setDeadline(updatedDetail);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        titleArea.setText(reminder.getTitle());
        detailTextArea.setText(reminder.getDetail());
        createdLabel.setText(reminder.getCreatedTime());
        modifiedLabel.setText(reminder.getModifiedTime());
        deadlineArea.setText(reminder.getDeadline());

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}







