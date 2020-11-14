<?php 

include 'connection.php';

if(isset($_POST['stid']))
{
	@$id = $_POST['stid'];
	@$sub = $_POST['subid'];
	@$mark = $_POST['mark'];


	$query = mysqli_query($con, "UPDATE `mark_db` SET `mark`='$mark' WHERE sub_id='$sub' and st_id='$id'");
	
if($query)
{
	$response['success'] = 1;
	$response['message'] = "Mark submitted!";

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