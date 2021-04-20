<HTML>
<HEAD>
	<TITLE>Lab 6</TITLE>
</HEAD>
<BODY>
	<?php
	include 'db.php';
	$DataBase=new db_pgsql('testing_projects_db','root','1111');
	$array=array("users","software_tester","client","contact_person","phone",'"e-mail"',"project","requirement","planed_test","completed_test");
	for($i=0;$i<10;$i++){
		$arr=$DataBase->getTable($array[$i]);
		$str=$DataBase->getTable_html($arr);
		print "<P>Table $array[$i]</P>".$str;
	}
	?>
</BODY>
</HTML>