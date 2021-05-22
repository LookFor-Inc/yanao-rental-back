package com.lookfor.yanaorental.models.rental;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "work_times")
public class WorkTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "rental_id", nullable = false)
    private Rental rental;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DayOfWeek fromDay;

    @Column(nullable = false)
    @Temporal(TemporalType.TIME)
    private Date fromTime;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DayOfWeek toDay;

    @Column(nullable = false)
    @Temporal(TemporalType.TIME)
    private Date toTime;
}
