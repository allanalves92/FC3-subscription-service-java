package com.fullcycle.subscription.domain.account;

import com.fullcycle.subscription.domain.exceptions.DomainException;
import com.fullcycle.subscription.domain.iam.UserId;
import com.fullcycle.subscription.domain.person.Document;
import com.fullcycle.subscription.domain.person.Email;
import com.fullcycle.subscription.domain.person.Name;
import com.fullcycle.subscription.domain.utils.InstantUtils;
import java.time.Instant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AccountCreatedTest {

    @Test
    public void givenValidParams_whenInstantiateEvent_shouldReturnIt() {
        // given
        var expectedAccountId = new AccountId("13dsa");
        var expectedUserId = new UserId("USER-123");
        var expectedName = new Name("John", "Doe");
        var expectedEmail = new Email("john@gmail.com");
        var expectedDocument = Document.create("12345678912", "cpf");
        var expectedAggregateType = "Account";

        var actualAccount = Account.newAccount(expectedAccountId, expectedUserId, expectedEmail, expectedName, expectedDocument);

        // when
        var actualEvent = new AccountEvent.AccountCreated(actualAccount);

        // then
        assertNotNull(actualAccount);
        assertEquals(expectedAccountId.value(), actualEvent.aggregateId());
        assertEquals(expectedAggregateType, actualEvent.aggregateType());
        assertEquals(expectedAccountId.value(), actualEvent.accountId());
        assertEquals(expectedEmail.value(), actualEvent.email());
        assertEquals(expectedName.fullname(), actualEvent.fullname());
        assertNotNull(actualEvent.occurredOn());
    }

    @Test
    public void givenInvalidId_whenInstantiate_ShouldReturnError() {
        // given
        var expectedMessage = "'accountId' should not be empty";

        String expectedAccountId = null;
        var expectedName = "John";
        var expectedEmail = "john@gmail.com";
        var expectedOccurredOn = InstantUtils.now();

        // when
        var actualError = Assertions.assertThrows(
                DomainException.class,
                () -> new AccountEvent.AccountCreated(expectedAccountId, expectedEmail, expectedName, expectedOccurredOn)
        );

        // then
        assertEquals(expectedMessage, actualError.getMessage());
    }

    @Test
    public void givenInvalidEmail_whenInstantiate_ShouldReturnError() {
        // given
        var expectedMessage = "'email' should not be empty";

        var expectedAccountId = "123";
        var expectedEmail = "";
        var expectedName = "John";
        var expectedOccurredOn = InstantUtils.now();

        // when
        var actualError = Assertions.assertThrows(
                DomainException.class,
                () -> new AccountEvent.AccountCreated(expectedAccountId, expectedEmail, expectedName, expectedOccurredOn)
        );

        // then
        assertEquals(expectedMessage, actualError.getMessage());
    }

    @Test
    public void givenInvalidName_whenInstantiate_ShouldReturnError() {
        // given
        var expectedMessage = "'fullname' should not be empty";

        var expectedAccountId = "123";
        var expectedName = "";
        var expectedEmail = "john@gmail.com";
        var expectedOccurredOn = InstantUtils.now();

        // when
        var actualError = Assertions.assertThrows(
                DomainException.class,
                () -> new AccountEvent.AccountCreated(expectedAccountId, expectedEmail, expectedName, expectedOccurredOn)
        );

        // then
        assertEquals(expectedMessage, actualError.getMessage());
    }

    @Test
    public void givenInvalidOccurredOn_whenInstantiate_ShouldReturnError() {
        // given
        var expectedMessage = "'occurredOn' should not be null";

        var expectedAccountId = "123";
        var expectedName = "John";
        var expectedEmail = "john@gmail.com";
        Instant expectedOccurredOn = null;

        // when
        var actualError = Assertions.assertThrows(
                DomainException.class,
                () -> new AccountEvent.AccountCreated(expectedAccountId, expectedEmail, expectedName, expectedOccurredOn)
        );

        // then
        assertEquals(expectedMessage, actualError.getMessage());
    }
}