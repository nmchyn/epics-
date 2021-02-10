<?php
$table=$_POST['table'];
$phone=$_POST['phone'];
$name=$_POST['name'];
$total=$_POST['total'];
    $conn = new mysqli("localhost", "root", "", "mymenu");
     $sql = "INSERT INTO `totalc` VALUES ('$name','$phone','$table',$total)";
        if (mysqli_query($conn, $sql) === TRUE) {
           echo "<br><br><center>Orders Confirmed Will Be Delivered Soon</center>";
        } else {
            echo "Error: in totalc" . $sql . "<br>" . $conn->error;
        }
?>
<br><a href="index.php">Go Back</a>
