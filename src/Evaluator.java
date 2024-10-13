import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Evaluator {
    private Card cardToCheck;
    private int direction = 1;
    private int cardsToDraw = 0;

    public int getCardsToDraw() {
        return cardsToDraw;
    }

    public void setCardToCheck(Card c) {
        cardToCheck = c;
    }

    public Card getCardToCheck() {
        return cardToCheck;
    }

    public int getDirection() {
        return direction;
    }

    public boolean checkSuit(Card mostRecentlyPlayed) {
        return cardToCheck.getSuit().equals(mostRecentlyPlayed.getSuit());
    }

    public boolean checkValue(Card mostRecentlyPlayed) {
        return (cardToCheck.getNumericValue() == mostRecentlyPlayed.getNumericValue());

    }

    public boolean isPlayable(ArrayList<Card> storedCard, Card mostRecentlyPlayed) {
        if (storedCard.size() == 0) {
            if (cardToCheck.getNumericValue() == 13 || cardToCheck.getNumericValue() == 14) { // for +4 and color change
                                                                                              // wild card which can  always be played
                                                                                              
                return true;
            }
            return checkValue(mostRecentlyPlayed) || checkSuit(mostRecentlyPlayed);
        }
        return checkValue(mostRecentlyPlayed);
    }

    private int getSuitChoice() {
        Scanner sc = new Scanner(System.in);
        int choice = -1;
        while (!(choice >= 1 && choice <= 4)) {
            try {
                System.out.printf("Select suit: \n1) Diamonds\n2) Clubs\n3) Hearts\n4) Spades\n\nOption : ");
                choice = sc.nextInt();
                if (!(choice >= 1 && choice <= 4)) {
                    choice = -1;
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("Please enter a number between 1 and 4\n");
            }
        }
        return choice;
    }

    // now returns the current index at the start of the next turn
    public int effect(ArrayList<Card> currentlyPicked, Table currentTable, int playerCount, int currentIndex,
            Scanner sc) {
        for (int i = 0; i < currentlyPicked.size(); i++) {
            String suit = currentlyPicked.get(i).getSuit();
            switch (currentlyPicked.get(i).getNumericValue()) {
                case 10:
                    currentIndex = movePlayer(currentIndex, playerCount);
                    break;
                case 11:
                    direction *= -1;
                    break;
                case 12:
                    cardsToDraw += 2;
                    break;
                case 14:
                    cardsToDraw += 4;
                case 13:
                    if (i == 0) {
                        int suitNumber = getSuitChoice();

                        switch (suitNumber) {
                            case 1:
                                suit = "Diamonds";
                                break;
                            case 2:
                                suit = "Clubs";
                                break;
                            case 3:
                                suit = "Hearts";
                                break;
                            case 4:
                                suit = "Spades";
                                break;
                        }
                        // to set suit only, disregard value
                        currentTable.setMostRecent(new Card("0", suit));
                    }
                default:
                    break;
            }
        }
        return movePlayer(currentIndex, playerCount);
    }

    public void resetDraw() {
        cardsToDraw = 0;
    }

    public int movePlayer(int currentIndex, int playerCount) {
        currentIndex += direction;
        if (currentIndex == -1) {
            return (playerCount - 1);
        } else if (currentIndex == playerCount) {
            return 0;
        }
        return currentIndex;
    }
}
