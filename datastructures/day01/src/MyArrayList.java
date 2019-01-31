public class MyArrayList {
    private Cow[] elems;
    private int size;


    // Runtime: O(1)
    public MyArrayList() {
        elems = new Cow[10];
        size = 0;
    }

    // Runtime: O(1)
    public MyArrayList(int capacity) {
        elems = new Cow[capacity];
        size = 0;
    }

    // Runtime: O(1)*
    public void add(Cow c) {
        if(size + 1 < elems.length) {
            elems[size] = c;
        } else {
            Cow[] temp = new Cow[(size + 1) * 2];
            for(int i = 0; i < elems.length; i++){
                temp[i] = elems[i];
            }
            elems = temp;
            elems[size] = c;
            //System.out.print("resizing to ");
            //System.out.println((elems.length));

        }
        size++;
    }

    // Runtime: O(1)
    public int size() {
        return size;
    }

    // Runtime: O(1)
    public Cow get(int index) {
        try
        {
            return elems[index];
        }
        catch(IndexOutOfBoundsException e)
        {
            System.out.println("There's no cow here!");
        }
        return null;
    }

    // Runtime: O(N)
    public Cow remove(int index) {
        if(index >= size){
            System.out.println("There's no cow here!");
            throw new IndexOutOfBoundsException("End of list or beyond!");

        }

        for(int i = index; i < size + 1; i++){
            elems[i] = elems[i + 1];
        }
        size--;
        if(size * 4 < elems.length && size > 0){
            Cow[] temp = new Cow[elems.length / 2];
            System.arraycopy(elems,0,temp,0,size);
            elems = temp;
            //System.out.print("reduced to ");
            //System.out.println(temp.length);

        }

        return null;
    }

    // Runtime: O(N)
    public void add(int index, Cow c) {

        if(index > size){
            throw new IndexOutOfBoundsException("End of list or beyond!");
        }
       add(elems[size - 1]);
       for(int i = size; i > index - 1; i--){
           elems[i] = elems[i - 1];
       }
       elems[index] = c;
    }

    public void print(){
        for(int i = 0; i < size; i++) {
            //System.out.println(elems[i].name);
        }
    }
}

