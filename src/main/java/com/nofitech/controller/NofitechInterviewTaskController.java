package com.nofitech.controller;

import com.nofitech.service.IScheduledReadFileService;
import com.nofitech.service.IWritePropertiesFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

@RestController("/api")
@RequiredArgsConstructor
public class NofitechInterviewTaskController {
    private final AtomicInteger counter = new AtomicInteger(1);
    private final IScheduledReadFileService iScheduledReadFileService;
    private final IWritePropertiesFileService iWritePropertiesFileService;

    @GetMapping("/getNext")
    public ResponseEntity<Integer> generateSequence() {
        return ResponseEntity.ok(counter.getAndIncrement());
    }

    @GetMapping("/getValue/{key}")
    public ResponseEntity<String> getValue(@PathVariable String key) {
        return ResponseEntity.ok().body(iScheduledReadFileService.getProperty(key));
    }

    @PutMapping("/setValue/{key}/{value}")
    public ResponseEntity<Void> putValue(@PathVariable String key, @PathVariable String value) throws IOException {
        iWritePropertiesFileService.writePropertiesFile(key, value);
        return ResponseEntity.ok().build();
    }
}
