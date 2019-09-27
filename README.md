# My considerations

- Tasks developed:
* Create a **Store** -- OK
* Update a **Store** information -- OK
* Retrieve a **Store** by parameters -- OK
* Create an **Order** with items -- OK
* Create a **Payment** for an **Order** -- OK
* Retrieve an **Order** by parameters -- The method to retrieve is created but I couldn't finish it. I would create a OrderSpecification (JPA) and do the same methodology as I did for the Store domain.
* Refund **Order** or any **Order Item** -- OK (there is a minor bug when the created Refund is returned to the client. To fulfill the deadline I created a simply refund table with no relationships and I simply save the orderId and a list of OrderItemIds to refund. The JSON returned doesn't show the list correctly, but the Order and Payment Status are changed correctly).

* Asynchronous processing -> At first I tried to use Spring WebFlux with Kotlin coroutines, but it got a bit messy so I used the old MVC.
* Database -> Done.
* Docker -> Done. 
* AWS -> I didn't have time. I would use Terraform to create a ECS Cluster and RDS, then deploy the app using containers.
* Security -> I would do like [that](https://auth0.com/blog/developing-restful-apis-with-kotlin/). Using JWT authentication.
* Swagger -> I didn't have time. I would do as I did in my [springboot-seed-api](https://github.com/GlenioSP/springboot-seed-api). I put a postman_collection file in the project's root folder...better than nothing...
* Clean Code -> I did my best
* (Extra) Tests: I didn't have time. I've configured the app to use JUnit 5 and MockK. I would do something link [that](https://github.com/spring-guides/tut-spring-boot-kotlin)

Obs.: for consideration, being a MVP for a microservices application, if I have enough time I would create three services, one for each domain (Order, Payment and Store). Each one would be an Aggregate (as in DDD). Then, I would use Axon Framework (helping with Event Sourcing and CQRS) to create the services. Spring Cloud with Circuit Break could help. The best scenario would be a deploy within Kubernetes with some service mesh, like Istio. But, I bit overkill for now...

### Run the app

- mvn clean install

- docker build -t api .

- docker run -p 8080:8080 api

- docker-compose up -d (to run MySQL container)

Obs.: I didn't have time to put the api docker image inside docker-compose.  

------------------------------------------------------------------------------
# Invillia recruitment challenge

[![Build Status](https://travis-ci.org/shelsonjava/invillia.svg?branch=master)](https://travis-ci.org/shelsonjava/invillia)

![Invillia Logo](https://invillia.com/public/assets/img/logo-invillia.svg)
[Invillia](https://https://www.invillia.com/) - A transformação começa aqui.

The ACME company is migrating their monolithic system to a microservice architecture and you’re responsible to build their MVP (minimum viable product)  .
https://en.wikipedia.org/wiki/Minimum_viable_product

Your challenge is:
Build an application with those features described below, if you think the requirements aren’t detailed enough please leave a comment (portuguese or english) and proceed as best as you can.

You can choose as many features you think it’s necessary for the MVP,  IT’S NOT necessary build all the features, we strongly recommend to focus on quality over quantity, you’ll be evaluated by the quality of your solution.

If you think something is really necessary but you don’t have enough time to implement please at least explain how you would implement it.

## Tasks

Your task is to develop one (or more, feel free) RESTful service(s) to:
* Create a **Store**
* Update a **Store** information
* Retrieve a **Store** by parameters
* Create an **Order** with items
* Create a **Payment** for an **Order**
* Retrieve an **Order** by parameters
* Refund **Order** or any **Order Item**

Fork this repository and submit your code with partial commits.

## Business Rules

* A **Store** is composed by name and address
* An **Order** is composed by address, confirmation date and status
* An **Order Item** is composed by description, unit price and quantity.
* A **Payment** is composed by status, credit card number and payment date
* An **Order** just should be refunded until ten days after confirmation and the payment is concluded.

## Non functional requirements

Your service(s) must be resilient, fault tolerant, responsive. You should prepare it/them to be highly scalable as possible.

The process should be closest possible to "real-time", balancing your choices in order to achieve the expected
scalability.

## Nice to have features (describe or implement):
* Asynchronous processing
* Database
* Docker
* AWS
* Security
* Swagger
* Clean Code
