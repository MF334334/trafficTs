package com.example.trafficts;

import java.util.ArrayList;
import java.util.Scanner;

public class task {

//    public static void main(String[] args) {
//        Print print = new Print();
//        print.print(5);
//        print.print(2, 5);
//        print.print(2, 10, 2);
//    }

//    static class Student {
//        public String NAME;
//        private String SEX;
//        private int AGE;
//
//        public String getNAME() {
//            return NAME;
//        }
//
//        public void setNAME(String NAME) {
//            this.NAME = NAME;
//        }
//
//        public String getSEX() {
//            return SEX;
//        }
//
//        public void setSEX(String SEX) {
//            this.SEX = SEX;
//        }
//
//        public int getAGE() {
//            return AGE;
//        }
//
//        public void setAGE(int AGE) {
//            this.AGE = AGE;
//        }
//
//        private void study() {
//            System.out.println("studying....");
//        }
//
//    }

//    static class Computer {
//        private String KEY;
//        private String SCREEN;
//        private String MOUSE;
//        private String TIME;
//
//        public String getKEY() {
//            return KEY;
//        }
//
//        public void setKEY(String KEY) {
//            this.KEY = KEY;
//        }
//
//        public String getSCREEN() {
//            return SCREEN;
//        }
//
//        public void setSCREEN(String SCREEN) {
//            this.SCREEN = SCREEN;
//        }
//
//        public String getMOUSE() {
//            return MOUSE;
//        }
//
//        public void setMOUSE(String MOUSE) {
//            this.MOUSE = MOUSE;
//        }
//
//        public String getTIME() {
//            return TIME;
//        }
//
//        public void setTIME(String TIME) {
//            this.TIME = TIME;
//        }
//    }

    static class Purse {
        private int MONEY;

        public Purse(int MONEY) {
            this.MONEY = MONEY;
        }

        public Purse() {
            this.MONEY = 0;
        }

        private void SaveMoney(int COUNT) {
            this.MONEY = this.MONEY + COUNT;
            System.out.println("当前余额：" + this.MONEY);
        }

        private void WithDraw(int COUNT) {
            this.MONEY = this.MONEY - COUNT;
            System.out.println("当前余额：" + this.MONEY);
        }
    }

    static class Print {

        private void print(int X) {
            if (X > 0) {
                for (int i = 1; i <= X; i++) {
                    System.out.println("打印：" + i);
                }
            } else {
                System.out.println("输入非法，应大于0");
            }
        }

        private void print(int X, int Y) {
            if (X > 0 && Y > X) {
                for (; X <= Y; X++) {
                    System.out.println("打印：" + X);
                }
            } else {
                System.out.println("输入非法，应大于0,Y>X");
            }
        }

        private void print(int X, int Y, int S) {
            if (X > 0 && Y > X) {
                for (; X <= Y; X += S) {
                    System.out.println("打印：" + X);
                }
            } else {
                System.out.println("输入非法，应大于0,Y>X");
            }
        }
    }


    public static class Father_baby extends Father {
        String num = "baby num";

        public void sound() {
            System.out.println("baby sound..");//子类自己的实现
//            super.sound();     //调用父类的sound方法。
            System.out.println(num);    //子类中的num
        }
    }

    public static class Father {
        int num;

        public void sound() {
            System.out.println("father sound...");
        }
    }

    public static class Student {
        String num;
        String name;
        String sex;
        String birth;

        public void init(String num, String name, String sex, String birth) {
            this.num = num;
            this.name = name;
            this.sex = sex;
            this.birth = birth;
        }

        public void display() {
            System.out.println(
                    "num: " + num + "name: " + name + "sex: " + sex + "birth: " + birth
            );
        }

        public void modify(String num, String name, String sex, String birth) {
            this.num = num;
            this.name = name;
            this.sex = sex;
            this.birth = birth;
        }

    }

    public static class Graduate extends Student {
        String subject;
        String adviser;

        public Graduate(String subject, String adviser) {
            this.subject = subject;
            this.adviser = adviser;
        }

        public void display() {
            System.out.println("我是学生->研究生");
        }


        public void modify(String num, String name, String sex, String birth, String subject, String adviser) {
            this.num = num;
            this.name = name;
            this.sex = sex;
            this.birth = birth;
            this.subject = subject;
            this.adviser = adviser;
        }

    }


