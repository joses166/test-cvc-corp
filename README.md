# test-cvc-corp

Documentação:

O projeto foi dividido em diferentes pacotes com suas devidas regras.

Pacotes:

br.com.cvc.api.config = configuração do Swagger para documentação da API;
br.com.cvc.api.controllers = configuração dos endpoints da API;
br.com.cvc.api.dto = DataTransferObject dados que são passados pelo usuário no endpoint;
br.com.cvc.api.entities = configuração das entidades que serão utilizadas para persistência no banco de dados;
br.com.cvc.api.repositories = configuração do repository para persistência no banco por meio do JPA;
br.com.cvc.api.responses = classe voltada para a resposta de dados para o usuário;
br.com.cvc.api.services = configuração das interfaces com os métodos utilizados na regra de negócio;
br.com.cvc.api.services.impl = classes voltadas às implementações dos métodos definidos nas interfaces com as regras de negócio definidas;
br.com.cvc.api.utils = métodos extras utilizados para alguma execução específica.

Foi definido dentro do pacote br.com.cvc.api.services.impl diferentes classes com objetivos específicos para que o código fique mais limpo e fazendo com que cada uma das classes tenha sua responsabilidade única, como por exemplo o cálculo da taxa, o cálculo da taxa pode ser visto nas diferentes classes definidas.

Foram criados dois endpoint:

GET
http://localhost:8080/api/financial 
Utilizado para recuperar as transferências financeiras feitas;

POST
http://localhost:8080/api/financial/schedulling 
Utilizado para o usuário agendar uma transferência financeira
Payload:
{
“originAccount”:”XXXXXX”,
“destinyAccount”:”XXXXXX”,
“transferValue”:100.0,
“transferDate”:”dd/MM/yyyy”
}







Swagger:



Para subir a aplicação é necessário:

git clone https://github.com/joses166/test-cvc-corp
cd test-cvc-corp
mvn clean package -DskipTests
cd target/
java -jar test-cvc-corp-0.0.1-SNAPSHOT.jar

Tecnologias:

Java 17
Spring Boot 2.4.4
H2 Database

Repositório:

https://github.com/joses166/test-cvc-corp

