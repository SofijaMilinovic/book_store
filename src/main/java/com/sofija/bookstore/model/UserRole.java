package com.sofija.bookstore.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_role")
public class UserRole {

    @EmbeddedId
    private UserRoleId userRoleId;

    public UserRole() {
    }

    public UserRoleId getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(UserRoleId userRoleId) {
        this.userRoleId = userRoleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole userRole = (UserRole) o;
        return Objects.equals(userRoleId, userRole.userRoleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userRoleId);
    }
}
