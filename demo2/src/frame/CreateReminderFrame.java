package frame;

import reminders.create_reminder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CreateReminderFrame extends JFrame {
    private JTextField titleField;
    private JTextField contentField;
    private JButton createButton;
    private JButton cancelButton;
    private List<Reminder> reminders;
    private DefaultListModel<String> listModel;
    private JSpinner yearSpinner;
    private JSpinner monthSpinner;
    private JSpinner daySpinner;
    private JSpinner hourSpinner;
    private JSpinner minuteSpinner;
    private JSpinner secondSpinner;

    public CreateReminderFrame(List<Reminder> reminders, DefaultListModel<String> listModel, int user_id) {
        this.reminders = reminders;
        this.listModel = listModel;

        titleField = new JTextField(15);
        contentField = new JTextField(15);
        yearSpinner = new JSpinner(new SpinnerNumberModel(2024, 2024, 2100, 1));
        monthSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 12, 1));
        daySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 31, 1));
        hourSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 23, 1));
        minuteSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
        secondSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
        createButton = new JButton("创建");
        cancelButton = new JButton("取消");

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(new JLabel("标题:"), constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        panel.add(titleField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        panel.add(new JLabel("内容:"), constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        panel.add(new JScrollPane(contentField), constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(new JLabel("截止年:"), constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        panel.add(yearSpinner, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(new JLabel("截止月:"), constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        panel.add(monthSpinner, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(new JLabel("截止日:"), constraints);

        constraints.gridx = 1;
        constraints.gridy = 4;
        panel.add(daySpinner, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        panel.add(new JLabel("截止时:"), constraints);

        constraints.gridx = 1;
        constraints.gridy = 5;
        panel.add(hourSpinner, constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        panel.add(new JLabel("截止分:"), constraints);

        constraints.gridx = 1;
        constraints.gridy = 6;
        panel.add(minuteSpinner, constraints);

        constraints.gridx = 0;
        constraints.gridy = 7;
        panel.add(new JLabel("截止秒:"), constraints);

        constraints.gridx = 1;
        constraints.gridy = 7;
        panel.add(secondSpinner, constraints);

        constraints.gridx = 0;
        constraints.gridy = 8;
        constraints.gridwidth = 1;
        panel.add(createButton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 8;
        panel.add(cancelButton, constraints);

        add(panel);

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String content = contentField.getText();
                int year = (int) yearSpinner.getValue();
                int month = (int) monthSpinner.getValue();
                int day = (int) daySpinner.getValue();
                int hour = (int) hourSpinner.getValue();
                int minute = (int) minuteSpinner.getValue();
                int second = (int) secondSpinner.getValue();
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month - 1, day, hour, minute, second);
                Date deadline = calendar.getTime();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String deadlineString = dateFormat.format(deadline);
                int reminder_id = 0;
                try {
                    reminder_id = create_reminder.create(user_id, title, content, deadlineString);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                Reminder newReminder = new Reminder(reminder_id, user_id, title, deadlineString, content);
                reminders.add(newReminder);
                listModel.addElement(newReminder.getTitle() + " - 截止时间: " + newReminder.getDeadline());
                try {
                    new ReminderListFrame(user_id).setVisible(true);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                CreateReminderFrame.this.dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new ReminderListFrame(user_id).setVisible(true);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                CreateReminderFrame.this.dispose();
            }
        });

        setSize(300, 400); // Adjusted height to accommodate larger content area
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}


