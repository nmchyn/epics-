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
    
</style>
<div>
    <ul>
        <li><a href="admin.php">Home</a></li>
        <li><a href="getOrders.php">Orders</a></li>
        <li><a href="UpdateAndDeleted.php">Update/Delete</a></li>
        <li><a href="Logout.php">Logout</a></li>
    </ul>
</div>
<form action="" method="post">
    <center> <br><h2 >Add Items</h2><br>
    <table>
        <tr>
            <tD><label>Image:</label></tD><td><input type="text" placeholder="image name" name="image"></td> </tr>
          <tr>   <tD><label>Item Name:</label></tD><td><input type="text" placeholder="item name" name="item"></td> </tr>
           <tr>  <tD><label>Price:</label></tD><td><input type="text" placeholder="price" name="price"></td> </tr>
          <tr>   <tD><label>Category:</label></tD><td><select name="cat"><option>select category</option><option value="veg_items">Veg</option><option value="menu_detail">Non-Veg</option><option value="cool_items">Beverages</option><option value="ice_items">Ice Creams</option></select></td> </tr>
           <tr>  <tD></tD><td><input type="submit"  name="submit"></td>
        </tr>
    </table>
</form>
