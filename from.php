<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>待办事务</title>
    <link rel="stylesheet" href="./from.css">
</head>
<script>
    alert("欢迎使用事务待办系统")
</script>
<body>
    
<div class="container">
    <div class="container-forms">

        <div class="container-info">
            
            <div class="info-item">
                <div class="table">
                    <div class="table-cell">
                        <p>
                            你有账户吗?
                        </p>
                        <button class="info-btn">
                            登 录
                        </button>
                   
                    </div>
                </div>
            </div>


            <div class="info-item">
                <div class="table">
                    <div class="table-cell">
                        <p>
                            没有账户快来注册?
                        </p>
                        <button class="info-btn">
                            注 册
                        </button>
                   
                    </div>
                </div>
            </div>


        </div>

        <div class="container-form">

            <div class="form-item login-in">
                <div class="table">
                    <div class="table-cell">
                        <form action="login in.php" method="get">

<input type="text"name="login-Username" placeholder="账号"id>
<input type="password"name="login-Password" placeholder="密码"id>

<button class="btn" type="submit">
 登 录
</button>
</form>
                           
                         
                    </div>
                </div>
            </div>

            <div class="form-item sign-up">
                <div class="table">
                    <div class="table-cell">
                        <form action="sign up.php" method="get">

                            <input type="text"name="sign-fullName" placeholder="用户名"id>
                            <input type="text"name="sign-Username" placeholder="账号"id>
                            <input type="password"name="sign-Password" placeholder="密码"id>
                            <button class="btn" type="submit">
                              注 册
                            </button>
                            </form>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
<script src="./from.js">


</script>
</body>
</html>