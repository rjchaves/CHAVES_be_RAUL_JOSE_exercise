package com.ecore.roles.web.rest;

import com.ecore.roles.model.Role;
import com.ecore.roles.service.RolesService;
import com.ecore.roles.web.RolesApi;
import com.ecore.roles.web.dto.RoleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.ecore.roles.web.dto.RoleDto.fromModel;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/roles")
public class RolesRestController implements RolesApi {

    private final RolesService rolesService;

    @Override
    @PostMapping(
            consumes = {"application/json"},
            produces = {"application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    public RoleDto createRole(
            @Valid @RequestBody RoleDto role) {
        return fromModel(rolesService.createRole(role.toModel()));
    }

    @Override
    @GetMapping(
            produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public List<RoleDto> getRoles() {

        List<Role> getRoles = rolesService.getRoles();

        List<RoleDto> roleDtoList = new ArrayList<>();

        for (Role role : getRoles) {
            RoleDto roleDto = fromModel(role);
            roleDtoList.add(roleDto);
        }

        return roleDtoList;
    }

    @Override
    @GetMapping(
            path = "/{roleId}",
            produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public RoleDto getRole(
            @PathVariable UUID roleId) {
        return fromModel(rolesService.getRole(roleId));
    }

    @Override
    @GetMapping(
            path = "/search",
            produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public RoleDto getRole(
            @RequestParam("teamMemberId") UUID userId,
            @RequestParam UUID teamId) {
        return fromModel(rolesService.getRole(userId, teamId));
    }
}
