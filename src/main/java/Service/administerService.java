package Service;

import Beans.Administer;

public interface administerService {

    public void reigster(Administer administer);

    public Administer login(String email,String password);

    public boolean isUsed(String email);

    public void changePasswolrd(String newPasswolrd,Administer administer);
}
