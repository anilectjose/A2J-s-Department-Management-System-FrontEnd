<?php 

include 'connection.php';

if(isset($_POST['leaid']))
{
	@$id = $_POST['leaid'];
	

	$query = mysqli_query($con, "UPDATE `leave_db` SET `t_result`='Approved' WHERE `leave_id`='$id'");
	
if($query)
{
	$response['success'] = 1;
	$response['message'] = "Leave Approved!";

	echo json_encode($response);

}
else
{
	$response['success'] = 0;
	$response['message'] = "Error occured!";

	echo json_encode($response);

}



}else
{
	$response['success'] = 0;
	$response['message'] = "No Access!";

	echo json_encode($response);

}


?>