# enterpriseExam 2022

## Todo
- [x] Change /api/authorization /api/authority to /api/authentication 
- [x] Make several users with different authorization
- [] Make users authorities show USER and ADMIN(if admin)
- [] Set which authorities have access to which endpoints
- [] Write documentation
- *MAYBE* [] split so only admin-users can delete, update and add, 
when users can see animals

## Testing
- Make unit tests (Mockk)
  - [] AnCUT: shouldGetAllAnimals
  - [] AnSUT: getAnimalsTest
  - [] AuSUT: getAuthoritiesTest(?necessary, really?)
  - []
- Make integration tests
  - [] DIT: shouldGetAnimals
  - [] DIT: findAminal(s?) by id(?), age, breed, type, name etc
  - [] FST: hmm maybe check if you can do something with the authority
  - []

