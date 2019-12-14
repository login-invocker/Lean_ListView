package com.invocker.invocker_math.Model;

public class UserScope {
    private String name;
    private int scope;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getScope() {
        return scope;
    }

    public void setScope(int scope) {
        this.scope = scope;
    }

    public UserScope() {
    }

    public UserScope(String name, int scope) {
        this.name = name;
        this.scope = scope;
    }
}