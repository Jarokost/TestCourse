package org.example.account;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

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

    @RepeatedTest(5)
    void newAccountWithNotNullAddressShouldBeActive() {

        //given
        Address address = new Address("Poznanska", "18/5");

        //when
        Account account = new Account(address);

        //then
        assumingThat(address != null, () -> {
            assertTrue(account.isActive());
        });
    }

    @Test
    void invalidEmailShouldThrowException() {

        //given
        Account account = new Account();

        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> account.setEmail("wrongEmail"));
    }

    @Test
    void validEmailShouldBeSet() {
        //given
        Account account = new Account();

        //when
        account.setEmail("kontakt@gmail.com");

        //then
        assertThat(account.getEmail(), is("kontakt@gmail.com"));
    }

}
