
<?php
define('HOST','localhost:3306');
define('USER','root');
define('PASS','');
define('DB','nixerdb');
 
$con = mysqli_connect(HOST,USER,PASS,DB);

$sql = "select * from user order by avgrating DESC";
 
$res = mysqli_query($con,$sql);
 
$result = array();
 
while($row = mysqli_fetch_array($res)){
array_push($result,
array('name'=>$row[1],
'password'=>$row[2],
'account_type'=>$row[3],
'profession'=>$row[4],
'phoneno'=>$row[5],
'emailid'=>$row[6],
'userlatitute'=>$row[7],
'userlongitute'=>$row[8],
'login'=>$row[9],
'avgrating'=>$row[10]

));
}

echo json_encode(array("result"=>$result));
 
mysqli_close($con);
?>