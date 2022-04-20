<?php
include "db2.php";

require 'PHPMailer/PHPMailerAutoload.php';

session_start();
    
if(isset($_POST["email"]))
    {
      

      $email=$_POST['email'];
      //echo $email; exit();

       $reset_query=mysqli_query($con,"SELECT * FROM role_db inner join stud_detail on role_db.role_id=stud_detail.role_id WHERE stud_detail.email='$email'");

      $count=mysqli_num_rows($reset_query);
      if($count>0)
      {
        $user_data = mysqli_fetch_assoc($reset_query);
      
$mail = new PHPMailer;
 
$mail->isSMTP();                                      // Set mailer to use SMTP
$mail->Host = 'smtp.gmail.com';                       // Specify main and backup server
$mail->SMTPAuth = true;                               // Enable SMTP authentication
$mail->Username = '';                   // SMTP username
$mail->Password = '';               // SMTP password
$mail->SMTPSecure = 'ssl';                            // Enable encryption, 'ssl' also accepted
$mail->Port = 465;       
/*$mail->SMTPDebug = 2;*/                             //Set the SMTP port number - 587 for authenticated TLS
$mail->setFrom('ajacreations156@gmail.com','Department Management System');     //Set who the message is to be sent from

$mail->addAddress($email); ; 
           
//$mail->addCC('example@xyz.com', 'name');
//$mail->addBCC('example@xyz.com', 'name');
$mail->WordWrap = 50;                                  
        
$mail->isHTML(true);                                   
 
$mail->Subject = "Password Recovery";
$mail->Body    = "<html>
<head>
<meta charset='utf-8'>
<title>Password Recovery</title>
</head>

<body>

<table width='200' border='1'>
  <tr>
    <th scope='row'>Mail From</th>
    <td>Department management system</td>
  </tr>
  <tr>
    <th scope='row'>Username</th>
    <td>".$user_data['name']."</td>
  </tr>
  <tr>
    <th scope='row'>Password</th>
    <td>".$user_data['password']."</td>
  </tr>
</table>
We request you to change your password after login.
</body>
</html>
";
  $mail->AltBody = 'msg';
 
  //Read an HTML message body from an external file, convert referenced images to embedded,
  //convert HTML into a basic plain-text alternative body
  //$mail->msgHTML(file_get_contents('contents.html'), dirname(__FILE__));
 
  if(!$mail->send()) 
  {
     echo 'Message could not be sent.';
     echo 'Mailer Error: ' . $mail->ErrorInfo;
     exit();
  }
  else
  {
    echo "<script>alert('Message has been sent');</script>";
  }  
      }
      else
      {
        echo "<SCRIPT>alert(' email id invalid .Enter  email id given in your profile.');</SCRIPT>";
       echo "<SCRIPT>window.location='forgot_password.php';</SCRIPT>";
      }

}
?>
