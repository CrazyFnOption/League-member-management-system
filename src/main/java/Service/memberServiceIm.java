package Service;

import Beans.Member;
import Dao.memberDaoIm;

import java.io.File;
import java.util.List;
import java.util.Map;

public class memberServiceIm implements memberService {
    private memberDaoIm dao = new memberDaoIm();

    @Override
    public void addOnByFile(File file) {

    }

    @Override
    public Long getAllNumber(String[] condition,String name) {
        return dao.getNumber(condition,name);
    }

    @Override
    public List<Member> query(int row, int current, String[] map,String name) {
        return dao.queryByPage(row,current,map,name);
    }

    @Override
    public Member findMemberById(int id) {
        return dao.findMember(id);
    }

    @Override
    public void delete(int id) {
        dao.delete(id);
    }

    @Override
    public void update(Member member) {


        Member mm = dao.findMember(member.getId());
        if (mm == null) dao.addOn(member);
        else dao.update(member);
    }


    @Override
    public void deleteBySnos(String... snos) {

    }

    @Override
    public void deleteByMajor(String... majors) {

    }

    @Override
    public void deleteBySdepts(String... sdepts) {

    }

    @Override
    public void deleteByClasses(String... classes) {

    }
}
