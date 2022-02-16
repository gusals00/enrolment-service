package jpa.enrolment;

public class Hello {
    public static void main(String[] args) {
        test();
    }

    public static void test(Item... item){
        System.out.println(1);
    }

    static class Item{}
}
