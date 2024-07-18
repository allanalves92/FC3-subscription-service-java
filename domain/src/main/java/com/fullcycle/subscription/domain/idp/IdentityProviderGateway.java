package com.fullcycle.subscription.domain.idp;

public interface IdentityProviderGateway {

    UserId create(User anUser);

    void addUserToGroup(UserId anId, GroupId aGroupId);

    void removeUserFromGroup(UserId anId, GroupId aGroupId);
}