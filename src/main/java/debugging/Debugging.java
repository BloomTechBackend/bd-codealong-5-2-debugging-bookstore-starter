package debugging;

public class Debugging {

    static boolean flag = true;


    public static void main(String[] args) {
        System.out.println("Here we are in the main method.");
        for (int i=0; i<3; i++) {
            method1();
        }
        System.out.println("finished");
    }


    public static int method1() {
        System.out.println("Here we are in method1");
        int num = 1;
        int den = 2;
        System.out.println("num = " + num);
        System.out.println("den = " + den);
        int div = num / den;

        return method2();
    }

    public static int method2() {
        System.out.println("Here we are in method2");
        if (!flag)
            return 1;
        return 2;
    }
}
