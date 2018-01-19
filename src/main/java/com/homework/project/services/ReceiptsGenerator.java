package com.homework.project.services;

import com.homework.project.domain.Event;
import com.homework.project.domain.EventUser;
import com.homework.project.domain.Receipt;
import com.homework.project.domain.User;
import com.homework.project.models.ReceiptGeneratedEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.homework.project.GeneralConstants.SYSTEM_USER;

@Service
@AllArgsConstructor
@Slf4j
public class ReceiptsGenerator {

    private final EventsService eventsService;
    private final UsersService usersService;
    private final ReceiptsService receiptsService;
    private final ApplicationEventMulticaster eventMulticaster;

    @Scheduled(cron = "0 */1 * * * ?")
    @Transactional
    public void generateReceipts() {
        log.info("generateReceipts is running");
        final Map<Long, Long> userHours = gatherInfoAboutWorkingHours();
        for (Map.Entry<Long, Long> concreteUserHours : userHours.entrySet()) {
            final User user = usersService.findById(concreteUserHours.getKey());
            final Long workedHours = concreteUserHours.getValue();
            generateReceipt(user, workedHours);
        }

    }

    @Async
    public void generateReceipt(User user, Long workedHours) {
        final Double salary = user.getHourSalary() * workedHours;
        final Receipt receipt = new Receipt();
        receipt.setCreatedBy(SYSTEM_USER);
        receipt.setLastModifiedBy(SYSTEM_USER);
        receipt.setUserId(user.getId());
        receipt.setSalary(salary);
        receiptsService.create(receipt);
        eventMulticaster.multicastEvent(new ReceiptGeneratedEvent(this, receipt.getId()));
    }

    private Map<Long, Long> gatherInfoAboutWorkingHours() {
        final int month = LocalDate.now().getMonth().getValue();
        final List<Event> allForMonth = eventsService.getAllForMonth(month);
        if (allForMonth == null || allForMonth.isEmpty()) {
            return Collections.emptyMap();
        }

        final Map<Long, Long> userHours = new HashMap<>();
        for (Event event : allForMonth) {
            for (EventUser eventUser : event.getEventUsers()) {
                final Long eventUserHours = eventUser.getHours();
                final Long userId = eventUser.getUserId();
                final Long hours = userHours.get(userId);
                userHours.put(userId, hours == null ? eventUserHours : hours + eventUserHours);
            }
        }
        return userHours;
    }

}
