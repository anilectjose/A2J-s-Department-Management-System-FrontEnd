<?php
include 'connection.php';

$data_list = array();

if(isset($_POST['atid']))
{
	@$id = $_POST['atid'];
 
$query=mysqli_query($con,"SELECT * FROM `stud_detail` join `role_db` on role_db.role_id=stud_detail.role_id and role_db.role_id='$id'");



if(mysqli_num_rows($query) > 0)
{
	$real= mysqli_fetch_assoc($query);
	

	$response['success'] =1; 
	$response['result'] = $real;
    
	echo json_encode($response);
}
else
{
	$response['success'] =0; 
	$response['result'] = "No data found!";
    
	echo json_encode($response);

}
}
else{
	$response['success'] =0; 
	$response['result'] = "No Access!";
    
	echo json_encode($response);
}


?>

