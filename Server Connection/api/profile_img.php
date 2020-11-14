<?php 

include 'connection.php';

if(isset($_POST['stu_id']))
{
    @$id = $_POST['stu_id'];
	@$fname = $_POST['file_name'];
	@$furl = $_POST['file_name_url'];


	$query = mysqli_query($con, "UPDATE `stud_detail` SET `image`='$fname', `image_url`='$furl' WHERE role_id='$id'");
	
if($query)
{
	$response['success'] = 1;
	$response['message'] = "Profile pic submitted!";

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