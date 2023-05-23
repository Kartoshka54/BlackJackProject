import CardClasses.Card;
import CardClasses.Deck;
import CardClasses.Qll;
import ChipClasses.ChipStacks;

import javax.swing.*;
import java.util.Scanner;
import java.util.Stack;

public class BlackJackMethods {

    private static final int WIN_CONDITION = 21;
    private static final int DEALER_RULE = 17;
    private static final int PUSH_RULE = 22;
    private static final int PLAYER_CONDITION = 29;
    Scanner sc = new Scanner(System.in);
    ChipStacks chipStacks = new ChipStacks();

    //Some final statements to avoid magic numbers.
    ChipStacks betStacks = new ChipStacks();
    Deck deck = new Deck();
    Stack<Card> playerHand = new Stack<>();
    //Here we decided to use the pre-written java stack class, to not heavily modify our own stack class.
    Stack<Card> dealerHand = new Stack<>();
    int turnCount = 0;

    //This method will be used to determine the turn order.
    public void turnOrder(String player, String dealer) {
        Qll playerTurn = new Qll();
        playerTurn.enqueue("player");
        betStacks.resetStacks();
        bet(chipStacks, betStacks);
        System.out.println("\n____________________________________________________" +
                "\nThe bets are made, I repeat, the bets are made!!!!!\n" +
                "____________________________________________________");
        playerHit();
        dealerHit();

        while (chipStacks.total() >= 0) {
            int currentHandValue = 0;
            if (playerTurn.getFront().equals("player")) {
                System.out.println("It is your turn, hit or stand? (h/s)");
                String choice = sc.next();
                if (choice.equals("h")) {
                    currentHandValue = playerHandValue();
                    //Here we made sure that the aceCheck method converts the ace value depending on the situation.
                    //The stack is checked and if conditions are met, aces are converted to 1.
                    aceCheck(playerHand);
                    playerTurn.deleteQueue();
                    playerTurn.enqueue("dealer");
                } else if (choice.equals("s")) {
                    if (dealerHandValue() >= DEALER_RULE) {
                        System.out.println("The dealer is standing, you must play!");
                    } else {
                        stand();
                        playerTurn.deleteQueue();
                        playerTurn.enqueue("dealer");
                    }

                }
            }
            if (playerTurn.getFront().equals("dealer")) {
                dealerTurn();
                dealerAceCheck(dealerHand);
                playerTurn.deleteQueue();
                playerTurn.enqueue("player");
            }

            //turnCount is used here to help us draw 2 cards when the game starts and then going down to 1 card per turn.
            //When the while loop reiterates, both the player and the dealer will again, draw 2 cards.
            turnCount++;
            if (!winConditionCheck(currentHandValue, chipStacks, betStacks)) {
                bet(chipStacks, betStacks);
            }
        }
    }


    //Hit method for the player, used to draw cards from the deck
    public int playerHit() {
        Card card = deck.draw();
        playerHand.push(card);
        return aceCheck(playerHand);
    }

    //Same method but for the dealer
    public void dealerHit() {
        Card card = deck.draw();
        dealerHand.push(card);
        aceCheck(dealerHand);
    }

    //This method will be used to end the player's turn.
    public void stand() {
        System.out.println("You have chosen to stand. Now only luck can save you!");
    }

    //The playerHandValue method both hits and prints the hand value of the player.
    public int playerHandValue() {
        int handValue = playerHit();
        System.out.println("Your hand contains: ");
        for (int i = 0; i < playerHand.size(); i++) {
            Card c = playerHand.get(i);
            System.out.println("\t" + c.getRank() + " of " + c.getSuit());

        }
        System.out.println("\nTotal hand value of the player: " + handValue +
                "\n___________________________________________________________");
        return handValue;
    }

