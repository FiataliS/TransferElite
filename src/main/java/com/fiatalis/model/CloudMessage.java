package com.fiatalis.model;

import java.io.Serializable;

public interface CloudMessage extends Serializable {
    MessageType getMessageType();
}
