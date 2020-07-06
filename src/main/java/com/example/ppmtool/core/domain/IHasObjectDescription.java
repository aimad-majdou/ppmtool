package com.example.ppmtool.core.domain;

public interface IHasObjectDescription {
    default String getObjectDescription() {
        return getClass().getName() + ": [" + hashCode() + "]";
    }
}
