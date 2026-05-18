class A{
    public void show(){
        System.out.println("in show");
    }
}



public class annonclass {
    public static void main(String[] args) {
        
    

    A obj = new A()
    {
        public void show(){
            System.out.println("in show of annon class");
        }
    };
    obj.show();
}
     
}
