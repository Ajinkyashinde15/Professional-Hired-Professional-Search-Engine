
<?php
define('HOST','localhost:3306');
define('USER','root');
define('PASS','');
define('DB','nixerdb');
 
$con = mysqli_connect(HOST,USER,PASS,DB);

$sql = "select * from userfeedback";
 
$res = mysqli_query($con,$sql);
 
$result = array();
 
while($row = mysqli_fetch_array($res)){
array_push($result,
array('professionalname'=>$row[1],
'standardname'=>$row[2],
'rating'=>$row[3],
));
}

echo json_encode(array("result"=>$result));
 
mysqli_close($con);
?>