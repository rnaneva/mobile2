package com.example.mobile2.service;

import com.example.mobile2.model.binding.UserLoginBindingModel;
import com.example.mobile2.model.binding.UserRegisterBindingModel;
import com.example.mobile2.model.entities.Enums.RoleEnum;
import com.example.mobile2.model.entities.Role;
import com.example.mobile2.model.entities.User;
import com.example.mobile2.repository.UserRepository;
import com.example.mobile2.session.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private CurrentUser currentUser;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public User findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password).orElse(null);
    }

    public void userRegister(UserRegisterBindingModel userRegisterBindingModel) {

        User user = modelMapper.map(userRegisterBindingModel, User.class);
        user.setCreated(LocalDateTime.now());
        user.setRole(new Role(RoleEnum.USER));

        userRepository.save(user);

    }

    public boolean loginUser(UserLoginBindingModel userLoginBindingModel) {

        User user = findByUsernameAndPassword(userLoginBindingModel.getUsername(), userLoginBindingModel.getPassword());
        if(user == null){
            return false;
        }
        user.setIsActive(true);
        currentUser.logIn(user);
        return true;
    }

    public void logout(){
        if(this.currentUser.isLogged()){
            User user = userRepository.findById(currentUser.getId()).get();
            user.setIsActive(false);
            currentUser.logOut();
        }
    }


}

