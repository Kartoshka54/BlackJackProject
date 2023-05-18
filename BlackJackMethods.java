import CardClasses.Card;
import CardClasses.Deck;
import CardClasses.Qll;
import ChipClasses.ChipStacks;

import javax.swing.*;
import java.util.Scanner;
import java.util.Stack;
public class BlackJackMethods {

        Scanner sc = new Scanner(System.in);
        ChipStacks chipStacks = new ChipStacks();
        Deck deck = new Deck();
        Stack<Card> playerHand = new Stack<>();
        Stack<Card> dealerHand = new Stack<>();
        private static final int WIN_CONDITION = 21;
        private static final int DEALER_RULE = 17;

        //This method will be used to determine the turn order.
        public void turnOrder(String player, String dealer) {
            Qll playerTurn = new Qll();
            playerTurn.enqueue("player");

            playerHand.clear();
            dealerHand.clear();

            playerHit();
            dealerHit();


            while (chipStacks.total() > 0) {
                if (playerTurn.getFront().equals("player")) {
                    System.out.println("It is your turn, hit or stand? (h/s)");
                    String choice = sc.nextLine();
                    if (choice.equals("h")) {
                        playerHandValue();
                        aceCheck(playerHand);
                        playerTurn.deleteQueue();
                        playerTurn.enqueue("dealer");
                        winConditionCheck();
                    } else if (choice.equals("s")) {
                        stand();
                        playerTurn.deleteQueue();
                        playerTurn.enqueue("dealer");
                    } else {
                        System.out.println("Invalid input, try again.");
                    }
                }
                if (playerTurn.getFront().equals("dealer")) {
                    dealerTurn();
                    aceCheck(dealerHand);
                    playerTurn.deleteQueue();
                    playerTurn.enqueue("player");
                }
            }
        }


    //Hit method for the player, used to draw cards from the deck
        public int playerHit() {
            Card card = deck.draw();
            playerHand.push(card);
            return aceCheck(playerHand);
        }

        public void dealerHit() {
            Card card = deck.draw();
            dealerHand.push(card);
            aceCheck(dealerHand);
        }
        //This method will be used to end the player's turn.
        public void stand () {
            System.out.println("You have chosen to stand. Now only luck can save you!");
        }
        //This method also converts the ace value
        public int playerHandValue () {
            int handValue = playerHit();
            System.out.println("Your hand contains: ");
            for (int i = 0; i < playerHand.size(); i++) {
                Card c = playerHand.get(i);
                System.out.println("\t" + c.getRank() + " of " + c.getSuit());

            }
            System.out.println("\nTotal hand value of the player: " + handValue);
            return handValue;
        }
        //So in the classic variation of blackjack, the dealer is supposed to hit until he reaches 17 or more.
        //Even if the player has a higher value, the dealer must abide by this rule which will give the player a chance to win.
        //Of-course we will make the dealer harder to beat as the money value of the player increases, he might even cheat ;).
        public void dealerTurn() {
            int handValue = dealerHandValue();
            if (handValue <= DEALER_RULE) {
                dealerHit();
                handValue = dealerHandValue();

                System.out.println("The hand of the dealer contains: ");
                for (int i = 0; i < dealerHand.size(); i++) {
                    Card card = dealerHand.get(i);
                    System.out.println("\t" + card.getRank() + " of " + card.getSuit());
                }
                System.out.println("Total hand value of the dealer: " + handValue);
            }else {
                System.out.println("The dealer has decided to stand");
            }
        }


    public int dealerHandValue() {
        int handValue = 0;
        for (int i = 0; i < dealerHand.size(); i++) {
            Card card = dealerHand.get(i);
            handValue += card.getValue();
        }
        return handValue;
    }
    public int aceCheck(Stack<Card> playerHand) {
            int value = 0;
            int numAces = 0;

            // First pass through the hand, counting the total value and number of Aces
            for (Card c : playerHand) {
                value += c.getValue();
                if (c.getRank().equals("Ace")) {
                    numAces++;
                }
            }

            // Adjust the total value for any Aces that need to be counted as 1 instead of 11
            while (numAces > 0 && value > 21) {
                value -= 10;
                if(numAces > 1) {
                    System.out.println("Your Aces have been converted to 1");
                } else {
                    System.out.println("Your Ace has been converted to 1");
                }

                numAces--;
            }

            // If there are still Aces in the hand, and the total value is less than or equal to 11,
            // count the next Ace as 11 instead of 1
            while (numAces > 0 && value <= 11) {

                numAces--;
            }

            return value;
        }

    public int betPlace () {
        System.out.println("WELCOME! WELCOME!, please place your bets ladies and gentleman");

        chipStacks.displayChipCountsByStacks();
        System.out.println("Balance: " + chipStacks.total());
        int bet;

        while(true){
            bet  = sc.nextInt();
            if (bet > chipStacks.total()){
                System.out.println("Your balance is not enough for this bet!");
                System.out.println("Balance: " + chipStacks.total());
            }
            else{

            }
        }

    }

