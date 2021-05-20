package com.lookfor.yanaorental.models.auth;

import com.lookfor.yanaorental.models.user.User;
import com.lookfor.yanaorental.utils.DateUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "password_reset_tokens")
public class PasswordResetToken extends BaseToken {
    public static final int EXPIRATION_IN_MINUTES = 60 * 24;

    private Date calculateExpiryDate() {
        return DateUtils.calculateExpiration(Calendar.MINUTE, EXPIRATION_IN_MINUTES);
    }

    public PasswordResetToken(String token, User user) {
        this.token = token;
        this.user = user;
        this.expiryDate = calculateExpiryDate();
    }

    @Override
    public String toString() {
        return token;
    }
}
