Requisitos:
Necessário ter o Maven e o Docker instalado.

Passos:
1- Extrair o arquivo rar dentro de uma pasta qualquer.
2- Dentro da pasta extraída (pasta do projeto, verificar se contém o arquivo Dockerfile) abrir o prompt de comando/terminal e executar o seguinte comando = mvn clean package dockerfile:build  (irá criar a imagem do projeto no Docker do usuário)
3- No mesmo prompt/terminal executar o comando = docker-compose up  (instrução para iniciar a imagem do projeto e do banco de dados MongoDB, é feito o pull da imagem do Mongo caso não tenha). Será exibida a inicialização do Spring.
4- Quando o Spring terminar a sua inicialização, acessar a URL http://localhost:8080/swagger-ui.html no browser.
5- Na URL contém todos os endpoints disponibilizados pela API e sua documentação. Clicando em um endpoint é possível ver o botão, no lado direito, "Try it out" onde o usuário pode executar uma requisição ao endpoint.

Foi utilizado Spring-boot, MongoDB, JUnit4 e Swagger no desenvolvimento dessa API.
