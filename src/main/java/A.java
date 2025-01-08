public class A {
    public String display() {
        return "A";
    }
}

class B extends A {
    public String display() {
        return "B";
    }

    public static void main(String[] args) {
        A obj = new B();
        String result = obj.display();
        System.out.println("Method : " +result);
    }
}


