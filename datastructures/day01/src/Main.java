public class Main{
    public static void main(String[] args) {
        // Put a breakpoint on line 4. When the debugger stops there, choose "Step Into"

        // After you step out of debugPractice, you should end up here
        System.out.println("Hello, World!");
        //Cow cow = new Cow("Micah", 8, "Blue");
        //System.out.println(cow);
        MyArrayList list = new MyArrayList(2);
        list.add(new Cow("Micah", 7, "Blue"));
        list.add(new Cow("Micah2", 7, "Blue"));
        list.add(new Cow("Micah3", 7, "Blue"));
        list.add(new Cow("Micah4", 7, "Blue"));
        list.add(new Cow("Micah5", 7, "Blue"));
        list.add(1,new Cow("Micah5", 7, "Blue"));


       list.print();


    }
}