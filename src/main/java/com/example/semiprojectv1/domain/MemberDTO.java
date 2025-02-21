package com.example.semiprojectv1.domain;

import lombok.Data;

@Data   // setter, getter, toString 자동 생성
public class MemberDTO {

    private String userid;
    private String passwd;
    private String repasswd;
    private String name;
    private String email;
}
