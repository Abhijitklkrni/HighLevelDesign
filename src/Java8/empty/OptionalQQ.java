package com.example.demo.empty;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OptionalQQ {
    public static void main(String[] args) {
        String s = "null";
      //  Optional.of(null);
        Optional<String> name = Optional.ofNullable(null);
     //   name.ifPresentOrElse(System.out::println, () -> name.orElseThrow());

        Optional<String> name1 = Optional.ofNullable("null");
        name1.
                ifPresentOrElse(System.out::println, () -> name1.orElseThrow());
        List<String> ss = name1.stream().collect(Collectors.toUnmodifiableList());
        System.out.println(ss);
    }
}
