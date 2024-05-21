package frame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import users.*;


public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    public LoginFrame() {
        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        loginButton = new JButton("登录");
        registerButton = new JButton("注册");

        setLayout(new GridLayout(3, 2));
        add(new JLabel("用户名:"));
        add(usernameField);
        add(new JLabel("密码:"));
        add(passwordField);
        add(loginButton);
        add(registerButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                int user_id = 0;
                try {
                    user_id = login_user.login(username,password);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                if(user_id != 0)
                // 假设登录成功，打开注意事项清单界面
                {
                    JOptionPane.showMessageDialog(LoginFrame.this, "登录成功！");
                    new ReminderListFrame(user_id).setVisible(true);
                    // 关闭当前窗口
                    LoginFrame.this.dispose();
                }
                else
                {
                    JOptionPane.showMessageDialog(LoginFrame.this, "登录失败！");
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 打开注册界面
                try {
                    new RegisterFrame().setVisible(true);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                // 关闭当前窗口
                LoginFrame.this.dispose();
            }
        });

        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginFrame().setVisible(true);
            }
        });
    }
}

