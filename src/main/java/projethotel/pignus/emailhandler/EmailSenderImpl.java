package projethotel.pignus.emailhandler;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.context.Context;
//import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class EmailSenderImpl implements EmailSender{

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private SpringTemplateEngine springTemplateEngine;

    @Async
    @Override
    public void sendConfirmationEmail(String to, String subject, Map datas) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(
                    mimeMessage,
                    MimeMessageHelper.MULTIPART_MODE_MIXED,
                    StandardCharsets.UTF_8.name()
            );

//            URL url = getClass().getResource("/static/images/logo_web.png");
//            Resource logo = new InputStreamResource(url.openStream());
            Context context = new Context();
//            datas.put("logo", new ClassPathResource("images/logo_web.png"));
            context.setVariables(datas);
//            context.setVariable("logo", new ClassPathResource("images/logo_web.png"));
            String html = springTemplateEngine.process("confirmation-email1", context);

            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setText(html, true);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setFrom("contact@toctocdoc.com");
//            mimeMessageHelper.addAttachment("logo_web.png", new ClassPathResource("images/logo_web.png"));
//            mimeMessageHelper.addInline("logo", logo);

            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new IllegalStateException("Failed to send email: "+e);
        }
    }

    @Async
    @Override
    public void sendConfirmedAppointmentEmail(String to, String subject, Map datas) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(
                    mimeMessage,
                    MimeMessageHelper.MULTIPART_MODE_MIXED,
                    StandardCharsets.UTF_8.name()
            );

            Context context = new Context();
            context.setVariables(datas);
            String html = springTemplateEngine.process("confirmed-appointment", context);

            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setText(html, true);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setFrom("contact@toctocdoc.com");

            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new IllegalStateException("Failed to send email: "+e);
        }
    }
}
