<?php
#Arquivo de Conexão com o banco de dados
#Criado por Marcos Diógenes, em 21/02/2019
$db_name="disfagia";
$mysql_user="root";
$server_name="localhost";

$connection= mysqli_connect($server_name,$mysql_user,"",$db_name);
#Teste de Conexão
if(!$connection){

  echo "Falha na conexão";
}
else{
  echo "Conexão bem sucedida";
}

?>
