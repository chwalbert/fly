package com.cloud.fly.content.core.constant;

import java.util.Arrays;
import java.util.Optional;

/**
 * @Auther: chw
 * @Date: 2019/1/3 15:37
 * @Description:
 */
public enum TripType {
    oneway("1", "单程"),
    roundtrip("2", "往返");


    private String type;
    private String desc;

    TripType(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public static TripType get(String type) {
        Optional<TripType> optionalPageView = Arrays.stream(values())
                .filter(tripType -> tripType.getType().equals(type))
                .findFirst();
        TripType typeEnum = optionalPageView.isPresent() ? optionalPageView.get() : null;
        return typeEnum;
    }
}
