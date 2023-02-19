import java.util.NoSuchElementException;

public class CircularQueue {
    private int front, end;
    private Passenger [] passQueue = new Passenger[5];        //Creating an array of Passengers for queue

    public CircularQueue() {
        this.front = this.end = -1;     //Initializing front and rear into -1
    }

    public void enqueue (Passenger pDetails) {
        if (isEmpty())          //Checks if the queue is empty
            front ++;
        end = (end+1) % passQueue.length;
        passQueue[end] = pDetails;
    }

    /**
     Check if the queue is empty
     Parameters- None
     Return- True if empty, False if not
     */
    public boolean isEmpty () {
        return front == -1;
    }

    /**
     Check if the queue is full
     Parameters- None
     Return- True if full, False if not
     */
    public boolean isFull () {
        return (end+1) % passQueue.length == front;
    }

    /**
     Returns Passenger object of the first in queue
     Parameters- None
     Return- True if empty, False if not
     */
    public Passenger dequeue () {
        if (isEmpty())
            throw new  NoSuchElementException();
        Passenger temp = passQueue[front];
        passQueue[front] = null;

        if (front == end)
            front = end -1;
        else
            front = (front+1) % passQueue.length;
        return temp;
    }
}