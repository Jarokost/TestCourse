package org.example;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void newlyCreatedAccountShouldNotBeActive() {

        //given
        Account newAccount = new Account();

        //then
        assertFalse(newAccount.isActive());
        assertThat(newAccount.isActive(), equalTo(false));
        assertThat(newAccount.isActive(), is(false));
    }

    @Test
    void activatedAccountShouldHaveActiveFlagSet() {

        //given
        Account newAccount = new Account();

        //when
        newAccount.active();

        //then
        assertTrue(newAccount.isActive());
        assertThat(newAccount.isActive(), equalTo(true));
        assertThat(newAccount.isActive(), is(true));
    }

    @Test
    void newlyCreatedAccountShouldNotHaveDefaultDeliveryCreatedAddressSet() {

        //given
        Account account = new Account();

        //when
        Address address = account.getDefaultDelivaryAddress();

        //then
        assertNull(address);
        assertThat(address, nullValue());
        assertThat(address, is(nullValue()));
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
        assertThat(defaultAddress, notNullValue());
        assertThat(defaultAddress, is(notNullValue()));
    }

}
