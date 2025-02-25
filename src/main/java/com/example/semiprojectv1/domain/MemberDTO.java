package com.example.semiprojectv1.domain;

import lombok.Builder;
import lombok.Data;

@Data   // setter, getter, toString 자동 생성
@Builder
public class MemberDTO {

    private String userid;
    private String passwd;
    private String name;
    private String email;
}
