<?php

class db_pgsql{

	protected $connection;

	function __construct($dbname,$user,$password){
		try{
			$this->connection=new PDO('pgsql:host=localhost;dbname='.$dbname.';',$user,$password);
		}
		catch(PDOException $e){
			$this->connection=null;
			include 'error_db.php';
		}
	}

	function delete($nameTable,$id){
		if($this->connection!=null){
			return $this->connection->exec("DELETE FROM $nameTable WHERE $nameTable.id = $id");
		}
		return -1;
	}

	function add($str){
		if($this->connection!=null){
			return $this->connection->exec($str);
		}
		return -1;
	}

	function getTable($nameTable){
		if($this->connection!=null){
			$statement = $this->connection->query('SELECT * FROM '.$nameTable);
			$data=array();
			while($row = $statement->fetch(PDO::FETCH_ASSOC)) {
    			array_push($data,$row);
			}
			$statement=null;
			return $data;
		}
		return null;
	}

	function getTable_html($data){
		if(!empty($data) and isset($data)){
			$NameColumn=array();
			foreach ($data[0] as $key => $value) {
				array_push($NameColumn,$key);
			}
			$str='<table border="1"><tr>';
			for($i=0;$i<count($NameColumn);$i++){
				$str=$str."<td>$NameColumn[$i]</td>";
			}
			$str=$str.'</tr>';
			for($i=0;$i<count($data);$i++){
				$row=$data[$i];
				$str=$str.'<tr>';
				for($j=0;$j<count($NameColumn);$j++){
					$name=$NameColumn[$j];
					$str=$str."<td>$row[$name]</td>";
				}
				$str=$str.'</tr>';
			}
			return $str.'</table>';
		}
		return null;
	}

	function printTable_html($data){
		echo $this->getTable_html($data);
	}

	protected function transform_PDO_statement($Statement){
		$data=array();
		while($row = $Statement->fetch(PDO::FETCH_ASSOC)) {
    		array_push($data,$row);
		}
		return $data;
	}

	function close_db(){
		$this->connection=null;
	}

}

class requests_lab4 extends db_pgsql{

	function task1($nameTable,$PrimeryKey){
		if($this->connection!=null){
			if($statement = $this->connection->query('SELECT * FROM '.$nameTable.' WHERE '.$nameTable.'.id='.$PrimeryKey)){
				$data=$this->transform_PDO_statement($statement);
				$statement=null;
				return $data;
			}
			return null;
		}
		return null;
	}


	function task2_1($PrimeryKey){
		if($this->connection!=null){
			if($statement=$this->connection->query('SELECT * FROM "e-mail" WHERE contact_person_id='.$PrimeryKey)){
				$data=$this->transform_PDO_statement($statement);
				$statement=null;
				return $data;
			}
			return null;
		}
		return null;
	}

	function task2_2($PrimeryKey){
		if($this->connection!=null){
			if($statement=$this->connection->query("SELECT project.id, project.client_id, project.start_date, project.end_date,(SELECT count(requirement) FROM requirement WHERE project.id=requirement.project_id) AS all_requirement, (SELECT count(level_of_criticality_for_the_client) FROM requirement WHERE project.id=requirement.project_id AND requirement.level_of_criticality_for_the_client='низкий') AS requirement_low, (SELECT count(level_of_criticality_for_the_client) FROM requirement WHERE project.id=requirement.project_id AND requirement.level_of_criticality_for_the_client='средний') AS requirement_middle, (SELECT count(level_of_criticality_for_the_client) FROM requirement WHERE project.id=requirement.project_id AND requirement.level_of_criticality_for_the_client='высокий') AS requirement_high, (SELECT count(level_of_criticality_for_the_client) FROM requirement WHERE project.id=requirement.project_id AND requirement.level_of_criticality_for_the_client='высокий')::real / (SELECT count(requirement) FROM requirement WHERE project.id=requirement.project_id)::real AS percent_high, (SELECT count(level_of_criticality_for_the_client) FROM requirement WHERE project.id=requirement.project_id AND requirement.level_of_criticality_for_the_client='средний')::real / (SELECT count(requirement) FROM requirement WHERE project.id=requirement.project_id)::real AS percent_middle, (SELECT count(level_of_criticality_for_the_client) FROM requirement WHERE project.id=requirement.project_id AND requirement.level_of_criticality_for_the_client='низкий')::real / (SELECT count(requirement) FROM requirement WHERE project.id=requirement.project_id)::real AS percent_low FROM project WHERE project.client_id=$PrimeryKey AND project.end_date IS NULL ORDER BY percent_high DESC, percent_middle DESC, percent_low DESC")){
				$data=$this->transform_PDO_statement($statement);
				$statement=null;
				return $data;
			}
			return null;
		}
		return null;
	}

