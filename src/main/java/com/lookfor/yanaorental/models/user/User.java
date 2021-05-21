package com.lookfor.yanaorental.models.user;

import com.lookfor.yanaorental.models.rental.Rental;
import com.lookfor.yanaorental.models.auth.ProviderAuth;
import com.lookfor.yanaorental.models.payments.Payment;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String password;

    @OneToOne(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private UserInfo userInfo;

    @Column(nullable = false)
    private boolean enabled;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType type;

    @OneToMany(
            mappedBy = "owner",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Rental> rentals;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<ProviderAuth> providerAuth;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Payment> payments;
}
