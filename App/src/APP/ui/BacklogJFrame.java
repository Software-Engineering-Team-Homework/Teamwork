package APP.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BacklogJFrame extends JFrame implements KeyListener, ActionListener {


    public BacklogJFrame(){
        initJFrame();


        //让界面显示出来
        this.setVisible(true);
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

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
