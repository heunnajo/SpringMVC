package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*
동시성 문제가 고려되어있지 않음. 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */

public class MemberRepository {
    //key:id, value:Member
    //static으로 했기 때문에 아무리 MemberRepository가 많아도 딱 하나만 생성된다!?
    private static Map<Long,Member> store = new HashMap<>();
    private static long sequence = 0L;

    private static final MemberRepository instance = new MemberRepository();

    //private 생성자로 외부로부터 생성을 막고 getInstance만을 통해서 객체를 얻을 수 있도록.
    public static MemberRepository getInstance(){
        return instance;
    }

    private MemberRepository(){

    }
    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(),member);
        return member;
    }

    public Member findById(Long id){
        return store.get(id);
    }

    public List<Member> findAll(){
        //store에 있는 값들이 조작되지 않기 위해 배열리스트로 만들어서 넘겨주도록한다!
        //물론 참조값을 통해 수정이 가능하기도 하지만 store 자체를 보호하기 위함이라고 함.
        return new ArrayList<>(store.values());
    }
    public void clearStore(){
        store.clear();
    }
}
