<HTML>
<HEAD><TITLE>Вывод данных</TITLE></HEAD>
<BODY>
<?php
$N=(int)$_POST['N']+1;
$tab='&nbsp&nbsp&nbsp&nbsp';
$prevArray=array(1);
for($count=0;$count<$N;$count++){
	$tableft='<P>';
	for($i=0;$i<$N-$count-1;$i++){
		$tableft=$tableft.$tab;
	}
	echo $tableft;
	for($i=0;$i<$count+1;$i++){
		echo "$prevArray[$i]$tab";
	}
	$prevnumber=0;
	for($i=1;$i<$count+1;$i++){
		if($prevnumber==0){
			$prevnumber=$prevArray[$i];
			$prevArray[$i]=$prevArray[$i-1]+$prevnumber;
		}
		else{
			$tmp=$prevnumber+$prevArray[$i];
			$prevnumber=$prevArray[$i];
			$prevArray[$i]=$tmp;
		}
		
	}
	$prevArray[$count+1]=1;
	echo '</P>';
}
 ?>
 </BODY>
 </HTML>