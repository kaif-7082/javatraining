public class methodoverloading {
    public static void main(String[] args) {
        cost cp=new cost();
        cp.set(0, 0);
        System.out.println(cp.a);
        cost cp2=new cost();
        cp2.set(1,0,3);
        System.out.println(cp2.c);
    }

    public static class cost{
        int a,b,c;
        public  void set(int c1,int c2)
        {
            a=c1;
            b=c2;
        } 
        public  void set(int c1,int c2,int c3)
        {
            a=c1;
            b=c2;
            c=c3;
        } 
    }
    
}
