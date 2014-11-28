#Address book web application

This web application supports managing of address entries.
User is able to create/view/update/delete addresses which are stored in the in-memory database.

Application is developed using Spring Framework and JPA (Java Persistence API).
Hibernate is used as a provider API for JPA.
Spring framework provides declarative transactions, dependency injection capabilities, i18n support,
and transparent MVC architecture model.

To run the app simply deploy war artifact "spring_address_book-*.war" to Tomcat container.

Or alternatively, you can use the following command to run the app.

```
gradlew tomcatRun
```

And when you finish work, use command command below to kill tomcat process.

```
gradlew tomcatStop
```