    public static class Monkey {
        String s;

        public Monkey(String s) {
            this.s = s;
        }

        public void speak() {
            System.out.println("伊呀呀呀。。。");
        }

        ;
    }

    public static class people extends Monkey {

        public people(String s) {
            super(s);
        }

        public void speak() {
            System.out.println("小样的，不错嘛 会说话了");
        }

        public void think() {
            System.out.println("别说话 认真思考");
        }
    }


    public static class Cat {
        public void speak() {
            System.out.println("maio miao ~");
        }
    }

    public static class Dog {
        public void bark() {
            System.out.println("wang wang ~");
        }
    }

    public static class Girl_first extends Cat {

    }

    public static class Girl_Second extends Dog {

    }


    abstract class animals {
        public void eat() {
            System.out.println("eating ....");
        }

        public abstract void sound();
    }

    interface Bounceable {
        int LOW_GRAVITY = 1;
        int MEDIUM_GRAVITY = 2;
        int HIGH_GRAVITY = 3;
        int LOW_BOUNCE = 4;
        int MEDIUM_BOUNCE = 8;
        int HIGH_BOUNCE = 12;

        public static final int LEG = 0;

        public void bounce();

        void setBounFactor(int bf);
    }


    public class Ball implements Bounceable {

        @Override
        public void bounce() {
            System.out.println(MEDIUM_BOUNCE);
        }

        @Override
        public void setBounFactor(int bf) {

        }
    }


    //内部类
    public static class Outer {
        Inner inner = new Inner();


        private void mySpeak() {
            inner.speak();
        }

        class Inner {
            private int InX = 5;

            private void speak() {
                System.out.println("I am inner class.." + InX);
            }

        }
    }

    abstract static class animal {
        public abstract void sund();
    }

    interface I {
        public void show();
    }

//    public static void main(String[] args) {
//        animal animal = new animal() {
//            @Override
//            public void sund() {
//                //匿名类继承抽象方法
//                System.out.println("匿名");
//            }
//        };
//        final int m = 0;
//        //匿名内部类实现接口
//
//        I i = new I() {
//            @Override
//            public void show() {
//                System.out.println(m);
//            }
//        };
//        //局部内部类
//        class P {
//
//        }
//        animal.sund();
//        Outer outer = new Outer();
//        outer.mySpeak();
//
//
//        Outer outer1 = new Outer() {
//            //重写方法
//            public void mySpeak() {
//                System.out.println("新方法");
//            }
//        };
//        outer1.mySpeak();
//    }


    interface ComputeTotalSales {
        public double totalSalesByYear();
    }

    static class Televison implements ComputeTotalSales {
        private double sales;

        public Televison(double sales) {
            this.sales = sales;
        }

        @Override
        public double totalSalesByYear() {
            return sales;
        }
    }

    static class Computer implements ComputeTotalSales {
        private double sales;

        public Computer(double sales) {
            this.sales = sales;
        }

        @Override
        public double totalSalesByYear() {
            return this.sales;
        }

    }

    static class Mobile implements ComputeTotalSales {
        private double sales;

        public Mobile(double sales) {
            this.sales = sales;
        }

        @Override
        public double totalSalesByYear() {
            return this.sales;
        }

    }

    static class Shop {
        private ArrayList<ComputeTotalSales> list;
        Object[] ComputeTotalSales = new Object[3];

        public Shop(ComputeTotalSales c) {
            list.add(c);
        }


        private void showYearSales() {
            double totalSales = 0;
            for (int i = 0; i < list.size(); i++) {
                totalSales += list.get(i).totalSalesByYear();
            }
            System.out.println("totalSalesOfYearis:" + totalSales);
        }

    }

    public interface Operation {
        public void getArea();

        public void getPerimeter();

        public void showArea();

        public void showPerimeter();
    }

    static class Circle implements Operation {
        private double radius;
        private double area;
        private double perimeter;

        public Circle(double radius) {
            this.radius = radius;
        }

        @Override
        public void getArea() {
            this.area = Math.PI * radius * radius;
        }

        @Override
        public void getPerimeter() {
            this.perimeter = Math.PI * radius * 2;
        }

        @Override
        public void showArea() {
            System.out.println("圆的面积" + this.area);
        }

