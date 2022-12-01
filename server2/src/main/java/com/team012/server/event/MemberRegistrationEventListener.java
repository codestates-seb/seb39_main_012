//package com.team012.server.event;
//
//
//import com.email.Member;
//import com.email.MemberService;
//import com.email.email.EmailSender;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.event.EventListener;
//import org.springframework.mail.MailSendException;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.scheduling.annotation.EnableAsync;
//import org.springframework.stereotype.Component;
//
//@EnableAsync
//@Configuration
//@Component
//@Slf4j
//public class MemberRegistrationEventListener {
//
//    @Value("${mail.subject.member.registration}")
//    private String subject;
//
//    private final EmailSender emailSender;
//
//    private final MemberService memberService;
//
//    public MemberRegistrationEventListener(EmailSender emailSender, MemberService memberService) {
//        this.emailSender = emailSender;
//        this.memberService = memberService;
//    }
//
//    @Async
//    @EventListener
//    public void listen(MemberRegistrationApplicationEvent event) throws Exception{
//        try {
//            String[] to = new String[]{event.getMember().getEmail()};
//            String message = event.getMember().getEmail() + "님 이 편지는 영국에서 최초로 시작되어 일년에 한바퀴를 돌면서 받는 사람에게 행운을 주었고 지금은 당신에게로 옮겨진 이 편지는 4일 안에 당신 곁을 떠나야 합니다. 이 편지를 포함해서 7통을 행운이 필요한 사람에게 보내 주셔야 합니다. 복사를 해도 좋습니다. 혹 미신이라 하실지 모르지만 사실입니다.";
//            emailSender.sendEmail(to, subject, message);
//
//        }catch (MailSendException e) {
//            e.printStackTrace();
//            log.error("MailSendException: rollback for Member Registration");
//            Member member = event.getMember();
//            memberService.deleteMember(member.getMemberId());
//        }
//    }
//
//}
