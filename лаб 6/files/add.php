<HTML>
<HEAD>
	<TITLE>Lab 6</TITLE>
</HEAD>
<BODY>
	<?php
		if(!empty($_POST)){
			if(isset($_POST["TableNameAdd"]) and !empty($_POST["TableNameAdd"])){
				$strtrim=trim($_POST["TableNameAdd"]," ");
				echo '<form name="form" action="change.php" method="post"><div><input type="hidden" name="TableNameAdd" value="'.$strtrim.'" /></div>';
				switch($strtrim){
					case "users":
						include 'Users.php';
						break;
					case "software_tester":
						include 'Software_tester.php';
						break;
					case "client":
						include 'Client.php';
						break;
					case "contact_person":
						include 'Contact_person.php';
						break;
					case "phone":
						include 'Phone.php';
						break;
					case "e-mail":
						include 'E-mail.php';
						break;
					case "project":
						include 'Project.php';
						break;
					case "requirement":
						include 'Requirement.php';
						break;
					case "planed_test":
						include 'Planed_test.php';
						break;
					case "completed_test":
						include 'Completed_test.php';
						break;
					default:
						echo "<b><FONT COLOR=RED>Invalid table name</FONT></b></form>";
				}
			}
			else{
				echo "<b><FONT COLOR=RED>Error</FONT></b>";
			}
		}
	?>
</BODY>
</HTML>