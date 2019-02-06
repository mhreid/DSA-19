package your_code;

/**
 * An implementation of a priority Queue
 */
public class MyPriorityQueue{
    private Node head;
    private Node tail;
    private int size;
    private class Node{
        int val;
        Node prev;
        Node next;

        private Node(int v, Node prev, Node next){
            this.val = v;
            this.prev = prev;
            this.next = next;
        }
    }
    public MyPriorityQueue(){
        head = null;
        tail = null;
        size = 0;
    }
    public void enqueue(int item) {
        if(size == 0){
            head = new Node(item, null, null);
            tail = head;
        } else {
            Node curr = head;
            for(int i = 0; i < size; i++){
                if(curr.next == null || curr.next.val >= item){
                    Node temp = curr.next;
                    curr.next = new Node(item, curr, temp);
                    if(temp != null){
                        temp.prev = curr.next;
                    } else {
                        tail = curr.next;
                    }
                    break;
                }
                curr = curr.next;
            }
        }
        size++;
    }

    /**
     * Return and remove the largest item on the queue.
     */
    public int dequeueMax() {
        Node temp = tail;
        tail = tail.prev;
        size--;
        return temp.val;
    }

}