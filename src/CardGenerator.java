
public class CardGenerator {

    private static char getSymbol(String suit) {
        switch (suit) {
            case "Diamonds":
                return 4;
            case "Clubs":
                return 5;
            case "Hearts":
                return 3;
            case "Spades":
                return 6;
            default:
                return 0;
        }
        //uncomment the below switch statement for MAC and comment the above switch statement
        // switch(suit) {
        //     case "Diamonds" :
        //         return '♦';
        //     case "Clubs" :
        //         return '♣';
        //     case "Hearts" :
        //         return '❤';
        //     case "Spades" :
        //         return '♠';
        //     default :
        //         return 0;
        // }
    }

    private static String getTopLine(int value, char shape) {
        switch (value) {
            case 10:
                return String.format("|Skip%c   |", shape);

            case 11:
                return String.format("|Reverse%c|", shape);

            case 12:
                return String.format("|+2%c     |", shape);

            case 13:
                return "|Wild    |";

            case 14:
                return "|Wild +4 |";

            default:
                return String.format("|%d%c      |", value, shape);
        }
    }

    private static String getBottomLine(int value, char shape) {

        switch (value) {
            case 10:
                return String.format("|   %cSkip|", shape);

            case 11:
                return String.format("|%cReverse|", shape);

            case 12:
                return String.format("|     %c+2|", shape);

            case 13:
                return "|    Wild|";

            case 14:
                return "| Wild +4|";

            default:
                return String.format("|      %c%d|", shape, value);
        }

    }

    public static String[] generateCard(Card card) {

        int value = card.getNumericValue();
        char shape = getSymbol(card.getSuit());

        String[] cardLines = new String[8];

        cardLines[0] = ".--------.";
        cardLines[1] = getTopLine(value, shape);
        cardLines[2] = "|  .--.  |";

        switch (shape) {
            case (char) 3:
                cardLines[3] = "|  (\\/)  |";
                cardLines[4] = "|  :\\/;  |";
                break;
            case (char) 4:
                cardLines[3] = "|  :/\\:  |";
                cardLines[4] = "|  :\\/:  |";
                break;
            case (char) 5:
                cardLines[3] = "|  :():  |";
                cardLines[4] = "|  ()()  |";
                break;
            case (char) 6:
                cardLines[3] = "|  :/\\:  |";
                cardLines[4] = "|  (__)  |";
                break;
            default:
                cardLines[3] = "|   YL   |";
                cardLines[4] = "|   LF   |";
        }
        if (value >= 13) {
            cardLines[3] = "|   YL   |";
            cardLines[4] = "|   LF   |";
        }

        cardLines[5] = "|  '--'  |";
        cardLines[6] = getBottomLine(value, shape);
        cardLines[7] = "`--------'";
        return cardLines;
    }
}
