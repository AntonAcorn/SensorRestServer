# URL shortener
## Review
This API service allows you to easily add a sensor and retrieve its measurements or count rainy days 
through the use of a tool such as 
Postman or any other preferred application.


### Использованные технологии
* Java 17
* Spring Boot
* Maven
* Lombok
* Hibernate validator
* Model mapper
* Jackson-databind
* Postgresql
* Log4j

## How to run
* Clone repository
* You should have docker and maven
* Just use start.sh
* To stop application use stop.sh

## Endpoints
<font color='#fa8072'>Sensor: /sensors - general path

* <font color='#5f9ea0'> POST /sensors/registration (adding sensor) 
</font>

If placed on port 7070, the request will look like:
**localhost:7070/sensors/registration**

Json example:
{
"name" : "SensorLA"
}

* <font color='#5f9ea0'> DELETE /registration/{name}</font>

    example: /registration/SensorLA (it will delete sensor by name)

<font color='#fa8072'>Measurements: /measurements - general path</font>

If placed on port 7070, the request will look like:
**localhost:7070/measurements**

* <font color='#5f9ea0'>POST /measurements/add (adding measurements to a sensor by name)</font>

Json example: {
"value" : 12,
"raining" : true,
"sensor": {
"name" : "SensorLA"
}
}


* <font color='#5f9ea0'>GET /measurements</font>
  localhost:7070/measurements

List all measurements

* <font color='#5f9ea0'>GET /measurements/rainyDaysCount</font>

Count rainy days






