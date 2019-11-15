# MicroServices Spring Boot with docker "Lambda+Store"
![alt text](https://www.red-gate.com/simple-talk/wp-content/uploads/2017/09/word-image-59.png)


The project contains 6 microservices and Zuul gateway and an Eureka server placed in a docker container .


1/ShoppingCart Microservice
2/SalesPromotion microservice
3/Stock microservice
4/Rating microservice
5/Delivery microservice
5/User microservice

Description:
-ShoppingCart Microservice : manage all items in shopping cart including quantity and price <br />
-SalesPromotion microservice : manage promotion for the items in the store and manage the use of discount coupon<br />
-Stock microservice : manage stock of items in the store <br />
-Rating microservice : manage rates for all items in the store <br />
-Delivery microservice : manage delivery of the bought items after checkout <br />
-User microservice : manage user authentification and register<br />



 microservices are in one container docker  and they all use  api from each other :

