<?php 

include 'connection.php';

if(isset($_POST['tot']))
{
	@$id = $_POST['atid'];
	@$tot = $_POST['tot'];
	@$janu = $_POST['jan'];
	@$febr = $_POST['feb'];
	@$marc = $_POST['mar'];
	@$june = $_POST['jun'];
	@$july = $_POST['jul'];
	@$augu = $_POST['aug'];
	@$sept = $_POST['sep'];
	@$octo = $_POST['oct'];
	@$nove = $_POST['nov'];
	@$dece = $_POST['dec'];

	$query = mysqli_query($con, "UPDATE `attendance_db` SET `jun`='$june',`jul`='$july',`aug`='$augu',`sep`='$sept',`oct`='$octo',`nov`='$nove',`dece`='$dece',`jan`='$janu',`feb`='$febr',`mar`='$marc',`total`='$tot' WHERE `att_id`=$id");
	
if($query)
{
	$response['success'] = 1;
	$response['message'] = "Attendance submitted!";

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