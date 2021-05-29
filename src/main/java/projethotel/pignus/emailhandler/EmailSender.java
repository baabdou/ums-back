package projethotel.pignus.emailhandler;

import java.util.Map;

public interface EmailSender {

    void sendConfirmationEmail(String to, String subject, Map datas);
    void sendConfirmedAppointmentEmail(String to, String subject, Map datas);
}
