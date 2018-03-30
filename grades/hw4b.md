hw4b Feedback
============


#### Implementation of Scrabble game (60/60)
  * Your implementation seems to completely or almost completely implement the functionality related to the game’s board (squares, tiles, special tiles, drawing and exchanging tiles, purchasing special tiles, making moves, etc). Well done.
  * Your implementation seems to completely or almost completely implement the functionality related to the validating a move in the game (checking placement, etc.). Well done!
  * Your implementation seems to completely or almost completely implement the functionality related to challenging a move in the game (validating words, reverting board state, etc). Well done!
  * Your implementation seems to completely or almost completely implement the functionality related to scoring a move in the game (adding points for tiles and words, applying multipliers and scoring-related special tiles, etc). Well done.
  *  Your implementation seems to completely or almost completely implement the special tiles in the game (reverse order, boom, negative points, and own tile). Well done.

#### Program Design (21/25)
  * Overall, you did a pretty good job here. My only concerns are about your responsibility assignment.
    * Game is doing everything, it should be delegating all that work. Think about what has the most information for each thing-- wouldn't it make more sense for Board to validate a Move?
   * Think about how you could have made more classes that were smaller as opposed to making your Game into a "God Class"

#### Testing and Build Automation (22/25)
  * Your testing practice meets our expectations. Well done!
  * Your testing coverage seems reasonable. 
  * Travis is not building homework 4. Please change your settings.gradle file in the top level of your repository to fix this issue.


#### Documentation and Style (5/10)
  * Please document your public classes/methods with comments. You are missing documentation in several classes.
    * You are missing several javadoc comments in the tiles package. Most importantly, in the SpecialTile interface.


---


#### Total (108/120)


Late days used: 0 (2 left)


---


#### Additional Notes



Graded by: Jordan Brown (jmbrown@andrew.cmu.edu)


To view this file with formatting, visit the following page: https://github.com/CMU-15-214/said/blob/master/grades/hw4b.md
