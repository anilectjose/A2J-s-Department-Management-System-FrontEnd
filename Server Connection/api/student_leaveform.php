<?php 

include 'connection.php';

if(isset($_POST['name']))
{
	@$name = $_POST['name'];
	@$year = $_POST['year'];
	@$regno = $_POST['regno'];
	@$date = $_POST['date'];
	@$days = $_POST['days'];
	@$rea = $_POST['reason'];


	$query = mysqli_query($con, "INSERT INTO `leave_db`(`date`, `name`, `year`, `reg_no`, `reason`, `no_of_days`) VALUES ('$date','$name','$year','$regno','$rea','$days')");
	
if($query)
{
	$response['success'] = 1;
	$response['message'] = "Leave application submitted!";

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