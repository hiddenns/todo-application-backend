//package com.todolist.todoapplication.controller;
//
//import com.todolist.todoapplication.dto.PasswordDto;
//import com.todolist.todoapplication.entity.PasswordResetToken;
//import com.todolist.todoapplication.entity.User;
//import com.todolist.todoapplication.errors.UserNotFoundException;
//import com.todolist.todoapplication.model.GenericResponse;
//import com.todolist.todoapplication.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.validation.Valid;
//import java.util.Calendar;
//import java.util.Locale;
//import java.util.Optional;
//import java.util.UUID;
//
//@Controller
//public class ResetPasswordController {
//
//    @Autowired
//    private UserService userService;
//
//    @PostMapping("/user/resetPassword")
//    public GenericResponse resetPassword(HttpServletRequest request,
//                                         @RequestParam("email") String userEmail) throws UserNotFoundException {
//        User user = userService.findUserByEmail(userEmail);
//        if (user == null) {
//            throw new UserNotFoundException();
//        }
//        String token = UUID.randomUUID().toString();
//        userService.createPasswordResetTokenForUser(user, token);
//        mailSender.send(constructResetTokenEmail(getAppUrl(request),
//                request.getLocale(), token, user));
//        return new GenericResponse(
//                messages.getMessage("message.resetPasswordEmail", null,
//                        request.getLocale()));
//    }
//
//    @GetMapping("/user/changePassword")
//    public String showChangePasswordPage(Locale locale, Model model,
//                                         @RequestParam("token") String token) {
//        String result = securityService.validatePasswordResetToken(token);
//        if(result != null) {
//            String message = messages.getMessage("auth.message." + result, null, locale);
//            return "redirect:/login.html?lang="
//                    + locale.getLanguage() + "&message=" + message;
//        } else {
//            model.addAttribute("token", token);
//            return "redirect:/updatePassword.html?lang=" + locale.getLanguage();
//        }
//    }
//
//    @PostMapping("/user/savePassword")
//    public GenericResponse savePassword(final Locale locale, @Valid PasswordDto passwordDto) {
//
//        String result = securityUserService.validatePasswordResetToken(passwordDto.getToken());
//
//        if(result != null) {
//            return new GenericResponse(messages.getMessage(
//                    "auth.message." + result, null, locale));
//        }
//
//        Optional user = userService.getUserByPasswordResetToken(passwordDto.getToken());
//        if(user.isPresent()) {
//            userService.changeUserPassword(user.get(), passwordDto.getNewPassword());
//            return new GenericResponse(messages.getMessage(
//                    "message.resetPasswordSuc", null, locale));
//        } else {
//            return new GenericResponse(messages.getMessage(
//                    "auth.message.invalid", null, locale));
//        }
//    }
//
//    private SimpleMailMessage constructResetTokenEmail(
//            String contextPath, Locale locale, String token, User user) {
//        String url = contextPath + "/user/changePassword?token=" + token;
//        String message = messages.getMessage("message.resetPassword",
//                null, locale);
//        return constructEmail("Reset Password", message + " \r\n" + url, user);
//    }
//
//    private SimpleMailMessage constructEmail(String subject, String body,
//                                             User user) {
//        SimpleMailMessage email = new SimpleMailMessage();
//        email.setSubject(subject);
//        email.setText(body);
//        email.setTo(user.getEmail());
//        email.setFrom(env.getProperty("support.email"));
//        return email;
//    }
//
//
//    public String validatePasswordResetToken(String token) {
//        final PasswordResetToken passToken = passwordTokenRepository.findByToken(token);
//
//        return !isTokenFound(passToken) ? "invalidToken"
//                : isTokenExpired(passToken) ? "expired"
//                : null;
//    }
//
//    private boolean isTokenFound(PasswordResetToken passToken) {
//        return passToken != null;
//    }
//
//    private boolean isTokenExpired(PasswordResetToken passToken) {
//        final Calendar cal = Calendar.getInstance();
//        return passToken.getExpiryDate().before(cal.getTime());
//    }
//
//}
