# Student API

Example of a simple REST API.
Provides an API to query students enrolled in a University.
Generated with Swagger/OpenAPI.

## Docker Deployment

1. Build Application with `maven clean install`
2. Create image from Dockerfile in studAppServer/ with `sudo docker build` (micro.war must be in same directory)
2.  Execute `sudo docker run -p 8080:8080 <containerId>`
