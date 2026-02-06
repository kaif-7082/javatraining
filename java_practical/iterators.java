import java.util.*;

public class iterators {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("Kaif");
        names.add("Arjun");
        names.add("Riya");
        names.add("Dev");

        // Using Iterator (forward traversal only)
        System.out.println("Using Iterator:");
        Iterator<String> it = names.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        // Using ListIterator (can go both forward and backward)
        System.out.println("\nUsing ListIterator:");
        ListIterator<String> listIt = names.listIterator();

        System.out.println("Forward:");
        while (listIt.hasNext()) {
            System.out.println(listIt.next());
        }

        System.out.println("Backward:");
        while (listIt.hasPrevious()) {
            System.out.println(listIt.previous());
        }
    }
}
