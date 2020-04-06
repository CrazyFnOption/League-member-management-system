package Dao;

import Beans.Member;

import java.util.List;
import java.util.Map;

public interface memberDao  {
    public void addOn(Member member);

    public Member findMember(int id);

    public List<Member> query(String condition, String... args);

    public List<Member> queryByPage(int row,int current,String[] condition,String name);

    public void delete(String condition, String... args);

    public void delete(int id);

    public void update(Member member);

    public Long getNumber(String[] condition,String name);
}
