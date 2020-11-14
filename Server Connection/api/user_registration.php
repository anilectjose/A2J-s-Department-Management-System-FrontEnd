<?php 

include 'connection.php';

if(isset($_POST['name']))
{
	@$name = $_POST['name'];
	@$email = $_POST['email_id'];
	@$gender = $_POST['gender'];
	@$mobile = $_POST['mobile_num'];
	@$registerno = $_POST['register_no'];
	@$username = $_POST['username'];
	@$password = $_POST['password'];
	@$flag = $_POST['flag'];

	@$register_type = $_POST['regtype'];
	@$year = "0";

	if ($flag==1) 
	{
		$role="teacher";
		$year = "-";
		mysqli_query($con, "INSERT INTO `role_db`(`name`, `password`, `role`) VALUES ('$username','$password','$role')");
	     $roleid =mysqli_insert_id($con);

	     mysqli_query($con, "INSERT INTO `stud_detail`(`nme`, `email`, `mobile`, `gender`,`role_id`, `register_no`, `year`) VALUES ('$name','$email','$mobile','$gender','$roleid','$registerno','$year')");

	}
	else
	{
		if($register_type =="First")
		{
			$year = "1";
		}
		else if($register_type =="Second")
		{
			$year = "2";
		}
		else if($register_type =="Third")
		{
			$year = "3";
		}


		$role="student";   
		 mysqli_query($con, "INSERT INTO `role_db`(`name`, `password`, `role`) VALUES ('$username','$password','$role')");
	     $roleid =mysqli_insert_id($con);                   
	                
		$query = mysqli_query($con, "INSERT INTO `student_db`(`name`, `reg_no`,`year`,`role_id`) VALUES ('$name','$registerno','$year','$roleid')");

		mysqli_query($con,"INSERT INTO `attendance_db`(`name`, `year`,`role_id`) VALUES ('$name','$year','$roleid')");

	     mysqli_query($con, "INSERT INTO `stud_detail`(`nme`, `email`, `mobile`, `gender`,`role_id`, `register_no`, `year`) VALUES ('$name','$email','$mobile','$gender','$roleid','$registerno','$year')");
	   }

		if($query)
		{
			$response['success'] = 1;
			$response['message'] = "Your registration is successfully completed!";

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