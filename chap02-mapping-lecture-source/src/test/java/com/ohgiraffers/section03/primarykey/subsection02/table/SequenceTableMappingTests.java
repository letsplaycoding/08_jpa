package com.ohgiraffers.section03.primarykey.subsection02.table;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.util.List;

public class SequenceTableMappingTests {
    private static EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;

    @BeforeAll
    public static void initFactory() {
        entityManagerFactory = Persistence.createEntityManagerFactory("jpatest");
    }

    @BeforeEach
    public void  initManager() {
        entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterAll
    public static void closeFactory() {
        entityManagerFactory.close();
    }

    @AfterEach
    public void closeManager() {
        entityManager.close();
    }
    @Test
    public void 식별자_매핑_테스트() {

        // given
        com.ohgiraffers.section03.primarykey.subsection02.table.Member member =
                new com.ohgiraffers.section03.primarykey.subsection02.table.Member();
//        member.setMemberNo(1);
        member.setMemberId("user01");
        member.setMemberPwd("pass01");
        member.setNickname("짱구");
        member.setPhone("010-1234-9876");
        member.setEmail("alu9@anevrc.ocm");
        member.setAddress("서울시 칠팔구");
        member.setEnrollDate(new java.util.Date());
        member.setMemberRole("ROLE_MEMBER");
        member.setStatus("Y");

        com.ohgiraffers.section03.primarykey.subsection02.table.Member member2
                = new com.ohgiraffers.section03.primarykey.subsection02.table.Member();
        //        member.setMemberNo(1);
        member2.setMemberId("user2");
        member2.setMemberPwd("pass02");
        member2.setNickname("훈이");
        member2.setPhone("010-1124-5233");
        member2.setEmail("asdds@anevrc.ocm");
        member2.setAddress("서울시 일산구");
        member2.setEnrollDate(new java.util.Date());
        member2.setMemberRole("ROLE_MEMBER");
        member2.setStatus("Y");

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(member);
        entityManager.persist(member2);
        entityTransaction.commit();

        com.ohgiraffers.section03.primarykey.subsection02.table.Member selectedMember = entityManager.find(Member.class,1);
        System.out.println("selectedMember = " + selectedMember);

        String jpql = "SELECT A.memberNo FROM member_section03_subsection02 A";     // 반드시 별칭을 달아야한다=> 컬럼 x, 속성임
        List<Integer> memberNoList = entityManager.createQuery(jpql, Integer.class).getResultList();

        memberNoList.forEach(System.out::println);
    }
}
