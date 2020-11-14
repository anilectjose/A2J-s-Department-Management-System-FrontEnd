<?php 

include 'connection.php';

if(isset($_POST['content']))
{
	@$name = $_POST['content'];


	$query = mysqli_query($con, "INSERT INTO `notification_db`(`content`, `to`) VALUES ('$name','student')");
	
if($query)
{
	$response['success'] = 1;
	$response['message'] = "Notification sent succesfully!";

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