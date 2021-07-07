package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

//JUnit5 부터는 public 생략 가능함.
class MemberRepositoryTest {
    //싱글톤이기 때문에 new로 하면 안된다.
    MemberRepository memberRepository = MemberRepository.getInstance();

    //실행되는 메섣드 순서는 보장X이므로 AfterEach로 꼭 초기화를 해주어야 원하는 테스트결과가 나온다!
    @AfterEach
    void afterEach(){
        memberRepository.clearStore();
    }
    @Test
    void save(){
        //given
        Member member = new Member("hello",20);
        
        //when
        Member savedMember = memberRepository.save(member);

        //then
        Member findMember = memberRepository.findById(savedMember.getId());
        assertThat(findMember).isEqualTo(savedMember);
    }

    @Test
    void findAll(){
        //find
        Member member1 = new Member("member1",20);
        Member member2 = new Member("member2",30);
        
        memberRepository.save(member1);
        memberRepository.save(member2);
        //when
        List<Member> result = memberRepository.findAll();
        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(member1,member2);
    }
}
