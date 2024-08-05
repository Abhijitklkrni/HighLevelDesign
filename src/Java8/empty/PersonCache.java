package com.example.demo.empty;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class PersonCache implements Runnable {

    final int timeout = 10;

    static ConcurrentHashMap<Integer,Person> cache = new ConcurrentHashMap<>();

    @Override
    public void run() {
        Set<Integer> lst = new HashSet<>();
        int[] arr = {1,2,3,4,5,6,7,8,9,10};
        List<Integer> lst1 = Arrays.stream(arr).boxed().toList();


      //  int w = lst.stream().max(Comparator.comparing(Integer::intValue)).get();

        Stack s = new Stack<>();
        while(!cache.isEmpty()){
             for(Map.Entry<Integer,Person> entry : cache.entrySet()){
                if(entry.getValue().entryTime().isBefore(LocalDateTime.now().minusSeconds(timeout))){
                    System.out.println("**********************************************");
                    System.out.println("Removing entry with id "+entry.getKey());
                    System.out.println("**********************************************");
                    cache.remove(entry.getKey());
                }
            }
        }
    }

    @PostConstruct
    public void populateCache(){
        Person p1 = new Person(1,"abc", LocalDateTime.now());
        Person p2 = new Person(2,"def", LocalDateTime.now());
        Person p3 = new Person(3,"ghi", LocalDateTime.now());
        Person p4 = new Person(4,"abc", LocalDateTime.now());
        Person p5 = new Person(5,"def", LocalDateTime.now());
        Person p6 = new Person(6,"ghi", LocalDateTime.now());
        Person p7 = new Person(7,"abc", LocalDateTime.now());
        Person p8 = new Person(8,"def", LocalDateTime.now());
        Person p9 = new Person(9,"ghi", LocalDateTime.now());
        Person p10 = new Person(10,"abc", LocalDateTime.now());
        cache.put(p1.id(),p1);cache.put(p2.id(),p2);cache.put(p3.id(),p3);cache.put(p4.id(),p4);cache.put(p5.id(),p5);
        cache.put(p6.id(),p6);cache.put(p7.id(),p7);cache.put(p8.id(),p8);cache.put(p9.id(),p9);cache.put(p10.id(),p10);
    }

    public Person getPerson(Integer id){
        return cache.get(id);
    }

    public void addToCache(Person person){
        this.cache.put(person.id(),person);
    }



}
