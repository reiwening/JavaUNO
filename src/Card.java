public class Card {
    private final String value;
    private final String suit;

    public Card(String value, String suit) {
        this.value = value;
        this.suit = suit;
    }

    public String getValue() {
        return value;
    }

    public String getSuit() {
        return suit;
    }

    public int getNumericValue() {
        switch(value) {
            case "Joker":
                return 14;
            case "King":
                return 13;
            case "Queen":
                return 12;
            case "Jack":
                return 11;
            case "Ace":
                return 1;
            default:
                return Integer.parseInt(value);
        } 
    }

    @Override
    public String toString() {
        String name = null;
        switch(value) {
            case "14" :
                return "+4 Wildcard";
            case "13" :
                return "Wildcard";
            case "12" :
                name = "+2";
                break;
            case "11" :
                name = "Reverse";
                break;
            case "10" :
                name = "Skip";
                break;
            default :
                name = value;
        }
        char symbol = 0;
        switch(suit) {
            case "Diamonds" :
                symbol = 4;
                break;
            case "Clubs" :
                symbol = 5;
                break;
            case "Hearts" :
                symbol = 3;
                break;
            case "Spades" :
                symbol = 6;
            default:
                break;
        }
        //uncomment the below switch statement for MAC and comment the above switch statement
        // switch(suit) {
        //     case "Diamonds" :
        //         // symbol = "\u2666";
        //         symbol = '♦';
        //         break;
        //     case "Clubs" :
        //         // symbol = "\u2663";
        //         symbol = '♣';
        //         break;
        //     case "Hearts" :
        //         // symbol = "\u2764";
        //         symbol = '❤';
        //         break;
        //     case "Spades" :
        //         // symbol = "\u2660";
        //         symbol = '♠';
        //         break;
        // }
        return name + symbol;
    }

    @Override
    public boolean equals(Object o) {
        if (!( o instanceof Card)) {
            return false;
        }
        Card c = (Card) o;
        return (value.equals(c.value) && suit.equals(c.suit));
    }
}
