# COMP2042_CW_hfywy1
Brick Breaker

***Break the game into four different packages,*** which is model, view, controller and main.
This is to ***make the classes more organized.***

***All the variables have been encapsulated*** and use getter and setter method
to retrieve or assign values to the variables. Un-encapsulated data is a
violation of key object-oriented principles. ***Encapsulation prevent
unauthorized parties have direct access to the data***.

***Extract method from long method.*** In ball model, four methods named as ***getUpLocation,
getDownLocation, getLeftLocation and getRightLocation have been extracted from
BallModel***. Method extraction ***makes the code easier to maintain and obeys single
responsibility principle.***

***Reduce some redundant parameters.*** In levels, the ***type parameter is taken out because
it is not used in the makeSingleTypeLevel method***. This can ***make the model easier to understand.***

***Replace temp with queries or inline methods and remove assignments to parameter.*** In ball model, ***setXSpeed method is called
rather than assign value to speedX.*** This can ***reduce code duplication.***

***Some methods and variables have been renamed.*** In home menu model,
***MENU_TEXT, menuFace and menuClicked have been renamed to EXIT_TEXT, exitFace
and exitClicked*** respectively. Methods related to these variables have also been renamed. These variables are related to exit
function. Rename of these variables, ***make the code more understandable.***

***Extraction of class also have been done.*** ***Wall class have been split into wall class and levels class
while brick class has been split into brick class and crack class.*** This is to ***achieve Single Responsibility Design Principle.***

***High score, game board and home menu and info have been organized according to MVC pattern.*** This make the ***maintenance and planning easier.***

***14 Junits tests*** have been added. Classes which have Junit test are ***GameFrame, Levels, PlayerController, Time, CementBrickModel, ClayBrickModel,
GameBoardModel, HighScore, HomeMenuModel, InfoModel, RubberBall, SpeedUpBrickModel, SteelBrickModel, SuperBrickModel and Wall.*** Junits tests is to ***test the functionalities of the program.***
It can catch bugs better than programmers.

***Maven is used to build the project.*** This is to automate the ***creation of executable applications from source code.***

***Two new levels added.*** Level 5 is cement brick with super brick. Level 6
is super brick with speed up brick. ***This is to make the game more exciting.***

***There is a permanent high score list.*** This can be access through the ***high score button
at home menu.*** This can ***impel players to play the game*** in order to be in the high score list.

There is ***speed up brick which will increase the speed of ball*** and ***make the game more exciting***.

***Two buttons(high score, info)*** have been added to home menu. Info is to show the ***related information about the game***. High score, info and home menu have their own ***background
pictures.*** Additional function ***back to home menu is added to the pause menu***.