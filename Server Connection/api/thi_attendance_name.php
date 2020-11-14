<?php
include 'connection.php';

$data_list = array();
// @$userid=$_POST['key_user_id'];

$query=mysqli_query($con,"SELECT * FROM `attendance_db` WHERE year=3 order by name" );

/*$result=mysqli_query($connection,"SELECT group_list.group_name,group_list.group_id FROM group_list LEFT JOIN member ON group_list.group_id=member.group_id WHERE member.user_register_id='$user_id'");*/

if(mysqli_num_rows($query) > 0)
{
	while($real= mysqli_fetch_assoc($query))
	{

		$data_list[] = $real;
	}

	$response['success'] =1; 
	$response['result'] = $data_list;
    
	echo json_encode($response);
}
else
{
	$response['success'] =0; 
	$response['result'] = "No data found!";
    
	echo json_encode($response);

}


?>

