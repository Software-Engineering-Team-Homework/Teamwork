<?php
$user=$_GET['login-Username'];
$psw=$_GET['login-Password'];
$con=mysqli_connect('localhost','root','','study');
function alert($str,$url)
{
    echo'<script> alert("'.$str.'");location.href="'.$url.'";</script>';
}
if($con)
{
    mysqli_query($con,'set names utf8');
    $sql="select * from userinfo where username='$user'and password='$psw'";
    $result=mysqli_query($con,$sql);
//var_dump($result);
if($result->num_rows>0)
  {
     $arr=mysqli_fetch_all($result,MYSQLI_ASSOC);
    alert(str:'登录成功',url:'test.php');
  }
  else
    alert(str:'账户或密码错误',url:'from.php');
      
}else
{
    echo'连接失败';
}
mysqli_close($con);


?>