package com.lookfor.yanaorental.events.listeners;

import com.lookfor.yanaorental.config.AppProperties;
import com.lookfor.yanaorental.events.OnGenerateResetLinkEvent;
import com.lookfor.yanaorental.models.auth.PasswordResetToken;
import com.lookfor.yanaorental.models.user.User;
import com.lookfor.yanaorental.services.EmailService;
import com.lookfor.yanaorental.services.auth.PasswordResetTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OnGenerateResetLinkEventListener implements ApplicationListener<OnGenerateResetLinkEvent> {
    private final PasswordResetTokenService passwordResetTokenService;
    private final AppProperties appProperties;
    private final EmailService emailService;

    @Override
    public void onApplicationEvent(OnGenerateResetLinkEvent event) {
        this.sendResetLink(event);
    }

    private void sendResetLink(OnGenerateResetLinkEvent event) {
        User user = event.getUser();
        PasswordResetToken token = passwordResetTokenService.createToken(user);

        String template = """
                <b>ЯНАО шеринг</b>
                <div style="border-top:1px solid #f0f0f0;border-bottom:1px solid #f0f0f0;padding:10px 0 10px 0;margin:10px 0 10px 0">
                    Для сброса пароля, пройдите, пожалуйста, по ссылке:
                    <br><br>
                    %s
                    <br><br>
                    Если вы получили это письмо по ошибке, просто игнорируйте его.
                </div>
                <span style="color:#999">© ЯНАО шерин</span>
                        """;

        String confirmationUrl = appProperties.getFront() + "/auth/recovery/confirm?token=" + token;
        String message = String.format(template, confirmationUrl);

        emailService.send(
                user.getEmail(),
                "Сброс пароля",
                message,
                true
        );
    }
}
