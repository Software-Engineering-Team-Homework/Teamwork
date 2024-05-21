package frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import users.*;
import reminders.*;

public class RegisterFrame extends JFrame{
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton registerButton;

    public RegisterFrame() throws Exception {
        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        registerButton = new JButton("注册");

        setLayout(new GridLayout(3, 2));
        add(new JLabel("用户名:"));
        add(usernameField);
        add(new JLabel("密码:"));
        add(passwordField);
        add(registerButton);


        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                int user_id = 0;
                try {
                    user_id = create_user.create(username,password);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                // 假设注册成功，返回登录界面
                if (user_id != 0) {
                    {
                        JOptionPane.showMessageDialog(RegisterFrame.this, "注册成功！");
                        new LoginFrame().setVisible(true);
                        // 关闭当前窗口
                        RegisterFrame.this.dispose();
                    }
            }
        }
        });

        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}

