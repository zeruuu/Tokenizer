import java.util.ArrayList;
// Eugene Andreu Cuello && Jasper Ajias

// The tokenizer should also be able to identify and classify each token's type
// (e.g., word, punctuation, number, alphanumeric, end of line, etc.).
public class Tokenizer {
    private String result;

    private ArrayList<String> words;
    private ArrayList<String> numbers;
    private ArrayList<String> alphanumerics;
    private ArrayList<String> punctuations;

    private ArrayList<String> resultPhase; // for the 2 phases


    public Tokenizer() {}

    public void tokenize(String token, String delimiter) {
        this.result = ""; // after delimiting '@'

        this.words = new ArrayList<>();
        this.numbers = new ArrayList<>();
        this.alphanumerics = new ArrayList<>();
        this.punctuations = new ArrayList<>();

        this.resultPhase = new ArrayList<>();

        for (int i = 0; i < token.length(); i++) {
            if (token.charAt(i) != delimiter.charAt(0)) { // delimits '@'
                result += token.charAt(i); // adds to 'result' string if the character is not the delimiter which is '@'
            }
        }

        // Test@String, Hello2 How are you! I am 22.2 y@ears old (for testing purposes)
        String currentToken = ""; // currentToken split from the 'result' string
        for (int i = 0; i < result.length(); i++) {
            char c = result.charAt(i); // checks for each character of the 'result' string

            if (isLetter(c) || isDigit(c)) {
                currentToken += c; // adds to 'currentToken' if the char is a letter or digit
            } else if (isWhitespace(c)) { // adds the 'currentToken' to their type whenever there is a whitespace or new line
                if (!currentToken.isEmpty()) {
                    addTokenToType(currentToken); // method to add currenToken to certain types (words, numbers, alphanumerics, punctuations)
                    currentToken = "";
                }
            } else if (isPunctuation(c)) {
                if (c == '.') { // if the punctuation is period, the if else statement below checks
                    if (isDecimal(result, i)) { // whether or not the period is a decimal or not
                        currentToken += c;
                    } else {
                        if (!currentToken.isEmpty()) {
                            addTokenToType(currentToken);
                            currentToken = "";
                        }
                        punctuations.add("" + c);
                        resultPhase.add("" + c);
                    }
                } else {
                    if (!currentToken.isEmpty()) {
                        addTokenToType(currentToken);
                        currentToken = "";
                    }
                    punctuations.add("" + c);
                    resultPhase.add("" + c);
                }
            }
        }
        //
        if (!currentToken.isEmpty()) { // if there is no more whitespace and there is still a token in the currentToken
            addTokenToType(currentToken);
        }
        // test
//        System.out.println("Words: " + words);
//        System.out.println("Numbers: " + numbers);
//        System.out.println("Alphanumeric: " + alphanumerics);
//        System.out.println("Punctuations: " + punctuations);
    }

    // observers

    public static boolean isLetter(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'); // ascii code value
    }
    public static boolean isDigit(char c) {
        return c >= '0' && c <= '9'; // ascii code value
    }

    public static boolean isPunctuation(char c) {
        char[] punctuations = {'.', ',', ';', ':', '!', '?', '-', '(', ')', '[', ']', '{', '}', '@', '\'', '\"'};
        for (char p : punctuations) {
            if (c == p) {
                return true;
            }
        }
        return false;
    }
    public static boolean isWhitespace(char c) {
        return c == ' ' || c == '\n';
    }

    public static boolean isWord(String token) {
        for (int i = 0; i < token.length(); i++) {
            if (!isLetter(token.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAlphaNumeric(String token) {
        boolean hasDigits = false;
        boolean hasLetters = false;

        for (int i = 0; i < token.length(); i++) {
            if (isDigit(token.charAt(i))) {
                hasDigits = true;
            }
            if (isLetter(token.charAt(i))) {
                hasLetters = true;
            }
        }
        return hasDigits && hasLetters;  // returns true if both are true
    }

    public static boolean isNumber(String token) {
        for (int i = 0; i < token.length(); i++) {
            if (isLetter(token.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isDecimal(String token, int index) { // checks the previous and next index of the token if there is a digit or not
        if (isNumber(String.valueOf(token.charAt(index - 1))) && isNumber(String.valueOf(token.charAt(index + 1)))) {
            return true;
        }
        return false;
    }

    // getters
    public String getResult() { return result; }

    public String getTokens() { // for phase 1
        String hold = "Phase 1 Output:\n";
        for (int i = 0; i < resultPhase.size(); i++) {
            String token = resultPhase.get(i);
            if (words.contains(token)) {
                hold += "Token: \"" + token + "\" - Type: Word\n";
            } else if (numbers.contains(token)) {
                hold += "Token: \"" + token + "\" - Type: Number\n";
            } else if (alphanumerics.contains(token)) {
                hold += "Token: \"" + token + "\" - Type: Alphanumeric\n";
            } else if (punctuations.contains(token)) {
                hold += "Token: \"" + token + "\" - Type: Punctuation\n";
            }
        }
        return hold;
    }

    public String getGranularBreakdown() { // for phase 2
        String hold = "Phase 2 Output (Granular Breakdown):\n";
        for (int i = 0; i < resultPhase.size(); i++) {
            String token = resultPhase.get(i);
            hold += "Token: \"" + token + "\" -> ";
            for (int j = 0; j < token.length(); j++) {
                hold += "'" + token.charAt(j) + "'";
                if (j < token.length() - 1) {
                    hold += ", ";
                }
            }
            hold += "\n";
        }
        return hold;
    }

    // methods
    private void addTokenToType(String currentToken) { // helper to add what type the 'currentToken' is
        if (isWord(currentToken)) {
            words.add(currentToken);
            resultPhase.add(currentToken);
        } else if (isNumber(currentToken)) {
            numbers.add(currentToken);
            resultPhase.add(currentToken);
        } else if (isAlphaNumeric(currentToken)) {
            alphanumerics.add(currentToken);
            resultPhase.add(currentToken);
        }
    }

    // for testing purposes

//    public static void main(String[] args) {
//        Tokenizer tokenizer = new Tokenizer();
//        tokenizer.tokenize("Mic Test @25, Hello Everyon3! I am 2.25 years old.", "@");
//        System.out.println(tokenizer.getGranularBreakdown());
//
//        }

    }

