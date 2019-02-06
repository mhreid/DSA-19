package your_code;

public class MyLinkedList {

    private Node head;
    private Node tail;
    private int size;

    private class Node {
        Chicken val;
        Node prev;
        Node next;

        private Node(Chicken d, Node prev, Node next) {
            this.val = d;
            this.prev = prev;
            this.next = next;
        }

        private Node(Chicken d) {
            this.val = d;
            prev = null;
            next = null;
        }
    }

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(Chicken c) {
        addLast(c);
    }

    public Chicken pop() {
        return removeLast();
    }

    public void addLast(Chicken c) {
        if(tail != null){
            tail.next = new Node(c, tail, null);
            tail = tail.next;
        } else {
            head = new Node(c, null, null);
            tail = head;
        }
        size++;
    }

    public void addFirst(Chicken c) {
        if(head != null) {
            head = new Node(c, null, head);
        } else {
            head = new Node(c, null, null);
            tail = head;
        }
        size++;

    }

    public Chicken get(int index) {
        if(index > size){
            throw new IndexOutOfBoundsException("End of list or beyond!");

        } else {
            Node curr = head;
            for(int i = 0; i < index; i++){
                curr = curr.next;
            }
            return curr.val;

        }
    }

    public Chicken remove(int index) {
        if(index > size){
            throw new IndexOutOfBoundsException("End of list or beyond!");

        } else {
            Node curr = head;
            for(int i = 0; i < index; i++){
                curr = curr.next;
            }
            if(curr.prev != null) {
                curr.prev.next = curr.next;
            }
            if(curr.next != null) {
                curr.next.prev = curr.prev;
            }
            size--;
            return curr.val;

        }
    }

    public Chicken removeFirst() {
        Node temp = head;
        head = head.next;
        size--;
        return temp.val;
    }

    public Chicken removeLast() {
        Node temp = tail;
        tail = tail.prev;
        size--;
        return temp.val;
    }
}