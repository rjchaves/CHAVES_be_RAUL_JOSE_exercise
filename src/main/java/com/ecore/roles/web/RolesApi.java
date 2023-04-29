package com.ecore.roles.web;

import com.ecore.roles.web.dto.RoleDto;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

public interface RolesApi {

    RoleDto createRole(
            RoleDto role);

    List<RoleDto> getRoles();

    RoleDto getRole(
            UUID roleId);

    RoleDto getRole(
            @RequestParam UUID userId,
            @RequestParam UUID teamId);
}
