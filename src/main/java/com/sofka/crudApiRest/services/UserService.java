package com.sofka.crudApiRest.services;

import com.sofka.crudApiRest.models.UserModel;
import com.sofka.crudApiRest.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public ArrayList<UserModel> getUsers(){
        return (ArrayList<UserModel>) userRepository.findAll();
    }

    public String saveUser(UserModel user){
        try {
            userRepository.save(user);
            return "El usuario fue guardado con exito";
        }catch (Exception e) {
            return null;
        }
    }

    public Optional<UserModel> getUserById(Long id){
        return userRepository.findById(id);
    }

    public ArrayList<UserModel> getByPriority(Integer priority){
        return userRepository.findByPriority(priority);
    }

    public Optional<UserModel> getByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public boolean deleteUser(Long id){
        try{
            userRepository.deleteById(id);
            return true;
        }catch (Exception err){
            return false;
        }
    }
}