    public int chipSelector(ChipStacks chipStack, ChipStacks betStack){
        int chipValue;
        int chipCount;
        int totalBet = 0;
        while(true){
            System.out.println("Total amount you have: " + chipStack.total());
            chipStack.displayChipCountsByStacks();
            System.out.println("Which chip value do you want to place?");
            chipValue = chipPlacer();
            System.out.println("How many chips do you want to place?");
            System.out.println("Current chip value: " + chipValue);
            chipStack.displayChipCountsByStacks();
            chipCount = sc.nextInt();
            if(chipCount*chipValue > chipStack.total()) {
                System.out.println("You cannot afford this bet please enter new values.");
            } else if (chipCount > chipStack.findTheStackOfTheChip(chipValue).getCount()) {
                System.out.println("You want to place " + chipCount + " chip/s from " + chipValue + " valued chip stack but you have "
                        + chipStack.findTheStackOfTheChip(chipValue).getCount() + " chips in that stack!");

                while(true) {
                    System.out.println("Do you want to convert some chips to " + chipValue + "? y/n");
                    String response = sc.next();
                    if (response.equals("y")) {
                        while(true) {
                            System.out.println("Which value do you want to convert? ");
                            int valueToBeConverted = chipPlacer();
                            if (valueToBeConverted > chipValue) {
                                chipStack.converterHighToLow(valueToBeConverted, chipValue);
                                break;
                            } else if (valueToBeConverted < chipValue) {
                                chipStack.convertLowToHigh(valueToBeConverted, chipValue);
                                break;
                            } else {
                                System.out.println("This operation is not possible.");
                            }
                        }
                    } else if (response.equals("n")) {
                        break;
                    } else {
                        System.out.println("This is not a valid option! y/n");
                    }
                }
            } else{
                for(int i = 0; i < chipCount; i++){
                    chipStack.popChipFromStackByValue(chipValue);
                }
                betStack.pushChipToStackByValue(chipValue,chipCount);
                totalBet += chipCount*chipValue;
                System.out.println("Your bet is " + totalBet);
                System.out.println("Do you want to place more bet ? y/n");
                String choice = sc.next();;
                if (choice.equals("y")){
                    continue;
                }else if (choice.equals("n")){
                    System.out.println("Total left: " + chipStack.total());
                    System.out.println("Your final bet: " + totalBet);
                    System.out.println("Bet on the board: ");
                    betStack.displayChipCountsByStacks();
                    break;
                }
            }
        }
        return totalBet;
    }

    /*public int chipCase(){
        System.out.println("1- 5\n2- 10\n3- 25\n4- 50\n5- 100\n6- 500\n7- 1000");
        int chipCase = sc.nextInt();
        if (chipCase == 1) {
            return 5;
        } else if (chipCase == 2) {
            return 10;
        } else if (chipCase == 3) {
            return 25;
        } else if (chipCase == 4) {
            return 50;
        } else if (chipCase == 5) {
            return 100;
        } else if (chipCase == 6) {
            return 500;
        } else if (chipCase == 7) {
            return 1000;
        }
        return -1;
    }*/
    public int chipCase(){
        System.out.println("1- 5\n2- 10\n3- 25\n4- 50\n5- 100\n6- 500\n7- 1000");
        int chipCase = sc.nextInt();
        int  chips;
        switch (chipCase) {
            case 1:
                chips = 5;
                break;
            case 2:
                chips = 10;
                break;
            case 3:
                chips = 25;
                break;
            case 4:
                chips = 50;
                break;
            case 5:
                chips = 100;
                break;
            case 6:
                chips = 500;
                break;
            case 7:
                chips = 1000;
                break;
            default:
                chips = -1;
                break;
        }
        return chips;
    }

    public int chipPlacer(){
        int chipValue = -1;
        while(chipValue == -1) {
            chipValue = chipCase();
            if(chipValue == -1)
                System.out.println("This is not a valid option!");
        }
        return chipValue;
    }

    public void winConditionCheck () {
        if (playerHandValue() == WIN_CONDITION && playerHandValue() != dealerHandValue()) {
            System.out.println("High and a winner! Got a hot hand, hot hand, hot hand!");
        } else if (dealerHandValue() == WIN_CONDITION && playerHandValue() != dealerHandValue()) {
            System.out.println("The dealer has won! And takes the money straight back to the house!");
        }
        else if(playerHandValue() > WIN_CONDITION) {
            System.out.println("Bust, bust, bust! Ladies and gentleman the player has busted!");
        } else if (dealerHandValue() > WIN_CONDITION) {
            System.out.println("The house has lost! Revenge will follow soon!");

        } else if (playerHandValue() == dealerHandValue()) {
            System.out.println("It's a tie! Tie! Tie!!!!");
        }
        else if (playerHandValue() != WIN_CONDITION && dealerHandValue() != WIN_CONDITION && playerHandValue() > WIN_CONDITION && dealerHandValue() > WIN_CONDITION) {
            System.out.println("Nobody wins, nobody loses. It's a push!!!");
        }

    }

}

