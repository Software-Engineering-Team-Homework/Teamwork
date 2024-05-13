package APP.util;

import java.util.ArrayList;
import java.util.Random;

public class CodeUtil {

    //获取验证码
    public static String getCode(){
        /*
        * 1.生成一个验证码
        * 2.创建一个集合
        * 3.添加四个随机字母
        * 4.在后面拼接数字
        * 5.把字符串变成字符数组
        * 6.最后把字符数组打乱
        * 7.再将数组变为字符串
        *
        *
        * */

        ArrayList<Character> list=new ArrayList<>();
        //添加字母
        for (int i = 0; i < 26; i++) {
            list.add((char)('a'+i));
            list.add((char)('A'+i));
        }
        //获取随机四个字母
        String result="";
        Random r=new Random();
        for (int i = 0; i < 4; i++) {
            int index=r.nextInt(list.size());
            char c=list.get(index);
            result=result+c;
        }

        //拼接数字
        int number=r.nextInt(10);
        result=result+number;
        char[] results=result.toCharArray();
        int index=r.nextInt(results.length);
        //交换顺序,打乱数据
        char temp=results[4];
        results[4]=results[index];
        results[index]=temp;
        //变回字符串
        String code=new String(results);
        return code;

    }
}
