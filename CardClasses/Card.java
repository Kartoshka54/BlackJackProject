package CardClasses;

//This is our card class. This is the place where we define the rank, suit and value of each card.
public class Card {
    //The suit of the card is the symbol which is on it.
    // For example: Hearts, Diamonds, Clubs, Spades.
    private String suit;
    //The rank is the number which the card has.
    private String rank;
    //Avoid magic numbers
    public static final int ACE_VALUE = 11;
    public static final int FACE_CARD_VALUE = 10;

    //The value helper is used to define the value of each card.
    // For example: Ace is 11, King, Queen and Jack are 10, and the rest are their own value.
    private int value;


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
        if (rank.equals("Ace")) {
            //In blackjack, the ace can be 1 or 11. We will define it as 11 for now.
            this.value = ACE_VALUE;
        } else if (rank.equals("King") || rank.equals("Queen") || rank.equals("Jack")) {
            //In blackjack, the king, queen and jack are all 10.
            this.value = FACE_CARD_VALUE;
        } else {
            //The rest of the cards are their own value.
            // Because we defined rank as a string, we need to convert it to an integer
            this.value = Integer.parseInt(rank);
        }
    }
    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }



}
