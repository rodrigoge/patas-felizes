package br.com.pf.api.domains.account.services;

import br.com.pf.api.domains.account.db.AccountRepository;
import br.com.pf.api.exceptions.GenericException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class EmailService {

    @Value("${spring.mail.username}")
    private String smtpEmail;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AccountRepository accountRepository;

    public void sendEmailToRecoveryPassword(String email) throws MessagingException {
        log.info("Starting the send email flow");
        var account = accountRepository.findByEmail(email).orElseThrow(() ->
                new GenericException(HttpStatus.BAD_REQUEST, "This e-mail doesn't exists")
        );
        var token = tokenService.generateToken(account.getAccountId().toString());
        String resetPasswordUrl = "http://localhost:8080/v1/accounts/reset-password?token=" + token;
        String subject = "Atualização de senha - Patas Felizes";
        String content = "<p>Olá,</p>"
                + "<p>Recebemos uma solicitação para atualizar sua senha.</p>"
                + "<p>Clique no link abaixo para redefinir sua senha:</p>"
                + "<a href=\"" + resetPasswordUrl + "\">Atualizar Senha</a>"
                + "<p>Se você não fez essa solicitação, ignore este e-mail.</p>";
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        try {
            helper.setFrom(smtpEmail);
            helper.setTo(email);
            helper.setSubject(subject);
            helper.setText(content, true);
            javaMailSender.send(message);
            log.info("Finishing the send email flow");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
