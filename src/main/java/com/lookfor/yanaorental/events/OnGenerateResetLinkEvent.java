package com.lookfor.yanaorental.events;

import com.lookfor.yanaorental.models.user.User;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class OnGenerateResetLinkEvent extends ApplicationEvent {
    private final User user;

    public OnGenerateResetLinkEvent(User user) {
        super(user);
        this.user = user;
    }
}
