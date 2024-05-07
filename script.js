const info_btn=document.getElementsByClassName("info-btn");
for(let i=0;info_btn.length;i++){
    info_btn[i].onclick=()=>{
        document.querySelector(".container").classList.toggle("login-in");
    }
   
};
//检测账号密码存在时能否登录转页

function newdata(){
    
    var fullname=document.getElementById("sign-fullname").value;
    var username=document.getElementById("sign-fullname").value;
    var passward=document.getElementById("sign-fullname").value;
}
function  login()
{
    var username=document.getElementById("login-fullname").value;
    var passward=document.getElementById("login-fullname").value;
console.log(username);
    
            alert("账户或密码错误")
       
}