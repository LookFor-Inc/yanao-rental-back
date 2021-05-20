package com.lookfor.yanaorental.models.auth;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lookfor.yanaorental.models.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    protected User user;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(timezone = "UTC")
    protected Date expiryDate;

    @Override
    public String toString() {
        return token;
    }
}

