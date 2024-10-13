import java.util.*;

public class Game {
    private ArrayList<Player> playerList = new ArrayList<>();;
    private int playerCount = 0;
    private boolean gameOver = false;
    private Deck currentDeck = new Deck();
    private Table currentTable = new Table();
    public static final String ANSI_CLEAR = "\033[H\033[2J";

    public Game() {

    }

    public void createPlayerCount() {
        Scanner sc = new Scanner(System.in);
        while (playerCount > 5 || playerCount < 2) {
            try {
                System.out.print("Enter number of players (2-5): ");
                playerCount = sc.nextInt();
                System.out.println();
                if (playerCount < 2) {
                    System.out.println("Player count is too low!");
                    throw new InputMismatchException();
                }
                if (playerCount > 5) {
                    System.out.print("Player count is too high!");
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println(" Choose a value between 2 to 5.");
                sc.nextLine();
                System.out.println();
            }
        }
    }

    public void draw(Player currentPlayer, int cardsToDraw) {
        for (int i = 0; i < cardsToDraw; i++) {

            // reshuffle from played pile if deck runs out
            if (currentDeck.getCurrentDeck().isEmpty()) {
                
                // game ends if played pile is empty
                if (currentTable.getTableCards() == null) {
                    displayWinner(checkWinnerEmptyDeck());
                    gameOver = true;
                    return;
                }
                currentDeck = new Deck(currentTable.getTableCards());
                currentDeck.shuffle();
                currentTable.reset();
            }
            currentPlayer.drawCard(currentDeck);
        }
    }

    // the case where both have same num of cards, lower hand size wins
    public Player checkWinnerEmptyDeck() {

        Player winner = playerList.get(0);
        int playersWithSameHandSize = 1;
        for (int i = 1; i < playerCount; i++) {
            Player player = playerList.get(i);
            if (player.getHand().getCardsInHand().size() < winner.getHand().getCardsInHand().size()) {
                winner = player;
            } else if (player.getHand().getCardsInHand().size() == winner.getHand().getCardsInHand().size()) {
                // if both players' hand size are the same 
                playersWithSameHandSize++;
            }
        }

        if (playersWithSameHandSize == playerCount) {
            winner = new Player("No one");
        }

        return winner;
    }

    public void displayWinner(Player winner) {
        System.out.println(ASCIIArtGenerator.textToAscii(winner.getName()));
        System.out.println(ASCIIArtGenerator.textToAscii("wins!"));
    }

    public void firstTurn(Player player) {
        Hand currentHand = player.getHand();
        boolean isActionCard = true;
        Card firstCard = null;

        // check if first hand consist of all action cards
        gameOver = true;
        while (gameOver) {
            for (Card card : currentHand.getCardsInHand()) {
                if (card.getNumericValue() < 10) {
                    gameOver = false;
                    break;
                }
            }
            if (gameOver) {
                currentHand.displayHand();
                System.out.println("Restarting game, first hand had no playable cards.");
                currentDeck.getCurrentDeck().addAll(currentHand.getCardsInHand());
                player.drawInitialCards(currentDeck);
            }
        }
        setFirstPlayableCards(currentHand);
        Scanner sc = new Scanner(System.in);

        // check for action card 
        while (isActionCard) {
            try {
                System.out.print("Select the first card to play: ");
                int cardIdx = sc.nextInt();

                firstCard = currentHand.getCardsInHand().get(cardIdx - 1);

               
                if (firstCard.getNumericValue() >= 10) {
                    throw new IllegalArgumentException();
                
                }

                isActionCard = false;
                currentHand.removeCard(firstCard);

         
            } catch (InputMismatchException e) {
                System.out.println("Input must be a number. Please enter a valid number!");
                sc.nextLine();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Index is out of bounds. Please enter a valid number!");
                sc.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println("First card cannot be action card. Please enter a valid number!");
                sc.nextLine();
            }
        }

        // set most recent card into current table
        currentTable.setMostRecent(firstCard);

        // add card into table's card array
        currentTable.addCard(firstCard);
    }

    // only for first turn
    public void setFirstPlayableCards(Hand currentHand) {
        for (Card c : currentHand.getCardsInHand()) {
            if (c.getNumericValue() < 10) {
                currentHand.getPlayableCards().add(c);
            }
        }
        currentHand.displayHand();
    }

    public void setPlayableCards(Hand currentHand, ArrayList<Card> currentlyPicked) {
        if (currentlyPicked.size() == 0) {
            currentHand.setPlayableCards(currentlyPicked, currentTable.getMostRecent());
        } else {
            currentHand.setPlayableCards(currentlyPicked, currentlyPicked.get(currentlyPicked.size() - 1));
        }
        currentHand.displayHand();
    }

    public void selectCards(Hand currentHand, Player currentPlayer, ArrayList<Card> currentlyPicked, Scanner sc) {
        boolean isSelecting = true;
        while (isSelecting) {

            if (currentHand.getPlayableCards().size() == 0) {
                System.out.println("There are no more playable cards.");
                System.out.println();
                break;
            }

            System.out.println();
            System.out.print("Player " + currentPlayer.getName() + ", enter the card you want to play: ");
            int idxPicked = -1;

            while (idxPicked < 1 || idxPicked > currentHand.getCardsInHand().size()) {
                try {
                    idxPicked = sc.nextInt();

                    Card selectedCard = currentHand.getCardsInHand().get(idxPicked - 1);

                    if (currentlyPicked.contains(selectedCard)) {
                        System.out.println();
                        System.out.println("You have already picked this card."); 
                        throw new InputMismatchException();
                    }

                    if (!(currentHand.getPlayableCards().contains(selectedCard))) {
                        throw new InputMismatchException();
                    }

                    currentlyPicked.add( selectedCard);

                } catch (InputMismatchException | IndexOutOfBoundsException e) {
                    System.out.println();
                    System.out.print("Please enter the index of a playable card: ");
                    sc.nextLine();
                    idxPicked = -1;
                }
            }

            currentHand.clearPlayableCards();

            System.out.println();
            System.out.println("Currently Picked Cards: " + currentlyPicked);
            System.out.println();

    
            boolean correctInput = false;
            while (!correctInput) {
                System.out.print("Continue adding cards? (Y/N): ");
                String answer = sc.next();
                if (answer.equalsIgnoreCase("N")) {
                    isSelecting = false;
                    correctInput = true;
                } else if (answer.equalsIgnoreCase("Y")) {
                    currentHand.clearPlayableCards();
                    setPlayableCards(currentHand, currentlyPicked);
                    if (currentHand.getCardsInHand().size() - currentlyPicked.size() == 1) {
                        isSelecting = false;
                        System.out.println("You cannot win by stacking your last card.");
                        System.out.println();
                    }
                    correctInput = true;
                } else {
                    System.out.println("Invalid input, try again.\n");
                }
            }
        }
    }

   
    private void initializePlayers() {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> existingNames = new ArrayList<>();

        for (int i = 1; i <= playerCount; i++) {
            String name = getPlayerName(i, existingNames);

            Player player = new Player(name);
            player.drawInitialCards(currentDeck);
            playerList.add(player);
        }
    }

    private boolean isAlpha(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    private String getPlayerName(int playerNumber, ArrayList<String> existingNames) {

        Scanner sc = new Scanner(System.in);
        String name = "";

        boolean valid = false;

        while (!valid) {
            try {
                System.out.print("Please enter player " + playerNumber + "'s name with no spaces to start: ");

                name = sc.nextLine().trim();

                // checking for letters
                for (int i = 0; i < name.length(); i++) {
                    if (!isAlpha(name.charAt(i))) {
                        throw new IllegalArgumentException("Name can only contain letters. ");
                    }
                }

                if (existingNames.contains(name.toUpperCase())) {
                    throw new IllegalArgumentException("This name has already been taken. ");
                }

                if (name.isEmpty()) {
                    throw new IllegalArgumentException("Please enter something to start. ");

                }

                if (name.length() > 10) {
                    throw new IllegalArgumentException("Name must be less than 10 characters long. ");

                }

                valid = true;
            }

            catch (IllegalArgumentException e) {
                System.out.println();
                System.out.println("Invalid Input. " + e.getMessage());
                System.out.println();
            }

        }

        existingNames.add(name.toUpperCase());
        return name;

    }


    public void startGame() {
        createPlayerCount();
        Evaluator currentEvaluator = new Evaluator();

        Scanner sc = new Scanner(System.in);
        initializePlayers();


        int currentIdx = 0;
        int cardsToDraw = 0;
        while (!gameOver) {

            // clear terminal
            System.out.print(ANSI_CLEAR);
            System.out.println("---------------------------------------------------------------");

            Player currentPlayer = playerList.get(currentIdx);

            // draw cards from previous turn
            Hand currentHand = currentPlayer.getHand();
            draw(currentPlayer, cardsToDraw);
            cardsToDraw = 0;

            System.out.println();
            currentPlayer.getDisplayName();

            //display number of cards of other players
            System.out.println("Number of cards opponents have");
            for (Player player : playerList){
                if (player != currentPlayer){
                    System.out.println(player.getName() + ": " + player.getHand().getCardsInHand().size() + " cards");
                }
            }
            System.out.println("---------------------------------------------------------------");
            

            // checking if player can end
            if (currentPlayer.hasUno()) {
                // player's last card is action card = cannot end
                if (currentHand.getCardsInHand().get(0).getNumericValue() >= 10) {
                    currentHand.displayHand();
                    draw(currentPlayer, 1);
                    System.out.print("You can't end with an action card. Drawing card. (Press enter until continue)");
                    sc.nextLine();
                    sc.nextLine();
                    currentIdx = currentEvaluator.movePlayer(currentIdx, playerCount);
                    continue;
                }
            }

            ArrayList<Card> currentlyPicked = new ArrayList<>();

            // entire if for first turn only
            if (currentTable.getMostRecent() == null) {
                firstTurn(currentPlayer);
                currentIdx = currentEvaluator.movePlayer(currentIdx, playerCount);
                continue;
            }

            System.out.println("Top card on table: ");
            String[] card = CardGenerator.generateCard(currentTable.getMostRecent());
            for (int i = 0; i < card.length; i++){
                System.out.println(card[i]);
            }

            System.out.println();

            boolean playable = false;
            while (!playable) {

                int result = 0;
                setPlayableCards(currentHand, currentlyPicked);
                while (!(result == 1 || result == 2)) {
                    try {
                        System.out.println("Actions:");
                        System.out.printf("1) Draw\n2) Play\nOption: ");
                        result = sc.nextInt();
                        if (!(result == 1 || result == 2)) {
                            throw new InputMismatchException();
                        }

                    } catch (InputMismatchException e) {
                        System.out.println("\nPlease enter 1 or 2: ");
                        sc.nextLine();
                        System.out.println();
                    }
                }

                // if player draws
                if (result == 1) {
                    draw(currentPlayer, 1);
                    currentIdx = currentEvaluator.movePlayer(currentIdx, playerCount);
                    break;
                }
                selectCards(currentHand, currentPlayer, currentlyPicked, sc);

                if (currentlyPicked.size() == 0) { // autodraw
                    draw(currentPlayer, 1);
                    sc.nextLine();
                    System.out.print("A card has been drawn for you. (Press enter to continue)");
                    sc.nextLine();
                    currentIdx = currentEvaluator.movePlayer(currentIdx, playerCount);
                    break;
                }
                playable = true;

                currentTable.setMostRecent(currentlyPicked.get(currentlyPicked.size() - 1));
                for (Card c : currentlyPicked) {
                    currentTable.getTableCards().add(c);
                    currentHand.getCardsInHand().remove(c);
                }

                System.out.println("Currently Picked Card(s): " + currentlyPicked);
                System.out.println();

                // check card effect
                currentIdx = currentEvaluator.effect(currentlyPicked, currentTable, playerCount, currentIdx, sc);

                sc.nextLine();
                System.out.println();
                System.out.print("Ending turn (Press enter to continue)");
                sc.nextLine();
            }

            // update cards to draw
            cardsToDraw = currentEvaluator.getCardsToDraw();

            // reset draw
            currentEvaluator.resetDraw();

            // when player win
            if (currentHand.getCardsInHand().size() == 0) {
                displayWinner(currentPlayer);
                gameOver = true;
            }
        }
    }
}
