package com.example.demo.empty;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class STREAMSQQ {

    public static void main(String[] args) {
        String s = """
						Separate odd and even numbers in a list of integers
						Given a list of integers, write a Java 8 program to separate the odd and even numbers into two separate lists.""";

        List<Integer> lst = Arrays.asList(1,2,3,1,1,1,1,22,2,2,2,2,2,27,9,8,4,5,6,7,8,9,10);

        Map<Boolean,List<Integer>> map = lst.stream().collect(Collectors.partitioningBy(n -> n%2 == 0));
        //Output : {false=[1, 3, 5, 7, 9], true=[2, 4, 6, 8, 10]}


        String s1 = """
                Remove duplicate elements from a list using Java 8 streams""";
        List<Integer> res = lst.stream().distinct().toList();
        //System.out.println( res.stream().max(Comparator.comparing(Integer::intValue)).get());
        //System.out.println(res);



        String s2 = """
                Write a Java 8 program to remove duplicate elements from a list using the stream API and lambda expressions.""";
        //  System.out.println("S2:" + lst );
        //lst.stream().max((a,b) -> a-b).ifPresent(System.out::println);

        String str = "aaavvvvbbb";
        String s3 = """
                Find the frequency of each character in a string using Java 8 streams""";
        Map<String,Long> mp;
        //Arrays.stream(str.split("")).collect(Collectors.groupingBy(c->c,Collectors.counting())).values().stream().max(Comparator.comparing(Long::longValue)).ifPresent(c -> System.out.println(c%2 ==0));

        //  str.chars().forEach(System.out::println);
        // System.out.println(mp);


        String s4= """
                Write a Java 8 program to find the frequency of each element in an array or a list using streams and collectors.""";

        Map<Integer,Long> map2 ;
        //      lst.stream().collect(Collectors.groupingBy(n -> n,Collectors.counting())).forEach((k,v) -> System.out.println("k:"+k+" -> v:"+v));


        String s5 = """
                Sort a given list of decimals in reverse order""";

        List<Double> decimals = Arrays.asList(2.2,3.1,4.2,2.2,1.3);
        float[] floats = new float[]{2.2f,3.1f,4.2f,2.2f,1.3f};
        double[] doubles = new double[floats.length];

        int[] ints = new int[]{2,3,4,2,1};
        // decimals.stream().sorted().forEach(System.out::println);
        //  Arrays.stream(decimals).boxed().sorted().forEach(System.out::println);

        String s6 = """
                Write a Java 8 program to find the frequency of each element in an array or a list using streams and collectors.""";


        List<Integer> lst2 = Arrays.asList(4,3,3,2,2,2,3,1,9,5,90,653210,4345670,5,70,75);
        // Map<Integer,Long> maaap =
        //System.out.println( new ArrayList<>(lst2.stream().collect(Collectors.groupingBy(n -> n, Collectors.counting())).entrySet()).stream().sorted((e1,e2) -> (int) (e1.getValue() - e2.getValue())).toList());

        Map<Integer,Integer> mp1 = new HashMap<>();
        mp1.put(1,2);
        mp1.put(3,4);
        mp1.put(5,6);

        new ArrayList<>(mp1.entrySet()).stream().sorted((e1,e2) -> e2.getValue()- e1.getValue());

        String s7 = """
        Given a list of strings, write a Java 8 program to join the strings with '[' as a prefix, ']' as a suffix, and ',' as a delimiter.""";

        List<String> strs = Arrays.asList("abas","","","","","","","","","","","","","","","","","","","","","","","","","","","","aaefa","aaxyz");
        String ss = strs.stream().collect(Collectors.joining("],["));
        ss = "[" +ss + "]";
        //  System.out.println(ss);


        String s8 = """
                Write a Java 8 program to print the numbers from a given list of integers that are multiples of 5.""";
        // System.out.println(lst2.stream().filter(n -> n%5 ==0).toList());


        String s9= """
                Given a list of integers, write a Java 8 program to find the maximum and minimum numbers in the list""";
        AtomicInteger min = new AtomicInteger(Integer.MAX_VALUE);
        AtomicInteger max = new AtomicInteger(Integer.MIN_VALUE);
        lst2.stream().forEach(e -> {
            if(e > max.get()) max.set(e);
            if(e < min.get()) min.set(e);
        } );
        //System.out.println(min);
        //System.out.println(max);


        String s10 = """
                Write a Java 8 program to merge two unsorted arrays into a single sorted array using the stream API.""";

        List<List<Integer>> ls = new ArrayList<>();
        ls.add(Arrays.asList(1,3,9,3,7,1));
        ls.add(Arrays.asList(2,4,12,8,10,11));
        lst = ls.stream().flatMap(l -> l.stream()).distinct().sorted().toList();
        //ls.stream().flatMap(l -> l.stream()).distinct().sorted().forEach(System.out::println);


        String s11= """
                Get the three maximum and three minimum numbers from a given list of integers""";
        //  System.out.println(lst.stream().sorted().limit(3).toList());
        //   System.out.println(lst.stream().sorted(Comparator.comparing(Integer::intValue).reversed()).limit(3).toList());

        String s12= """
                Write a Java 8 program to check if two strings are anagrams or not using the stream API and lambda expressions.""";
        String a1= "ABHIJIT";
        String a2= "ABTHIIJ";


        Arrays.stream(a1.split("")).collect(Collectors.groupingBy(c -> c,Collectors.counting()));


        String s13= """
                Write a Java 8 program to find the sum of all digits of a given number.""";
        //   System.out.println(lst);
        //  lst.stream().max(Comparator.comparing(Integer::intValue)).map(a->a*2).ifPresent(System.out::println);
        System.out.println("--------------------------------------------------------;");
        System.out.println(Optional.ofNullable(null).stream().mapToInt(i -> (int)i).sum());
        //0
        //System.out.println("--------------------------------------------------------;");
        String s14 = """
                Write a Java 8 program to find the second largest number in an integer array.""";
        // System.out.println(lst.stream().sorted(Comparator.reverseOrder()).limit(2).collect(Collectors.toCollection(LinkedList::new)).get(1));

        String s15 = """
                Sort a list of strings according to the increasing order of their length""";
        final List<String> strs1 = new ArrayList<>();
        strs1.add("abas");strs1.add("aaefa");strs1.add("aaxyz");strs1.add("     k    ");
                //Arrays.asList("abas","","","","","","","","","","","","","","","","","","","","","","","","","","","","aaefa","aaxyz");

        //strs1.stream().map(s22->s22.trim().toUpperCase()).sorted(Comparator.comparing(String::length).reversed().thenComparing(String::compareTo)).forEach(System.out::println);


        String s16 = """
                Write a Java 8 program to find the sum and average of all elements in an integer array.""";
        System.out.println(lst.stream().mapToInt(i -> i).summaryStatistics());


        String s17 = """
                 Find the common elements between two arrays""";
        List<Integer> lst11 = List.of(1,42,2,4,6,1,12);

        //lst.stream().filter(n -> lst11.contains(n)).forEach(System.out::println);


        String s18 = """
                 Write a Java 8 program to reverse each word of a given string using the stream API and lambda expressions.""";
        //System.out.println(strs);
        //strs.stream().map(n -> new StringBuilder(n).reverse().toString()).forEach(System.out::println);

        String s19 = """
                 Write a Java 8 program to reverse an integer array""";
        //System.out.println(lst);
        //  lst.stream().toList().reversed().stream().forEach(a -> System.out.print(a + ","));
        //*********************************************************************************************************
        String s20 = """
                Write a Java 8 program to find the most repeated element in an array.""";
        lst = Arrays.asList(1,2,3,1,1,1,1,22,2,2,2,2,2,27,9,8,4,5,6,7,8,9,10);
       // System.out.println(lst);
     //   lst.stream().collect(Collectors.groupingBy(c->c,Collectors.counting())).entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(1).forEach(System.out::println);

        //*********************************************************************************************************

        String s21 = """
                Write a Java 8 program to check if a given string is a palindrome using the stream API and lambda expressions.""";
        String palim = "amadaaam";
        //System.out.println(Character.isDigit(palim.charAt(0)));
        //System.out.println(Arrays.stream(palim.split("")).toList().reversed().stream().collect(Collectors.joining()).equals(palim));
        //System.out.println("a".charAt(0) - 'a');
        String s22 = """
                Write a Java 8 program to print the duplicate characters in a string.""";
        int [] chars = new int[26];
        //   System.out.println(palim);
        Arrays.stream(palim.split("")).forEach(n -> {
            int index = n.charAt(0) - 'a';
            //if(chars[index] > 0) System.out.print(n+" , ");
            chars[index]++;
        });

        String s23= """
                Write a Java 8 program to generate the Fibonacci series.""";
        AtomicInteger at1 = new AtomicInteger(0);
        AtomicInteger at2 = new AtomicInteger(1);
        strs = Arrays.asList("abas","","","","","","","","","","","","","","","","","","","aaefa","aaxyz");
        HashSet<String>  hs = strs.stream().sorted().collect(Collectors.toCollection(() -> new HashSet<>()));
      //  System.out.println(hs);


        Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() < 4)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e));
              //  .collect(Collectors.toList());

        //fibonacci series starting 0 and 1
        Stream.generate(() -> Math.random())
                .limit(10)
                .forEach(n ->{
                    System.out.println(at1.get());
                    int temp = at1.get();
                    at1.set(at2.get());
                    at2.set(temp + at2.get());
                });


        List<Order> orders = Arrays.asList(
                new Order(1, Arrays.asList(new Item("Item1", 10), new Item("Item2", 20))),
                new Order(2, Arrays.asList(new Item("Item3", 30), new Item("Item4", 40))),
                new Order(3, Arrays.asList(new Item("Item5", 50), new Item("Item6", 60)))
        );

        orders.stream().flatMap(o -> o.getItems().stream()).forEach(System.out::println);

        Map<Integer, Item> totalCostPerOrder = orders.stream()
                .collect(Collectors.toMap(
                        Order::getId,
                        order -> order.getItems().stream().min(Comparator.comparing(Item::getPrice)).get()
                ));
        System.out.println(totalCostPerOrder);



    }
}


class Item {
    String name;
    double price;

    // constructor, getters, and setters
    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}

class Order {
    int id;
    List<Item> items;

    public Order(int id, List<Item> items) {
        this.id = id;
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    // constructor, getters, and setters

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", items=" + items +
                '}';
    }
}