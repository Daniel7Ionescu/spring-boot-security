## What is this
A spring boot app used for learning / testing Spring security alongside JPA

### Functionality
- users can register using a username (in this case their email) and a password
- their data is saved in a DB (postgreSQL)
- setting up the authorization with securityFilterChain, some requests don't require to be authenticated, while other do
- on trying to access restricted areas, users are checked via an authenticationManager
