package Service;

import Beans.Administer;
import Dao.administerDaoIm;
import Utils.JavaMailUtils;
import Utils.UUIDUtils;

public class administerServiceIm implements administerService {
    private administerDaoIm dao = new administerDaoIm();

    @Override
    public void reigster(Administer administer) {
        administer.setStatus("N");
        administer.setCode(UUIDUtils.getId());
        dao.addOn(administer);
        JavaMailUtils.sendEmail("smtp.qq.com", "785988220@qq.com", "eyfjkbycpxbpbeha", "785988220@qq.com", new String[]{
                        administer.getEmail() //这里就是一系列的收件人的邮箱了
                }, "团员管理系统管理员身份信息验证", "<h1>欢迎你注册团员管理系统，有幸成为我们的一员</h1>\n\n\n" +
                        "<h3>愿你一生温暖纯良，不舍爱与自由......</h3>\n" +
                        "请点击一下的网址来帮助激活\n\n\n" +
                        "<br><a href='localhost:8088/wsx/active?code="+administer.getCode()+"'>激活团员管理系统资格</a></br>\n\n\n\n" +
                        "该网址就是 localhost:8088/wsx/active?code=" + administer.getCode(),
                "text/html;charset=utf-8");
    }

    @Override
    public Administer login(String email,String password) {
        Administer login = dao.login(email, password);
        return login;
    }

    @Override
    public boolean isUsed(String email) {
        Administer byEmail = dao.findByEmail(email);
        if (byEmail != null) return true;
        return false;
    }

    @Override
    public void changePasswolrd(String newPasswolrd,Administer administer) {
        dao.change(newPasswolrd,administer);
    }

}
