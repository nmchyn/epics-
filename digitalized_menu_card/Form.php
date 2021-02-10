
<!DOCTYPE html>
<html>
<head>
  <title>Order Form</title>
<!--  <link rel="stylesheet" type="text/css" href="style.css">-->
</head>
<body>
  <div class="header">
  	
  </div>
<?php
$total=$_GET['total'];
?>
<center>   
    <h2>Order Form</h2>
    <form method="post" action="order.php">
        <input type="hidden" name="total" value="<?php echo $total;?>">
  	<div class="input-group">
  	  <label>Username</label>
  	  <input type="text" name="name" value="">
  	</div>
  	<div class="input-group">
  	  <label>Phone</label>
  	  <input type="text" name="phone" value="">
  	</div>
  	<div class="input-group">
      <label>Table</label>
      <select name="table"><option>select table</option>
          <?php
          for ($i=1;$i<=8;$i++){
          ?>
          <option><?php echo $i;?></option>
      <?php
          }
      ?>
      </select>
    </div>
  	<div class="input-group">
  	  <button type="submit" class="btn" name="user">Order now</button>
  	</div>
    </form></center>
<bR><a href="index.php">Go Back</a>
</body>
</html>


