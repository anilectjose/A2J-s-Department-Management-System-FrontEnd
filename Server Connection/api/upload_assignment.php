 <?php

// Path to move uploaded files
$target_path = "uploads/";

// array for final json respone
$response = array();

// getting server ip address
$server_ip = gethostbyname(gethostname());

// final file url that is being uploaded
$file_upload_url = 'http://' . $server_ip . '/' . 'aja_admin' . '/' . $target_path;


if (isset($_FILES['pdf']['name'])) {
   $target_path = $target_path . basename($_FILES['pdf']['name']);

   

   $response['file_name'] = basename($_FILES['pdf']['name']);

   try {
       // Throws exception incase file is not being moved
       if (!move_uploaded_file($_FILES['pdf']['tmp_name'], $target_path)) {
           // make error flag true
           $response['error'] = true;
           $response['message'] = 'Could not move the file!';
       }

       // File successfully uploaded
       $response['message'] = 'File uploaded successfully!';
       $response['error'] = false;
       $response['file_path'] = $file_upload_url . basename($_FILES['pdf']['name']);
   } catch (Exception $e) {
       // Exception occurred. Make error flag true
       $response['error'] = true;
       $response['message'] = $e->getMessage();
   }
} else {
   // File parameter is missing
   $response['error'] = true;
   $response['message'] = 'Not received any file!F';
}

// Echo final json response to client
echo json_encode($response, JSON_UNESCAPED_SLASHES);



?> 