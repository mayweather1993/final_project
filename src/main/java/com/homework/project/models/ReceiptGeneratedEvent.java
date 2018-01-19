package com.homework.project.models;

import lombok.Getter;

@Getter
public class ReceiptGeneratedEvent extends BaseEvent {
    private final Long receiptId;

    public ReceiptGeneratedEvent(Object source, Long receiptId) {
        super(source);
        this.receiptId = receiptId;
    }
}
