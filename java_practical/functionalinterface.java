public class functionalinterface {

    @FunctionalInterface
    public interface Print{
        void print();
    }
    public static void main(String args[])
    {
        Print p=()->{
            System.out.println("Hello");
        };

        p.print();
    }
}
