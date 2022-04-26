# enterpriseExam 2022

## Todo
- [x] Change /api/authorization /api/authority to /api/authentication 
- [x] Make several users with different authorization
- [x] Make users authorities show USER and ADMIN(if admin)
- [x] Set which authorities have access to which endpoints
- [] Write documentation
- [x] split so only admin-users can delete, update and add, 
when users can see animals

## Testing
- Make unit tests (Mockk)
  - [] AnimalControllerUT: shouldGetAllAnimals
  - [] AnimalServiceUT: getAnimalsTest
  - [] AuthorityServiceUT: getAuthoritiesTest(?necessary, really?)
  - [] AuthControllerUT: testRegisterEndpoint, testAuthorityAllEndpoints
- Make integration tests
  - [] DIT: shouldGetAnimals
  - [] DIT: findAminal(s?) by id(?), age, breed, type, name etc
  - [] FST: hmm maybe check if you can do something with the authority
  - []

