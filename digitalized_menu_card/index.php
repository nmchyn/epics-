<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <style>
    /* Remove the navbar's default margin-bottom and rounded borders */ 
    .navbar {
      margin-bottom: 0;
      border-radius: 0;
    }
    
    /* Set height of the grid so .sidenav can be 100% (adjust as needed) */
    .row.content {height: 450px}
    
    /* Set gray background color and 100% height */
    .sidenav {
      padding-top: 20px;
      background-color: #f1f1f1;
      height: 100%;
    }
    
    /* Set black background color, white text and some padding */
    footer {
      background-color: #555;
      color: white;
      padding: 15px;
    }
    
    /* On small screens, set height to 'auto' for sidenav and grid */
    @media screen and (max-width: 767px) {
      .sidenav {
        height: auto;
        padding: 15px;
      }
      .row.content {height:auto;} 
    }
li a:hover{
     background-color:#555;
     color:white;
}	 
  </style>
</head>
<body>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Home</a></li>
        <li><a href="#">About</a></li>
        <li><a href="index21.html">Contact</a></li>
        <li><a href="admin.php">Admin</a></li>
        <li><a href="Cancel.php">Cancel</a></li>
      </ul>
       </div>
</div>
</nav>
  
<div class="container-fluid text-center">    
  <div class="row content">
    <div class="col-sm-2 sidenav">
	<ul>
            <li><font size="4"><a href="Veg.php">Non Veg</a></font></li>
	  <br>
      <li><font size="4"><a href="index2.php">Veg</a></font></li>
	  <br>
      <li><font size="4"><a href="index4.php">Drinks</a></font></li>
	  <br>
      <li><font size="4"><a href="index3.php">Ice creams</a></font></li>
	  </ul>
    </div>
    <div class="col-sm-8 text-left"> 
      <h1 align="center">Welcome</h1>
      <img src="res2.jpeg" height="350" width="850">
    </div>
    <div class="col-sm-2 sidenav">
      <div class="well">
        <a href="MyOrders.php"><input type="button" value="My Orders"></a>
      </div>
<!--      <div class="well">
        <Button type="button">Order now</button>
      </div>-->
    </div>
  </div>
</div>
<footer class="container-fluid text-center">
  <p>Footer Text</p>
</footer>
</body>
</html>
