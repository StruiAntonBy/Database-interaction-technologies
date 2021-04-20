<HTML>
<HEAD>
	<TITLE>Lab 6</TITLE>
</HEAD>
<BODY>
	<?php
		if(!empty($_POST)){
			if(isset($_POST["TableNameDelete"],$_POST["IdDelete"]) and !empty($_POST["TableNameDelete"]) and !empty($_POST["IdDelete"])){
				$strtrim=trim($_POST["TableNameDelete"]," ");
				$id=$_POST["IdDelete"];
				$array=array("users","software_tester","client","contact_person","phone",'"e-mail"',"project","requirement","planed_test","completed_test");
				if(in_array($strtrim,$array)){
					include 'db.php';
					$DataBase=new db_pgsql('testing_projects_db','root','1111');
					$res=$DataBase->delete($strtrim,$id);
					if($res>0){
						echo "<b>The data was successfully deleted</b>";
					}
					else if($res==0){
						echo "<b>The data was not deleted</b>";
					}
					echo '<p><a href="lab6.html">Return</a></p>';
					$DataBase->close_db();	
				}
				else{
					echo "<b><FONT COLOR=RED>Invalid table name</FONT></b>";
				}	
			}
			else if(isset($_POST["TableNameAdd"],$_POST["login"],$_POST["password"],$_POST["role"]) and !empty($_POST["TableNameAdd"]) and !empty($_POST["login"]) and !empty($_POST["password"]) and !empty($_POST["role"])){
				include 'db.php';
				$DataBase=new db_pgsql('testing_projects_db','root','1111');
				$sql="INSERT INTO users (login, password, role) VALUES ('". $_POST['login']. "', '". $_POST['password']. "',". $_POST['role']. ")";
				$res=$DataBase->add($sql);
				if($res>0){
					echo "<b>Data was added successfully</b>";
				}
				else if($res==0){
					echo "<b>Data is not added</b>";
				}
				echo '<p><a href="lab6.html">Return</a></p>';
				$DataBase->close_db();
			}
			else if(isset($_POST["TableNameAdd"],$_POST["name"],$_POST["registered_address"],$_POST["bank_details"]) and !empty($_POST["TableNameAdd"]) and !empty($_POST["name"]) and !empty($_POST["registered_address"]) and !empty($_POST["bank_details"])){
				include 'db.php';
				$DataBase=new db_pgsql('testing_projects_db','root','1111');
				$sql="INSERT INTO client (name, registered_address, bank_details) VALUES ('". $_POST['name']. "', '". $_POST['registered_address']. "', '". $_POST['bank_details']. "')";
				$res=$DataBase->add($sql);
				if($res>0){
					echo "<b>Data was added successfully</b>";
				}
				else if($res==0){
					echo "<b>Data is not added</b>";
				}
				echo '<p><a href="lab6.html">Return</a></p>';
				$DataBase->close_db();
			}
			else if(isset($_POST["TableNameAdd"],$_POST["surname"],$_POST["name"],$_POST["middle_name"]) and !empty($_POST["TableNameAdd"]) and !empty($_POST["surname"]) and !empty($_POST["name"]) and !empty($_POST["middle_name"])){
				include 'db.php';
				$DataBase=new db_pgsql('testing_projects_db','root','1111');
				$sql="INSERT INTO contact_person (surname, name, middle_name) VALUES ('". $_POST['surname']. "', '". $_POST['name']. "', '". $_POST['middle_name']. "')";
				$res=$DataBase->add($sql);
				if($res>0){
					echo "<b>Data was added successfully</b>";
				}
				else if($res==0){
					echo "<b>Data is not added</b>";
				}
				echo '<p><a href="lab6.html">Return</a></p>';
				$DataBase->close_db();
			}
			else if(isset($_POST["TableNameAdd"],$_POST["number"],$_POST["contact_person_id"]) and !empty($_POST["TableNameAdd"]) and !empty($_POST["number"]) and !empty($_POST["contact_person_id"])){
				include 'db.php';
				$DataBase=new db_pgsql('testing_projects_db','root','1111');
				$sql="INSERT INTO phone (number, contact_person_id) VALUES ('". $_POST['number']. "', ". $_POST['contact_person_id'].")";
				$res=$DataBase->add($sql);
				if($res>0){
					echo "<b>Data was added successfully</b>";
				}
				else if($res==0){
					echo "<b>Data is not added</b>";
				}
				echo '<p><a href="lab6.html">Return</a></p>';
				$DataBase->close_db();
			}
			else if(isset($_POST["TableNameAdd"],$_POST["login"],$_POST["contact_person_id"]) and !empty($_POST["TableNameAdd"]) and !empty($_POST["login"]) and !empty($_POST["contact_person_id"])){
				include 'db.php';
				$DataBase=new db_pgsql('testing_projects_db','root','1111');
				$sql='INSERT INTO "e-mail"'." (login, contact_person_id) VALUES ('". $_POST['login']. "', ". $_POST['contact_person_id'].")";
				$res=$DataBase->add($sql);
				if($res>0){
					echo "<b>Data was added successfully</b>";
				}
				else if($res==0){
					echo "<b>Data is not added</b>";
				}
				echo '<p><a href="lab6.html">Return</a></p>';
				$DataBase->close_db();
			}
			else if(isset($_POST["TableNameAdd"],$_POST["client_id"],$_POST["start_date"],$_POST["end_date"]) and !empty($_POST["TableNameAdd"]) and !empty($_POST["client_id"]) and !empty($_POST["start_date"])){
				include 'db.php';
				$DataBase=new db_pgsql('testing_projects_db','root','1111');
				$sql;
				if(!empty($_POST["end_date"])){
					$sql="INSERT INTO project (client_id, start_date, end_date) VALUES (". $_POST['client_id']. ", '". $_POST['start_date']. "', '". $_POST['end_date']. "')";
				}
				else{
					$sql="INSERT INTO project (client_id, start_date) VALUES (". $_POST['client_id']. ", '". $_POST['start_date']."')";
				}
				$res=$DataBase->add($sql);
				if($res>0){
					echo "<b>Data was added successfully</b>";
				}
				else if($res==0){
					echo "<b>Data is not added</b>";
				}
				echo '<p><a href="lab6.html">Return</a></p>';
				$DataBase->close_db();
			}
			else if(isset($_POST["TableNameAdd"],$_POST["project_id"],$_POST["requirement"],$_POST["start_date"],$_POST["planned_time"],$_POST["the_priority_of"],$_POST["level_of_criticality_for_the_client"],$_POST["a_mark_of_completion"],$_POST["the_probability_of_a_change"]) and !empty($_POST["TableNameAdd"]) and !empty($_POST["project_id"]) and !empty($_POST["requirement"]) and !empty($_POST["planned_time"]) and !empty($_POST["the_priority_of"]) and !empty($_POST["level_of_criticality_for_the_client"]) and !empty($_POST["the_probability_of_a_change"])){
				include 'db.php';
				$DataBase=new db_pgsql('testing_projects_db','root','1111');
				$sql;
				if(!empty($_POST["start_date"]) and empty($_POST["a_mark_of_completion"])){
					$sql="INSERT INTO requirement (project_id, requirement, start_date, planned_time, the_priority_of, level_of_criticality_for_the_client, the_probability_of_a_change) VALUES (". $_POST['project_id']. ", '". $_POST['requirement']. "', '". $_POST['start_date']."', ". $_POST['planned_time'].", '". $_POST['the_priority_of']."', '". $_POST['level_of_criticality_for_the_client']."', '".$_POST['the_probability_of_a_change']."')";
				}
				else if(!empty($_POST["start_date"]) and !empty($_POST["a_mark_of_completion"])){
					$sql="INSERT INTO requirement (project_id, requirement, start_date, planned_time, the_priority_of, level_of_criticality_for_the_client, a_mark_of_completion, the_probability_of_a_change) VALUES (". $_POST['project_id']. ", '". $_POST['requirement']. "', '". $_POST['start_date']."', ". $_POST['planned_time'].", '". $_POST['the_priority_of']."', '". $_POST['level_of_criticality_for_the_client']."', '".$_POST['a_mark_of_completion']."', '".$_POST['the_probability_of_a_change']."')";
				}
				else if(empty($_POST["start_date"]) and !empty($_POST["a_mark_of_completion"])){
					$sql="INSERT INTO requirement (project_id, requirement, planned_time, the_priority_of, level_of_criticality_for_the_client, a_mark_of_completion, the_probability_of_a_change) VALUES (". $_POST['project_id']. ", '". $_POST['requirement']."', ". $_POST['planned_time'].", '". $_POST['the_priority_of']."', '". $_POST['level_of_criticality_for_the_client']."', '".$_POST['a_mark_of_completion']."', '".$_POST['the_probability_of_a_change']."')";
				}
				else if(empty($_POST["start_date"]) and empty($_POST["a_mark_of_completion"])){
					$sql="INSERT INTO requirement (project_id, requirement, planned_time, the_priority_of, level_of_criticality_for_the_client, the_probability_of_a_change) VALUES (". $_POST['project_id']. ", '". $_POST['requirement']."', ". $_POST['planned_time'].", '". $_POST['the_priority_of']."', '". $_POST['level_of_criticality_for_the_client']."', '".$_POST['the_probability_of_a_change']."')";
				}
				$res=$DataBase->add($sql);
				if($res>0){
					echo "<b>Data was added successfully</b>";
				}
				else if($res==0){
					echo "<b>Data is not added</b>";
				}
				echo '<p><a href="lab6.html">Return</a></p>';
				$DataBase->close_db();
			}
			else if(isset($_POST["TableNameAdd"],$_POST["requirement_id"],$_POST["description_of_the_performance"],$_POST["expected_result"],$_POST["planned_time"],$_POST["level_test"]) and !empty($_POST["TableNameAdd"]) and !empty($_POST["requirement_id"]) and !empty($_POST["description_of_the_performance"]) and !empty($_POST["expected_result"]) and !empty($_POST["planned_time"]) and !empty($_POST["level_test"])){
				include 'db.php';
				$DataBase=new db_pgsql('testing_projects_db','root','1111');
				$sql="INSERT INTO planed_test (requirement_id, description_of_the_performance, expected_result, planned_time, level_test) VALUES (". $_POST['requirement_id']. ", '". $_POST['description_of_the_performance']."', '". $_POST['expected_result']."', ".$_POST['planned_time'].", '".$_POST['level_test']."')";
				$res=$DataBase->add($sql);
				if($res>0){
					echo "<b>Data was added successfully</b>";
				}
				else if($res==0){
					echo "<b>Data is not added</b>";
				}
				echo '<p><a href="lab6.html">Return</a></p>';
				$DataBase->close_db();
			}
			else if(isset($_POST["TableNameAdd"],$_POST["tester_id"],$_POST["planed_test_id"],$_POST["start_date_and_time"],$_POST["length"],$_POST["result"]) and !empty($_POST["TableNameAdd"]) and !empty($_POST["tester_id"]) and !empty($_POST["planed_test_id"]) and !empty($_POST["start_date_and_time"]) and !empty($_POST["length"]) and !empty($_POST["result"])){
				include 'db.php';
				$DataBase=new db_pgsql('testing_projects_db','root','1111');
				$sql="INSERT INTO completed_test (tester_id, planed_test_id, start_date_and_time, length, result) VALUES (". $_POST['tester_id']. ", ". $_POST['planed_test_id'].", '". $_POST['start_date_and_time']."', ".$_POST['length'].", '".$_POST['result']."')";
				$res=$DataBase->add($sql);
				if($res>0){
					echo "<b>Data was added successfully</b>";
				}
				else if($res==0){
					echo "<b>Data is not added</b>";
				}
				echo '<p><a href="lab6.html">Return</a></p>';
				$DataBase->close_db();
			}
			else if(isset($_POST["TableNameAdd"],$_POST["surname"],$_POST["name"],$_POST["middle_name"],$_POST["work_experience"]) and !empty($_POST["TableNameAdd"]) and !empty($_POST["surname"]) and !empty($_POST["name"]) and !empty($_POST["middle_name"]) and !empty($_POST["work_experience"])){
				include 'db.php';
				$DataBase=new db_pgsql('testing_projects_db','root','1111');
				$sql="'".$_POST["surname"]."', '".$_POST["name"]."', '".$_POST["middle_name"]."', ".$_POST["work_experience"];
				$str="INSERT INTO software_tester (surname, name, middle_name, work_experience) VALUES($sql)";
				$res=$DataBase->add($str);
				if($res>0){
					echo "<b>Data was added successfully</b>";
				}
				else if($res==0){
					echo "<b>Data is not added</b>";
				}
				echo '<p><a href="lab6.html">Return</a></p>';
				$DataBase->close_db();		
			}
			else{
				echo "<b><FONT COLOR=RED>Error</FONT></b>";
			}
		}
	?>
</BODY>
</HTML>