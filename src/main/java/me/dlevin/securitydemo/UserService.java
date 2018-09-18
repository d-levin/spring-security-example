package me.dlevin.securitydemo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

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
            .roles(user.getRoles()
                    .stream()
                    .map(Role::getName)
                    .toArray(String[]::new)
            )
            .build();
  }

}
