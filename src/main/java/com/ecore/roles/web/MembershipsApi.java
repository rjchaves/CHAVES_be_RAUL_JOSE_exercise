package com.ecore.roles.web;

import com.ecore.roles.web.dto.MembershipDto;

import java.util.List;
import java.util.UUID;

public interface MembershipsApi {

    MembershipDto assignRoleToMembership(
            MembershipDto membership);

    List<MembershipDto> getMemberships(
            UUID roleId);

}
