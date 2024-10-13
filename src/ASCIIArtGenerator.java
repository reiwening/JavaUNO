
import java.util.HashMap;

public class ASCIIArtGenerator {

        private static final int NUM_LINES_PER_CHAR = 5;

        // Define ASCII art characters
        private static final HashMap<Character, String[]> asciiArt = new HashMap<>();

        static {
                asciiArt.put('A', new String[] {
                                "    _    ",
                                "   / \\   ",
                                "  / _ \\  ",
                                " / ___ \\ ",
                                "/_/   \\_\\"

                });
                asciiArt.put('B', new String[] {
                                " ____  ",
                                "| __ ) ",
                                "|  _ \\ ",
                                "| |_) |",
                                "|____/ "
                });
                asciiArt.put('C', new String[] {
                                "  ____ ",
                                " / ___|",
                                "| |    ",
                                "| |___ ",
                                " \\____|"
                });
                asciiArt.put('D', new String[] {
                                " ____  ",
                                "|  _ \\ ",
                                "| | | |",
                                "| |_| |",
                                "|____/ "
                });
                asciiArt.put('E', new String[] {
                                " _____ ",
                                "| ____|",
                                "|  _|  ",
                                "| |___ ",
                                "|_____|"
                });
                asciiArt.put('F', new String[] {
                                " _____ ",
                                "|  ___|",
                                "| |_   ",
                                "|  _|  ",
                                "|_|    "
                });
                asciiArt.put('G', new String[] {
                                "  ____ ",
                                " / ___|",
                                "| |  _ ",
                                "| |_| |",
                                " \\____|"
                });
                asciiArt.put('H', new String[] {
                                " _   _ ",
                                "| | | |",
                                "| |_| |",
                                "|  _  |",
                                "|_| |_|"
                });
                asciiArt.put('I', new String[] {
                                " ___ ",
                                "|_ _|",
                                " | | ",
                                " | | ",
                                "|___|"
                });
                asciiArt.put('J', new String[] {
                                "     _ ",
                                "    | |",
                                " _  | |",
                                "| |_| |",
                                " \\___/ "
                });
                asciiArt.put('K', new String[] {
                                " _  __",
                                "| |/ /",
                                "| ' / ",
                                "| . \\ ",
                                "|_|\\_\\"
                });
                asciiArt.put('L', new String[] {
                                " _     ",
                                "| |    ",
                                "| |    ",
                                "| |___ ",
                                "|_____|"
                });
                asciiArt.put('M', new String[] {
                                " __  __ ",
                                "|  \\/  |",
                                "| |\\/| |",
                                "| |  | |",
                                "|_|  |_|"
                });
                asciiArt.put('N', new String[] {
                                " _   _ ",
                                "| \\ | |",
                                "|  \\| |",
                                "| |\\  |",
                                "|_| \\_|"
                });
                asciiArt.put('O', new String[] {
                                "  ___  ",
                                " / _ \\ ",
                                "| | | |",
                                "| |_| |",
                                " \\___/ "
                });
                asciiArt.put('P', new String[] {
                                " ____  ",
                                "|  _ \\ ",
                                "| |_) |",
                                "|  __/ ",
                                "|_|    "
                });
                asciiArt.put('Q', new String[] {
                                "  ___  ",
                                " / _ \\ ",
                                "| | | |",
                                "| |_| |",
                                " \\__\\_\\"
                });
                asciiArt.put('R', new String[] {
                                " ____  ",
                                "|  _ \\ ",
                                "| |_) |",
                                "|  _ < ",
                                "|_| \\_\\"
                });
                asciiArt.put('S', new String[] {
                                " ____  ",
                                "/ ___| ",
                                "\\___ \\ ",
                                " ___) |",
                                "|____/ "
                });
                asciiArt.put('T', new String[] {
                                " _____ ",
                                "|_   _|",
                                "  | |  ",
                                "  | |  ",
                                "  |_|  "
                });
                asciiArt.put('U', new String[] {
                                " _   _ ",
                                "| | | |",
                                "| | | |",
                                "| |_| |",
                                " \\___/ "
                });
                asciiArt.put('V', new String[] {
                                "__     __",
                                "\\ \\   / /",
                                " \\ \\ / / ",
                                "  \\ V /  ",
                                "   \\_/   "
                });
                asciiArt.put('W', new String[] {
                                "__        __",
                                "\\ \\      / /",
                                " \\ \\ /\\ / / ",
                                "  \\ V  V /  ",
                                "   \\_/\\_/   "
                });
                asciiArt.put('X', new String[] {
                                "__   __",
                                "\\ \\ / /",
                                " \\ V / ",
                                " /   \\ ",
                                "/_/\\_\\"
                });
                asciiArt.put('Y', new String[] {
                                "__   __",
                                "\\ \\ / /",
                                " \\ V / ",
                                "  | |  ",
                                "  |_|  "
                });
                asciiArt.put('Z', new String[] {
                                " _____",
                                "|__  /",
                                "  / / ",
                                " / /_ ",
                                "/____|"
                });
                asciiArt.put('!', new String[] {
                                " _ ",
                                "| |",
                                "|_|",
                                " _ ",
                                "|_|"
                });
        }

        // Convert text to ASCII art
        public static String textToAscii(String text) {
                StringBuilder result = new StringBuilder();
                for (int i = 0; i < NUM_LINES_PER_CHAR; i++) {
                        for (char ch : text.toCharArray()) {
                                String[] lines = asciiArt.getOrDefault(Character.toUpperCase(ch),
                                new String[] { "      ", "      ", "      ", "      ", "      " });
                                result.append(lines[i]).append(" ");
                        }
                        result.append("\n");
                }
                return result.toString();
        }
}
