
# Easy Dine Out

It is a Restaurant Management System build to allow customers to book tables at a any branch of a restaurant. It enables owners to efficiently manage their restaurant operations, including menu updates, table bookings, and branch management.

## Set up data

To understand the structure of the tables, You can checkout the ER diagram for the database `project-details/easy_dine_er_diagram.png`

Database setup is required prior to loading application, use the file `project-Info/easy_dine_set_up.sql`
```
mysql> source path-to-set-up-file
```

## Environment Variables

To run this project, you will need to update the following environment variables in your `application.properties` file


```bash
spring.datasource.url=jdbc:mysql://localhost:3306/easy_dine
spring.datasource.username=user_name
spring.datasource.password=user_password

jwt.token.secret.key=256-bit key 
#5 minutes
jwt.token.secret.validity=300
```

## Run Locally

Run following command from the root directory of the project to run the application.
```bash
  mvn spring-boot:run
```

Application will start running at port `8085`
## Documentation

#### Swagger
You can access Swagger API Documentation here:

```
http://localhost:8085/swagger-ui/index.html
```
#### Swagger
I have also attached postman collection export `project-details/ease_dine.postman_collection.json`  Follow the steps

```
Postman > import > drop the file
```

#### Implementation

    1. Login/Signup User 
        - Admin
        - Restaurant owner
        - Customer
        - JWT based Authentication + Authorization

    2. Restaurant Owner
        - Add branches
        - Add tables at each branch
        - Add Dishes with price
        - View booked tables by customer

    3. Customer 
        - Search restaurant by city
        - Search restaurant by name
        - Search restaurant by food
        - Book table at a restaurant date and start time
        - Cancel booking
        
    4. Admin
        - View Restaurant tables and bookings
        - View Restaurant foods
        - View Customer bookings
        - Add food categories
        - Add Cities
        - Add Dishes
        - Add table


#### Practices followed

    1. REST 
    2. Git commit
    3. SQL ng)
    4. Spring Data JPA 
    5. Spring Security 
    6. Authorization RBAC
    7. Authorization custom ABAC 
    8. Documentation
    


#### Learning:

    1. Normalization branches, menu, tables 
    2. With Spring Security permit /error page to avoid 403 for every error
    3. ControllerAdvice handles exception after filter, errors before filter to be handled by first filter and map to resolver, also handle RuntimeException
    4. JOIN FETCH to avoid N + 1
    5. Data JPA updates all fields by default fetch and update
    6. Role Based Access Control
    7. Adding @Transactional to update/delete methods only along with @Version for optimistic locking, as very less updates are expected. Not required for Create methods as DB handles duplicate creation based on unique constraints.
    8. Optimizing queries
        - JOIN FETCH
        - Fetch.LAZY
    9. Custom Annotation based validation
    10. Delete CASCADE 

            food -> dish -> menu
            restaurant -> branch   \
            city -> branch ---------> branch_tables -> bookings
            table_type ------------/
    11. Separate DTO for request with Validations
    12. Pick user from Authentication, do not pass Id
    13. Implement UserDetails with our entity for using Id
    14. Event Listeners to notify User on deletion of booking PostRemove 
    15. Attribute Based Access Control - PermissionEvaluator  DefaultEvaluator -> DenyAllPE Create Bean of DefaultMethodSecurityHandler and set PE to Custom PE ABAC on Restaurant, Branch, Branch table, Booking