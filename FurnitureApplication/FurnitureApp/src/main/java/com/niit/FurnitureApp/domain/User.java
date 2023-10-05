package com.niit.FurnitureApp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    private String email;
    private String name;
    private String mobileNo;
    private List<Furniture> furniture;
}
