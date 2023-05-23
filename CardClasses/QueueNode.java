package CardClasses;

public class QueueNode {
    /*This is our foundation for our data structures that we are going to use for this project
    we did not want to use pre-written libraries to not only better modify the structures for our needs but
    also to display our understanding of the data structures we have learned so far. This class will be used for
    the queue structure to keep track of the player and dealer turns.
     */
    private String turn; //Our turn attribute is kept as a String value because we do not use this class for numeric values
    private QueueNode link;//Link attribute is used to keep track of the next node in the queue.

    //We decided that we will implement those structures as Singly Linked Lists because we only need 1 direction.
    public QueueNode() {
        turn = null;
        link = null;
        //Only 2 attributes are needed for this class. Item to keep track of the value and link to keep track of the next node.

    }

    //My project buddy already explained why we use getters and setters. So I will not explain it again.
    public String getTurn() {
        return turn;
    }

    public void setTurn(String turn) {
        this.turn = turn;
    }

    public QueueNode getLink() {
        return link;
    }

    public void setLink(QueueNode link) {
        this.link = link;
    }

}
