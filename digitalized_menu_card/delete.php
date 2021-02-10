<?php
$conn = new mysqli("localhost", "root", "", "mymenu");
    $id=$_GET['id'];
    $category=$_GET['type'];
     $sql = "DELETE FROM `$category` WHERE `id`=$id";
        if (mysqli_query($conn, $sql) === TRUE) {
           echo "deleted successfully";
        } else {
            echo "Error: in $category" . $sql . "<br>" . $conn->error;
        }
        header("Location: UpdateAndDeleted.php");
?>