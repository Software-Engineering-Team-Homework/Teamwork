package APP.ui;

import APP.domain.User;
import APP.domain.UserService;
import APP.util.CodeUtil;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.AllPermission;
import java.util.ArrayList;
import java.util.List;

public class LoginIFrame extends JFrame implements MouseListener {

    //设置静态方法,在本方法中都可以用
//    static ArrayList<User> allUsers =new ArrayList<>();
//    static {
//        allUsers.add(new User("zhangsan","123"));
//        allUsers.add(new User("lisi","1234"));
//    }




//    public static ArrayList<User> getAllUsers() {
//        allUsers.add(new User("zhangsan","123"));
//        allUsers.add(new User("lisi","1234"));
//        return allUsers;
//    }

    //用户数据获取
     public UserService userService = new UserService();


    //    在多个方法中用到
    //1.登录按钮
    JButton login=new JButton();
    //2,注册按钮
    JButton register=new JButton();

    //3.用户输入宽
    JTextField username=new JTextField();
    //4.密码输入
    JTextField password=new JTextField();
    //5.验证号码输入
    JTextField code=new JTextField();

    //正确的验证码
    JLabel rightCode=new JLabel();


    public LoginIFrame(){
        //初始化界面
        initJFrame();

        //在这个界面中添加内容,图片背景样式等
        initView();

        //显示界面
        this.setVisible(true);
    }


    //初始化界面
    private void initJFrame() {
        //宽高
        this.setSize(488,430);
        //设置标题
        this.setTitle("TO-DO-LIST  V1.0登录");
        //设置关闭模式
        this.setDefaultCloseOperation(3);
        //设置居中
        this.setLocationRelativeTo(null);
        //设置置顶
        this.setAlwaysOnTop(true);
        //取消内部默认布局
        this.setLayout(null);
    }

    //丰富界面
    private void initView() {
//        1.添加用户名图片
        JLabel usernameTxt=new JLabel(new ImageIcon("image\\login\\用户名.png"));
        usernameTxt.setBounds(116,135,47,17);
        this.getContentPane().add(usernameTxt);

//        2.添加用户输入框
        username.setBounds(195,134,200,30);
        this.getContentPane().add(username);

        //3.添加密码文字
        JLabel passwordTxt=new JLabel(new ImageIcon("image\\login\\密码.png"));
        passwordTxt.setBounds(130,195,32,16);
        this.getContentPane().add(passwordTxt);

        //3.密码输入框
        password.setBounds(195,195,200,30);
        this.getContentPane().add(password);

//        5.验证码提示
        JLabel codeTxt=new JLabel(new ImageIcon("image\\login\\验证码.png"));
        codeTxt.setBounds(133,256,50,30);
        this.getContentPane().add(codeTxt);

        //验证码输入框
        code.setBounds(195,256,100,30);
        this.getContentPane().add(code);

        String codeStr= CodeUtil.getCode();
        //设置内容
        rightCode.setText(codeStr);
        //绑定鼠标事件
        rightCode.addMouseListener(this);
        rightCode.setBounds(300,256,50,30);
        //添加到界面
        this.getContentPane().add(rightCode);

        //6.添加登录按钮
        login.setBounds(123,310,128,47);
        login.setIcon(new ImageIcon("image\\login\\登录按钮.png"));
//        去除边框
        login.setBorderPainted(false);
//        去除背景
        login.setContentAreaFilled(false);
        //绑定鼠标事件
        login.addMouseListener(this);
        //显示在界面上
        this.getContentPane().add(login);

        //7.添加注册按钮
        register.setBounds(256,310,128,47);
        register.setIcon(new ImageIcon("image\\login\\注册按钮.png"));
        register.setBorderPainted(false);
        register.setContentAreaFilled(false);
        register.addMouseListener(this);
        this.getContentPane().add(register);

        //8.添加背景图片
        JLabel background=new JLabel(new ImageIcon("image\\login\\background.png"));
        background.setBounds(0,0,470,390);
        this.getContentPane().add(background);


    }



