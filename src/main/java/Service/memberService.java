package Service;

import Beans.Member;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface memberService {


    public void addOnByFile(File file);

    public Long getAllNumber(String[] condition,String name);

    public List<Member> query(int row, int current, String[] map,String name);

    public Member findMemberById(int id);

    public void delete(int id);

    public void update(Member member);

    public void deleteBySnos(String... snos);

    public void deleteByMajor(String... majors);

    public void deleteBySdepts(String... sdepts);

    public void deleteByClasses(String...classes);
}
