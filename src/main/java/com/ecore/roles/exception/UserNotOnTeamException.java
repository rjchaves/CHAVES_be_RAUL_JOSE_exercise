package com.ecore.roles.exception;

public class UserNotOnTeamException extends ValidationException {
    public UserNotOnTeamException() {
        super("User is not on provided Team");
    }
}
