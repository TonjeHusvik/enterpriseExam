# enterpriseExam

## Frontend


## Things I used to connect frontend and backend
 - [] proxy

## Backend
Users with login-info
Plot in localhost:8080/api/login in Postman and choose one of these to log in with.



| Username (email) | Password | 	Authority |
|------------------|----------|------------|
| user@user.com	   | pirate	  | User       |
| admin@admin.com  | pirate	  | Admin      |


Every endpoint starts with localhost:8080/api 

| Endpoint                                                                         | 	Example of JASON-input                                                                                       | Explanation	                                                                          | Authority  |
|----------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------| ---------- |
| /login                                                                           | {\"email": "user@user.com",\"password": "pirate"\}                                                            | log in with user authority                                                            | all        |
| /login                                                                           | 	{\ "email": "admin@admin.com",\ "password": "pirate"\ }	                                                     | log in with admin authority	                                                          | all        |
| /shelter/edit/newanimal                                                          | 	{\ "name": "cola",\ "animalType": "cat",\ "breed": "Polish mustache",\ "age": 55,\ "health": "old but gold"\ }	 | add a new animal	                                                                     | admin      |
| /register 	                                                                      | {\ "email":"new@user.com",\ "password":"password"\ }                                                          | to register new user                                                                  | all        |
| /shelter/edit/{id}\ (Swap id with number!)                                       | {\ "name": "nils",\ "animalType": "tiger",\ "breed": "brazilian fox",\ "age": 25,\ "health": "hungry, but fine"\ } | Update animal by id                                                                   | admin      |
| /shelter/edit/{id}\ (Swap id with number!)                                       |                                                                                                               | delete animal by id                                                                   | admin     |	
| /user/edituser/{id}\ (Swap id with number!)\ (Works for both update and delete)	 | {\ "id": 1,\ "authorityName": "ADMIN"\ }                                                                      | to edit user by id, for example to make a user with user authority to admin authority | admin |	
| /shelter/{id}\ (Swap id with number!)                                            |                                                                                                               | to get an animal by id                                                                | all       |
| /shelter                                                                         | 		                                                                                                            | to show all animals	                                                                  | all       |
| /user		                                                                          |                                                                                                               | to show all users	                                                                    | all       |
| /authentication/all		                                                            |                                                                                                               | to show all authorities	                                                              | admin     |
 

When I first read the exam and saw the specifications, I got the idea that only a user with admin-authority should have the ability to post/delete and update an animal. In my mind a user with admin-authority could be an employee or owner of the shelter and they are using it do add, update or removing the animals at the shelter. A user is a possibly new animal-owner that is interested in getting an animal and therefore only have access to see information about them.

My fuck-ups
Testers	When testing for the first time, all testers passes. The second time, the database is all wrecked up and half of the tests passes. Tried to fix it by putting @DirtiesContext at every function in both DatabaseIntegrationTest and FullSystemTest (because internet told me it would work) but it seems it is a bit randomly when it works or not. This is the same when running all test(all classes) as in FullSystemTest(main problem is in here)
Endpoints	Often getting error when going from endpoint to endpoint in postman, remember to reset the database often! When (for an example) deleting a user at id=1, that user doesn’t exist when you afterwards are going to get animal from id=1! This is common sense, but it is easily to forget.

Testers
I made a total of 38 passing tests. Argued a bit with IntelliJ but it seems that no coverage could be found when using mock in tests. But that is the reason to why I have so many tests, I wanted _everything_ to be tested and thought of.

Overall
All requirements have been finished. I am satisfied with my solution.
