# Link Social Media

## Project Description
Link is the second iteration of a social media application created by Team Avatar as part of the Revature Full Stack Angular training program in April 2021. Developed with a microservice architecture, this web application allows every user to follow each other and view each other's posts. Each user has their own account that they can customize with their own information. Within this network, users can interact with each other through comments, likes, and a global chatroom. This social media application is meant to ease the transition of becoming a Revature employee.

Gateway is the routing manager that serves as the point of entry for the frontend client and routes requests to proper service locations.

## Technologies Used

- Spring Boot
	- Euereka Discovery Client
	- Spring Cloud Gateway

## Features

- Uses a single port that can recieve requests from the front end.
- Routes request based on service availability that Eureka keeps account of. 
- If service is up, sends request to the proper service.

## Getting Started
   
> Clone this repository
```
git clone https://github.com/LinkSocialNetwork/Gateway.git
```

> Clone Eureka Service repository
```
git clone https://github.com/LinkSocialNetwork/Eureka.git
```

## **Usage**

> Run the services together, sequentially in an IDE
```
Eureka > Gateway
```

> Visit the url and confirm Eureka can see the Gateway
```
http://localhost:9999/Eureka
```

## **License**

This project uses the following license: [<The MIT License>](https://www.mit.edu/~amini/LICENSE.md).
