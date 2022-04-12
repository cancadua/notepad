package sample.model;

import java.io.Serializable;

public class User implements Serializable {

    String username;
    String password;

    public User() {
    }

    public User(String user_, String pass_) {
        username = user_;
        password = pass_;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword(){
        return password;
    }

    @Override
    public String toString() {
        return "{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof User){
            User p = (User) o;
            return (this.username.equals(p.getUsername()) && this.password.equals(p.getPassword()));
        } else
            return false;
    }
}
