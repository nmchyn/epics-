<h3>Order Details</h3>  
<div class="table-responsive">  
    <table class="table table-bordered">  
        <tr>  
            <th width="40%">Item Name</th>  
            <th width="10%">Quantity</th>  
            <th width="20%">Price</th>  
            <th width="15%">Total</th>  
            <th width="5%">Action</th>  
        </tr>  
        <?php
        session_start();
         if(isset($_GET["action"]))  
 {  
      if($_GET["action"] == "delete")  
      {  
           foreach($_SESSION["shopping_cart"] as $keys => $values)  
           {  
                if($values["item_id"] == $_GET["id"])  
                {  
                     unset($_SESSION["shopping_cart"][$keys]);  
                     echo '<script>alert("Item Removed")</script>';  
                     echo '<script>window.location="MyOrders.php"</script>';  
                }  
           }  
      }  
 }
        if (!empty($_SESSION["shopping_cart"])) {
            $total = 0;
            foreach ($_SESSION["shopping_cart"] as $keys => $values) {
                ?>  
                <tr>  
                    <td><?php echo $values["item_name"]; ?></td>  
                    <td><?php echo $values["item_quantity"]; ?></td>  
                    <td>$ <?php echo $values["item_price"]; ?></td>  
                    <td>$ <?php echo number_format($values["item_quantity"] * $values["item_price"], 2); ?></td>  
                    <td><a href="MyOrders.php?action=delete&id=<?php echo $values["item_id"]; ?>"><span class="text-danger">Remove</span></a></td>  
                </tr>  
        <?php
        $total = $total + ($values["item_quantity"] * $values["item_price"]);
    }
    ?>  
            <tr>  
                <td colspan="3" align="right">Total</td>  
                <td align="right">$ <?php echo number_format($total, 2); ?></td>  
                <td></td>  
             
    <?php
    
} else {
    echo '<tr><td>cart is empty</td></tr>';
}
?>  
    </table>  

</div>  
</div>  
<br />  
<p align="right">
    <a href="Form.php?total=<?php echo $total ;?>">order confirm</a><br>
    <a href="index.php">Go Back</a>