<HTML>
<HEAD>
	<TITLE>Lab 6</TITLE>
</HEAD>
<BODY>
	<P>Requests lab 4:</P>
	<?php
		include 'db.php';
		$DataBase=new requests_lab4('testing_projects_db','root','1111');
		if(!empty($_POST)){
			if(isset($_POST["TableName"],$_POST["PrimaryKey"]) and !empty($_POST["TableName"]) and !empty($_POST["PrimaryKey"])){
				echo "<P>Task 1</P>";
				$arr=$DataBase->task1($_POST["TableName"],$_POST["PrimaryKey"]);
				$DataBase->printTable_html($arr);
			}
			elseif(isset($_POST["PrimaryKeyContactPerson"]) and !empty($_POST["PrimaryKeyContactPerson"])){
				echo "<P>Task 2 item 1</P>";
				$arr=$DataBase->task2_1($_POST["PrimaryKeyContactPerson"]);
				$DataBase->printTable_html($arr);
			}
			elseif(isset($_POST["PrimaryKeyClientItem2"]) and !empty($_POST["PrimaryKeyClientItem2"])){
				echo "<P>Task 2 item 2</P>";
				$arr=$DataBase->task2_2($_POST["PrimaryKeyClientItem2"]);
				$DataBase->printTable_html($arr);
			}
			elseif(isset($_POST["PrimaryKeyClient"]) and !empty($_POST["PrimaryKeyClient"])){
				echo "<P>Task 2 item 3</P>";
				$arr=$DataBase->task2_3($_POST["PrimaryKeyClient"]);
				$DataBase->printTable_html($arr);
			}
			elseif(isset($_POST["PrimaryKeyRequirement"]) and !empty($_POST["PrimaryKeyRequirement"])){
				echo "<P>Task 2 item 4</P>";
				$arr=$DataBase->task2_4($_POST["PrimaryKeyRequirement"]);
				$DataBase->printTable_html($arr);
			}
			elseif(isset($_POST["PrimaryKeyPlanedTest"]) and !empty($_POST["PrimaryKeyPlanedTest"])){
				echo "<P>Task 2 item 5</P>";
				$arr=$DataBase->task2_5($_POST["PrimaryKeyPlanedTest"]);
				$DataBase->printTable_html($arr);
			}
			elseif(isset($_POST["task31"]) and !empty($_POST["task31"])){
				echo "<P>Task 3 item 1</P>";
				$arr=$DataBase->task3_1();
				$DataBase->printTable_html($arr);
			}
			elseif(isset($_POST["task32"]) and !empty($_POST["task32"])){
				echo "<P>Task 3 item 2</P>";
				$arr=$DataBase->task3_2();
				$DataBase->printTable_html($arr);
			}
			elseif(isset($_POST["task33"]) and !empty($_POST["task33"])){
				echo "<P>Task 3 item 3</P>";
				$arr=$DataBase->task3_3();
				$DataBase->printTable_html($arr);
			}
			elseif(isset($_POST["task41"]) and !empty($_POST["task41"])){
				echo "<P>Task 4 item 1</P>";
				$arr=$DataBase->task4_1();
				$DataBase->printTable_html($arr);
			}
			elseif(isset($_POST["task42"]) and !empty($_POST["task42"])){
				echo "<P>Task 4 item 2</P>";
				$arr=$DataBase->task4_2();
				$DataBase->printTable_html($arr);
			}
		}
		$DataBase->close_db();
	?>
</BODY>
</HTML>