package org.example;

import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void newlyCreatedAccountShouldNotBeActive() {

        //given
        Account newAccount = new Account();

        //then
        assertFalse(newAccount.isActive());
        assertThat(newAccount.isActive()).isFalse();
    }

    @Test
    void activatedAccountShouldHaveActiveFlagSet() {

        //given
        Account newAccount = new Account();

        //when
        newAccount.active();

        //then
        assertTrue(newAccount.isActive());
        assertThat(newAccount.isActive()).isTrue();
    }

    @Test
    void newlyCreatedAccountShouldNotHaveDefaultDeliveryCreatedAddressSet() {

        //given
        Account account = new Account();

        //when
        Address address = account.getDefaultDelivaryAddress();

        //then
        assertNull(address);
        assertThat(address).isNull();
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
        assertThat(address).isNotNull();
    }

}
