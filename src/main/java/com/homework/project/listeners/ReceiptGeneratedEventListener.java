package com.homework.project.listeners;

import com.homework.project.domain.Receipt;
import com.homework.project.domain.User;
import com.homework.project.models.ReceiptGeneratedEvent;
import com.homework.project.services.MailService;
import com.homework.project.services.ReceiptsService;
import com.homework.project.services.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@AllArgsConstructor
public class ReceiptGeneratedEventListener implements ProjectEventListener<ReceiptGeneratedEvent> {

    private final ReceiptsService receiptsService;
    private final UsersService usersService;
    private final MailService mailService;

    @Override
    public void onApplicationEvent(final ReceiptGeneratedEvent event) {
        final Long receiptId = event.getReceiptId();
        final Receipt receipt = receiptsService.findById(receiptId);
        final Long userId = receipt.getUserId();
        final User user = usersService.findById(userId);
        mailService.sendReceiptEmail(user.getEmail(), Collections.singletonList(String.valueOf(receipt.getSalary())));
    }
}
