package com.ebsl.utils;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.ebsl.data.model.User;
import javax.mail.internet.MimeMessage;

import java.util.HashMap;
import java.util.Map;

public class SimpleMailService {

    private JavaMailSender mailSender;
    private VelocityEngine velocityEngine;

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }

    public void register(User user) {

        // Do the registration logic...

    }

    private void sendConfirmationEmail(final String destEmailAddress) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo(destEmailAddress);
                message.setFrom("webmaster@csonth.gov.uk"); // could be parameterized...
                Map model = new HashMap();
               // model.put("user", user);
                String text = VelocityEngineUtils.mergeTemplateIntoString(
                        velocityEngine, "com/dns/registration-confirmation.vm","UTF-8", model);
                message.setText(text, true);
            }
        };
        this.mailSender.send(preparator);
    }

}
