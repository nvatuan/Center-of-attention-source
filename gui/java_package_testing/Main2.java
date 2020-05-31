public class Main2 {
    public static void main (String[] args) {
        packages.one.A a1 = new packages.one.A();
        packages.two.A a2 = new packages.two.A();
        System.out.println(a1.msg);
        System.out.println(a2.msg);
    }
}