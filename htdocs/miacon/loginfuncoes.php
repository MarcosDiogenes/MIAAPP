<?php
$host="localhost";
$database="disfagia";
$username="root";
$password="";

$u_nome = 'P1'; //$_POST["nome"];
$u_cpf = '102';//$_POST["cpf"];

$sql= "select id_paciente from tlb_paciente where nome = '$u_nome' and cpf = '$u_cpf';";

$con= mysqli_connect($host,$username,$password,$database);

$result= mysqli_query($con,$sql);

$response= array();

while($row=mysqli_fetch_array($result)){

  #array_push($response,array("id_classificados"=>$row[0],"niveldisfagia"=>$row[1],
  #"tipoalimento"=>$row[2],"id"=>$row[3],"duracao"=>$row[4],"datahora"=>$row[5]));
  array_push($response,array("id_paciente"=>$row[0]));
}

echo json_encode(array("server_response"=>$response));
mysqli_close($con);

?>
