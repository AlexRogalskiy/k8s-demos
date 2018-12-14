public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello World!");

        while (true) {
            System.out.println("Hello from Java main()!");
            System.out.println("this is a change! random number -> " + Math.round(Math.random()*10));
            Thread.sleep(1000);
        }
    }

}

class NoChangesClass {
    private String value;
}
