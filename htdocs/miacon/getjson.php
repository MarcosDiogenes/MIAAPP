<?php
$host="localhost";
$database="disfagia";
$username="root";
$password="";
$u_tbl_paciente_id_paciente = $_POST["tbl_paciente_id_paciente"];

$sql= "select niveldisfagia, alimentosindicados, consistencyfood from
tbl_dadosclassificados where tbl_paciente_id_paciente = '$u_tbl_paciente_id_paciente';";

$con= mysqli_connect($host,$username,$password,$database);

$result= mysqli_query($con,$sql);

$response= array();

while($row=mysqli_fetch_array($result)){

  #array_push($response,array("id_classificados"=>$row[0],"niveldisfagia"=>$row[1],
  #"tipoalimento"=>$row[2],"id"=>$row[3],"duracao"=>$row[4],"datahora"=>$row[5]));
  array_push($response,array("niveldisfagia"=>$row[0],"alimentosindicados"=>$row[1],
  "consistencyfood"=>$row[2]));
}

echo json_encode(array("server_response"=>$response));
mysqli_close($con);

?>
