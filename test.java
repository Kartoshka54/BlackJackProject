import ChipClasses.ChipStacks;

public class test {
    public static void main(String[] args) {
        BlackJackMethods bj = new BlackJackMethods();
        //bj.turnOrder("player","dealer");

        ChipStacks chipStacks = new ChipStacks();
        ChipStacks betStack = new ChipStacks();
        betStack.resetStacks();
        bj.chipSelector(chipStacks,betStack);


    }
}



