package exercise2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args){
        Date date = new Date();
        String name = "Ofir";
        List<Long> numbers = new ArrayList<>(List.of(1l, 2l, 3l));
        List<String> strings = new ArrayList<>(List.of("Taboola" , "is" , "an" , "amazing" , "company"));
        MyClass myClass = new MyClass(date , name , numbers, strings);
        MyClass myClass1 = new MyClass(date , name , numbers, strings);

        System.out.println("contains number: " + myClass.containsNumber(1l));
        myClass.removeString("an");
        System.out.println("is historic: " + myClass.isHistoric());

        System.out.println("equals: " + myClass.equals(myClass1));
    }
}