    //点击
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==login){
            System.out.println("点击了登录按钮");
            //获取两个文本输入的内容
            String usernameInput=username.getText();
            String passwordInput=password.getText();
            //获取用户输入的验证码
            String codeInput =code.getText();

            //创建一个User对象
//            User userInfo=new User(usernameInput,passwordInput);
            System.out.println("用户输入的用户为"+usernameInput);
            System.out.println("用户输入的密码为"+passwordInput);

            if (codeInput.length()==0){
                System.out.println("验证码为空");
                //提示信息
                showJDialog("验证码不能为空");
            }else if (usernameInput.length()==0||passwordInput.length()==0){
                System.out.println("用户名或密码为空");
                showJDialog("用户名或密码为空");
            } else if (!codeInput.equalsIgnoreCase(rightCode.getText())) {
                System.out.println("验证码输入错误");
                showJDialog("验证码输入错误");
            } else {
                try {
                    if (correctUser(usernameInput,passwordInput)) {
                        System.out.println("用户名和密码正确,登录成功");
                        //关闭当前登录界面
                        this.setVisible(false);
                        //打开系统的主界面
                        //并把当前登录的用户名传递给主界面
                        new BacklogJFrame();
                    }else {
                        System.out.println("用户名或密码错误");
                        showJDialog("用户名或密码有误");
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }else if (e.getSource()==register){
            System.out.println("点击了注册按钮");
            //关闭当前登录界面
            this.setVisible(false);
            //打开注册界面
            new RegisterJFrame();
        } else if (e.getSource()==rightCode) {
            System.out.println("更换验证码");
            //获取一个新的验证码
            String code = CodeUtil.getCode();
            //更新验证码数据
            rightCode.setText(code);
        }
    }

    //判断用户在集合中是否存在
//    public boolean contansUser(User userInput) {
//        for (int i = 0; i < allUsers.size(); i++) {
//            User rightUser=allUsers.get(i);
//            if(userInput.getUsername().equals(rightUser.getUsername()) && userInput.getPassword().equals(rightUser.getPassword())){
//                //有相同的代表存在，返回true，后面的不需要再比了
//                return true;
//            }
//        }
//        return false;
//
//    }



    //输入的用户名和密码与文档中的进行判断,看是否一致,一致,登录成功
    public boolean correctUser(String usernameInput,String passwordInput) throws IOException {
        //获取文件路径
        String userFilePath="Users.txt";
        //        出错显示
        if(userFilePath==null){
            System.out.println("userFilePath is null");
        }else {
            //获取文档中的用户名和密码
            try {
                //读取文件,获取数据,放进一个list集合中
                List<String> users=readFile(userFilePath);
                //遍历list集合
                for (int i = 0; i < users.size(); i++) {
                    //逐个获取用户名和密码
                    String username=users.get(i).split(",")[0];
                    String password=users.get(i).split(",")[1];
                    //判断用户名和密码是否一致
                    if (username.equals(usernameInput)&&password.equals(passwordInput)){
                        return true;
                    }
                }
            }catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return false;

    }

    private static List<String> readFile(String filePath) throws IOException {
        List<String> lines=new ArrayList<>();
        try(BufferedReader reader=new BufferedReader(new FileReader(filePath))){
            String line;
            while ((line=reader.readLine())!=null){
                lines.add(line);
            }
        }
        return lines;
    }
    //提示界面
    private void showJDialog(String content) {
        //创建一个提示弹框
        JDialog jDialog=new JDialog();
        //设置弹框大小
        jDialog.setSize(200,150);
        //弹宽置顶
        jDialog.setAlwaysOnTop(true);
        //弹框居中
        jDialog.setLocationRelativeTo(null);
        //弹框不关闭永远无法操作下面的界面
        jDialog.setModal(true);

        //创建Jlable对象管理文字并添加到弹框中
        JLabel warning=new JLabel(content);
        warning.setBounds(0,0,200,250);
        jDialog.getContentPane().add(warning);

        //弹框展示出来
        jDialog.setVisible(true);
    }


    //鼠标按下不松
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource()==login){
            login.setIcon(new ImageIcon("image\\login\\登录按下.png"));
        } else if (e.getSource()==register) {
            register.setIcon(new ImageIcon("image\\login\\注册按下.png"));
        }
    }

    //鼠标松开
    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == login) {
            login.setIcon(new ImageIcon("image\\login\\登录按下.png"));
        } else if (e.getSource() == register) {
            register.setIcon(new ImageIcon("image\\login\\注册按下.png"));
        }
    }

    //鼠标划入
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    //鼠标划出
    @Override
    public void mouseExited(MouseEvent e) {

    }
}
