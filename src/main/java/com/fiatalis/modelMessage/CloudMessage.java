package com.fiatalis.modelMessage;

import java.io.Serializable;

public interface CloudMessage extends Serializable {
    MessageType getMessageType();
}
