import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private final ArrayList<Card> deck;
    private static final int NUM_OF_SUITS = 4;
    private static final int NUM_VALUES = 14;

    public Deck() {
        deck = new ArrayList<>();
        for (int i = 1; i <= NUM_VALUES; i++) {
            for (int j = 0; j < NUM_OF_SUITS; j++) {
                String suit;
                switch (j) {
                    case 0:
                        suit = "Diamonds";
                        break;
                    case 1:
                        suit = "Clubs";
                        break;
                    case 2:
                        suit = "Hearts";
                        break;
                    default:
                        suit = "Spades";
                        break;
                }
                deck.add(new Card(Integer.toString(i), suit));
            }
        }
    }

    public Deck(ArrayList<Card> tableCards) {
        deck = tableCards;
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public Card dealCard() {
        return deck.remove(0);
    }

    public ArrayList<Card> getCurrentDeck() {
        return deck;
    }

}
