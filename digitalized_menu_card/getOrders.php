<?php
if(isset($_POST['submit'])){
    $image=$_POST['image'];
    $itemname=$_POST['item'];
    $price=$_POST['price'];
    $category=$_POST['cat'];
    $conn = new mysqli("localhost", "root", "", "mymenu");
     $sql = "INSERT INTO `$category`(`id`, `name`, `image`, `price`) VALUES ('','$itemname','$image',$price)";
        if (mysqli_query($conn, $sql) === TRUE) {
           echo "inserted successfully";
        } else {
            echo "Error: in $category" . $sql . "<br>" . $conn->error;
        }
}
?>
<style>
    *{
        padding: 0px;
        margin: 0px;
    }
    ul,li{
        list-style-type: none;
        display: inline;
        margin: 5px;
    }
    a{
        text-decoration: none;
        color:black;
    }
    div{
        background: red;
        width: 100%;
        padding: 4px;
    }
    td{
        border: 1px solid black;
        border-collapse: collapse;
        
    }
</style>
<div>
    <ul>
        <li><a href="admin.php">Home</a></li>
        <li><a href="getOrders.php">Orders</a></li>
        <li><a href="UpdateAndDeleted.php">Update/Delete</a></li>
        <li><a href="Logout.php">Logout</a></li>
    </ul>
</div>
    <center> <br><h2 >Orders</h2><br>
    <table>
        <tr><td>Name</td><td>Phone</td><td>Booked Table</td><td>Total Ordered Money</td></tr>
        <?php
         $conn = new mysqli("localhost", "root", "", "mymenu");
     $sql = "SELECT * FROM `totalc`";
     $result=mysqli_query($conn, $sql);
//        if () {
            while($row = mysqli_fetch_assoc($result)) {
            echo '<tr><td>'.$row['name'].'</td><td>'.$row['phone'].'</td><td>'.$row['tablebooked'].'</td><td>'.$row['total'].'</td></tr>';
            }
//        } else {
//            echo "Error: in totalc" . $sql . "<br>" . $conn->error;
//        }
        ?>
    </table>
</form>
