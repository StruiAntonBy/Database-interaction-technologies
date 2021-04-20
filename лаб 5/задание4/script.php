<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="refresh" content="5">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
<?php
$Atom=0;
$Chrome=0;
$Firefox=0;
$YaBrowser=0;
$Opera=0;
$InternetExplorer=0;
$Safari=0;
    $user_agent = $_SERVER["HTTP_USER_AGENT"];
    if (strpos($user_agent, "Firefox") !== false) $browser = "Firefox";
	elseif(strpos($user_agent, "YaBrowser") !== false) $browser = "YaBrowser";
    elseif (strpos($user_agent, "Opera") !== false) $browser = "Opera";
    elseif (strpos($user_agent, "Atom") !== false) $browser = "Atom";
    elseif (strpos($user_agent, "Chrome") !== false) $browser = "Chrome";
    elseif (strpos($user_agent, "MSIE") !== false) $browser = "Internet Explorer";
    elseif (strpos($user_agent, "Safari") !== false) $browser = "Safari";
    else $browser = "Неизвестный";
    echo "Ваш браузер: $browser<br><br>";
    
    $file=fopen("data.csv", 'a+') or die("Не удалось открыть файл");

        fputcsv($file, explode(',', $browser),';');
        
        
   
    fclose($file);
    $file=fopen("data.csv", 'r') or die("Не удалось открыть файл");
    for($i=0; $data = fgetcsv($file, 1000,";"); $i++){
       if($data[0]=="Chrome")$Chrome++;
       if($data[0]=="Atom")$Atom++;
	   if($data[0]=="Firefox")$Firefox++;
	   if($data[0]=="YaBrowser")$YaBrowser++;
	   if($data[0]=="Opera")$Opera++;
	   if($data[0]=="Internet Explorer")$InternetExplorer++;
	   if($data[0]=="Safari")$Safari++;
    }
    fclose($file);
     echo "Atom=$Atom   Chrome=$Chrome   YaBrowser=$YaBrowser   Opera=$Opera   Firefox=$Firefox   Internet Explorer=$InternetExplorer   Safari=$Safari";
?>
</body>
</html>