class A{
    int age;

    public void show(){
        System.out.println("in show");
    }
   

    class B{

        public void config(){
                System.out.println("in config");
        }

    }
}



public class InnerClass {
    public static void main(String a[]){

        A obj = new A();
        obj.show();
        A.B obj1 = obj.new B();
        obj1.config();
       


    }
    
}
