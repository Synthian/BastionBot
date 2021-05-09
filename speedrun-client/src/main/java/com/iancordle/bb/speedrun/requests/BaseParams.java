package com.iancordle.bb.speedrun.requests;

import java.util.List;

// TODO: Bring this class back when Feign 10.3.0 releases and is integrated into Spring Cloud.
// Currently, @SpringQueryMap does not support inheritance.

public abstract class BaseParams {
    // Pagination
    int max;
    int offset;
    // Sorting
    String orderby;
    String direction;
    // Embed
    List<String> embed;

    public int getMax() {
        return max;
    }

    abstract public BaseParams withMax(int max);

    public int getOffset() {
        return offset;
    }

    abstract public BaseParams withOffset(int offset);

    public String getOrderby() {
        return orderby;
    }

    abstract public BaseParams withOrder(String orderby);

    public String getDirection() {
        return direction;
    }

    abstract public BaseParams withDirection(String direction);

    public List<String> getEmbed() {
        return embed;
    }

    abstract public BaseParams withEmbeds(List<String> embed);
}
