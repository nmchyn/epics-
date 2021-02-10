<?php
$conn = new mysqli("localhost", "root", "", "mymenu");
if(isset($_POST['update'])){
    $id=$_POST['id'];
     $image=$_POST['image'];
    $itemname=$_POST['name'];
    $price=$_POST['price'];
    $category=$_GET['type'];
     $sql = "UPDATE `$category` SET `name`='$itemname',`image`='$image',`price`=$price WHERE `id`=$id";
        if (mysqli_query($conn, $sql) === TRUE) {
           echo "updated successfully";
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
                <th>Id</th>
                <th>Image</th>
                <th>Name</th>
                <th>Price</th>
                <th>Update</th>
                <th>Delete</th>
            </tr><?php
$sql = "SELECT * FROM `veg_items` ";
$result = mysqli_query($conn, $sql);
if (mysqli_num_rows($result) > 0) {
    while ($row = $result->fetch_assoc()) {
        ?>
      
             <tr>
                 <td><form action="UpdateAndDeleted.php?type=<?php echo "veg_items";?>" method="post"><?php echo $row['id'];?><input type="hidden" name="id" value="<?php echo $row['id'];?>" readonly=""></td>
                <td><input type="text" name="image" value="<?php echo $row['image'];?>"></td>
                <td><input type="text" name="name" value="<?php echo $row['name'];?>"></td>
                <td><input type="text" name="price" value="<?php echo $row['price'];?>"></td>
                <td><input type="submit" name="update" value="Update"></form></td>
                <th><button><a href="delete.php?id=<?php echo $row['id'];?>&type=<?php echo "veg_items";?>">Delete</a></button></th>
            </tr>
      
        <?php

    }
}
?>
              </table>
  <table>
            <tr>
                <th>Id</th>
                <th>Image</th>
                <th>Name</th>
                <th>Price</th>
                <th>Update</th>
                <th>Delete</th>
            </tr><?php
$sql = "SELECT * FROM `menu_detail` ";
$result = mysqli_query($conn, $sql);
if (mysqli_num_rows($result) > 0) {
    while ($row = $result->fetch_assoc()) {
        ?>
              <tr>
                  <td><form action="UpdateAndDeleted.php?type=<?php echo "menu_detail";?>" method="post"><?php echo $row['id'];?><input type="hidden" name="id" value="<?php echo $row['id'];?>" readonly=""></td>
                <td><input type="text" name="image" value="<?php echo $row['image'];?>"></td>
                <td><input type="text" name="name" value="<?php echo $row['name'];?>"></td>
                <td><input type="text" name="price" value="<?php echo $row['price'];?>"></td>
                <td><input type="submit" name="update" value="Update"></form></td>
                <th><button><a href="delete.php?id=<?php echo $row['id'];?>&type=<?php echo "menu_detail";?>">Delete</a></button></th>
            </tr>
            <?php

    }
}
?>
  </table>
  <table>
            <tr>
                <th>Id</th>
                <th>Image</th>
                <th>Name</th>
                <th>Price</th>
                <th>Update</th>
                <th>Delete</th>
            </tr>
                <?php
$sql = "SELECT * FROM `cool_items` ";
$result = mysqli_query($conn, $sql);
if (mysqli_num_rows($result) > 0) {
    while ($row = $result->fetch_assoc()) {
        ?>
              <tr>
                  <td><form action="UpdateAndDeleted.php?type=<?php echo "cool_items";?>" method="post"><?php echo $row['id'];?><input type="hidden" name="id" value="<?php echo $row['id'];?>" readonly=""></td>
                <td><input type="text" name="image" value="<?php echo $row['image'];?>"></td>
                <td><input type="text" name="name" value="<?php echo $row['name'];?>"></td>
                <td><input type="text" name="price" value="<?php echo $row['price'];?>"></td>
                <td><input type="submit" name="update" value="Update"></form></td>
                <th><button><a href="delete.php?id=<?php echo $row['id'];?>&type=<?php echo "cool_items";?>">Delete</a></button></th>
            </tr>
            <?php

    }
}
?>
</table>
<table>
            <tr>
                <th>Id</th>
                <th>Image</th>
                <th>Name</th>
                <th>Price</th>
                <th>Update</th>
                <th>Delete</th>
            </tr>
                <?php
$sql = "SELECT * FROM `ice_items` ";
$result = mysqli_query($conn, $sql);
if (mysqli_num_rows($result) > 0) {
    while ($row = $result->fetch_assoc()) {
        ?>
              <tr>
                  <td><form action="UpdateAndDeleted.php?type=<?php echo "ice_items";?>" method="post"><?php echo $row['id'];?><input type="hidden" name="id" value="<?php echo $row['id'];?>" readonly=""></td>
                <td><input type="text" name="image" value="<?php echo $row['image'];?>"></td>
                <td><input type="text" name="name" value="<?php echo $row['name'];?>"></td>
                <td><input type="text" name="price" value="<?php echo $row['price'];?>"></td>
                <td><input type="submit" name="update" value="Update"></form></td>
                <th><button><a href="delete.php?id=<?php echo $row['id'];?>&type=<?php echo "ice_items";?>">Delete</a></button></th>
            </tr>
            <?php

    }
}
?>
</table>
