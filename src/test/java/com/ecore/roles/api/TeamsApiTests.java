package com.ecore.roles.api;

import com.ecore.roles.utils.RestAssuredHelper;
import com.ecore.roles.web.dto.TeamDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static com.ecore.roles.utils.MockUtils.mockGetTeamById;
import static com.ecore.roles.utils.RestAssuredHelper.getTeam;
import static com.ecore.roles.utils.TestData.ORDINARY_CORAL_LYNX_TEAM;
import static com.ecore.roles.utils.TestData.UUID_1;
import static java.lang.String.format;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TeamsApiTests {

    private final RestTemplate restTemplate;

    private MockRestServiceServer mockServer;

    @LocalServerPort
    private int port;

    @Autowired
    public TeamsApiTests(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @BeforeEach
    void setUp() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
        RestAssuredHelper.setUp(port);
    }

    @Test
    void shouldFailToGetTeamWhenItDoesNotExists() {
        mockGetTeamById(mockServer, UUID_1, null);
        getTeam(UUID_1)
                .validate(404, format("Team %s not found", UUID_1));
    }

    @Test
    void shouldGetTeam() {
        mockGetTeamById(mockServer, UUID_1, ORDINARY_CORAL_LYNX_TEAM());
        TeamDto expectedTeam = TeamDto.fromModel(ORDINARY_CORAL_LYNX_TEAM());
        getTeam(UUID_1)
                .statusCode(200)
                .body("id", equalTo(expectedTeam.getId().toString()))
                .body("name", equalTo(expectedTeam.getName()))
                .body("teamLeadId", equalTo(expectedTeam.getTeamLeadId().toString()));
    }
}
