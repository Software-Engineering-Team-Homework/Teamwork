<?php
//语法:mysqli_connect('域名','账号','密码','数据库','端口号')
$con=mysqli_connect('localhost','root','','study');
// var_dump($con);
if($con)
{
mysqli_query($con,'set names utf8');

// 插入语句
// 语法：insert into 表名(字段1) values(字段1值)"
//$sql="insert into userinfo(username,password) values('张三','456789')";
//$sql="insert into userinfo values(null,'小明','4444')";


//修改 $sql="update 表名 set 字段1=’新值‘ where 条件
//$sql="update userinfo  set username='小红' where id=1";
//$sql="update userinfo set username='小刚'where id=15";


//删除 $sql="delete from 表名 where 条件"
//$sql="delete from userinfo where id=16";


//查询 $sql="select 信息 from 表 where 询查条件
//$sql="select * from userinfo";
//查询用户名
//$sql="select username from userinfo";
//查询id
//$sql="select * from userinfo where id=1";
//$sql="select * from userinfo "
//查询账户密码
//$sql="select * from userinfo where username='小红' and password='456789'";

// 发送sql语句
$result=mysqli_query($con,$sql);
var_dump($result);
 if($result->num_rows>0)
  {
     $arr=mysqli_fetch_all($result,MYSQLI_ASSOC);
      print_r($arr);
  }
  else{
      echo'查不到'; }
}else{
    echo'连接失败';
}
mysqli_close($con);
?>