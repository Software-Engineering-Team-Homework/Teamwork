<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>导航页</title>
    <link rel="stylesheet" href="./test.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudFlare.com/ajax/libs/font-awesome/5.8.2/css/all.css"/>
<script src="https://cdnjs.cloudFlare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>

    <div class="container">
     <input class="txtb" type="text" placeholder="Add a task">
         <div class="notcomp">
             <h3>Not Completed</h3>

            <div class="task">
            task
            <i class="fas fa-trash-alt"></i>
            <i class="fas fa-check"></i>
            </div>

           
        </div>


        <div class="comp">
               <h3>Completed</h3>
              
        </div>
</div>



<script text="text/javascript">
//监控键盘
$(".txtb").on("keyup",function(e)
{
    //监控键盘按下的是否为回车键及输入框的值是否为空
    if(e.keyCode ==13 && $(".txbt").val()!="")
        {
//生成一个带有输入框的值为文本的div标签
var task=$("<div class='task'></div>").text($(".txtb").val());
   //生成一个删除标签，并删除图标绑定点击时间
var del=$("<i class='fas fa-trash-alt'></i>").click(function()
        {
            //找到确认图标的父标签（对应的类为task的div标签）
             var p=$(this).parent();

              //淡出
               p.fadeOut(function(){

          //将comp中的标签移除
                p.remove();
 });
});
//生成一个确认图标并确认图标绑定点击事件
 var check=$("<i class='fas fa-check'></i>").click(function()
        {
             //找到确认图标的父标签（对应的类为task的div标签）
            var p=$(this).parent();
            //将p淡出
        p.fadeOut(function()
         {
          //将div标签加入到从comp的div效果中  
            $(".comp").append(p);
       //将p淡入以达到显示在comp的div中的效果
            p.fadeIn();
        });
       //将此标签从类为notcomp的div中移出
       $(this).remove();
        });
       

 
        
        //将确认和删除图标加入前面生产div标签中
 task.append(del,check);

 //将刚刚生成的div标签放置到notcoomp的顶标签
 $(".notcomp").append(task);
 //将输入框的值清空
 $(".txtb").val("");}
    });
    </script>
</body>
</html>