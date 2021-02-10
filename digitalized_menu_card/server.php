<?php
session_start();

// initializing variables
$name = "";
$email    = "";
$feedback ="";
$errors = array(); 

// connect to the database
$db = mysqli_connect('localhost', 'root', '', 'mymenu');

// REGISTER USER
if (isset($_POST['con_user'])) {
  // receive all input values from the form
  $name = mysqli_real_escape_string($db, $_POST['name']);
  $email = mysqli_real_escape_string($db, $_POST['email']);
  $address = mysqli_real_escape_string($db, $_POST['address']);

  // form validation: ensure that the form is correctly filled ...
  // by adding (array_push()) corresponding error unto $errors array
  if (empty($name)) { array_push($errors, "Username is required"); }
  if (empty($email)) { array_push($errors, "Email is required"); }
  if (empty($feedback)) { array_push($errors, "feedback is required"); }
  //if ($password_1 != $password_2) {
	//array_push($errors, "The two passwords do not match");
  //}

  // first check the database to make sure 
  // a user does not already exist with the same username and/or email
  $user_check_query = "SELECT * FROM feedback WHERE username='$name' OR email='$email' LIMIT 1";
  $result = mysqli_query($db, $user_check_query);
  $user = mysqli_fetch_assoc($result);
  
  //if ($user) { // if user exists
    //if ($user['username'] === $username) {
      //array_push($errors, "Username already exists");
    //}

    //if ($user['email'] === $email) {
      //array_push($errors, "email already exists");
    //}
  //}

  // Finally, register user if there are no errors in the form
  if (count($errors) == 0) {
  	//$password = md5($password_1);//encrypt the password before saving in the database

  	$query = "INSERT INTO feedback (name, email, feedback) 
  			  VALUES('$name', '$email', '$feedback')";
  	mysqli_query($db, $query);
  	$_SESSION['name'] = $name;
  	$_SESSION['success'] = "Thank You for your feedback";
  	header('location: index21.php');
  }
}
