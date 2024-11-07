package com.tco.requests;

import com.tco.misc.BadRequestException; // to return 400
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FindRequest extends Request {
    private static final transient Logger log = LoggerFactory.getLogger(FindRequest.class);

    public String match;
    public List<String> type;
    public List<String> where;
    public Integer limit;
    public Integer found;
    public Places places;

    @Override
    public void buildResponse() throws BadRequestException {
        places = buildFindList();
        log.trace("buildResponse -> {}", this);
    }

    public Places buildFindList() throws BadRequestException {
        // ...
        return places;
    }

    public FindRequest(String match, List<String> type, List<String> where, Integer limit) {
        super();
        this.requestType = "find";
        this.match = match;
        this.type = type;
        this.where = where;
        this.limit = limit;
    }

    public String match() {
        return match;
    }

    public List<String> type() {
        return type;
    }

    public List<String> where() {
        return where;
    }

    public Integer limit() {
        return limit;
    }

    public Integer found() {
        return found;
    }

    public Places places() {
        return places;
    }
}
