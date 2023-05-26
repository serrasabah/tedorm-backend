package tedorm.app.application.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tedorm.app.application.student.entity.EmailDetails;
import tedorm.app.application.student.service.EmailService;

// Annotation
@RestController
// Class
public class EmailController {

    @Autowired private EmailService emailService;

    // Sending a simple Email
    @PostMapping("/sendMail")
    public Boolean
    sendMail(@RequestBody EmailDetails details)
    {
        boolean status
                = emailService.sendSimpleMail(details);

        return status;
    }

    // Sending email with attachment
    @PostMapping("/sendMailWithAttachment")
    public Boolean sendMailWithAttachment(
            @RequestBody EmailDetails details)
    {
        boolean status
                = emailService.sendMailWithAttachment(details);

        return status;
    }
}
