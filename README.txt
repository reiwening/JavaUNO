Uno Game Requirements

Introduction:
Welcome to our Uno game, a digital rendition of the classic card game designed for 2 to 5 players. Before you start the game, please go through the following prerequisites to ensure you have a smooth gaming experience.


System Requirements:
- Java Runtime Environment (JRE) version 8 or higher installed on your machine.
- Terminal or Command Prompt access to execute Java commands..


Playing on MAC
- The current version of the game is suitable to be run on Windows. If you are running on MAC, perform the following steps:
  1. Open “Card.java”
  2. Uncomment the code from line 72 to line 89 inclusive
  3. Comment the code from line 56 to line 70 inclusive

  4. Open “CardGenerator.java”
  5. Uncomment the code from line 17 to line 29 inclusive
  6. Comment the code from line 5 to line 16 inclusive


Game Setup:
- The game is fully ran through the terminal
- To begin, run compile.bat in order to compile the Java files
- Next, run run.bat to start the game


Player Requirements: 
- The game supports 2 to 5 players. 
- Each player will be prompted to enter their name at the beginning of the game. Names should be unique and contain no spaces, with a maximum length of 10
  characters.
- Ensure all players are present before starting the game, as the game requires real-time decisions from each player.


Gameplay Overview:
- The game is played in rounds, where each player takes turns drawing cards from the deck or playing cards on the table.
- Players will be prompted to select actions and cards during their turn. When selecting cards to play, enter the index of the card you choose, which is
  displayed at the top of the card. 
- The objective is to be the first to play all of your cards. 



Rules:
- On your turn, you can draw a card from the deck or play a card that matches the top card of the discard pile in number or symbol. Cards that are playable
  on your turn will be highlighted in yellow for easy viewing. 
- Action cards (Skip, Reverse, Draw Two, Wild, and Wild Draw Four) have special effects. Be sure to understand these effects before playing.
- For cards that cause other players to draw cards, those players will draw the cards immediately at the beginning of their turn. They cannot add on +2 or 
  4 cards to pass it on to the next player. 
- Announce "Uno" when you're down to your last card. Failing to do so before the next player's turn may result in penalties. It is on the onus of players to
  catch other players who do not announce by checking the number of cards the other players have. 
- The game continues until a player has played all their cards. 
- In the event that there are no cards in the deck or the discard pile, The winner will be the player with the fewest cards in their hand. A tie will occur
  if no singular player has the least amount of cards. 

Card Effects:
+2: Force the next player to draw 2 cards. 

Reverse: Reverse the direction of play

Skip: Skip the next player’s turn

Wild: Choose the suit to resume play on. The next card played only needs to match this suit. If multiple “Wild” cards are stacked, the player will be
  prompted to select the suit only once. This card is suitless, and can be played as any suit

Wild +4: Choose the suit to resume play on, and force the next player to draw 4 cards. The next card played only needs to match this suit. If multiple “Wild
  +4” cards are stacked, the player will be prompted to select the suit only once. This card is suitless, and can be played as any suit


Ending Notes: 
- The game is designed for fun and strategy. Collaboration and competition are encouraged among players.
- There is no set penalty for not announcing UNO. Players are to discuss suitable penalties at the start of the game. A suggested penalty is to have the
  player draw for 2 consecutive turns with other players watching (while covering the hand of course)
- Should any issues arise during gameplay, please refer to this README and the game's comments for troubleshooting tips.


Thank you for playing our Uno game. May the best strategist win!


