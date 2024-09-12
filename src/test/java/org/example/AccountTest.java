package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void myTest() {
        Account newAccount = new Account();
        assertFalse(newAccount.isActive(), "Check if new account is not active");
    }

    @Test
    void myTest2() {
        Account newAccount = new Account();
        assertFalse(newAccount.isActive());
        newAccount.active();
        assertTrue(newAccount.isActive());
    }

    @Test
    void newlyCreatedAccountShouldNotHaveDefaultDeliveryCreatedAddressSet() {

        //given
        Account account = new Account();

        //when
        Address address = account.getDefaultDelivaryAddress();

        //then
        assertNull(address);
    }

    @Test
    void defaultDeliveryAddressShouldNotBeNullAfterBeingSet() {

        //given
        Address address = new Address("Poznanska", "194c");
        Account account = new Account();
        account.setDefaultDelivaryAddress(address);

        //when
        Address defaultAddress = account.getDefaultDelivaryAddress();

        //then
        assertNotNull(defaultAddress);
    }

}
