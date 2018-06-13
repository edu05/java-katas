package kata.exceptions;

public class MyClass {

    public static void main(String[] args) {
        callA();
    }

    private static void callA() {
        callB();
    }

    private static void callB() {
        callC();
    }

    private static void callC() {
        System.out.println("Hello World");
    }
}
