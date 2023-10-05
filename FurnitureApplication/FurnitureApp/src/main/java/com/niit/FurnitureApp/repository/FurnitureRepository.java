package com.niit.FurnitureApp.repository;

import com.niit.FurnitureApp.domain.Furniture;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface FurnitureRepository extends MongoRepository<Furniture,Integer> {
}
