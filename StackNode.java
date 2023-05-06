public class StackNode {
// Stack node is going to be used in singly linked stack data structure hence there is only one link instance.
    private int chipValue; // chipValue instance is going to be used to store the value of the chips as integer values.
                          // Chip values are going to be 5,10,25,50,100,200,300,500,1000.
    private StackNode link; // link instance is going to be used to connect the nodes in the stack structure.

    /*There are 4 different type of StackNode constructor each of which can be used in different scenarios when needed.
    1st constructor: chipValue is set to '0' by default and link is pointing to 'null'.
    2nd constructor: chipValue is set to the integer value passed in the constructor and link is pointing to 'null'.
    3rd constructor: chipValue is set to '0' and link is pointing to the StackNode instance passed in the constructor.
    4th constructor: chipValue is set to the integer value passed in the constructor and link is pointing to the
                     StackNode instance passed in the constructor.
    */
    //1st constructor
    public StackNode(){
        setChipValue(0);
        setLink(null);
    }
    //2nd constructor
    public StackNode(int chipValue){
       setChipValue(chipValue);
       setLink(null);
    }
    //3rd constructor
    public StackNode(StackNode link){
        setChipValue(0);
        setLink(null);
    }
    //4th constructor
    public StackNode(int chipValue, StackNode link){
        setChipValue(chipValue);
        setLink(link);
    }
    /* get and set methods to obtain the values StackNode instances have or change the data they contain while the
    program is being executed.
    */
    public void setChipValue(int chipValue) {
        this.chipValue = chipValue;
    }

    public int getChipValue(){
        return this.chipValue;
    }

    public void setLink(StackNode link) {
        this.link = link;
    }

    public StackNode getLink(){
        return this.link;
    }
}
