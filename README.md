# SimpleREST
A simple REST API implementation written using Spring Data JPA, Boot and REST.
# Idea
A pseudo weather REST API. You can send a weather measurements to it, or get all measurements.
# Prerequisites
A new Maven/Gradle project with the next dependencies:
1) <a href="https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind">Jackson core databind</a>
2) <a href="https://mvnrepository.com/artifact/org.springframework/spring-web">Spring WEB</a>

or Python installed.

# Get started
Change the keys in application.properties file to make them true for you. And then launch the app.
Then, register the weather sensor by making a POST-request to the server. If you a using a Java project, paste this code in a main method and run:

RestTemplate template = new RestTemplate();
Map<String,String> jsonMap = new HashMap<>();
jsonMap.put("name","Atlas");
HttpEntity<Map<String,String>> request = new HttpEntity<>(jsonMap);
String url = "localhost:8080/sensors/registration";
System.out.println(template.postForObject(url,request,String.class));

or this if you'r using Python:

import requests
url = 'localhost:8080/sensors/registration'
json = '{"name":"Atlas"}'
print(requests.post(url=url, json=json).status_code)


Congratulations, you've registered a weather sensor! Now, you can make some measurements and send them. To make this run this code:

Java:

RestTemplate template = new RestTemplate();

Map<String,String> jsonMap = new HashMap<>();

jsonMap.put("value","43");

jsonMap.put("raining","false");

jsonMap.put("sensorName","Atlas");

HttpEntity<Map<String,String>> request = new HttpEntity<>(jsonMap);

String url = "localhost:8080/measurements/add";

System.out.println(template.postForObject(url,request,String.class));

Python:

import requests

url = 'localhost:8080/measurements/add'

json = '{"value":"43","raining":"false","sensorName":"Atlas"}'

print(requests.post(url=url, json=json).status_code)

And if you want to get all measurements, just make a get request to the this <a href="http://localhost:8080/measurements">url<a/>,or to <a href="http://localhost:8080/measurements/rainyDaysCount">this url<a/> if you just want to get a rainy days count.
