package com.niit.FurnitureApp.service;

import com.niit.FurnitureApp.domain.Furniture;
import com.niit.FurnitureApp.domain.User;

import java.util.List;

public interface IUserService {
    public User addUser(User user);
    public Furniture addFurniture(Furniture furniture);
    public Furniture updateDetails(Furniture furniture,int id);
    public boolean deleteDetails(int id);
    public List<Furniture> viewDetails();
//    public List<CartItem> addToCart(String email, int id);
    public Furniture getProductById(int id);
    public List<Furniture> FindAllCartItemByEmail(String email);
    public boolean deleteCartItemById(String email,int id);
    public User addToCart(String email, int furnitureId);
}
