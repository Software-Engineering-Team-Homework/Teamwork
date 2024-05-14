<?php
$name=$_GET['sign-fullName'];
$user=$_GET['sign-Username'];
$psw=$_GET['sign-Password'];
$con=mysqli_connect('localhost','root','','study');

function alert($str,$url)
{
    echo'<script> alert("'.$str.'");location.href="'.$url.'";</script>';
}


if($con)
{
    mysqli_query($con,'set names utf8');
    $sql1="select * from userinfo where username='$user'or name='$name'";
    $result=mysqli_query($con,$sql1);
//var_dump($result);
if($result->num_rows>0)
  {
     alert(str:'已存在该用户,点击确定返回',url:'from.php');
     
  }else
  {
    if(strlen($psw)<5)
    {
        alert(str:'密码不能少于5位',url:'from.php');
    }
    else{
        $sql2="insert into userinfo(name,username,password) values('$name','$user','$psw')";
        $result=mysqli_query($con,$sql2);
        alert(str:'注册成功,点击确定返回',url:'from.php');
       
    }
   
  }

}else{
    echo'连接失败';
}
mysqli_close($con);
?>