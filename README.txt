I developed program that include next functions:
 - Possibility to add url and program immediately check given url with special parameters from url.properties
  and then write result in database and then jsp show it.
 - url has three status: OK WARNING CRITICAL rely on status code which return method getResponseCode(); from
 MonitoringServiceImpl, in case status WARNING or CRITICAL program will play special alarm sound,
 - Delete urk by given id.
Work instance - monitoringsite.herokuapp.com

I use jsp technologies, so after i immediately wanted rewrite all to Angular 2.
Database Postgresql on heroku
Lombok - to avoid getter, setter, AllArgsConstructor, NoArgsConstructor, and Clean up for method close in case with
database connection, and finally val for local variable, i use this one when type of my local variable has long name.

