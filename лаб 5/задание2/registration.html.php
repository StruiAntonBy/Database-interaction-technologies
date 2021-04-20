<HTML>
<HEAD><TITLE>Registration</TITLE></HEAD>
<BODY>
<form action="processing.php" method="post">
<div><label for="Login">Login:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="text" name="Login" id="Login"></label>
</div>
<P></P>
<div><label for="Password">Password:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="text" name="Password" id="Password"></label>
</div>
<P></P>
<div><label for="RepeatPassword">Repeat password:&nbsp;
<input type="text" name="Repeatpassword" id="RepeatPassword"></label>
</div>
<p>Are you a software tester?</p>
<div><label>Yes<input type="radio" name="time" value="Yes"></label> 
<label>No<input type="radio" name="time" value="No"></label></div>
<P></P>
<div><label><input type="checkbox" name="human" value="Woman">Woman</label> 
<label><input type="checkbox" name="human" value="Man">Man</label></div>
<P>Your main language?</P>
<div><select name="language">
	 <option value="English">English language</option>
     <option value="Russian">Russian language</option>
     <option value="German">German language</option>
   </select></div>
<P></P>
<p>First name:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="Firstname" maxlength="50" size="30" value="Ivan"></p>
<P></P>
<p>Last name:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="Lastname" maxlength="50" size="30" value="Ivanov"></p>
<P></P>
<p>Middle name:&nbsp;&nbsp;&nbsp;<input name="Middlename" maxlength="50" size="30" value="Ivanovich"></p>
<P></P>
<p>e-mail:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="e-mail" maxlength="50" size="30" value="Ivanov@gmail.com"></p>
<P></P>
<p>Phone number:&nbsp;<input name="Phonenumber" maxlength="50" size="30" value="+375331234567"></p>
<P></P>
<p>Tell us about yourself:</p>
<p><textarea name="story"></textarea></p>
<P></P>
<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="Authorize"></div>
</form>
</BODY>
</HTML>