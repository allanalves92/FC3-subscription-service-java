package com.fullcycle.subscription.domain.idp;

import com.fullcycle.subscription.domain.Identifier;

public record GroupId(String value) implements Identifier<String> {

    public GroupId {
        this.assertArgumentNotEmpty(value, "'groupId' should not be empty");
    }
}