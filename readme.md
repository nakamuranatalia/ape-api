# Ape-api

Ape-api is an application used for identify whether given DNA is simian or human. It can also inform about the stats, in other words,  how many human and simian DNA is in the database and the ratio.

# How to set up

## Requirements

To start the project you will need:
- MySQL 8.0.35 or higher.
- Maven 2.0.9 or higher.
- Java 17.0.6 or higher.
- Git.

## Step by step
1. **Clone the application**

 ``` bash
git clone https://github.com/nakamuranatalia/ape-api.git
```

2. **Create MySQL database**

  ``` sql
 create dabatase ape
```

3. **Change MySQL username and password**
- Open ```src/main/resources/application.properties```
- Change ```spring.datasource.username``` and ```spring.datasource.password``` to match your MySQL configuration.
4. **Build and run the application using Maven**
``` bash
mvn spring-boot:run
```
The application will start running at [http://localhost:8080](http://localhost:8080/).

# How to run tests
``` bash
mvn test
```

# Endpoints
| HTTP Verb  | Endpoint     | Action                                           |
|:-----------|:-------------|:-------------------------------------------------|
| ```POST``` | ```/ape```   | To verify if the DNA is simian or human.         |
| ```GET```  | ```/stats``` | To obtain more information about the data stats. |

Example of JSON request for the ```POST``` endpoint:
``` json
{
    "dna": ["ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"]
}
```
Example oj JSON response for the ```GET``` endpoint: 
``` json
{
    "countMutantDna":6.0,
    "countHumanDna":2.0,
    "ratio":3.0
}
```