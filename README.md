# UrlShortenerProject
This project allows users to convert Long Urls to Short Urls

When the user adds an Url the Url is passed from Frontend applcation to Spring Boot using REST Api.
The Url is then inserted into embedded H2 Database. A short Url is generated by taking primary key and converting to Base 62.
The generated value us then added to customer url "emurl/<Base62 conversion>" and persisted to the dB.
  
The Url is then available in a table and user can link on the short Url and be naviagted to the original long url after application has converted the short url back to the original url.  

I created two Dockerfile to deploy to Docker.
One for the Java jar and java executable.
Other for node and Angular 6 application.

## To Deploy to Docker:
#### For Spring Boot Server:
* Open docker terminal
* Navigate inside Server/demo 
* docker build -f Dockerfile -t demo .
* docker run -p 8080:8080 demo


#### For Angular Client:
* Open another docker terminal
* Navigate Client/UrlClient
* docker build -t urlclient:prod .
* docker run -p 80:80 urlclient:prod



