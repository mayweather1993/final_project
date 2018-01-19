package com.homework.project;

public final class SecurityConstants {

    public static final String ADMIN_AUTHORITY = "ADMIN";
    public static final String MODERATOR_AUTHORITY = "MODERATOR";
    public static final String HAS_ADMIN_AUTHORITY = "hasAuthority('ADMIN')";
    public static final String HAS_ADMIN_MODERATOR_AUTHORITY = "hasAnyAuthority('ADMIN', 'MODERATOR')";

    private SecurityConstants() {
    }
}
