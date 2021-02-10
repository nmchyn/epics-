<?php 
    $name= $_Post['name'];
	$visitor_email=$_POST['email'];
	$message=$_POST['message'];
	
	$email_from='EasyTutorial@avinashkr.com';
	$email_subject= "Form Submission";
	$email_body="User Name: $name.\n".
	            "User Email: $visitor_email.\n".
				  "User Message: $message.\n";
    $to="avinash6252@gmil.com";
    $headers="From: $email_from \r\n";
    $headers="Reply-To: $visitor_email \r\n";
    mail($to,$email_subject,$email_body,$headers);
    header("Location: index.html");
?>	