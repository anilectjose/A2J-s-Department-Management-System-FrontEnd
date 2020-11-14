<?php
require 'PHPMailerAutoload.php';
 

if(isset($_POST['send_msg']))
{
  $to_address = $_POST['to_address'];

  $subject = $_POST['msg_title'];
  $msg     = $_POST['msg_description'];


  echo "<script>window.history.back();</script>";
 
$mail = new PHPMailer;
 
$mail->isSMTP();                                      // Set mailer to use SMTP
$mail->Host = 'smtp.gmail.com';                       // Specify main and backup server
$mail->SMTPAuth = true;                               // Enable SMTP authentication
$mail->Username = 'test@gmail.com';                   // SMTP username
$mail->Password = 'password';               // SMTP password
$mail->SMTPSecure = 'ssl';                            // Enable encryption, 'ssl' also accepted
$mail->Port = 465;       
/*$mail->SMTPDebug = 2;*/                             //Set the SMTP port number - 587 for authenticated TLS
$mail->setFrom('test@gmail.com', 'SafeLand');     //Set who the message is to be sent from

$mail->addAddress('to_address'); 
           
//$mail->addCC('example@xyz.com', 'name');
//$mail->addBCC('example@xyz.com', 'name');
$mail->WordWrap = 50;                                  
        
$mail->isHTML(true);                                   
 
$mail->Subject = $subject;
$mail->Body    = "<html>
<head>
<meta charset='utf-8'>
<title>Safe Land</title>
</head>

<body>

<table width='200' border='1'>
  <tr>
    <th scope='row'>Mail From</th>
    <td>District Disaster Management Authority,Malappuram</td>
  </tr>
  <tr>
    <th scope='row'>Subject</th>
    <td>$subject</td>
  </tr>
  <tr>
    <th scope='row'>Message</th>
    <td>$msg</td>
  </tr>
</table>

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
     exit;
  }

  else
  {
    echo "<script>alert('Message has been sent');</script>";

    
  }
 

}

?>