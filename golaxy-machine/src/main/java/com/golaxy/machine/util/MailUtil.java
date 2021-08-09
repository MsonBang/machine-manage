package com.golaxy.machine.util;

import com.golaxy.machine.common.contants.ResultContants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Map;

/**
 * @author miaoxuebing
 * @Description: TODO[处理发送邮件的工具类]
 * @date 2021/8/6 上午10:20
 */
@Component
public class MailUtil {
    @Autowired
    JavaMailSender mailSender;


    /**
     * @Description: 发送邮件启动类
     * @Params: [sendForm, sendTo, theme, content]
     * @Return: void
     * @Author: miaoxuebing
     * @Date: 2021/8/9 上午9:57
     **/
    public void sendMail(String sendForm, String sendTo, String theme, String content) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(sendForm);
            helper.setTo(sendTo);
            helper.setSubject(theme);
            helper.setText(content, true);
            mailSender.send(mimeMessage);
        } catch (MessagingException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * @Description: 用户提交申请-给管理员发送申请邮件
     * @Params: [map]
     * @Return: java.util.Map<java.lang.String, java.lang.String>
     * @Author: miaoxuebing
     * @Date: 2021/8/9 上午8:57
     **/
    public void makeSendAdminParam(Map<String, Object> map) {
        String servername = (String) map.get("servername");
        String serverip = (String) map.get("serverip");
        String applyuser = (String) map.get("applyuser");
        String applyorg = (String) map.get("applyorg");
        String email = (String) map.get("email");
        String applyremark = (String) map.get("applyremark");
        //content
        String content = "<html>\n" +
                "<body>\n" +
                "    <h5>管理员您好！" + applyorg + "-" + applyuser + "请求申请服务器资源，具体服务器申请需求如下：</h5>\n" +
                "    <h6 style=\"margin-left: 10px;\">申请服务器名称：" + servername + "</h6>\n" +
                "    <h6 style=\"margin-left: 10px;\">申请服务器IP：" + serverip + "</h6>\n" +
                "    <h6 style=\"margin-left: 10px;\">申请服务器资源描述：" + applyremark + "</h6>\n" +
                "    <h5>请到运维管理系统进行审核操作，如有问题请你及时回复，谢谢！</h5>\n" +
                "</body>\n" +
                "</html>";
        sendMail(email, ResultContants.ADMIN_EMAIL, ResultContants.APPLY_RESOURCE, content);
    }

    /**
     * @Description: 审核结果发送给用户通知
     * @Params: [map]
     * @Return: void
     * @Author: miaoxuebing
     * @Date: 2021/8/9 上午10:07
     **/
    public void makeSendUserParam(Map<String, Object> map) {
        String servername = (String) map.get("servername");
        String serverip = (String) map.get("serverip");
        String applyuser = (String) map.get("applyuser");
        int checkstatus = (int) map.get("checkstatus");
        String email = (String) map.get("email");
        String checkremark = (String) map.get("checkremark");
        String checkResult = "";
        if (ResultContants.OPERATOR_YES.equals(String.valueOf(checkstatus))) {
            checkResult = "已通过";
        } else {
            checkResult = "未通过";
        }
        //content
        String content = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Document</title>\n" +
                "</head>" +
                "<body>\n" +
                "    <h5>" + applyuser + "你好！服务器申请结果如下：</h5>\n" +
                "    <h6 style=\"margin-left: 3px;\">服务器信息</h6>\n" +
                "    <h6 style=\"margin-left: 10px;\">申请服务器名称：" + servername + "</h6>\n" +
                "    <h6 style=\"margin-left: 10px;\">申请服务器IP：" + serverip + "</h6>\n" +
                "    <h6 style=\"margin-left: 3px;\">申请结果</h6>\n" +
                "    <h6 style=\"margin-left: 10px;\">是否通过：" + checkResult + "</h6>\n" +
                "    <h6 style=\"margin-left: 10px;\">审核备注：" + checkremark + "</h6>\n" +
                "    <h5>如有问题请你及时回复，谢谢！</h5>\n" +
                "</body>\n" +
                "</html>";
        sendMail(ResultContants.ADMIN_EMAIL, email, ResultContants.APPLY_RESULT, content);
    }

}
