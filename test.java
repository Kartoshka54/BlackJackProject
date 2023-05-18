import ChipClasses.ChipStacks;

// 9,4 - 8,3 random method
// 21de bitirmiyor
//Shuffle algortihm needs to be implemented
public class test {
    public static void main(String[] args) {

        ChipStacks chipStacks = new ChipStacks();

        BlackJackMethods bj = new BlackJackMethods();
    //    bj.turnOrder("player","dealer");
        bj.chipSelector(chipStacks);

    }
}
