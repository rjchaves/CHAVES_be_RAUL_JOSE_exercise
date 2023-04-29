package com.ecore.roles.web;

import com.ecore.roles.web.dto.RoleDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.UUID;

public interface RolesApi {

    RoleDto createRole(
            RoleDto role);

    List<RoleDto> getRoles();

    RoleDto getRole(
            UUID roleId);

    @GetMapping(
            path = "/search",
            produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    RoleDto getRole(
            @RequestParam UUID userId,
            @RequestParam UUID teamId);
}
