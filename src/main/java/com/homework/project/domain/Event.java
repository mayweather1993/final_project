package com.homework.project.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@ToString
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "events")
@NamedQuery(name = "Event.getAllForMonth", query = "select e from Event e where month(e.createdDate) = :month")
public class Event extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "event_type")
    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "event")
    private List<EventUser> eventUsers;

}
