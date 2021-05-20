package com.lookfor.yanaorental.models.auth;

import com.lookfor.yanaorental.models.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "provider_auth")
public class ProviderAuth {
    @Id
    public String id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "user_id")
    private User user;
}
