package com.malt.collect.util;

import com.malt.collect.entity.User;

import java.util.Date;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmailUtils {
    //发件人地址
    public static String senderAddress = "seafloorlao@163.com";
    //收件人地址
    public static String recipientAddress = "zhouao7850@163.com";
    //发件人账户名
    public static String senderAccount = "seafloorlao";
    //发件人账户密码
    public static String auth = "lihang11";

    public static void sendEmail(User user) throws Exception {
        //1.连接邮件服务器的配置参数
        Properties properties = new Properties();
        //设置用户认证方式
        properties.setProperty("mail.smtp.auth", "true");
        //设置传输协议
        properties.setProperty("mail.transport.protocol", "smtp");
        //设置发件人的SMTP服务器地址
        properties.setProperty("mail.smtp.host", "smtp.163.com");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.port", "465");

        //2.创建定义整个应用程序所需的环境信息的Session对象
        Session session = Session.getInstance(properties);
        session.setDebug(true);
        //3.创建邮件的实例对象
        MimeMessage msg = getMimeMessage(session,user);
        //4.根据session对象获取邮件传输对象
        Transport transport = session.getTransport("smtp");
        transport.connect("smtp.163.com", senderAccount, auth);
        //发送邮件，并发送到所有收件人地址，message.getAllRecipients()获取到创建又见对象时添加的所有收件人抄送人密送人
        transport.sendMessage(msg, msg.getAllRecipients());
        //如果只想发送给指定的人，可以如下写法
        //transport.sendMessage(msg, new Address[]{new InternetAddress("xxx@qq.com")});
        //5.关闭邮件链接
        transport.close();
    }

    /**
     * 获得创建一封邮件的实例对象
     *
     * @param session
     * @return
     * @throws MessagingException
     * @throws AddressException
     */
    public static MimeMessage getMimeMessage(Session session, User user) throws Exception {
        //创建一封邮件的实例对象
        MimeMessage msg = new MimeMessage(session);
        //设置发件人地址
        msg.setFrom(new InternetAddress(senderAddress));
        /**
         * 设置收件人地址（可以增加多个收件人、抄送、密送），即下面这一行代码书写多行
         * MimeMessage.RecipientType.TO:发送
         * MimeMessage.RecipientType.CC：抄送
         * MimeMessage.RecipientType.BCC：密送
         */
        msg.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(recipientAddress));
        //设置邮件主题
        msg.setSubject("点点麦芽用户联系邮件", "UTF-8");
        //设置邮件正文
        msg.setContent("联系人信息: \n" +
                "品牌: " + user.getBrand() + "\n" +
                "姓名: " + user.getName() + "\n" +
                "手机号:" + user.getPhone() + "\n" +
                "微信号: " + user.getWeChat(), "text/html;charset=UTF-8");
        //设置邮件的发送时间,默认立即发送
        msg.setSentDate(new Date());
        //结束

        return msg;
    }

}
