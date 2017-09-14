
<b>Address book web application</b>

This web application supports managing of address entries.
User is able to create/view/update/delete addresses which are stored in H2 in-memory database (DB configuration is replaceable).

Application is developed using Spring Framework and JPA (Java Persistence API).
Hibernate is used as a provider API for JPA.
Spring framework provides declarative transactions, dependency injection capabilities, i18n support,
and transparent MVC architecture model.

To run the app simply deploy war artifact "address_book-${version}.war" to Tomcat container.

Or alternatively, you can use the following command to run the app.

```
gradlew tomcatRun
```

And when you finish work, use command command below to kill tomcat process.

```
gradlew tomcatStop
```