	function task2_3($PrimeryKey){
		if($this->connection!=null){
			if($statement=$this->connection->query("SELECT requirement.id, requirement.project_id, requirement.requirement, requirement.start_date, requirement.a_mark_of_completion FROM requirement, client, project WHERE client.id=$PrimeryKey AND project.client_id=client.id AND project.id=requirement.project_id ORDER BY start_date")){
				$data=$this->transform_PDO_statement($statement);
				$statement=null;
				return $data;
			}
			return null;
		}
		return null;
	}

	function task2_4($PrimeryKey){
		if($this->connection!=null){
			if($statement=$this->connection->query("SELECT * FROM planed_test WHERE requirement_id=$PrimeryKey AND level_test='глубокий' ORDER BY planned_time")){
				$data=$this->transform_PDO_statement($statement);
				$statement=null;
				return $data;
			}
			return null;
		}
		return null;
	}

	function task2_5($PrimeryKey){
		if($this->connection!=null){
			if($statement=$this->connection->query("SELECT * FROM completed_test WHERE planed_test_id=$PrimeryKey ORDER BY result DESC,start_date_and_time")){
				$data=$this->transform_PDO_statement($statement);
				$statement=null;
				return $data;
			}
			return null;
		}
		return null;
	}

	function task3_1(){
		if($this->connection!=null){
			if($statement=$this->connection->query("SELECT contact_person.surname, contact_person.name, contact_person.middle_name, phone.number FROM contact_person INNER JOIN phone ON phone.contact_person_id=contact_person.id")){
				$data=$this->transform_PDO_statement($statement);
				$statement=null;
				return $data;
			}
			return null;
		}
		return null;
	}

	function task3_2(){
		if($this->connection!=null){
			if($statement=$this->connection->query('SELECT contact_person.surname, contact_person.name, contact_person.middle_name, "e-mail".login FROM contact_person INNER JOIN "e-mail" ON "e-mail".contact_person_id=contact_person.id')){
				$data=$this->transform_PDO_statement($statement);
				$statement=null;
				return $data;
			}
			return null;
		}
		return null;
	}

	function task3_3(){
		if($this->connection!=null){
			if($statement=$this->connection->query("SELECT software_tester.surname, software_tester.name, software_tester.middle_name, software_tester.work_experience FROM software_tester LEFT JOIN users ON users.id=software_tester.id")){
				$data=$this->transform_PDO_statement($statement);
				$statement=null;
				return $data;
			}
			return null;
		}
		return null;
	}

	function task4_1(){
		if($this->connection!=null){
			if($statement=$this->connection->query("SELECT planed_test_id, count(*) AS idcount FROM completed_test GROUP BY planed_test_id")){
				$data=$this->transform_PDO_statement($statement);
				$statement=null;
				return $data;
			}
			return null;
		}
		return null;
	}

	function task4_2(){
		if($this->connection!=null){
			if($statement=$this->connection->query("SELECT tester_id, sum(length) AS sumlength FROM completed_test GROUP BY tester_id")){
				$data=$this->transform_PDO_statement($statement);
				$statement=null;
				return $data;
			}
			return null;
		}
		return null;
	}

}
?>