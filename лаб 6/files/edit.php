<HTML>
<HEAD>
	<TITLE>Lab 6</TITLE>
</HEAD>
<BODY>
	<?php
		if(!empty($_POST)){
			if(isset($_POST["TableNameEdit"],$_POST["ColumnNameEdit"],$_POST["IdEdit"],$_POST["ValueEdit"]) and !empty($_POST["TableNameEdit"]) and !empty($_POST["ColumnNameEdit"]) and !empty($_POST["IdEdit"]) and !empty($_POST["ValueEdit"])){
				$trimTableName=trim($_POST["TableNameEdit"]," ");
				$trimColumnName=trim($_POST["ColumnNameEdit"]," ");
				include 'db.php';
				$DataBase=new db_pgsql('testing_projects_db','root','1111');
				$sql="UPDATE $trimTableName SET $trimColumnName='".$_POST['ValueEdit']."' WHERE id=".$_POST['IdEdit'];
				$res=$DataBase->add($sql);
				if($res>0){
					echo "<b>Data was changed successfully</b>";
				}
				else if($res==0){
					echo "<b>The data was not changed</b>";
				}
				echo '<p><a href="lab6.html">Return</a></p>';
				$DataBase->close_db();
			}
			else{
				echo "<b><FONT COLOR=RED>Error</FONT></b>";
				echo '<p><a href="lab6.html">Return</a></p>';
			}
		}
	?>
</BODY>
</HTML>