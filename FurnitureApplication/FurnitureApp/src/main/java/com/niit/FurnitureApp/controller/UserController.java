package com.niit.FurnitureApp.controller;

import com.niit.FurnitureApp.domain.Furniture;
import com.niit.FurnitureApp.domain.User;
import com.niit.FurnitureApp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/product/v1")
public class UserController {
    @Autowired
    private IUserService iUserService;
    @PostMapping("/add-user")
    public ResponseEntity addUser(@RequestBody User user)
    {
        return new ResponseEntity(iUserService.addUser(user), HttpStatus.OK);
    }
    @PostMapping("/add-product")
    public ResponseEntity addFurniture(@RequestBody Furniture furniture, HttpServletRequest httpServletRequest){
        if(httpServletRequest.getAttribute("attr2") != null && httpServletRequest.getAttribute("attr2").equals("Admin_Role"))
        {
            System.out.println("attr2 value: " + httpServletRequest.getAttribute("attr2"));
            return new ResponseEntity<>(iUserService.addFurniture(furniture), HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity("You are Not Authorized to access this link...",HttpStatus.BAD_REQUEST);
        }

    }
    @PutMapping("/update-product/{id}")
    public ResponseEntity updateDetails(HttpServletRequest httpServletRequest,@RequestBody Furniture furniture,@PathVariable int id){
        String role = (String) httpServletRequest.getAttribute("attr2");
        if(role != null && role.equals("Admin_Role")) {
            return new ResponseEntity<>(iUserService.updateDetails(furniture,id), HttpStatus.OK);
        }
        else {
            return new ResponseEntity("You are Not Authorized to access this link...",HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete-product/{id}")
    public ResponseEntity deleteDetails(HttpServletRequest httpServletRequest,@PathVariable int id){
        String role = (String) httpServletRequest.getAttribute("attr2");
        if(role != null && role.equals("Admin_Role")) {
            return new ResponseEntity<>(iUserService.deleteDetails(id), HttpStatus.OK);
        }
        else {
            return new ResponseEntity("You are Not Authorized to access this link...",HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/get-product")
    public ResponseEntity getAllProduct()
    {
        return new ResponseEntity(iUserService.viewDetails(), HttpStatus.OK);
    }
    @PostMapping("/addToCart/{email}/{id}")
    public ResponseEntity<?> addToCart(@PathVariable String email, @PathVariable int id,HttpServletRequest httpServletRequest) {
        String role = (String) httpServletRequest.getAttribute("attr2");
        if(role != null && role.equals("User_Role")) {
            User cartItems = iUserService.addToCart(email, id);
            if (cartItems == null) {
                return ResponseEntity.badRequest().body("Invalid email or furniture ID");
            }
            return new ResponseEntity<>(cartItems, HttpStatus.OK);
        }
        else {
            return new ResponseEntity("You are Not Authorized to access this link...",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getProductById/{id}")
    public ResponseEntity getProductById(@PathVariable int id)
    {
        return new ResponseEntity(iUserService.getProductById(id), HttpStatus.OK);
    }
    @GetMapping("/getCartItems/{email}")
    public ResponseEntity FindAllCartItem(@PathVariable String email)
    {
        return new ResponseEntity(iUserService.FindAllCartItemByEmail(email), HttpStatus.OK);
    }

    @DeleteMapping("/deleteById-product/{email}/{id}")
    public ResponseEntity deleteByFurnitureId(@PathVariable int id,@PathVariable String email){
        return new ResponseEntity<>(iUserService.deleteCartItemById(email,id),HttpStatus.OK);
    }
}