        @Override
        public void showPerimeter() {
            System.out.println("圆的周长" + this.perimeter);
        }
    }

    static class Rectangle implements Operation {
        private double width; //宽
        private double height; //高
        private double area; //面积
        private double perimeter; //周长

        public Rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }

        @Override
        public void getArea() {
            this.area = width * height;
        }

        @Override
        public void getPerimeter() {
            this.perimeter = (width + height) * 2;
        }

        @Override
        public void showArea() {
            System.out.println("the Area is:" + this.area);
        }

        @Override
        public void showPerimeter() {
            System.out.println("the Perimeter is:" + this.perimeter);
        }
    }


    public abstract class GeometricObject {
        private String coloeOfShape;

        public abstract double getArea();

        public abstract double getPerimeter();

        public abstract void showArea();

        public abstract void showPerimeter();

        public GeometricObject(String coloeOfShape) {
            this.coloeOfShape = coloeOfShape;
        }

    }

    //三角形类
    public class Triangle extends GeometricObject {
        private double edge1;
        private double edge2;
        private double edge3;

        public Triangle(double edge1, double edge2, double edge3, String coloeOfShape) {
            super(coloeOfShape);
            this.edge1 = edge1;
            this.edge2 = edge2;
            this.edge3 = edge3;
        }

        @Override
        public double getArea() {
            Double p = getPerimeter() / 2;
            double S = Math.sqrt(p * (p - edge1) * (p - edge2) * (p - edge3));
            return S;
        }

        @Override
        public double getPerimeter() {
            return edge1 + edge2 + edge3;
        }

        @Override
        public void showArea() {
            System.out.println("the Area is :" + getArea());
        }

        @Override
        public void showPerimeter() {
            System.out.println("the Perimeter is :" + getPerimeter());
        }
    }

    //矩形类
    public class Rectangular extends GeometricObject {
        private double width;
        private double height;

        public Rectangular(String color, double width, double height, String colorOfShape) {
            super(colorOfShape);
            this.width = width;
            this.height = height;
        }

        @Override
        public double getArea() {
            return height * width;
        }

        @Override
        public double getPerimeter() {
            return (height + width) * 2;
        }

        @Override
        public void showArea() {
            System.out.println("the Area is :" + (width * height));
        }

        @Override
        public void showPerimeter() {
            System.out.println("the perimeter is :" + 2 * (width + height));
        }
    }


    static class StudentInfo {
        private long id;
        private String name;
        private String sex;
        private int age;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public StudentInfo(long id, String name, String sex, int age) {
            this.id = id;
            this.name = name;
            this.sex = sex;
            this.age = age;
        }
    }

    static class StudentScore extends StudentInfo {
        private int totalCourse;
        private String[] Course;
        private int[] Scores;

        public StudentScore(long id, String name, String sex, int age) {
            super(id, name, sex, age);
        }

        public StudentScore(long id, String name, String sex, int age, int totalCourse, String[] course, int[] scores) {
            super(id, name, sex, age);
            this.totalCourse = totalCourse;
            Course = course;
            Scores = scores;
        }

        public void showScores() {
            System.out.println(super.getName() + "同学");
            for (int i = 0; i < Course.length; i++) {
                System.out.print(Course[i] + "的成绩为:" + Scores[i]);
            }

        }

    }

    interface call {
        public void jiao();
    }

    static class MyCat implements call {
        private static int ageOfCat;
        private static int count = 0;

        public MyCat(int ageOfCat) {
            this.ageOfCat = ageOfCat;
            count++;
        }

        @Override
        public void jiao() {
            System.out.println("miao~");
        }

        static void showCatSum() {
            System.out.println("the sum is" + count);
        }
    }

    public static void main(String[] args) throws EmptyStringException, IncludeNumberException {
        Scanner sc = new Scanner(System.in);
        String X = sc.nextLine();
        if (X.equals("")) {
            throw new EmptyStringException("读入了空串");
        } else if (isContainNum(X)) {
            throw new IncludeNumberException("包含数字");
        }
    }

    public static boolean isContainNum(String X) {
        for (int i = 0; i < 10; i++) {
            if (!X.contains(String.valueOf(i))) {
                //未匹配到数字
                return false;
            }
        }
        return true;
    }


}
