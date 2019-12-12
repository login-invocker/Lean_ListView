package com.invocker.invocker_math.Model;

import java.util.List;

public class ListUser{
    private String name;

    public ListUser(String name, List<UserScope> userScopes) {
        this.name = name;
        this.userScopes = userScopes;
    }

    public List<UserScope> getUserScopes() {
        return userScopes;
    }

    public void setUserScopes(List<UserScope> userScopes) {
        this.userScopes = userScopes;
    }

    private List<UserScope> userScopes;
}
