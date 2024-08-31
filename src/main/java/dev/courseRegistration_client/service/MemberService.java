package dev.courseRegistration_client.service;

import dev.courseRegistration_client.Repository.MemberRepository;
import dev.courseRegistration_client.model.Member;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberService {
    private MemberRepository memberRepository= new MemberRepository();
    public Member login(String email, String password) {
        Member member = memberRepository.findByEmailAndPassword(email, password);
        if (member != null) {
                return member;
        } else {
            System.out.println("이메일 또는 비밀번호가 잘못되었습니다.");
            return null;
        }
    }
    public Member get(String email)  {
    	return memberRepository.findByEmail(email);
    }
}

