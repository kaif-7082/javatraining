public class inheritence {
    public static void main(String args[])
    {
       zebra z=new zebra("a1","white","yes");
       System.out.println(z.stripes);
    }
    public static class animal{
        String name;
        String colour;
        animal(String name,String colour)
        {
            this.name=name;
            this.colour=colour;
        }

    }
   public static class zebra extends animal{
       String stripes;
        zebra(String name,String colour,String stripes){
           super(name,colour);
           this.stripes=stripes;
        }
   }

}
