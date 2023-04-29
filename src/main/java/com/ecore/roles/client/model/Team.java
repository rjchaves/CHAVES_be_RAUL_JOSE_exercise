package com.ecore.roles.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import java.util.List;
import java.util.UUID;

import static java.util.Optional.ofNullable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class Team {

    @Id
    @JsonProperty
    private UUID id;

    @JsonProperty
    private String name;

    @JsonProperty
    private UUID teamLeadId;

    @JsonProperty
    private List<UUID> teamMemberIds;

    public boolean containsUser(UUID userId) {
        return ofNullable(getTeamMemberIds())
                .map((l) -> l.contains(userId))
                .orElse(false);
    }
}
