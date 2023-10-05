package com.niit.FurnitureApp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class Furniture {
    @Id
    private int id;
    private String furnitureName;
    private String quality;
    private String url;
    private int price;
    private String color;
    private String description;
}
