package me.dlevin.securitydemo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class UserService implements UserDetailsService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public Optional<User> findByUsername(final String username) {
    return this.userRepository.findByUsername(username);
  }

  @Override
  /**
   * @Transactional annotation is required when fetchType is set to LAZY
   * in a many-to-many relationship
   *
   * In this case there is a many-to-many relationship between users and roles
   */
  @Transactional
  public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
    log.debug("Loading user [{}]", username);

    return this.findByUsername(username)
            .map(this::buildUserDetails)
            .orElseThrow(() -> new UsernameNotFoundException(username));
  }

  private UserDetails buildUserDetails(final User user) {
    return org.springframework.security.core.userdetails.User
            .withUsername(user.getUsername())
            .password(user.getPassword())
            .passwordEncoder(this.passwordEncoder::encode)
            .authorities(this.getGrantedAuthorities(user.getRoles()))
            .build();
  }

  private Set<GrantedAuthority> getGrantedAuthorities(final Set<Role> roles) {
    final Set<String> roleNames = roles.stream()
            .map(Role::getName)
            .map(roleName -> "ROLE_" + roleName)
            .collect(Collectors.toSet());

    final Set<String> permissionNames = this.getPermissions(roles).stream()
            .map(Permission::getName)
            .collect(Collectors.toSet());

    final Set<String> rolesAndPermissionNames = new HashSet<>();
    rolesAndPermissionNames.addAll(roleNames);
    rolesAndPermissionNames.addAll(permissionNames);

    return rolesAndPermissionNames.stream()
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toSet());
  }

  private Set<Permission> getPermissions(final Set<Role> roles) {
    return roles.stream()
            .map(Role::getPermissions)
            .flatMap(Set::stream)
            .collect(Collectors.toSet());
  }

}
