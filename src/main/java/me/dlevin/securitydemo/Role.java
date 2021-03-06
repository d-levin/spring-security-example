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
public class Role {

  @Id
  @GeneratedValue
  private Long id;

  private String name;

  @ManyToMany(mappedBy = "roles")
  @EqualsAndHashCode.Exclude
  private Set<User> users = new HashSet<>();

  @ManyToMany
  @JoinTable(
          name = "role_permission",
          joinColumns = @JoinColumn(name = "role_id"),
          inverseJoinColumns = @JoinColumn(name = "permission_id")
  )
  @EqualsAndHashCode.Exclude
  private Set<Permission> permissions = new HashSet<>();


  public Role(final String name) {
    this.name = name;
  }

}
