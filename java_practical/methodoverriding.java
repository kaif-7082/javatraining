public class methodoverriding {
    public static void main(String args[])
    {
       h2 h=new h2();
       h.print();
    }

    public static class h1{
        public void print()
        {
            System.out.println("hello");
        }

    }
    public static class h2 extends h1{
        public void print(){
            System.out.println("hi");
        }
    }
    
}
