<?php include('server.php') ?>
<html>
  <head>
     <link rel="stylesheet" type="text/css" href=style.css">
  </head>
  <body>
       <div class="contact-title">
	          <h2 align="center">Contact Us....</h2>
	   </div>
	   <div class="contact-form">
	                 <form id="contact-form" method="post" action="contact-response.html">
					 <input name="name" type="text" class="form-control" placeholder="Customer Name" required>
					 </br>
					 <input name="email" type="email" class="form-control" placeholder="Customer Email" required>
					 </br>
					 <textarea name="feedback" class="form-control" placeholder="Customer feedback" required></textarea>
					 </br>
					 <input type="submit" class="form-control submit" name="con_user" value="SEND FEEDBACK">
					 </form>
		</div>
	</body>
</html>