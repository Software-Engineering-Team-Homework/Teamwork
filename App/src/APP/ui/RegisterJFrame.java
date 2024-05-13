package APP.ui;

import APP.domain.User;
import APP.domain.UserService;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegisterJFrame extends JFrame implements MouseListener {

    //创建存储账号密码的数据
    private static final String USER_PASSWORD_FILE="Users.txt";


    //用户数据存储
    public UserService userService = new UserService();

    //1.注册按钮
    JButton register=new JButton();
    //2.重置按钮
    JButton reset=new JButton();
    //3.用户名输入框
    JTextField username=new JTextField();
    //4.密码输入框
    JTextField password=new JTextField();
    //5.重复密码输入框
    JTextField repassWord=new JTextField();



    public RegisterJFrame(){
        //初始化界面
        initJFrame();

        //在界面中添加内容,监听事件,图片背景
        initView();

        //显示出来
        this.setVisible(true);

//        getContentPane();
    }

    //丰富界面
    private void initView() {
        //        1.添加用户名图片
        JLabel usernameTxt=new JLabel(new ImageIcon("image\\register\\注册用户名.png"));
        usernameTxt.setBounds(105,135,80,17);
        this.getContentPane().add(usernameTxt);

//        .添加用户输入框
        username.setBounds(195,135,200,30);
        this.getContentPane().add(username);

        //3.添加密码文字
        JLabel passwordTxt=new JLabel(new ImageIcon("image\\register\\注册密码.png"));
        passwordTxt.setBounds(125,195,65,16);
        this.getContentPane().add(passwordTxt);

        //.密码输入框
        password.setBounds(195,195,200,30);
        this.getContentPane().add(password);

        //4..添加重复密码文字
        JLabel rePasswordTxt=new JLabel(new ImageIcon("image\\register\\再次输入密码.png"));
        rePasswordTxt.setBounds(90,256,100,16);
        this.getContentPane().add(rePasswordTxt);

        //重复密码输入框
        repassWord.setBounds(195,256,200,30);
        this.getContentPane().add(repassWord);

       //5.添加重置按钮
        //位置
        reset.setBounds(123,310,128,47);
        //添加图片路径
        reset.setIcon(new ImageIcon("image\\register\\重置按钮.png"));
//        去除边框
        reset.setBorderPainted(false);
        //去除背景
        reset.setContentAreaFilled(false);
        //绑定鼠标事件
        reset.addMouseListener(this);
        //显示在界面上
        this.getContentPane().add(reset);

        //6,添加注册按钮
        register.setBounds(256,310,128,47);
        register.setIcon(new ImageIcon("image\\register\\注册按钮.png"));
        register.setBorderPainted(false);
        register.setContentAreaFilled(false);
        register.addMouseListener(this);
        this.getContentPane().add(register);

        //7.添加背景图片
        JLabel background=new JLabel(new ImageIcon("image\\register\\background.png"));
        background.setBounds(0,0,470,390);
        this.getContentPane().add(background);


    }

    private void initJFrame() {
        //大小
        this.setSize(488,430);
        //界面标题
        this.setTitle("TO-DO LIST 注册");
        //置顶
        this.setAlwaysOnTop(true);
        //界面居中
        this.setLocationRelativeTo(null);
        //关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //取消内部默认布局
        this.setLayout(null);

    }

//    鼠标点击
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==reset){
            System.out.println("点击了重置按钮");
            //直接再次出现注册界面
            //关闭注册界面,再打开
            this.setVisible(false);
            new RegisterJFrame();
        } else if (e.getSource()==register) {
            System.out.println("点击了注册按钮");
            //获取三个文本输入的内容
            String usernameInput=username.getText();
            String passwordInput=password.getText();
            String rePassWordInput=repassWord.getText();


            if(usernameInput.length()==0||passwordInput.length()==0){
                System.out.println("用户名或密码为空");
                showJDialog("用户名或密码为空");
            }else if (rePassWordInput.length()==0){
                System.out.println("再次输入密码为空");
                showJDialog("再次输入密码为空");
            } else if (!passwordInput.equalsIgnoreCase(rePassWordInput)) {
                System.out.println("两次输入密码不一致");
                showJDialog("两次输入密码不一致");
            } else if (correctUsers(usernameInput)) {
                System.out.println("用户名已经存在,请重新输入");
                showJDialog("用户名已经存在,请重新输入");
            } else {
                System.out.println("注册成功");
                //1.把注册的信息放进数据存储
                try {
                    writeFile(usernameInput,passwordInput);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
//                userService.registerUser(usernameInput,passwordInput);
                //2.关闭注册界面
                this.setVisible(false);
                //打开登录界面
                new LoginIFrame();
            }

        }

    }

    //将注册的账号密码写入文档当中,且已经去重
    public void writeFile(String usernameInput,String passwordInput) throws IOException {
        FileWriter userPasswordFile=new FileWriter(USER_PASSWORD_FILE,true);
        String users=usernameInput+","+passwordInput;
        userPasswordFile.append(users+"\n");
        userPasswordFile.close();
    }

    //从文件中获取账号密码,与当前的账号形成判断,看是否已经存在
    //存在返回true,不存在返回false
    private static boolean correctUsers(String usernameInput){
        String userFilePath="Users.txt";
        //        出错显示
        if(userFilePath==null){
            System.out.println("userFilePath is null");
        }else {
            //读取文档中的用户名
            try {
                //调用readFile函数,读取文件,并把他们放到一个list集合中,方便查询
                List<String> users=readFile(userFilePath);
                //遍历list集合,逐渐获取每个用户名,并于当前用户名比较
                for (int i = 0; i < users.size(); i++) {
                    //逐个获取没用用户的用户名,用来判断
                    String passWord=users.get(i).split(",")[0];
                    //判断
                    //说明已经存在
                    if(usernameInput.equals(passWord)){
                        return true;
                    }
                }
            }catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return false;

    }

    //读取文件,并且返回一个集合的形式
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
        if (e.getSource()==reset){
            reset.setIcon(new ImageIcon("image\\register\\重置按下.png"));
        } else if (e.getSource()==register) {
            register.setIcon(new ImageIcon("image\\register\\注册按下.png"));
        }
    }

    //鼠标松开
    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == reset) {
            reset.setIcon(new ImageIcon("image\\register\\重置按钮.png"));
        } else if (e.getSource() == register) {
            register.setIcon(new ImageIcon("image\\register\\注册按钮.png"));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}


