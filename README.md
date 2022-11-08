# Enterprise programming 1

## Users with login-info
Plot in localhost:8080/api/login in Postman and choose one of these to log in with.
| Username (email) | Password | 	Authority |
|------------------|----------|------------|
| `user@user.com`	 | pirate	  | User       |
| `admin@admin.com` | pirate	  | Admin      |

<br/>
Every endpoint starts with localhost:8080/api 

| Endpoint                                                     | 	Example of JSON body                                                                                                               | Explanation	                                                                          | Authority  |
|--------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------| ---------- |
| POST /login                                                  | {<br/>"email": `"user@user.com"`,<br/>"password": "pirate"<br />}                                                                   | log in with user authority                                                            | all        |
| POST /login                                                  | 	{<br/>"email": `"admin@admin.com"`,<br/>"password": "pirate"<br/> }	                                                               | log in with admin authority	                                                          | all        |
| POST /shelter/edit/newanimal                                 | 	{<br/>"name": "cola",<br/>"animalType": "cat",<br/>"breed": "Polish mustache",<br/>"age": 55,<br/>"health": "old but gold"<br/>}	  | add a new animal	                                                                     | admin      |
| POST /register 	                                             | {<br/>"email":`"new@user.com"`,<br/>"password":"password"\ }                                                                        | to register new user                                                                  | all        |
| PUT /shelter/edit/{id}<br/>(Swap id with number!)            | {<br/>"name": "nils",<br/>"animalType": "tiger",<br/>"breed": "brazilian fox",<br/>"age": 25,<br/>"health": "hungry, but fine"<br/>} | Update animal by id                                                                   | admin      |
| DELETE /shelter/edit/{id}<br/>(Swap id with number!)         |                                                                                                                                     | delete animal by id                                                                   | admin     |	
| PUT /user/edituser/{id}<br/>(Swap id with number!)<br/>	     | {<br/>"id": 1,<br/>"authorityName": "ADMIN"<br/> }                                                                                  | to edit user by id, for example to make a user with user authority to admin authority | admin |	
| DELETE /user/edituser/{id}<br/>(Swap id with number!)<br/> 	 | {<br/>"id": 1,<br/>"authorityName": "ADMIN"<br/> }                                                                                  | to edit user by id, for example to make a user with user authority to admin authority | admin |	
| GET /shelter/{id}<br/>(Swap id with number!)                 |                                                                                                                                     | to get an animal by id                                                                | all       |
| GET /shelter                                                 | 		                                                                                                                                  | to show all animals	                                                                  | all       |
| GET /user		                                                  |                                                                                                                                     | to show all users	                                                                    | all       |
| GET /authentication/all		                                    |                                                                                                                                     | to show all authorities	                                                              | admin     |
 

When I first read the exam and saw the specifications, I got the idea that only a user with admin-authority should have the ability to post/delete and update an animal. In my mind a user with admin-authority could be an employee or owner of the shelter and they are using it do add, update or removing the animals at the shelter. A user is a possibly new animal-owner that is interested in getting an animal and therefore only have access to see information about them.

## My fuck-ups
Testers<br/>	When testing for the first time, all testers passes. The second time, the database is all wrecked up and half of the tests passes. Tried to fix it by putting @DirtiesContext at every function in both DatabaseIntegrationTest and FullSystemTest (because internet told me it would work) but it seems it is a bit randomly when it works or not. This is the same when running all test(all classes) as in FullSystemTest(main problem is in here)
Endpoints<br/>	Often getting error when going from endpoint to endpoint in postman, remember to reset the database often! When (for an example) deleting a user at id=1, that user doesn’t exist when you afterwards are going to get animal from id=1! This is common sense, but it is easily to forget.

## Testers
I made a total of 38 passing tests. Argued a bit with IntelliJ but it seems that no coverage could be found when using mock in tests. But that is the reason to why I have so many tests, I wanted _everything_ to be tested and thought of.

## Overall
All requirements have been finished. I am satisfied with my solution.
