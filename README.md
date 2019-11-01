# Bulgarian-Solitaire-Game
The game starts with 45 cards. (They need not be playing cards. Unmarked index cards work just as well.) Randomly divide them into some number of piles of random size. For example, you might start with piles of size 20, 5, 1, 9, and 10. In each round you take one card from each pile, forming a new pile with these cards. For example, the starting configuration would be transformed into piles of size 19, 4, 8, 9, and 5. The solitaire is over when the piles have size 1, 2, 3, 4, 5, 6, 7, 8, and 9, in some order. (It can be shown that you always end up with such a configuration.) your program will be able to be run in a few different modes, each of these controlled by a command-line argument. The user may supply one or both of the arguments, or neither.
The command-line argument:
1. -u:Prompts for the initial configuration from the user, instead of generating a random configuration.
2. -s:Stops between every round of the game. The game only continues when the user hits enter (a.k.a., return).
