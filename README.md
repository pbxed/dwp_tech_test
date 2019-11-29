# Technical Test - Associate Java Software Engineer (DWP)
***
*Jordan Shenolikar*
***
### Objective

*Using the language of your choice please build your own API which calls the API at
https://bpdts-test-app.herokuapp.com/, and returns people who are listed as either living in London, or whose current 
coordinates are within 50 miles of London.*
***

### Architecture (3-tier)
To achieve the objective, a 3-layer (presentation, service and data) architecture was used. Service layer  
contains the core logic of the application, e.g. to calculate the distance between two pairs of latitude and longitude 
coordinates. The service layer has no dependencies from other layers. Other layers depend on it and it is protected 
against changes like changing the presentation layer to a Web application. 

### Exception Handling
Using spring annotation @ControllerAdvice and @ExceptionHandler, the application demonstrates how custom exception 
handling can be implemented and returned as a JSON when the user consumes the API. This avoids presenting stacktrace's 
should something go wrong and provides a greater level of feedback to the user.

### Installation and Use
Clone the repository and run locally.

Send a GET request to http://localhost:8080/api/users and include the following query parameters

?city= *(String)*
?lat= *(double)*
?long= *(double)*
?distance *(double)*

e.g for the case of retrieving all users marked as living in London or withing 50 miles the url would be:

http://localhost:8080/api/users?city=London&lat=51.5074&long=0.1278&dist=50

Where lat & long are the coordinates of London as per Google and 50 is the inclusion radius in miles.

### Swagger
Swagger UI has also been implemented and be found at:

http://localhost:8080/swagger-ui.html#







