package com.sofka.crudApiRest.controllers;

import com.sofka.crudApiRest.models.UserModel;
import com.sofka.crudApiRest.services.UserService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PostUpdate;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping()
    public ArrayList<UserModel> getUsers(){
        return userService.getUsers();
    }

    @PostMapping()
    public String saveUsers(@RequestBody UserModel user){
        return this.userService.saveUser(user);
    }

    @GetMapping( path = "/{id}")
    public Optional<UserModel> getUserById(@PathVariable("id") Long id){
        return this.userService.getUserById(id);
    }

    @GetMapping("/query")
    public ArrayList<UserModel> getUserByPriority(@RequestParam("priority") Integer priority){
        return this.userService.getByPriority(priority);
    }

    @GetMapping("/{email}")
    public Optional<UserModel> getByEmail(@RequestParam("email") String email){
        return this.userService.getByEmail(email);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") Long id){
        boolean ok = this.userService.deleteUser(id);
        if (ok){
            return "Se eliminó el usuario con id " + id;
        }else{
            return "No pudp eliminar el usuario con id " +id;
        }
    }
}
