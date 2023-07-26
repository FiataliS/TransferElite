package com.fiatalis.modelMessage;

import java.io.IOException;

public class FileDir implements CloudMessage {

    private final String item;

    public FileDir(String item) throws IOException {
        this.item = item;
    }

    public String getItem() {
        return item;
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.FILE_DIR;
    }
}
