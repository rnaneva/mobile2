package com.example.mobile2.session;

import com.example.mobile2.model.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Getter
@Setter
@NoArgsConstructor
@SessionScope
@Component
public class CurrentUser {

    private long id;
    private String username;

    public boolean isLogged(){
        return this.id > 0;
    }

    public void logIn(User user){
        this.id = user.getId();
        this.username = user.getUsername();
    }

    public void logOut(){
        this.id = 0;
        this.username = null;
    }


}
