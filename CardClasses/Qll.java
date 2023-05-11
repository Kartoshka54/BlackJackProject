package CardClasses;

import CardClasses.QueueNode;

public class Qll {
    /*This will be our queue data structure and will be implemented using linked lists. Why linked lists?
    Because we can not be sure how many turns there are going to be so linked lists give us the flexibility
    of adding and removing the nodes without issue.
     */
    private QueueNode front;//Front of the queue
    private QueueNode rear;//back of the queue
    private int count;//Count will be used to keep track of added and deleted items in the queue.

    public Qll() {
        front = null;
        rear = null;
    }
    public boolean isEmpty() {
        return (front == null);//A simple method to check if the queue is empty, we only need to check the front value
        //Because queues are FIFO (First in first out) data structures. So keeping track of the front node is enough.
        /*Also as a bonus, a FIFO data structure is a perfect fit for a queue in a card game. So we will have no issue
        with the order of the turns.*/
    }
    public String getFront() {
        return front.getTurn();
    }
    public String getRear() {
        return rear.getTurn();
    }
    public void enqueue(String turn) {
        QueueNode newNode = new QueueNode();//newNode is the new item that will be added to the queue
        newNode.setTurn(turn);
        newNode.setLink(null);
        if (isEmpty()) {//checks if the queue is empty before enqueuing.
            front = newNode;
            rear = newNode;
        } else {
            rear.setLink(newNode);
            rear = newNode;//If the queue is not empty, the new item will be added at the rear of the queue.
        }
        count++;//Keeps track of added items and increments if the item is added
    }
    public void deleteQueue() {
        if (isEmpty()) {
            System.out.println("Queue is empty");//No need to delete if the structure is already empty
        } else {
            front = front.getLink();
            if (front == null) {
                rear = null;//If the front is null, the rear is also null because there is only 1 item in the queue
            }
        }
        count--;//Decreases the count if the item is deleted.
    }
    public void printQueue () {
        QueueNode temp = front;
        while (temp != null) {//We need our temp helper here to traverse through the queue and print the items.
            System.out.println(temp.getTurn());
            temp = temp.getLink();//The temp helper jumps from node to node and gets every item in the queue.
        }
    }

}