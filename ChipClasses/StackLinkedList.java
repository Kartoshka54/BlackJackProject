package ChipClasses;

public class StackLinkedList {
    //add chip sum
    //write a method to count the chip values; 300x2, 500x4 etc.
    //chip converter ?
    //5,10,25,50,100,500,1000.
    // null point exception for getvalue method !!
    private StackNode top;
    private int count;

    // To initialize the chip stack.
    public StackLinkedList(){
        top = null;
        count = 0;
    }
    // To check if the chip stack is empty or not.
    public boolean isEmpty(){
        return top == null;
    }

    // Putting chips into the chip stack.
    public void push(StackNode node){
        node.setLink(top);
        top = node;
        count++;
    }

    //Take a chip out of chip stack.
    public void pop(){
        if(isEmpty()){
            System.out.println("This stack is empty!");

        }else{
            top = top.getLink();
            count--;

        }
    }
    // Total number of chips in a chip stack.
         public int getCount(){
             return this.count;
         }
         //Returning the value of the chips in the stack.


 /*       public int getValue(){
            assert (top == null);
            return top.getChipValue();
           }
*/

        }







/*
    public void traverse(){
        ChipClasses.StackNode current = top;
        while(current != null){
            System.out.println(current.getChipValue());
            current = current.getLink();
        }
    }
*/

