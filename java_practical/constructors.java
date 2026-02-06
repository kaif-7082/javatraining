public class constructors {
    public static void main(String[] args) {
        Car c1=new Car();
        Car c2=new Car(1, 2, 3);
    }

    public static class Car{
        int a,b,c;
        Car(){
            System.out.println("non parametrized constructor");
        }
        Car(int a,int b,int c){
            this.a=a;
            this.b=b;
            this.c=c;
        }

    }
}
