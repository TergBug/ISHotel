# **ISHotel application**

It is a RESTful CRUD application that has next entities:
Service, Facility, Employee, Room, Customer.

It uses MySQL database as a storage.

User is able to create, read (get by ID or get all), update and delete data.

Layers:
>model - POJO classes

>rest - servlets that handles user’s POST requests from network via HTTP/HTTPS protocol

>service - simple business logic

>repository - classes that provide access to storage

>storage - tables in database

There is one index page, which contains description of project in three languages: 
English, Russian and Chinese. And there is a documentation page on endpoint /documentation, 
which has been built with Swagger UI.

To start up application you should compile code (version of Java is 8) and start this with 
entry point in Main class. Or just start jar file.