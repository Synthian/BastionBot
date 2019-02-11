package com.iancordle.bb.speedrun.model;

import java.util.List;

public class Category extends Embeddable {

    private String name;
    private String weblink;
    private String type;
    private String rules;
    private CategoryPlayers players;
    private Boolean miscellaneous;
    private List<Link> links;
    private List<Variable> variables;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeblink() {
        return weblink;
    }

    public void setWeblink(String weblink) {
        this.weblink = weblink;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public CategoryPlayers getPlayers() {
        return players;
    }

    public void setPlayers(CategoryPlayers players) {
        this.players = players;
    }

    public Boolean getMiscellaneous() {
        return miscellaneous;
    }

    public void setMiscellaneous(Boolean miscellaneous) {
        this.miscellaneous = miscellaneous;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }
}
