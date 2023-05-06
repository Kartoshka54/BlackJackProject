public class ChipStacks {
    // for low to high ?
    // 1 valued chips ??
    private final StackLinkedList[] chipStacksArray = new StackLinkedList[7];
    private final int[] chipValuesOfTheStacks = {5, 10, 25, 50, 100, 500, 1000};

    // To initialize the player chip stacks with proper values and starting quantity.
    public ChipStacks(){
        for(int i = 0 ; i<chipStacksArray.length;i++){
            chipStacksArray[i] = new StackLinkedList();
        }
        for(int i = 0; i< 20; i++){
            chipStacksArray[0].push(new StackNode(5));
        }
        for(int i = 0; i< 10; i++){
            chipStacksArray[1].push(new StackNode(10));
        }
        for(int i = 0; i< 4; i++){
            chipStacksArray[2].push(new StackNode(25));
        }
        for(int i = 0; i< 4; i++){
            chipStacksArray[3].push(new StackNode(50));
        }
        for(int i = 0; i< 5; i++){
            chipStacksArray[4].push(new StackNode(100));
        }
        for(int i = 0; i< 4; i++){
            chipStacksArray[5].push(new StackNode(500));
        }
        for(int i = 0; i< 2; i++){
            chipStacksArray[6].push(new StackNode(1000));
        }
    }

    // To calculate the total money the player has.
    public int total(){
        int sum = 0;
        for(int i = 0; i < chipStacksArray.length; i++) {
            if(chipStacksArray[i].isEmpty()){
                sum +=0;
            }else{
                sum += chipStacksArray[i].getCount() * chipValuesOfTheStacks[i];
            }
        }
        return sum;
    }

    // To show to the player how many chips do they have from every chip stack.
    public void displayChipCountsByStacks(){
        for(int i = 0; i<chipStacksArray.length;i++){
            System.out.println(chipStacksArray[i].getCount() + " chip/s left in " + chipValuesOfTheStacks[i] +
                    " valued chip stack.");
        }
    }

    // To convert the high valued chips to low valued chips if needed in the game.
    public void converterHighToLow(int highValue, int lowValue){
        int newChipCount;
        int chipCountOfHighValueStack = findTheStackOfTheChip(highValue).getCount();
        if(highValue == 5){
            System.out.println("5 is the lowest value a chip can have! You cannot convert it to a lower value whatsoever.");
        }else if (highValue == 25) {
            popChipFromStackByValue(25);
            pushChipToStackByValue(10,2);
            pushChipToStackByValue(5,1);
        }else if(lowValue > highValue || highValue % lowValue != 0){
            System.out.println("This calculation is not possible.");
        }
        else{
            if(chipCountOfHighValueStack == 0){
                System.out.println("There are no chips in the " + highValue +" chip stack!");
            }
            else{
                newChipCount = highValue/lowValue;
                popChipFromStackByValue(highValue);
                pushChipToStackByValue(lowValue,newChipCount);
                System.out.println("Successfully converted from " + highValue + " chips to " + lowValue + " chips.");
            }
        }
    }

    // To convert the low valued chips to high valued chips if needed in the game.
    public void convertLowToHigh(int lowValue, int highValue){
        StackLinkedList lowValueStack = findTheStackOfTheChip(lowValue);
        StackLinkedList highValueStack = findTheStackOfTheChip(highValue);

        int chipCountNeededFromLowValueStack;

        if(lowValue == 1000){
            System.out.println("1000 is the highest value a chip can have! You cannot convert it to a higher value whatsoever!");
        }
        else if(lowValue > highValue || highValue % lowValue != 0){
            System.out.println("This calculation is not possible!");
        }
        else{
            chipCountNeededFromLowValueStack = highValue / lowValue;
            if(chipCountNeededFromLowValueStack > lowValueStack.getCount()){
                System.out.println("You do not have enough " + lowValue + " valued chips to obtain a " + highValue + " value chip!");
            }
            else{
                while(chipCountNeededFromLowValueStack > 0){
                    lowValueStack.pop();
                    chipCountNeededFromLowValueStack--;
                }
                highValueStack.push(new StackNode(highValue));
            }
        }
    }

    // To delete the desired chips from the proper chip stack. This method is in other methods in this class.
    public void popChipFromStackByValue(int chipValue){
        if(chipValue == 5){
            chipStacksArray[0].pop();
        }
        else if(chipValue == 10){
            chipStacksArray[1].pop();
        }
        else if(chipValue == 25){
            chipStacksArray[2].pop();
        }
        else if(chipValue == 50){
            chipStacksArray[3].pop();
        }
        else if(chipValue == 100){
            chipStacksArray[4].pop();
        }else if(chipValue == 500){
            chipStacksArray[5].pop();
        }else{
            chipStacksArray[6].pop();
        }
    }

    // To add the desired chips to the proper chip stack. This method is used in other methods in this class.
    public void pushChipToStackByValue(int chipValue, int chipCount) {
        while (chipCount > 0){
            if (chipValue == 5) {
                chipStacksArray[0].push(new StackNode(5));
            } else if (chipValue == 10) {
                chipStacksArray[1].push(new StackNode(10));
            } else if (chipValue == 25) {
                chipStacksArray[2].push(new StackNode(25));
            } else if (chipValue == 50) {
                chipStacksArray[3].push(new StackNode(50));
            } else if (chipValue == 100) {
                chipStacksArray[4].push(new StackNode(100));
            } else if (chipValue == 500) {
                chipStacksArray[5].push(new StackNode(500));
            } else {
                chipStacksArray[6].push(new StackNode(1000));
            }
            chipCount--;
        }
    }

    // To obtain the stack of the desired chip value. This method is used in other methods in this class.
    public StackLinkedList findTheStackOfTheChip(int chipValue){
        if(chipValue == 5){
            return chipStacksArray[0];
        }
        else if(chipValue == 10){
            return chipStacksArray[1];
        }
        else if(chipValue == 25){
            return chipStacksArray[2];
        }
        else if(chipValue == 50){
            return chipStacksArray[3];
        }
        else if(chipValue == 100){
            return chipStacksArray[4];
        }
        else if(chipValue == 500){
            return chipStacksArray[5];
        }
        else {
            return chipStacksArray[6];
        }
    }
}