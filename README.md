# Assets
This is a spring boot based spring rest application for assets management of user.User can add a new asset and can find nearest asset by proving location information.
Right now only shops are supported . so user can add a shop and find closest shop to location provided . It uses google map api to get geolocation and store this location 
for further use.


 Dependencies--

Gradle 2.3
Java 8

 Build--

This is restfull service with an embedded tomcat. Hence, a WAR file may not be required. A JAR can be built which can be deployed/run in server. Since it is gradle based java application use the following to package it in a jar.
gradle build

To build without running tests

gradle build -x test
On successful build the jar can be found at build/libs/ path.

Run---

Running the application is as easy as building and a one step process. Once you have the jar it can be run with the following command.
java -jar path/to/jar/assests-0.0.1-SNAPSHOT.jar
During development the following command will be useful to run without jar.
gradle run
You will see the following message after a successful start up.
:: Spring Boot ::        (1.5.4.RELEASE)
Application has started successfully with server running at http://localhost:8080

#To Run Tests

The application comes with unit and integration tests for the rest end points. Use the following command to run tests.

gradle test

API---

The Assets management service uses Google's GeoCoding API to locate a shop and exposes following rest APIs to post a shop and get the closest shop. 

Add Shop-

This api adds a shop to the in-memory database.

URI            - /shops/postShop

REQUEST BODY   - 
{"shopName" : "Test Shop","shopAddress" : {"number": "Om Sweets,sector 56,gurgaon,haryana","postCode" : 94043","shopGeoLocation" : {"latitude": "123456","longitude" : 123456}}}

HTTP METHOD    - POST

HTTP RESPONSE  -  OK / CREATED

RESPONSE BODY  -

null/{"shopName" : "Test Shop","shopAddress" : {"number": "Om Sweets,sector 56,gurgaon,haryana","postCode" : 94043","shopGeoLocation" : {"latitude": "123456","longitude" : 123456}}}

Response will contains replaced version of shop if already exists or null

e.g - http://localhost:8080/assets/shops/postShop

Get Closest Shop-

This api gets the closest shop to the location (latitude, longitude) passed in the request parameter.

URI            - /shops/closest

REQUEST PARAMS - latitude, longitude

HTTP METHOD    - GET

HTTP RESPONSE  - 200 OK

RESPONSE BODY  - /{"shopName" : "Test Shop","shopAddress" : {"number": "Om Sweets,sector 56,gurgaon,haryana","postCode" : 94043","shopGeoLocation" : {"latitude": "123456","longitude" : 123456}}}

e.g - http://localhost:8080/assets/shops/closest?latitude=123456&longitude=123456



