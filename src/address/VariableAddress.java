package address;

/**
 * Created by cachee on 2017/6/2.
 */
public class VariableAddress {
    public static void main(String[] args) {
        String a = "123";
        changeString(a);
        System.out.println(a);

        Obj obj = new Obj();
        obj.setA("123");
        changeObj(obj);
        System.out.println(obj.getA());
    }

    static void changeString(String b) {
        b = "abc";
    }

    static void changeObj(Obj obj) {
        obj.setA("abc");
    }

}

class Obj {
    private String a;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }
}
