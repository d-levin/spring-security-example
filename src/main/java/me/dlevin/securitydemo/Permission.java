package me.dlevin.securitydemo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Permission {

  /**
   * TODO violates the open-closed principle?
   */
  public static final String ITEM_CREATE = "ITEM_CREATE";
  public static final String ITEM_READ = "ITEM_READ";
  public static final String ITEM_UPDATE = "ITEM_UPDATE";
  public static final String ITEM_DELETE = "ITEM_DELETE";

  @Id
  @GeneratedValue
  private Long id;

  private String name;

  public Permission(final String name) {
    this.name = name;
  }

  @ManyToMany(mappedBy = "permissions")
  @EqualsAndHashCode.Exclude
  private Set<Role> roles = new HashSet<>();

}
