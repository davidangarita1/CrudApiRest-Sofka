package com.sofka.crudApiRest.controllers;

import com.sofka.crudApiRest.models.UserModel;
import com.sofka.crudApiRest.services.UserService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ArrayList<UserModel> getUsers() {
        return userService.getUsers();
    }

    @PostMapping()
    public String saveUsers(@RequestBody UserModel user) {
        return this.userService.saveUser(user);
    }

    @GetMapping(path = "/{id}")
    public Optional<UserModel> getUserById(@PathVariable("id") Long id) {
        return this.userService.getUserById(id);
    }

    @GetMapping("/query")
    public ArrayList<UserModel> getUserByPriority(@RequestParam("priority") Integer priority) {
        return this.userService.getByPriority(priority);
    }

    @GetMapping("/emails")
    public UserModel getByEmails(@RequestBody UserModel user) {
        UserModel userModel = userService.getByEmailParam(user);
        if(userModel != null) {
            return this.userService.getByEmailParam(user);
        } else {
            return null;
        }

    }

    @PutMapping("/setUser")
    public String updateUser(@RequestBody UserModel user) {
        boolean userModel = userService.updateUser(user);
        if(userModel != false){
            this.userService.updateUser(user);
            return "El usuario fue actualizado con exito";
        } else {
            return "El usuario no fue actualizado";
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id){
        if (userService.deleteUser(id))
            return new ResponseEntity<String>("El usuario ha sido eliminado", HttpStatus.OK);
        return new ResponseEntity<String>("No se ha encontrado el usuario", HttpStatus.BAD_REQUEST);
    }
}
