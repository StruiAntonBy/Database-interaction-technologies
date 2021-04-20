<?php
$ret='';
if(strlen($_POST['Login'])==0){
	$ret=$ret.'Error Login ' ;
}
if(strlen($_POST['Password'])==0){
	$ret=$ret.'Error Password ';
}
if(strlen($_POST['Repeatpassword'])==0){
	$ret=$ret.'Error Repeat password ';
}
if(strcmp($_POST['Password'],$_POST['Repeatpassword'])!=0){
	$ret=$ret.'Error Password and Repeat password do not match ';
}
if(isset($_POST['time'])==False){
	$ret=$ret.'Error Software tester ';
}
if(isset($_POST['human'])==False){
	$ret=$ret.'Error no gender ';
}
if(strlen($_POST['Firstname'])==0){
	$ret=$ret.'Error First name ';
}
if(strlen($_POST['Lastname'])==0){
	$ret=$ret.'Error Last name ';
}
if(strlen($_POST['Middlename'])==0){
	$ret=$ret.'Error Middle name ';
}
if(strlen($_POST['language'])==0){
	$ret=$ret.'Error Language ';
}
if(strlen($_POST['e-mail'])==0){
	$ret=$ret.'Error e-mail ';
}
if(strlen($_POST['Phonenumber'])==0){
	$ret=$ret.'Error Phone number ';
}
if(substr_count($_POST['e-mail'],'@')!=1 or substr_count($_POST['e-mail'],'.')!=1){
	$ret=$ret.'Wrong e-mail ';
}
if(strlen($_POST['story'])==0){
	$ret=$ret.'Error Tell us about yourself ';
}
if($ret!=''){
	include 'registration.html.php';
	echo "<b><FONT COLOR=#FF0000>$ret";
}
else{
	echo 'Welcome';
}

?>