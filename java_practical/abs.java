public class abs {
    public static void main(String args[])
    {
        deer d=new deer();
        d.eat();
    }

    abstract static class animal{
        abstract void eat();
    }
     public static class deer{
        void eat(){
            System.out.println("grass");
        }
     }


    
}
