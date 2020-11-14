<?php
include 'connection.php';

$data_list = array();

if(isset($_POST['stud_id']))
{
	@$id = $_POST['stud_id'];
 
$query=mysqli_query($con,"SELECT * FROM `stud_detail` WHERE role_id='$id' ");
$query2=mysqli_query($con,"SELECT * FROM `role_db` WHERE role_id='$id' "); 
$query3=mysqli_query($con,"SELECT * FROM `attendance_db` WHERE role_id='$id' "); 


$real=mysqli_fetch_assoc($query);
$real2=mysqli_fetch_assoc($query2);
$real3=mysqli_fetch_assoc($query3);


@$title=$real['nme'];
@$title2=$real2['name'];
@$title3=$real['year'];
@$title4=$real['image_url'];
@$title5=$real['email'];
@$title6=$real['mobile'];
@$title7=$real['register_no'];
@$title8=$real3['total'];




if($query)
{

    $response['success'] =1;
    $response['message'] = "Updation successful";
    $response['nme']=$title;
    $response['name']=$title2;
    $response['year']=$title3;
    $response['image_url']=$title4;
    $response['email']=$title5;
    $response['mobile']=$title6;
    $response['register_no']=$title7;
    $response['attendance']=$title8;




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

