import java.util.Scanner;
import java.util.Stack;
public class BlackJackMethods {
    Scanner sc = new Scanner(System.in);
    ChipStacks chipStacks = new ChipStacks();
    Deck deck = new Deck();
    Stack<Card> playerHand = new Stack<>();
    Stack<Card> dealerHand = new Stack<>();

    public void turnOrder(String player, String dealer) {//This method will be used to determine the turn order.
        //Of course the house gets to go last because of the advantage of knowing the player's hand.
        Qll playerTurn = new Qll();
        playerTurn.enqueue("player");//We simply utilize our queue data structure to keep track of the turns.
//Here I have created a new queue and added only the player so we have the right order.
        while (chipStacks.total() > 0) {//This while loop will keep the game going until the player runs out of chips.
            int totalMoney = chipStacks.total();
            if (playerTurn.getFront() == player) {
                System.out.println("It is your turn, hit or stand? (h/s)");
                String choice = sc.nextLine();
                if (choice.equals("h")) {
                    hit();
                    playerHandValue();
//hit and stand methods need to be programmed don't forget.
                } else if (choice.equals("s")) {
                    stand();

                } else if (chipStacks.total() == 0 || playerHandValue() >= 21) {
                    System.out.println("You lost! Wanna try again ;).");

                } else if (playerHand.size() == 21 || playerHand.size() > dealerHand.size()) {
                    if (playerHand.size() == 21) {
                        System.out.println("Congratulations! You won!");


                    } else if (dealerHand.size() == 21) {
                        System.out.println("Bust!)");

                    }

                } else {
                    System.out.println("Invalid input, try again.");
                }
                playerTurn.deleteQueue();//This deletes player from the queue and makes dealer the new front
                //So the dealer gets to play on the next iteration of the loop.
                playerTurn.enqueue("dealer");//Dealer is added so the other condition is met on the next loop.
            } else {

                dealerTurn();
                //bla bla bla en son

                playerTurn.deleteQueue();//This deletes dealer from the queue and makes player the new front
                playerTurn.enqueue("player");//Player is added so the other condition is met on the next loop.
                //Now we have a nice and simple method to keep track of our turns :)!

            }

        }

    }


    public int hit() {//This method will combine the draw method from the deck class and the push the drawn card into our stack so we can keep track of the hand.
        Card card = deck.draw();
        playerHand.push(card);//pushes the card into a stack
        int value = 0;
        for (int i = 0; i < playerHand.size(); i++) {//This for loop will iterate through the stack and add the value of each card to the total value of the hand.
            Card c = playerHand.get(i);
            value += c.getValue();
        }

        return value;//returns the total value of the hand.

    }

    public void stand() {//This method will be used to end the player's turn.
        System.out.println("You have chosen to stand. Now only luck can save you!");
    }

    public int playerHandValue() {//A simple method to print our hand, I wanted to make it a separate method to keep the code clean.
        int handValue = hit();
        System.out.print("Your hand contains: ");
        for (int i = 0; i < playerHand.size(); i++) {
            Card c = playerHand.get(i);
            System.out.print(c.getRank() + ", ");
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
