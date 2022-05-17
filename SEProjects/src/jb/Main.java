package jb;

import java.lang.reflect.Field;

/**
 * @Description
 * @Date 2022/5/17 17:15
 * @Created by jalivv
 */
public class Main {
    public static void main(String[] args) throws Exception {
        A a = new A("a", 1, false);
        B b = new B("b", 2, true);
        Class<?> aClazz = Class.forName(A.class.getName());
        Class<?> bClazz = Class.forName(B.class.getName());
        AandB aandB = new AandB();
        Class<?> abClazz = Class.forName(aandB.getClass().getName());
        for (Field f : aClazz.getDeclaredFields()) {
            f.setAccessible(true);
            Field abClazzDeclaredField = abClazz.getDeclaredField( f.getName());
            abClazzDeclaredField.setAccessible(true);
            abClazzDeclaredField.set(aandB, f.get(a));
        }
        for (Field f : bClazz.getDeclaredFields()) {
            f.setAccessible(true);
            Field abClazzDeclaredField = abClazz.getDeclaredField( f.getName());
            abClazzDeclaredField.setAccessible(true);
            abClazzDeclaredField.set(aandB, f.get(b));
        }
        System.out.println(aandB);
    }
}

class A {
    private String f1;
    private Integer f2;
    private Boolean f3;

    public A(String f1, Integer f2, Boolean f3) {
        this.f1 = f1;
        this.f2 = f2;
        this.f3 = f3;
    }

    public String getF1() {
        return f1;
    }

    public void setF1(String f1) {
        this.f1 = f1;
    }

    public Integer getF2() {
        return f2;
    }

    public void setF2(Integer f2) {
        this.f2 = f2;
    }

    public Boolean getF3() {
        return f3;
    }

    public void setF3(Boolean f3) {
        this.f3 = f3;
    }
}

class B {
    private String f4;
    private Integer f5;
    private Boolean f6;

    public B(String f4, Integer f5, Boolean f6) {
        this.f4 = f4;
        this.f5 = f5;
        this.f6 = f6;
    }

    public String getF4() {
        return f4;
    }

    public void setF4(String f4) {
        this.f4 = f4;
    }

    public Integer getF5() {
        return f5;
    }

    public void setF5(Integer f5) {
        this.f5 = f5;
    }

    public Boolean getF6() {
        return f6;
    }

    public void setF6(Boolean f6) {
        this.f6 = f6;
    }
}


class AandB {
    private String f1;
    private Integer f2;
    private Boolean f3;
    private String f4;
    private Integer f5;
    private Boolean f6;

    @Override
    public String toString() {
        return "AandB{" +
                "f1='" + f1 + '\'' +
                ", f2=" + f2 +
                ", f3=" + f3 +
                ", f4='" + f4 + '\'' +
                ", f5=" + f5 +
                ", f6=" + f6 +
                '}';
    }

    public String getF1() {
        return f1;
    }

    public void setF1(String f1) {
        this.f1 = f1;
    }

    public Integer getF2() {
        return f2;
    }

    public void setF2(Integer f2) {
        this.f2 = f2;
    }

    public Boolean getF3() {
        return f3;
    }

    public void setF3(Boolean f3) {
        this.f3 = f3;
    }

    public String getF4() {
        return f4;
    }

    public void setF4(String f4) {
        this.f4 = f4;
    }

    public Integer getF5() {
        return f5;
    }

    public void setF5(Integer f5) {
        this.f5 = f5;
    }

    public Boolean getF6() {
        return f6;
    }

    public void setF6(Boolean f6) {
        this.f6 = f6;
    }
}
