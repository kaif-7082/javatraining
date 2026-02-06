public class object{
    public static void main(String args[])
    {
       car c1=new car("alto",9000);
       System.out.println(c1.name);
    }

   public static class car{
    String name;
    int price;
      car(String name,int price){
        this.name=name;
        this.price=price;
      }

   } 
} 