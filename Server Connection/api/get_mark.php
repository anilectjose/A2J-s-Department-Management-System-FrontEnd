<?php
include 'connection.php';

@$year = $_POST['year'];
@$id = $_POST['stid'];

if ($year=="1") 
{
	$sem1='1';
	$sem2='2';
}
elseif ($year=="2") 
{
	$sem1='3';
	$sem2='4';
}
elseif ($year=="3") 
{
	$sem1='5';
	$sem2='6';
}
$data_list = array();
$data_list_2 = array();

if(isset($_POST['stid']))
{
	@$id = $_POST['stid'];
	@$subid = $_POST['subid'];

 
$query1=mysqli_query($con,"SELECT * FROM `student_db` where role_id='$id'");

$query_check=mysqli_query($con,"SELECT * FROM `mark_db` where st_id='$id'");

//if(mysqli_num_rows($query_check) == 0 ) {
	$query2=mysqli_query($con,"SELECT * FROM `subject_db` where year='$year'");

	while($real2= mysqli_fetch_assoc($query2))
	{

		$sub_id=$real2['sub_id'];
		$query4=mysqli_query($con,"SELECT * from mark_db where st_id='$id' and sub_id='$sub_id'");
		if(mysqli_num_rows($query4)==0)
		{
			$query3=mysqli_query($con,"INSERT INTO `mark_db`(`st_id`, `sub_id`) VALUES ('$id','$sub_id')");
		}
		

	}
//}

$query=mysqli_query($con,"SELECT * FROM `subject_db` join mark_db on subject_db.sub_id=mark_db.sub_id where mark_db.st_id='$id' AND subject_db.sem_id='$sem1' "); 
$query_sem2=mysqli_query($con,"SELECT * FROM `subject_db` join mark_db on subject_db.sub_id=mark_db.sub_id where mark_db.st_id='$id' AND subject_db.sem_id='$sem2' ");

if(mysqli_num_rows($query) > 0 || mysqli_num_rows($query_sem2) > 0)
{
	while($real= mysqli_fetch_assoc($query))
	{

		$data_list[] = $real;
	}
	while($real_sem_2= mysqli_fetch_assoc($query_sem2))
	{

		$data_list_2[] = $real_sem_2;
	}

	$response['success'] =1; 
	$response['result'] = $data_list;
	$response['result_2'] = $data_list_2;
    
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

