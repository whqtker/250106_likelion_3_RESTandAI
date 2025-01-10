package ai.OpenAI.domain.member.repository;

import ai.OpenAI.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByUserName(String userName);
    Optional<Member> findByRefreshToken(String refreshToken);
}
