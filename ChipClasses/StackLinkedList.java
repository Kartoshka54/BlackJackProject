package ChipClasses;

public class StackLinkedList {
    private StackNode top;
    private int count;

    // To initialize the chip stack.
    public StackLinkedList() {
        top = null;
        count = 0;
    }

    // To check if the chip stack is empty or not.
    public boolean isEmpty() {
        return top == null;
    }

    // Putting chips into the chip stack.
    public void push(StackNode node) {
        node.setLink(top);
        top = node;
        count++;
    }

    //Take a chip out of chip stack.
    public void pop() {
        if (isEmpty()) {
            System.out.println("This stack is empty!");

        } else {
            top = top.getLink();
            count--;

        }
    }

    // Total number of chips in a chip stack.
    public int getCount() {
        return this.count;
    }
}