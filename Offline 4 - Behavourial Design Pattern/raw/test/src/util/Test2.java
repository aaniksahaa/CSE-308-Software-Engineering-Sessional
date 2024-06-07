package src.util;

public class Test2 extends class1{
    public static void main(String[] args) {

    }
    void f5(){

    }
    public void f2(){

    }
}

interface interface1 {
    default void fl () {
    }
    void f2();
}
interface interface2 {
    void f3();
    void f4();
}
abstract class class1 implements interface1 {
    abstract void f5();
    final void f6() {
    }
}
