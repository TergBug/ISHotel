package org.mycode.repository;

import org.mycode.model.AuthoritySecurity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthoritySecurityRepository extends CrudRepository<AuthoritySecurity, String> {
    @EntityGraph(value = "Authority.detail", type = EntityGraph.EntityGraphType.LOAD)
    AuthoritySecurity findAuthoritySecurityByAuthority(String authority);

    @EntityGraph(value = "Authority.detail", type = EntityGraph.EntityGraphType.LOAD)
    List<AuthoritySecurity> findAll();
}
