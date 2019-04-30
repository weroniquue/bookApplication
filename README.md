# bookApplication
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/baf8dd8813ad42619c20cb8683103ba1)](https://www.codacy.com?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=weroniquue/bookApplication&amp;utm_campaign=Badge_Grade)

Simple Spring app which provide several endpoints with relevant information based on data read from a JSON file or url Google Books Api (example: https://www.googleapis.com/books/v1/volumes?q=world&maxResults=40).

<h4>Building</h4>
To build the project use following command:

```
mvn clean package install
```

<h4>Running</h4>
To runn the project use following command:

```
mvn jetty:run
```

Default: read from file books.json. <br>
If you want to change data source for example fetch data from google API you can use following command:
```
mvn jetty:run -DfromUrl=true -Dapiurl=<LINK>
```
Also it is possible to change json file. Put this file in /src/main/resources and use command:
```
mvn jetty:run -DfromUrl=false -Djsonpath=<FILE NAME>
```
Dashboar was written in pure JS so page to run you can find in bookApplication/resources/booksFront/index.html



<h3>API after run</h3>
Get all book: GET http://localhost:8080/all </br>
Get available categories: GET http://localhost:8080/availableCategory </br>
Get book by isbn: GET http://localhost:8080/isbn?isbn=&ltPARAM&gt </br>
Get books by category: GET http://localhost:8080/category?category=&ltPARAM&gt </br>
Get rating per author: GET http://localhost:8080/rating </br>
 
