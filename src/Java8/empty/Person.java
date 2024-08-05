package com.example.demo.empty;

import java.time.LocalDateTime;

public record Person(Integer id, String name, LocalDateTime entryTime) {
}
