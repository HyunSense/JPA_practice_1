package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    // Test Case에 @Transactional이 붙어있으면 Test가 끝난후 Rollback을 한다.
    // 데이터가 들어가있으면 반복적인 테스트 수행을 할 수가 없기때문
    // Rollback을 원치않으면 @Rollback(false), 기본값 true
    @Test
    @Transactional
    @Rollback(false)
    public void testMember() {
        //given
        Member member = new Member();
        member.setUserName("memberA");

        //when
        Long saveId = memberRepository.save(member);
        Member findMember = memberRepository.find(saveId);

        //then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUserName()).isEqualTo(member.getUserName());

    }
}