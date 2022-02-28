<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン成功</title>
<script src="https://kit.fontawesome.com/fe1fd552f1.js" crossorigin="anonymous"></script>

<link rel="stylesheet" href="reset.css">
<link rel="stylesheet" href="style.css">
<link rel="stylesheet" href="button.css">

</head>
<body link="#006095" vlink="#006095" alink="yellow">
<div class="hhead">
<header>
      <h1 class="heading">
      ユーザーログイン画面
      </h1>
       <div class="social">
      <i class="fab fa-instagram-square"></i>
      <a href="https://www.instagram.com/takaharayota/">
        <i class="fab fa-instagram"></i></a>
        <a href="https://www.youtube.com/channel/UC0rOdPYrfo1bjfN2C4BPiWg">
        <i class="fab fa-youtube"></i></a>
        <a href="https://twitter.com/opensesametech">
        <i class="fab fa-twitter"></i></a>
        
        </div>
     
        
      
    </header>
     <nav>
      <ul class="nav-list">
        <li class="nav-list-item">
          <a href="login.html" class="btn btn--purple btn--radius btn--cubic"><i class="fas fa-angle-double-right fa-position-left"></i>HOME<i class="fas fa-angle-double-left fa-position-right"></i></a>
        </li>
        
        <li class="nav-list-item">
        <a href="https://www.opensesame-tech.com/recruit/information/" class="btn btn--purple btn--radius btn--cubic"><i class="fas fa-angle-double-right fa-position-left"></i>採用情報<i class="fas fa-angle-double-left fa-position-right"></i></a>
          </li>       
        <li class="nav-list-item"><a href="https://twitter.com/shinoyan" class="btn btn--purple btn--radius btn--cubic"><i class="fas fa-angle-double-right fa-position-left"></i>憧れのあの人へ<i class="fas fa-angle-double-left fa-position-right"></i></a></li>      </ul>
        
    </nav></div>
    <div class="gazou4">
    
    <div  class="btn btn-flat">
	
	<span>
    
<input type = "button" onclick="location.href='test.html'" value="自己分析" class="button">
</span>
	
	</div>
	
    

<%String str=(String)session.getAttribute("employment");%>
<%if(str.equals("A")){ %>
 <div  class="btn btn-flat">
	
	<span>
    <input type = "button" onclick="location.href='resultA.html'" value="職業適正を見る"  class="button">

</span>
	
	</div>
	
    

<%} else if(str.equals("B")){ %>
<div  class="btn btn-flat">
	
	<span>
    <input type = "button" onclick="location.href='resultB.html'" value="職業適正を見る"  class="button">

</span>
	
	</div>
	
<%}else if(str.equals("C")){ %>
<div  class="btn btn-flat">
	
	<span>
    <input type = "button" onclick="location.href='resultC.html'" value="職業適正を見る"  class="button">

</span>
	
	</div>
	
<%} %>
<br>


</div>




</body>
</html>