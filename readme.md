# Student API

Provides an API to query students enrolled in a University.
REST API generated with Yoeman and Swagger/OpenAPI.

**Deployed Application available at:** https://payaramicro-student-api.herokuapp.com/micro/v1/students (May need a minute to wake up)
**Postman configuration available at:** https://www.getpostman.com/collections/d40c2bfdec870bdf66c4

## Docker Deployment

1. Build Application with `maven clean install`
2. Create image from Dockerfile in studAppServer/ with `sudo docker build` (micro.war must be in same directory)
2.  Execute `sudo docker run -p 8080:8080 <containerId>`