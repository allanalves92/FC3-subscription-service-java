package com.fullcycle.subscription.domain.subscription;

import com.fullcycle.subscription.domain.exceptions.DomainException;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SubscriptionIdTest {

    @Test
    public void givenValidId_whenInstantiate_shouldReturnVO() {
        // given
        var expectedSubscriptionId = "123";

        // when
        var actualUserId = new SubscriptionId(expectedSubscriptionId);

        // then
        assertEquals(expectedSubscriptionId, actualUserId.value());
    }

    @Test
    public void givenEmptySubscriptionId_whenInstantiate_shouldReturnError() {
        // given
        var expectedSubscriptionId = "";
        var expectedError = "'subscriptionId' should not be empty";

        // when
        var actualError = assertThrows(DomainException.class, () -> new SubscriptionId(expectedSubscriptionId));

        // then
        assertEquals(expectedError, actualError.getMessage());
    }
}