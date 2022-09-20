# Projeto de Demonstração - OnSafety
Foi implementado acesso CRUD a classe Pessoa, com acesso aos métodos:
  - Get, coleção e individual;
  - Post, individual;
  - Delete;
  - Put;
  - Patch, altera o dado escolhido.
 
 # Configuração
 A configuração do banco de dados deve ser feita através do arquivo:
 ```src/main/resources/application.properties```
 
 È necessário especificar o banco de dados padrão, que tem valor ``` testedb ```, e a autenticação.
 Não é necessário inicializar as tabelas, apenas criar e especificar o banco de dados.
 
 # Considerações
 Foi considerado utilizar constraits de unicidade no CPF, porém não foi incluido.
 A própria API valida e limpa o CPF, também utilizando RegEx.
 A data de nascimento deve ser especificada para a API no formato ``` YYYY-mm-dd ```.
 
 # Testes
 Para o teste foi utilizada a ferramenta ``` Postman ``` , e o arquivo de configuração que inclui as requisições está disponível no repositório:
 ``` OnSafety Pessoas.postman_collection.json ``` 
