package com.fullcycle.subscription.domain.iam;

import com.fullcycle.subscription.domain.Identifier;

public record UserId(String value) implements Identifier<String> {

    public UserId {
        this.assertArgumentNotEmpty(value, "'userId' should not be empty");
    }
}
