package com.example.semiprojectv1.repository;

import com.example.semiprojectv1.domain.MemberDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberRepository {

    @Insert("insert into members (userid, passwd, name, email) values (#{userid}, #{passwd}, #{name}, #{email})")
    int insertMember(MemberDTO member);
}
