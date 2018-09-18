package me.dlevin.securitydemo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class User {

  @Id
  @GeneratedValue
  private Long id;

  private String username;

  private String password;

  @ManyToMany
  @JoinTable(
          name = "user_role",
          joinColumns = @JoinColumn(name = "user_id"),
          inverseJoinColumns = @JoinColumn(name = "role_id")
  )
  @EqualsAndHashCode.Exclude
  private Set<Role> roles = new HashSet<>();

  public User(final String username, final String password) {
    this.username = username;
    this.password = password;
  }

}
