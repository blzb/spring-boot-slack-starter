package com.blzb.bot.events.service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by apimentel on 2/5/18.
 */
public enum EventTypeEnum {
    MESSAGE("message"),
    REACTION_ADDED("reaction_added");
    private final String jsonValue;

    private EventTypeEnum(String jsonValue) {
        this.jsonValue = jsonValue;
    }

    public String getJsonValue() {
        return jsonValue;
    }

    private final static Map<String, EventTypeEnum> mapJson;

    static {
        Map<String, EventTypeEnum> tempJson = new HashMap<String, EventTypeEnum>();

        for (EventTypeEnum current : EventTypeEnum.values()) {
            tempJson.put(current.getJsonValue(), current);
        }
        mapJson = tempJson;
    }


    public static EventTypeEnum fromJson(String json) {
        EventTypeEnum e = null;

        if (json != null) {
            e = mapJson.get(json.toLowerCase());
        }

        if (e == null) {
            throw new IllegalArgumentException(json);
        }

        return e;
    }
}