    //So in the classic variation of blackjack, the dealer is supposed to hit until he reaches 17 or more.
    //Even if the player has a higher value, the dealer must abide by this rule which will give the player a chance to win.
    public void dealerTurn() {
        int handValue = dealerHandValue();
        if (handValue < DEALER_RULE) {
            dealerHit();
            handValue = dealerHandValue();

            System.out.println("The hand of the dealer contains: ");
            for (int i = 0; i < dealerHand.size(); i++) {
                Card card = dealerHand.get(i);
                System.out.println("\t" + card.getRank() + " of " + card.getSuit());
            }
            System.out.println("Total hand value of the dealer: " + handValue +
                    "\n___________________________________________________________");
        } else {
            System.out.println("The dealer has decided to stand, it's gettin hot here!");
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
            if (numAces > 1) {
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

    //Same method but to print the message with dealer instead of player.
    public int dealerAceCheck(Stack<Card> playerHand) {
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
            if (numAces > 1) {
                System.out.println("The dealer's Aces have been converted to 1");
            } else {
                System.out.println("The dealer's Ace has been converted to 1");
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

    // This is the main method that handles the bet system of the game.
    //Placing bets, converting chips to another valued chips, asking the player whether they want to stop or continue to bet.
    //Giving information about the total value the player has, the bet they placed and the total bet on the board.
    public int bet(ChipStacks chipStack, ChipStacks betStack) {
        int chipValue;
        int chipCount;
        int totalBet = 0;
        while (true) {
            System.out.println("Total amount you have: " + chipStack.total());
            chipStack.displayChipCountsByStacks();
            System.out.println("Select the chip value to be placed!");
            chipValue = chipPlacer(chipStacks);
            chipCount = chipCountController(chipStack, chipValue);
            if (chipCount * chipValue > chipStack.total()) {
                System.out.println("Sorry pal, not enough chips for this.");
            } else if (chipCount > chipStack.findTheStackOfTheChip(chipValue).getCount()) {
                System.out.println("You want to place " + chipCount + " chip/s from " + chipValue + " valued chip stack but you have "
                        + chipStack.findTheStackOfTheChip(chipValue).getCount() + " chips in that stack!");

                chipConverter(chipStack, chipValue);
            } else {
                for (int i = 0; i < chipCount; i++) {
                    chipStack.popChipFromStackByValue(chipValue);
                }
                betStack.pushChipToStackByValue(chipValue, chipCount);
                totalBet += chipCount * chipValue;
                System.out.println("Your bet is " + totalBet);
                System.out.println("Do you want to increase your bet? y/n\n" +
                        "______________________________________________________");
                String choice;
                while (true) {
                    choice = sc.next();
                    if (choice.equals("y") || choice.equals("n")) {
                        break;
                    } else {
                        System.out.println("Enter a valid value! y/n");
                    }
                }
                if (choice.equals("y")) {
                    continue;
                }
                System.out.println("Total left: " + chipStack.total() + "" +
                        "\n______________________________________________________");
                System.out.println("Your final bet: " + totalBet);
                System.out.println("Bet on the board: \n");
                betStack.displayChipCountsByStacks();
                break;
            }
        }
        return totalBet;
    }

    // This method provides the player to SELECT between different types of chips he/she has..
    // Used in various methods in this class
    public int chipCase(ChipStacks playerStacks) {
        int chipCase;
        do {
            System.out.println("1- 5\n2- 10\n3- 25\n4- 50\n5- 100\n6- 500\n7- 1000");
            try {
                chipCase = sc.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Please enter a value between 1-7 (not as string)");
                sc.next();
            }
        } while (true);

        int chips;
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

    // This method is used to determine which valued chip the player wants to PLACE on the board.
    public int chipPlacer(ChipStacks playerStacks) {
        int chipValue = -1;
        while (chipValue == -1) {
            chipValue = chipCase(playerStacks);
            if (chipValue == -1) {
                System.out.println("This is not a valid option!");
            }
        }
        return chipValue;
    }

    // This method makes player to give an integer value as an input in order to place the previously determined chip value on the board.
    public int chipCountController(ChipStacks chipStack, int chipValue) {
        int chipCount;
        System.out.println("How many chips do you want to place?");
        System.out.println("Current chip value: " + chipValue);
        chipStack.displayChipCountsByStacks();
        do {
            try {
                chipCount = sc.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Enter an integer value! ");
                sc.next();
            }
        } while (true);
        return chipCount;
    }

    // This method makes it available for player to convert between chip values he/she has in order to place the bet as he/she desires.
    // Player can convert from a higher value or a lower value depending on the choice he/she makes.
    public void chipConverter(ChipStacks chipStack, int chipValue) {
        while (true) {
            System.out.println("Do you want to convert some chips to " + chipValue + "? y/n");
            String response = sc.next();
            if (response.equals("y")) {
                while (true) {
                    System.out.println("Which value do you want to convert? ");
                    int valueToBeConverted = chipPlacer(chipStacks);
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
    }

    //I decided to separate the turnOrder and winConditionCheck methods
    //Originally, I wanted to check the win conditions inside the while loop in turnOrder, but it was getting too messy.
    public boolean winConditionCheck(int handValue, ChipStacks playerStack, ChipStacks dealerStack) {
        if (handValue == WIN_CONDITION && handValue != dealerHandValue()) {
            System.out.println("\n__________________________________________________________" +
                    "\nHigh and a winner! Got a hot hand, hot hand, hot hand!\n" +
                    "__________________________________________________________");
            chipStacks.popFromOnePushToOther(playerStack, dealerStack, 2);
            playerHand.clear();
            dealerHand.clear();
            //Now the turnCount helper which I have implemented above becomes handy.
            //If the game continues after the first turn, the player and the dealer will again draw 2 cards
            if (turnCount >= 1) {
                playerHit();
                dealerHit();
            }
            return false;
        } else if (dealerHandValue() == WIN_CONDITION && handValue != dealerHandValue()) {
            System.out.println("\n__________________________________________________________" +
                    "\nThe dealer has won! And takes the money straight back to the house!\n" +
                    "__________________________________________________________");
            System.out.println("You lost " + betStacks.total() + " valued chips.");
            dealerStack.resetStacks();
            System.out.println("New total is: " + playerStack.total());
            playerHand.clear();
            dealerHand.clear();
            if (turnCount >= 1) {
                dealerHit();
                playerHit();
            }
            return false;
        } else if (handValue > WIN_CONDITION) {
            System.out.println("\n_________________________________________________________" +
                    "\nBust, bust, bust! Ladies and gentleman the player has busted!\n" +
                    "__________________________________________________________");
            System.out.println("You lost " + betStacks.total() + " valued chips.");
            dealerStack.resetStacks();
            System.out.println("New total is: " + playerStack.total());
            if (playerStack.total() == 0) {
                System.out.println("You have no more chips to play with. Game over!");
                System.exit(0);
            }
            playerHand.clear();
            dealerHand.clear();
            if (turnCount >= 1) {
                playerHit();
                dealerHit();
            }
            return false;
        } else if (dealerHandValue() > WIN_CONDITION) {
            System.out.println("\n__________________________________________________________" +
                    "\nThe house has lost! Revenge will follow soon!\n" +
                    "__________________________________________________________");
            chipStacks.popFromOnePushToOther(playerStack, dealerStack, 2);
            playerHand.clear();
            dealerHand.clear();
            if (turnCount >= 1) {
                playerHit();
                dealerHit();
            }
            return false;

        } else if (handValue == dealerHandValue() && turnCount > 1) {
            System.out.println("\n__________________________________________________________" +
                    "\nIt's a tie! Tie! Tie!!!!\n" +
                    "__________________________________________________________");
            System.out.println("Do you want to split the bet, or do you want to continue? (continue/split)");
            String response = sc.next();
            if (response.equals("continue")) {
                System.out.println("The game continues!");
            } else {
                chipStacks.popFromOnePushToOther(playerStack, dealerStack, 1);
                playerHand.clear();
                dealerHand.clear();
                if (turnCount >= 1) {
                    playerHit();
                    dealerHit();
                }
            }
            return false;
        }
        //I want to explain the last condition so, it becomes clear
        //The casino rules are as followed: If the dealer busts with 22 and the player has a handValue less than 29
        //The pot is split between the player and the dealer.
        else if (dealerHandValue() == PUSH_RULE && handValue <= PLAYER_CONDITION) {
            System.out.println("\n__________________________________________________________" +
                    "\nNobody wins, nobody loses. It's a push!!!\n" +
                    "__________________________________________________________");
            chipStacks.popFromOnePushToOther(playerStack, dealerStack, 1);
            playerHand.clear();
            dealerHand.clear();
            if (turnCount >= 1) {
                playerHit();
                dealerHit();
            }
            return false;
        }

        return true;
    }
}

