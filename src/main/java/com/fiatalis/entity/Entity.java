package com.fiatalis.entity;

public interface Entity {
    EntityEnum getKey();
    String[] getOptionName();
    String[] getObjectValue();
    void saveEntity();
    Entity getEntity();
}
