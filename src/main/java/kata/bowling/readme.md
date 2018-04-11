# bowling game kata

The goal of this kata is to calculate the score of a bowling game while practicing TDD and pair programming.

1. A game of bowling has 10 frames, a player has a maximum of 2 shots to clear all the pins (10) for a single frame. A player who doesn't
throw any pins in any of their shots gets a score of 0.

2. Provided a player doesn't throw the full 10 pins of any frame, the player's score is the sum of the pins thrown

3. If a player throws the 10 pins of a frame with the help of both shots (a spare), the player's next shot counts twice towards the score

4. If a player spares on the last frame, the player gets another shot to add as a bonus

5. If a player throws the 10 pins of a frame with one shot (a strike), the player's next two shots count twice towards the score