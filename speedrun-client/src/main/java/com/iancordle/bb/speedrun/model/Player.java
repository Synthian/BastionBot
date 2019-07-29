package com.iancordle.bb.speedrun.model;

import java.time.ZonedDateTime;

public class Player {
    private String rel;
    private String id;
    private String uri;
    private Names names;
    private String weblink;
    private String role;
    private ZonedDateTime signup;

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Names getNames() {
        return names;
    }

    public void setNames(Names names) {
        this.names = names;
    }

    public String getWeblink() {
        return weblink;
    }

    public void setWeblink(String weblink) {
        this.weblink = weblink;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public ZonedDateTime getSignup() {
        return signup;
    }

    public void setSignup(ZonedDateTime signup) {
        this.signup = signup;
    }
}
