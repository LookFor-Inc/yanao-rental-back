package com.lookfor.yanaorental.events.listeners;

import com.lookfor.yanaorental.config.AppProperties;
import com.lookfor.yanaorental.events.OnRegistrationCompleteEvent;
import com.lookfor.yanaorental.models.auth.EmailVerificationToken;
import com.lookfor.yanaorental.models.user.User;
import com.lookfor.yanaorental.services.EmailService;
import com.lookfor.yanaorental.services.auth.EmailVerificationTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OnRegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
    private final EmailVerificationTokenService emailVerificationTokenService;
    private final AppProperties appProperties;
    private final EmailService emailService;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        EmailVerificationToken token = emailVerificationTokenService.createToken(user);

        String template = """
                <b>ЯНАО шеринг</b>
                <div style="border-top:1px solid #f0f0f0;border-bottom:1px solid #f0f0f0;padding:10px 0 10px 0;margin:10px 0 10px 0">
                    Добро пожаловать!
                    <br><br>
                    Для подтверждения электронной почты и завершения процесса регистрации, пройдите, пожалуйста, по ссылке:
                    <br><br>
                    %s
                    <br><br>
                    Если вы получили это письмо по ошибке, просто игнорируйте его.
                </div>
                <span style="color:#999">© ЯНАО шерин</span>
                        """;

        String confirmationUrl = appProperties.getFront() + "/auth/registration/confirm?token=" + token;
        String message = String.format(template, confirmationUrl);

        emailService.send(
                user.getEmail(),
                "Подтверждение почты",
                message,
                true
        );
    }
}
