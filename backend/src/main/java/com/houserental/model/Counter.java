
package com.houserental.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "counters")
@Data
public class Counter {
    @Id
    private String id;
    private int value;
    

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}