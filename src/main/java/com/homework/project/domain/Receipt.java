package com.homework.project.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@ToString
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "receipt")
@NamedQuery(name = "Receipt.getSalaryForUser", query = "select sum (r.salary) from Receipt r where r.userId = :userId and r.createdDate > :from and r.createdDate < :to")
public class Receipt extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "salary")
    private Double salary;


}
