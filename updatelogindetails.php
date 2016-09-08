<?php
  define('HOST','localhost:3306');
  define('USER','root');
  define('PASS','');
  define('DB','nixerdb');

  $con = mysqli_connect(HOST,USER,PASS,DB);

  $name = $_POST['name'];
  $password = $_POST['password'];
  $login = $_POST['login'];
  
  $sql = "UPDATE user set login='$login' where name='$name' and password='$password'";
  if(mysqli_query($con,$sql)){
    echo 'success';
  }
  else{
    echo 'failure';
  }
  mysqli_close($con);
?>