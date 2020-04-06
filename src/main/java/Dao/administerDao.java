package Dao;

import Beans.Administer;

public interface administerDao {
    public void addOn(Administer administer);

    public Administer findByEmail(String email);

    public Administer findByCode(String code);

    public Administer login(String email, String password);

    public void change(String newPassworld,Administer administer);

}
