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
        while (chipStacks.total() > 0) {
            int totalMoney = chipStacks.total();
            if (playerTurn.getFront() == player) {
                System.out.println("It is your turn, hit or stand? (h/s)");
                String choice = sc.nextLine();
                if (choice.equals("h")) {
                    playerHandValue();
                } else if (choice.equals("s")) {
                    stand();
                } else if (chipStacks.total() == 0 || playerHandValue() >= WIN_CONDITION) {
                    System.out.println("You lost! Wanna try again ;).");
                }
            } else {
                System.out.println("Invalid input, try again.");
            }
            playerTurn.deleteQueue();
            playerTurn.enqueue("dealer");

            // The closing curly brace for the while loop should be here.
            // The code below should be inside the while loop, not outside.
            if (chipStacks.total() == 0) {
                dealerTurn();
                playerTurn.deleteQueue();
                playerTurn.enqueue("player");
            }
        }
    }




    //Hit method for the player, used to draw cards from the deck
    public int hit() {
        Card card = deck.draw();
        playerHand.push(card);//pushes the card into a stack
        int value = 0;
        //This for loop will iterate through the stack 
        // and add the value of each card to the total value of the hand.
        for (int i = 0; i < playerHand.size(); i++) {
            Card c = playerHand.get(i);
            value += c.getValue();
        }
        return value;
    }
    //This method will be used to end the player's turn.
    public void stand() {
        System.out.println("You have chosen to stand. Now only luck can save you!");
    }
    //This method also converts the ace value
    public int playerHandValue() {
        int handValue = hit();
        System.out.print("Your hand contains: ");
        int aceCount = 0;
        // keep track of the number of aces that have been converted to a value of 1
        int convertedAceCount = 0;
        for (int i = 0; i < playerHand.size(); i++) {
            Card c = playerHand.get(i);
            if (c.getRank() == "Ace") {
                aceCount++;
                if (handValue > 21 && aceCount >= 2 && convertedAceCount < 2) {
                    if (convertedAceCount == 0) {
                        // always keep the first ace with a value of 11
                        System.out.println("\nYour first ace of " + c.getSuit() + " remains 11");
                    } else {
                        c.setValue(1);
                        System.out.println("\nYour " + (convertedAceCount + 1) + "the ace of " + c.getSuit() + " has been changed to 1");
                    }
                    convertedAceCount++;
                }
            }
            System.out.print(c.getRank() + " of " + c.getSuit() + " ");
        }
        System.out.println("\nTotal value: " + handValue);
        return handValue;
    }

    //So in the classic variation of blackjack, the dealer is supposed to hit until he reaches 17 or more.
    //Even if the player has a higher value, the dealer must abide by this rule which will give the player a chance to win.
    //Of course we will make the dealer harder to beat as the money value of the player increases, he might even cheat ;).
    public void dealerTurn() {

        while (dealerHandValue() <=17){
            Card dealerCard = deck.draw();
            dealerHand.push(dealerCard);
            for (int i = 0; i < dealerHand.size(); i++) {
                Card c = dealerHand.get(i);
                System.out.print(c.getRank() + ", ");
                break;

            }

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
