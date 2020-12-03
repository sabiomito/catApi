# CatApi
Aplicação que coleta dados da Api de gatos disponível em https://thecatapi.com/, salva algumas informações em um banco de dados sqLite e fornece uma Api onde é possível realizar 4 operações distintas:
- listar todas as raças
- listar as informações de uma raça 
- listar as raças que contém determinado temperamento
- listar as raças de uma determinada origem 

## Arquitetura

![Alt text](docs/architeture.png?raw=true "Title")

## Coleção Postman

Coleção do postman exportado

[PostmanArchive](docs/CatApiCollection.postman_collection.json)

Link publico porem nao funciona com requisições http mas talvez seja mais facil de baixar e usar no postman instalado

[PostmanPublicLink](https://web.postman.co/workspace/c2cfb52d-e761-496f-8e0b-25328860ef4a/collection/13718289-8b54fb67-924e-4e92-9dd1-5dfb87a0ff58?ctx=documentation)

## Docmentação da Api
A Api está rodando(até 02/01/2021) na url **http://191.235.78.82:80** é preciso utilizar o header **X-API-KEY** com o valor da apiKey **PUBLIC_TEST_API_KEY** para validação como um meio de segurança, as quatro operações fornecidas pela Api estão sendo explicadas abaixo:
Todas as operações da Api retornam um Json com um campo **timestamp** contendo a representação do instante em que foi executada a chamada, existe um campo **status** que pode conter o valor **OK** e um campo caso nao tenha ocorrido nenhum erro ou o valor **FALHA** e no caso de ocorrer uma falha o campo **errorMessage** contém a mensagem de errro da exceção lançada na aplicação.

### Listar todas as raças
Para listar todas as raças basta fazer uma requisição **GET** para o endereço **http://191.235.78.82:80/breeds** com o cabeçalho da forma indicada no inicio da sessão.
Além das informações comuns a todas as respostas no caso de sucesso existe um campo **breeds** contendo uma lista de todos as raças em que cara objeto da lista tem um campo **origin**,**name** e **temperament** todos do formato string e que representam a origem da raça, nome da raça e temperamentos da raça respectivamente. Abaixo podemos ver um exemplo do retorno dessa função da api:

**http://191.235.78.82:80/breeds**
```
{
    "timestamp": 1606961054595,
    "status": "OK",
    "errorMessage": "",
    "breeds": [
        {
            "origin": "Egypt",
            "name": "Abyssinian",
            "temperament": "Active, Energetic, Independent, Intelligent, Gentle"
        },
        .
        .
        .
        {
            "origin": "Greece",
            "name": "Aegean",
            "temperament": "Affectionate, Social, Intelligent, Playful, Active"
        }]
}
```

Em caso de falha por falha em que o token está incorreto a resposta é a seguinte:
```
{
    "status": "FALHA",
    "errorMessage": "A chave de api está errada ou faltando no header consulte https://github.com/sabiomito/catApi para mais detalhes"
}
```


### listar as informações de uma raça 
Para listar a informação de uma raça especifica basta fazer uma requisição **GET** para o endereço **http://191.235.78.82:80/breedByName** com o cabeçalho da forma indicada no inicio da sessão e passando o parametro **name** que pode conter apenas letras e numeros.
Além das informações comuns a todas as respostas no caso de sucesso existe um campo **breeds** contendo um objeto representando a raça que contém um campo **origin**,**name** e **temperament** todos do formato string e que representam a origem da raça, nome da raça e temperamentos da raça respectivamente. Abaixo podemos ver um exemplo do retorno dessa função da api:


**http://191.235.78.82:80/breedByName?name=Bambino**
```
{
    "timestamp": 1606964361579,
    "status": "OK",
    "errorMessage": "",
    "breeds": [
        {
            "origin": "United States",
            "name": "Bambino",
            "temperament": "Affectionate, Lively, Friendly, Intelligent"
        }
    ]
}
```

### listar as raças que contém determinado temperamento
Para listar todas as raças que contenham um determinado temperamento basta fazer uma requisição **GET** para o endereço **http://191.235.78.82:80/breedsBytemperament** com o cabeçalho da forma indicada no inicio da sessão e passando o parametro **temperament** que pode conter apenas letras e numeros.
Além das informações comuns a todas as respostas no caso de sucesso existe um campo **breeds** contendo uma lista de todos as raças em que cara objeto da lista tem um campo **origin**,**name** e **temperament** todos do formato string e que representam a origem da raça, nome da raça e temperamentos da raça respectivamente. Abaixo podemos ver um exemplo do retorno dessa função da api:

**http://191.235.78.82:80/breedsBytemperament?temperament=Intelligent**
```
{
    "timestamp": 1606964724812,
    "status": "OK",
    "errorMessage": "",
    "breeds": [
        {
            "origin": "Egypt",
            "name": "Abyssinian",
            "temperament": "Active, Energetic, Independent, Intelligent, Gentle"
        },        
        .
        .
        .
        {
            "origin": "Greece",
            "name": "Aegean",
            "temperament": "Affectionate, Social, Intelligent, Playful, Active"
        }]
}
```

### listar as raças que contém determinada origem
Para listar todas as raças que tenham a mesma origem basta fazer uma requisição **GET** para o endereço **http://191.235.78.82:80/breedsByOrigin** com o cabeçalho da forma indicada no inicio da sessão e passando o parametro **origin** que pode conter apenas letras e numeros.
Além das informações comuns a todas as respostas no caso de sucesso existe um campo **breeds** contendo uma lista de todos as raças em que cara objeto da lista tem um campo **origin**,**name** e **temperament** todos do formato string e que representam a origem da raça, nome da raça e temperamentos da raça respectivamente. Abaixo podemos ver um exemplo do retorno dessa função da api:

**http://191.235.78.82:80/breedsByOrigin?origin=Egypt**
```
{
    "timestamp": 1606964891915,
    "status": "OK",
    "errorMessage": "",
    "breeds": [
        {
            "origin": "Egypt",
            "name": "Abyssinian",
            "temperament": "Active, Energetic, Independent, Intelligent, Gentle"
        },
        {
            "origin": "Egypt",
            "name": "Chausie",
            "temperament": "Affectionate, Intelligent, Playful, Social"
        },
        {
            "origin": "Egypt",
            "name": "Egyptian Mau",
            "temperament": "Agile, Dependent, Gentle, Intelligent, Lively, Loyal, Playful"
        }
    ]
}
```

## Configurando o ambiente de produção

Com o projeto compilado usando o comando 
```
mvn clean package
```
será criado um arquivo catapi-0.0.1-SNAPSHOT.jar que será utilizado mais adiante.

Em um ambiente linux 18
Instale o java usando o comando:

```
sudo apt install openjdk-11-jre-headless
```

Copie o arquivo [catapi-0.0.1-SNAPSHOT.jar](target/catapi-0.0.1-SNAPSHOT.jar) para qualquer pasta do seu ambiente e para começar o serviço execute o comando:

```
sudo nohup java -jar catapi-0.0.1-SNAPSHOT.jar
```
Após executar é preciso ao menos uma vez requisitar a api **http://191.235.78.82:80/getDataFromTheCatApi** para que os dados da api dos gatos sejam coletados e inserido no banco de dados

É possível utilizar o windows basta instalar o java e executar o jar **catapi-0.0.1-SNAPSHOT.jar** e o mesmo procedimento funciona em uma maquina local


## Documentação do projeto

O projeto foi feito utilizando o framework spring sua classe principal **CatApiApplication**, essa classe é responsável por mapear as requisições receber os parametros e executar as ações necessárias, ao todo no projeto temos 8 classes que podemos separar em 4 categorias:

### Modelagem dos dados
As duas classes **Cat** e **CatBreed** representam os dados que são manipulados.
#### Cat
Contem apenas as informações necessárias para guardar a url da foto de um gato e manter associado a um id, objeto e raça, todos atributos tem métodos get e set.
##### Atributos
- **id** do tipo **String** representa o indice da figura no banco de dados original
- **url** do tipo **String** representa o endereço da imagem do gato
- **breed** do tipo **String** armazena o nome da raça do gato
- **object** do tipo **String** armazena o tipo de objeto que pode estar presente na fotografia do gato
 
#### CatBreed
Contém as informações da raça do gato e para cada atributo um get e set.
##### Atributos
- **name** do tipo **String** representa o nome da raça
- **origin** do tipo **String** representa a origem da raça
- **temperament** do tipo **String** pode conter varios temperamentos tipicos da raça


### Coleta de dados da api dos gatos
Categoria formada por apenas uma classe **TheCatApiCollector**

#### TheCatApiCollector
Responsável por acessar a api original e retornas as informações já convertidas no modelo de dados da aplicação, possui apenas um atributo **restTemplate** que é um helper do spring em que é utilizado uma função **getForObject** que recebe a url ja montada e a referencia da classe e automaticamente ja converte o json de resultado em um objeto da classe basta os nomes dos atributos coincidirem com os nomes dos atributos do json de resposta da api.
Dentro do construtor é adicionado um header que vai ser usado em todas as requisições e contém a apiKey.

##### Métodos
- **getBreeds** recupera uma lista com todas as raças usando a url ***https://api.thecatapi.com/v1/breeds***
- **getImagesUrls** recupera 3 urls de imagens de gatos recebendo como parametro o id de uma raça usando a url **https://api.thecatapi.com/v1/images/search?breed_ids={0}&limit={1}**
- **getImagesUrlsWithSunglasses** recupera 3 urls de imagens de gatos usando óculos esse parametro é definido pelo numero 4 no categoy_id usando a url **https://api.thecatapi.com/v1/images/search?category_ids={0}&limit={1}** tambem é utilizado a limitação fornecida pela propria api dos gatos.
- **getImagesUrlsWithHats** recupera 3 urls de imagens de gatos usando chapeu esse parametro é definido pelo numero 1 no categoy_id usando a url **https://api.thecatapi.com/v1/images/search?category_ids={0}&limit={1}** tambem é utilizado a limitação fornecida pela propria api dos gatos.

### Gerenciamento do banco de dados
Categoria formada por apenas uma classe **CatDataBase**

#### CatDataBase
Responsável pela criação do banco de dados, suas tabelas, inserção e leitura dos dados é utilizado o sqLite com driver jdbc do java, no proprio construtor as tabelas são criadas se caso ainda nao existam e existe 7 métodos alinhados com os propósitos da API.
O único atributo é um CatBreed que representa um objeto nao encontrado para evitar o uso de NULL que é uma péssima pratica de programação.

##### Métodos
- **insertBreed** insere uma raça no bando de dados 
- **insertCatImageUrl** insere uma url de imagem no bando de dados
- **getBreeds** recupera a lista de todas as raças
- **getBreedByName** recupera uma raça recebendo o nome como parametro
- **getBreedsByTemperament** recupera uma lista de raças recebendo um temperamento como parametro, uma raça pode ter mais de um temperamento entao basta ter o que foi passado para isso foi usado o comando **LIKE** do sql
- **getBreedsByOrigin** recupera uma lista de raças recebendo uma origem como parametro
- **parseResultSetToCatBreedArray** converte o resultado de uma query em um array de **CatBreeds** que representa uma raça, criado para reuso de código e foi usado em todos metodos dessa classe que retornam lista

### Api desenvolvida nesse trabalho
Responsável por mapear as requisições das rotas da api e retornar o resultado necessário, trata todas as execções para que se ocorra algum erro o usuário seja notificado e trata alguns erros esperados como entrada invalida e o teste da chave da Api.
Formada por 4 classes:
#### ApiKeyManager
Classe simples que apenas armazena o valor da chave de teste e tem um método **validateApiKey** que testa se uma String recebida é igual a chave de test **PUBLIC_TEST_API_KEY**, escolhi usar apenas uma chave de api para exemplificar a preocupação conm a segurança e a forma de se fazer, mas entendo que é necessário todo um sistema para geração das chaves e associação com usuários do sistema.

#### JsonResponse
Classe responsável por converter objetos em uma string Json utilizando **ObjectMapper** da biblioteca **Jackson** que já é importada por padrao no spring, algumas mensagens foram colocadas em variáveis estáticas para facilitar a manutenção do código(boas praticas).
##### Atributos
- **status** que pode ter dois valores **OK** ou **FALHA**
- **errorMessage** que recebe a mensagem de erro ou fica vazio
##### Métodos
- Getters e Setters
- **toString** que converte o proprio objeto em uma String em formato Json que tem todos os atributos
- **getFailJsonWithMapperError** retorna um json de erro interno para quando tem algum problema na conversão de objeto para Json
- **getApiKeyError** retorna um json de erro para quando é recebido uma ApiKey inválida
- **invalidArgument** retorna um json de erro para quando é recebido um argumento inválido

#### BreedsResponse
Classe que extende a classe **JsonResponse** e adiciona apenas um atributo **breeds** do tipo **CatBreed[]* para que faça parte de json convertido é necessário os Getters e Setters e algumas mensagens de erro específicas.
##### Métodos
- **breedNotFoundByName** retona um json de erro para quando a raça nao é encontrada recebendo nome como parametro
- **breedNotFoundByTemperament** retona um json de erro para quando a raça nao é encontrada recebendo temperamento como parametro
- **breedNotFoundByOrigin** retona um json de erro para quando a raça nao é encontrada recebendo origem com parametro
- **breedsNotFound** retona um json de erro para quando nenhuma raça é encontrada provavelmente por a base nao estar populada

#### CatApiApplication
Classe principal que trata as execeções e utiliza as classes ja citadas nesta secção para retornas os objetos corretamente fazendo verificações nas entradas, no header e chamando metodos das classes de banco de dados e do collector.
##### Métodos
- **isStringNumberAndLettersOnly** testa se uma string contém apenas letras e numeros
- **getDataFromTheCatApi** chamado quando o servidor recebe uma requisição para o caminho **/getDataFromTheCatApi** não recebe nenhum parametro além do header, testa a apiKey e usa a classe **TheCatApiCollector** para recuperar as informações e usa a classe **catDataBase** para inseri-las no banco de dados, as informações são 3 imagens de gatos com chapeu, 3 imagens de gato com oculos, todas as raças e 3 imagens de gato para cada raça, é ideal que essa função seja chamada apenas uma vez após isso é lançada uma execção do bando de dados ao tentar adicionar elementos repetidos em uma chave UNIQUE
- **getBreeds** chamado quando o servidor recebe uma requisição para o caminho **/breeds** não recebe nenhum parametro além do header, usa a classe **catDataBase** para retornar um json com uma lista de todas as raças testando se o resultado da lista é maior que 0
- **getBreedByName** chamado quando o servidor recebe uma requisição para o caminho **/breedByName**, usa a classe **catDataBase** para retornar um json com uma lista com uma raça que tenha o mesmo nome passado por parametro testando se ela existe ou nao, tambem é feito o tratamento do parametro se contem apenas letras e numeros
- **getBreedsByTemperament** chamado quando o servidor recebe uma requisição para o caminho **/breedsBytemperament** , usa a classe **catDataBase** para retornar um json com uma lista de todas as raças contendo o temperamento recebido como parametro e testando se o resultado da lista é maior que 0, tambem é feito o tratamento do parametro se contem apenas letras e numeros
- **getBreedsByOrigin** chamado quando o servidor recebe uma requisição para o caminho **/breedsByOrigin** , usa a classe **catDataBase** para retornar um json com uma lista de todas as raças contendo a origem recebida como parametro e testando se o resultado da lista é maior que 0, tambem é feito o tratamento do parametro se contem apenas letras e numeros

