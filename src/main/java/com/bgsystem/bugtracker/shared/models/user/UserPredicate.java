package com.bgsystem.bugtracker.shared.models.user;

import com.bgsystem.bugtracker.shared.models.listRequest.CommonPathExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import org.springframework.stereotype.Service;

@Service
public class UserPredicate extends CommonPathExpression<User> {

    private final QUser user = QUser.user;

    public UserPredicate(){
        super();
        this.entityPath = new PathBuilder<User>(User.class, "user");

    }

}
