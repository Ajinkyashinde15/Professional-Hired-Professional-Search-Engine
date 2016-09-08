<?php
  define('HOST','localhost:3306');
  define('USER','root');
  define('PASS','');
  define('DB','nixerdb');

  $con = mysqli_connect(HOST,USER,PASS,DB);

  $name = $_POST['name'];
  $avgrating= $_POST['avgrating'];
  
  $sql = "UPDATE user set avgrating='$avgrating' where name='$name'";
  if(mysqli_query($con,$sql)){
    echo 'success';
  }
  else{
    echo 'failure';
  }
  mysqli_close($con);
?>