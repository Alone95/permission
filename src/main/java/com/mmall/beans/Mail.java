package com.mmall.beans;

import lombok.*;

import java.util.Set;

/**
 * @author alone95
 * @date 2017/11/2
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Mail {

    //邮件的主题
    private String subject;

    //邮件的信息
    private String message;

    //邮箱
    private Set<String> receivers;
}
