package at.videc.survia.node.domain.model;

import at.videc.survia.node.domain.model.base.BaseEntity;
import jakarta.persistence.*;

import java.util.Set;

/**
 * Survia uses OAuth2 so there will be no local passwords...
 */
@Entity
public class User extends BaseEntity<Long> {

    @Column(nullable = false, unique = true)
    private String username;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
