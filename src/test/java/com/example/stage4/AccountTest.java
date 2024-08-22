package com.example.stage4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.example.stage4.Model.Account;


public class AccountTest {

    @Test
    public void testUsernameGetterAndSetter() {
        Account account = new Account();
        account.setUsername("testUser");
        assertEquals("testUser", account.getUsername());
    }

    @Test
    public void testPasswordGetterAndSetter() {
        Account account = new Account();
        account.setPassword("testPass");
        assertEquals("testPass", account.getPassword());
    }
}
