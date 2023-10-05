package com.niit.FurnitureApp.service;

import com.niit.FurnitureApp.domain.Furniture;
import com.niit.FurnitureApp.domain.User;
import com.niit.FurnitureApp.repository.FurnitureRepository;
import com.niit.FurnitureApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService{
    private UserRepository userRepository;
    private FurnitureRepository furnitureRepository;

@Autowired
    public UserServiceImpl(UserRepository userRepository, FurnitureRepository furnitureRepository) {
        this.userRepository = userRepository;
        this.furnitureRepository = furnitureRepository;
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Furniture addFurniture(Furniture furniture) {

    return furnitureRepository.save(furniture);
    }

    @Override
    public Furniture updateDetails(Furniture furniture, int id) {
        Optional<Furniture> checkFurniture = furnitureRepository.findById(id);
        if (checkFurniture.isEmpty()){
            return null;
        }
        Furniture existingFurniture = checkFurniture.get();
        if (furniture.getFurnitureName() != null) {
            existingFurniture.setFurnitureName(furniture.getFurnitureName());
        }
        if (furniture.getFurnitureName() != null) {
            existingFurniture.setDescription(furniture.getDescription());
        }
        if (furniture.getColor() != null){
            existingFurniture.setColor(furniture.getColor());
        }
        if (furniture.getPrice() != 0) {
            existingFurniture.setPrice(furniture.getPrice());
        }
        if (furniture.getQuality() != null){
            existingFurniture.setQuality(furniture.getQuality());
        }
        if (furniture.getUrl() != null){
            existingFurniture.setUrl(furniture.getUrl());
        }
        return furnitureRepository.save(existingFurniture);
    }

    @Override
    public boolean deleteDetails(int id) {
        if (furnitureRepository.findById(id).isEmpty()) {
            return false;
        }
        else{
            furnitureRepository.deleteById(id);
            return true;
        }
    }
    @Override
    public boolean deleteCartItemById(String email,int id) {
        Optional<User> userOptional = userRepository.findById(email);
        if (!userOptional.isPresent()) {
            return false;
        }
        User user = userOptional.get();
        Optional<Furniture> optionalFurniture = furnitureRepository.findById(id);
        if (!optionalFurniture.isPresent()) {
            return false;
        }
        Furniture furniture = optionalFurniture.get();
        user.getFurniture().remove(furniture);
        userRepository.save(user);
        return true;
    }

    @Override
    public List<Furniture> viewDetails() {

    return furnitureRepository.findAll();
    }
    @Override
    public List<Furniture> FindAllCartItemByEmail(String email) {
        Optional<User> userOptional = userRepository.findById(email);
        if (!userOptional.isPresent()) {
            return null;
        }
        User user = userOptional.get();
        return user.getFurniture();
    }

    @Override
    public Furniture getProductById(int id) {

    return furnitureRepository.findById(id).get();
    }

    @Override
    public User addToCart(String email, int furnitureId){
        Optional<User> userOptional = userRepository.findById(email);
        if (!userOptional.isPresent()) {
            return null;
        }
        User user = userOptional.get();
        Optional<Furniture> furnitureOptional = furnitureRepository.findById(furnitureId);
        if (!furnitureOptional.isPresent()) {
            return null;
        }
        Furniture furniture = furnitureOptional.get();
        user.getFurniture().add(furniture);
        return userRepository.save(user);
    }

}
