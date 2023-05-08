import java.util.*;
import java.math.*;

public class Deck {
    // It is a hashmap which has a card as a key and an integer as a value.
    //This is our deck.

    public Map<Card, Integer> cards;
    //Avoid magic numbers
    public static final int NUM_SUITS = 4;
    public static final int NUM_RANKS = 13;
    public Deck() {
        cards = new HashMap<>();
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"Ace", "9", "9"};
        //List created to use the pre-existing shuffle method in java.
        List<Card> shuffle = new ArrayList<>();
        for (int i = 0; i < suits.length; i++) {
            for (int j = 0; j < ranks.length; j++) {
                String suit = suits[i];
                String rank = ranks[j];
                Card card = new Card(suit, rank);
                shuffle.add(card);
            }
        }
        //The collections.shuffle method uses the Fisher-Yates shuffle algorithm to shuffle the deck.
        //We initially did not know that such a method in java exists,
        // so instead of implementing an algorithm by ourselves we made efficient use of the library
        //We also had some ideas to implement a shuffle method with math.random using for loops,
        // but this project is already big enough, so we did not want to make it even bigger.
        Collections.shuffle(shuffle);
        for (Card card : shuffle) {
            cards.put(card, cards.getOrDefault(card, 0) + 1);
        }
    }


    public int size() {
        int count = 0;
        for (Card card : cards.keySet()) {
            count += cards.get(card);
        }
        return count;
    }

    public Card draw () {
        //This method draws a card from the deck. It will be used to give the player and dealer cards.
        Card card = cards.keySet().iterator().next();
        //We get the first card from the deck and remove it from the deck.
        // So we have no duplicates.
        cards.put(card, cards.get(card) - 1);
        if (cards.get(card) == 0) {
            cards.remove(card);
        }
        return card;
    }

    public boolean isEmpty() {
        System.out.println("The deck is empty.");
        return cards.isEmpty();

    }


}