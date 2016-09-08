<?php
  define('HOST','localhost:3306');
  define('USER','root');
  define('PASS','');
  define('DB','nixerdb');

  $con = mysqli_connect(HOST,USER,PASS,DB);

  $name = $_POST['name'];
  $password = $_POST['password'];
  $userlatitute = $_POST['userlatitute'];
  $userlongitute= $_POST['userlongitute'];
  
  $sql = "UPDATE user set userlatitute='$userlatitute',userlongitute='$userlongitute' where name='$name' and password='$password'";
  if(mysqli_query($con,$sql)){
    echo 'success';
  }
  else{
    echo 'failure';
  }
  mysqli_close($con);
?>