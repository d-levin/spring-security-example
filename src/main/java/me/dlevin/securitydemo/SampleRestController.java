package me.dlevin.securitydemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Slf4j
public class SampleRestController {

  @GetMapping("/admin")
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  public String requiresAdminRole() {
    log.debug("Inside requiresAdminRole");
    return "ROLE_ADMIN";
  }

  @GetMapping("/user")
  @PreAuthorize("hasAuthority('ROLE_USER')")
  public String requiresUserRole() {
    log.debug("Inside requiresUserRole");
    return "ROLE_USER";
  }

  @GetMapping("/items")
  @PreAuthorize("hasAuthority(T(me.dlevin.securitydemo.Permission).ITEM_READ)")
  public String requiresItemReadPermission() {
    log.debug("Inside requiresItemReadPermission");
    return "ITEM_READ";
  }

  @PostMapping("/items")
  @PreAuthorize("hasAuthority(T(me.dlevin.securitydemo.Permission).ITEM_CREATE)")
  public String requiresItemCreatePermission(@RequestBody final Map<String, String> createRequest) {
    log.debug("Inside requiresItemCreatePermission");

    createRequest.keySet().forEach(key -> log.debug("{}: {}", key, createRequest.get(key)));

    return "ITEM_CREATE\n\nRequest Body:\n" + createRequest;
  }

  @PutMapping("/items")
  @PreAuthorize("hasAuthority(T(me.dlevin.securitydemo.Permission).ITEM_UPDATE)")
  public String requiresItemUpdatePermission() {
    log.debug("Inside requiresItemUpdatePermission");
    return "ITEM_UPDATE";
  }

  @DeleteMapping("/items")
  @PreAuthorize("hasAuthority(T(me.dlevin.securitydemo.Permission).ITEM_DELETE)")
  public String requiresItemDeletePermission() {
    log.debug("Inside requiresItemDeletePermission");
    return "ITEM_DELETE";
  }

}
