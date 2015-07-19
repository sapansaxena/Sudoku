# Sudoku
A very simple Sudoku based on REST. There is no UI and only backend implementation.
Two web services are exposed, one to create a new game and a valid Sudoku game is retirned back in JSON format in a double dimensional array. This service takes "level" as parameter, and should have either of the three values, EASY, MEDIUM, HARD.
The service can be consumed via a GET call
/Sudoku/rest/sudoku/MEDIUM

The other service is a POST call and is called whenever a number is entered in the Sudoko board. THis number is validated against a complete Sudoku on the backend and if the attempt is successful, "Succeeful move" message is returned, otherwise "Wrong move" is returned.
If this is the last successful move, the same method returns "Game Complete"
The service can be consumed via a POST call
/Sudoku/rest/move

Sample Payload-
{
"number": "8",
"x": "4",
"y": "3"
}

TO create the war, gradle file should be executed. 

The WAR file is included in the repo.

