package com.loco;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Objects;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);

        ArrayList<String> list2 = new ArrayList<>();
        list2.add("A");
        list2.add("B");

        ArrayList<ArrayList<Object>> cartesianProduct = new ArrayList<>();

        for (int i = 0; i < list1.size(); i++) {
            for (int j = 0; j < list2.size(); j++) {
                ArrayList<Object> pair = new ArrayList<>();
                pair.add(list1.get(i));
                pair.add(list2.get(j));
                cartesianProduct.add(pair);
            }
        }

        // 输出笛卡尔积
        for (ArrayList<Object> pair : cartesianProduct) {
            System.out.println(pair);
        }
    }
//    public static void main( String[] args ) {
//        App app = new App();
////        System.out.println( app.getStr());
//        app.forTest();
//    }

    public String getStr(){

        return """
                123
                123
                """;
    }

    public void forTest(){
        ArrayList<String> strings = new ArrayList<>();
        strings.add("001");
        strings.add("002");
        strings.add("003");

        ListIterator li = strings.listIterator();
        while(li.hasNext()){
            Object obj = li.next();
            if(obj.equals("001")){
                li.set("java009");
            }
        }


        strings.forEach(System.out::println);

    }
}
