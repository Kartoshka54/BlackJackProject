import CardClasses.Card;
import CardClasses.Deck;
import CardClasses.Qll;
import ChipClasses.ChipStacks;

import java.util.Scanner;
import java.util.Stack;
public class BlackJackMethods {
    //sj
    Scanner sc = new Scanner(System.in);
    ChipStacks chipStacks = new ChipStacks();
    Deck deck = new Deck();
    Stack<Card> playerHand = new Stack<>();
    Stack<Card> dealerHand = new Stack<>();
    private static final int WIN_CONDITION = 21;
    private static final int DEALER_RULE = 17;

    //This method will be used to determine the turn order.
    public void turnOrder(String player, String dealer) {
        //We simply utilize our queue data structure to keep track of the turns.
        Qll playerTurn = new Qll();
        playerTurn.enqueue("player");
        hit();
        playerHandValue();

        //This while loop will keep the game going until the player runs out of chips.
        while (chipStacks.total() > 0) {//TODO: Change if to switch case
            int totalMoney = chipStacks.total();
            if (playerTurn.getFront() == player) {
                System.out.println("It is your turn, hit or stand? (h/s)");
                String choice = sc.nextLine();
                if (choice.equals("h")) {
                    aceCheck(playerHand);
                } else if (choice.equals("s")) {
                    stand();

                } else if (chipStacks.total() == 0 || playerHandValue() >= WIN_CONDITION) {
                    System.out.println("You lost! Wanna try again ;).");

                } else if (playerHand.size() == 21 || playerHand.size() > dealerHand.size()) {
                    if (playerHand.size() == 21) {
                        System.out.println("Congratulations! You won!");


                    } else if (dealerHand.size() == WIN_CONDITION) {
                        System.out.println("Bust!)");

                    }

                } else {
                    System.out.println("Invalid input, try again.");
                }
                //This deletes player from the queue and makes dealer the new front
                //So the dealer gets to play on the next iteration of the loop.
                playerTurn.deleteQueue();
                playerTurn.enqueue("dealer");
            } else {
                dealerTurn();
                playerTurn.deleteQueue();
                //Player is added so the other condition is met on the next loop.
                //Now we have a nice and simple method to keep track of our turns :)!
                playerTurn.enqueue("player");

            }

        }*/


        //Hit method for the player, used to draw cards from the deck
        public int hit() {
            Card card = deck.draw();
            playerHand.push(card);
            return aceCheck(playerHand);
        }
        //This method will be used to end the player's turn.
        public void stand () {
            System.out.println("You have chosen to stand. Now only luck can save you!");
        }
        //This method also converts the ace value
        public int playerHandValue () {
            int handValue = hit();
            System.out.print("Your hand contains: ");
            for (int i = 0; i < playerHand.size(); i++) {
                Card c = playerHand.get(i);
                System.out.print(c.getRank() + " of " + c.getSuit() + " ");

            }
            System.out.println("\nTotal value: " + handValue);
            return handValue;
        }
        //So in the classic variation of blackjack, the dealer is supposed to hit until he reaches 17 or more.
        //Even if the player has a higher value, the dealer must abide by this rule which will give the player a chance to win.
        //Of-course we will make the dealer harder to beat as the money value of the player increases, he might even cheat ;).
        public void dealerTurn () {

        if(dealerHandValue() <= DEALER_RULE) {
            dealerHandValue();
        }else {
            System.out.println("The dealer has decided to stand");
        }

    }
    public int dealerHandValue() {
        int dealerHandValue = 0;
        for (int i = 0; i < dealerHand.size(); i++) {
            Card c = dealerHand.get(i);
            dealerHandValue += c.getValue();
        }
        return dealerHandValue;
    }

}


