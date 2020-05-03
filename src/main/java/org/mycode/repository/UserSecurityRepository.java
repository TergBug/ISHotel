package org.mycode.repository;

import org.mycode.model.UserSecurity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserSecurityRepository extends CrudRepository<UserSecurity, String> {
    @EntityGraph(value = "User.detail", type = EntityGraph.EntityGraphType.LOAD)
    UserSecurity findUserSecurityByUsername(String username);

    @EntityGraph(value = "User.detail", type = EntityGraph.EntityGraphType.LOAD)
    List<UserSecurity> findAll();
}
