import java.util.ArrayList;

public class Table {
    private ArrayList<Card> tableCards;
    private Card mostRecentCard = null;

    public Table() {
        tableCards = new ArrayList<>();
    }

    public void addCard(Card card) {
        tableCards.add(card);
    }

    public void reset() {
        tableCards = null;
    }

    public void setMostRecent(Card card) {
        this.mostRecentCard = card;
    }

    public Card getMostRecent() {
        return mostRecentCard;
    }

    public ArrayList<Card> getTableCards() {
        return tableCards;
    }

}