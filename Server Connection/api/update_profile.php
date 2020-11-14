<?php 

include 'connection.php';

if(isset($_POST['name']))
{
	@$id = $_POST['atid'];
	@$name = $_POST['name'];
	@$email = $_POST['email_id'];
	@$year = $_POST['year'];
	@$mobile = $_POST['mobile_num'];
	@$registerno = $_POST['register_no'];
	@$username = $_POST['username'];
	@$password = $_POST['password'];
	@$rol = $_POST['role'];

if($rol="teacher")
{
		mysqli_query($con, "UPDATE `role_db` SET `name`='$username',`password`='$password' WHERE role_id='$id'");

	    $query = mysqli_query($con, "UPDATE `stud_detail` SET `nme`='$name', `email`='$email', `mobile`='$mobile', `gender`='$gender', `register_no`='$registerno', `year`='$year' WHERE role_id='$id'");  
	        }
	        else
	        {      
	    mysqli_query($con, "UPDATE `role_db` SET `name`='$username',`password`='$password' WHERE role_id='$id'");

	    mysqli_query($con, "UPDATE `stud_detail` SET `nme`='$name', `email`='$email', `mobile`='$mobile', `gender`='$gender', `register_no`='$registerno', `year`='$year' WHERE role_id='$id'");
		mysqli_query($con, "UPDATE `student_db` SET `name`='$name', `reg_no`='$registerno', `year`='$year' WHERE role_id='$id'");

		mysqli_query($con,"UPDATE `attendance_db` SET SET `name`='$name', `year`='$year' WHERE role_id='$id'");
          }
		if($query)
		{
			$response['success'] = 1;
			$response['message'] = "Your Update is successfully completed!";

			echo json_encode($response);

		}
		else
		{
			$response['success'] = 0;
			$response['message'] = "Error occured!";

			echo json_encode($response);

		}


}
else
{
	$response['success'] = 0;
	$response['message'] = "No access!";

	echo json_encode($response);
}



?>