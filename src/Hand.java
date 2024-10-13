import java.util.ArrayList;
import java.util.List;

public class Hand {

    private ArrayList<Card> cardsInHand;
    private ArrayList<Card> playableCards = new ArrayList<>();

    public static final int DISPLAY_NUM_OF_CARDS_PER_ROW = 7; // for terminal aesthetic purposes
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    public Hand() {
        cardsInHand = new ArrayList<>();
    }

    public void displayHand() {
        int handsize = cardsInHand.size();
        int rowCounter = 0;

        System.out.println("Your hand:");

        while (handsize > 0) {

            int numCardsInRow = Math.min(handsize, DISPLAY_NUM_OF_CARDS_PER_ROW);

            String[][] cardList = new String[numCardsInRow][];
            List<Integer> indexToLightUp = new ArrayList<>();

            for (int i = 0; i < numCardsInRow; i++) {
                Card card = cardsInHand.get(i + (DISPLAY_NUM_OF_CARDS_PER_ROW * rowCounter));
                cardList[i] = CardGenerator.generateCard(card);
                if (playableCards.contains(card)) {
                    indexToLightUp.add(i);
                    System.out.print(ANSI_YELLOW);
                }
                System.out.printf("--> %-7d", i + 1 + (DISPLAY_NUM_OF_CARDS_PER_ROW * rowCounter));
                System.out.print(ANSI_RESET);
            }
            System.out.println();

            for (int i = 0; i < cardList[0].length; i++) {
                for (int j = 0; j < cardList.length; j++) {
                    if (indexToLightUp.contains(j)) {
                        System.out.print(ANSI_YELLOW);
                    }
                    System.out.print(cardList[j][i]);
                    System.out.print(ANSI_RESET);
                    System.out.print(" ");
                }
                System.out.println();
            }

            rowCounter++;
            handsize -= DISPLAY_NUM_OF_CARDS_PER_ROW;
        }
    }

    public void removeCard(Card card) {
        cardsInHand.remove(card);
    }

    public ArrayList<Card> getCardsInHand() {
        return cardsInHand;
    }

    public ArrayList<Card> getPlayableCards() {
        return playableCards;
    }

    public void setPlayableCards(ArrayList<Card> currentlyPicked, Card lastCardPicked) {
        clearPlayableCards();
        Evaluator e = new Evaluator();

        for (Card c : cardsInHand) {
            e.setCardToCheck(c);
            if (e.isPlayable(currentlyPicked, lastCardPicked) && !currentlyPicked.contains(c)) {
                playableCards.add(c);
            }
        }
    }

    public void clearPlayableCards() {
        playableCards.clear();
    }
}
