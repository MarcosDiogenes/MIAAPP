<?php
# Arquivo para coleta de dados da tabela tbl_teste
# Criado por Marcos Diógenes em 21/02/2019

# Requisição de funcionalidade do arquivo de conexão
require "init.php";

#Variáveis representando os atributos a serem coletados da tabela
$id="22";

#Comando de coleta de dados
#$sql_query="select niveldisfagia from tbl_dadosclassificados where id like '$id';";
$sql_query="select niveldisfagia from tbl_dadosclassificados where id_classificados=24";

#Variável que guarda o dado coletado pelo comando de coleta
$result=mysqli_query($connection,$sql_query);

#Execução da coleta
if(mysqli_num_rows($result)>0){
  #selecionar e demonstrar dados coletados
  $row= mysqli_fetch_assoc($result);
  $niveldisfagia= $row["niveldisfagia"];
  echo " $niveldisfagia";
} else {
  #Mensagem de erro
  echo " Erro na Coleta de Dados";

}

?>
