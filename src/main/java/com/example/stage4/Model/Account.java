package com.example.stage4.Model;

/**
 * Represents an account with user credentials.
 * Contains information about the user's username and password.
 */
public class Account {
    private String username;
    private String password;

    /**
     * Gets the username of the account.
     * 
     * @return The username of the account.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the account.
     * 
     * @param username The username to set for the account.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password of the account.
     * 
     * @return The password of the account.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the account.
     * 
     * @param password The password to set for the account.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
