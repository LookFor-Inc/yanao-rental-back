package com.lookfor.yanaorental.models.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.net.URL;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "users_info")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    @Column(length = 20)
    private String firstName;

    @Column(length = 20)
    private String lastName;

    @Column(length = 20)
    private String middleName;

    @Column(length = 256)
    private URL avatar;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @JsonFormat(timezone = "UTC")
    private Date registrationDate;
}
