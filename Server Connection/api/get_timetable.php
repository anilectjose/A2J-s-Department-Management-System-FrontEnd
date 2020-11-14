<?php
include 'connection.php';

$data_list = array();

 
$query=mysqli_query($con,"SELECT * FROM `timetable_db`");



if(mysqli_num_rows($query) > 0)
{
	$real= mysqli_fetch_assoc($query);
	

	$response['success'] =1; 
	$response['result'] = $real;
    
	echo json_encode($response);
}
else
{
	$response['success'] =0; 
	$response['result'] = "No data found!";
    
	echo json_encode($response);

}

?>

