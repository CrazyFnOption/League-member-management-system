package Servlet;

import Beans.Administer;
import Service.administerServiceIm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@WebServlet("/administer/*")
public class administerServlet extends BaseServlet {
    private ObjectMapper mapper = new ObjectMapper();
    private administerServiceIm service = new administerServiceIm();

    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        Map<String, String[]> map = request.getParameterMap();
        Administer administer = new Administer();
        try {
            BeanUtils.populate(administer,map);
            service.reigster(administer);
            Map<String,Object> mmap = new HashMap<>();
            mmap.put("res",true);
            mapper.writeValue(response.getWriter(),mmap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void checkEmail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        boolean used = service.isUsed(email);
        Map<String,Object> map = new HashMap<>();

        if (used) map.put("exist",true);
        else map.put("exist",false);
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getWriter(),map);
    }

    public void checkcc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        HttpSession session = request.getSession();
        String checkCode = (String) session.getAttribute("checkCode");
        session.removeAttribute("checkCode");
        String code = request.getParameter("Code");
        Map<String,Object> map = new HashMap<String,Object>();
        if (code.equalsIgnoreCase(checkCode)) {
            map.put("res",true);
            map.put("msg","验证码正确");

        }else {
            map.put("res",false);
            map.put("msg","验证码错误");
        }
        mapper.writeValue(response.getWriter(),map);
    }
    public void checkCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String checkCode = "abcdefghijklmnopqrstuvwxyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        Random random = new Random();

        int width = 100;
        int height = 40;
        BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        Graphics g = bufferedImage.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);

        g.setColor(Color.YELLOW);
        g.drawRect(0, 0, width - 1, height - 1);
        HttpSession session = request.getSession();
        StringBuilder stringBuilder = new StringBuilder();
        g.setColor(Color.YELLOW);
        g.setFont(new Font("黑体", Font.BOLD,24));
        for (int i = 1; i <= 4; i++) {
            char ch = checkCode.charAt(random.nextInt(checkCode.length()));
            int x = random.nextInt(5);
            if (x % 2 == 0) x = -x ;
            g.drawString(ch + "",  width / 5 * i , height / 2 + 10 + x);
            stringBuilder.append(ch);
        }
        g.setColor(Color.lightGray);
        for (int i = 0; i < 5; i++) {
            g.drawLine(random.nextInt(width),random.nextInt(height),random.nextInt(width),random.nextInt(height));
        }
        session.setAttribute("checkCode", stringBuilder.toString());
        ImageIO.write(bufferedImage,"jpg",response.getOutputStream());

    }

    public void findAdministerName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("application/json;charset=utf-8");
        HttpSession session = request.getSession();
        Administer administer = (Administer) session.getAttribute("administer");
        Map<String,Object>map = new HashMap<>();

        if (administer != null) {
            map.put("res",true);
            map.put("name",administer.getName());
        }
        else map.put("res",false);
        mapper.writeValue(response.getWriter(),map);
    }

    public void LogOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        HttpSession session = request.getSession();
        session.removeAttribute("administer");
        response.sendRedirect("/wsx/home.html");
    }
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        Administer login = service.login(email, password);
        Map<String,Object> map = new HashMap<>();
        if (login == null) {
            map.put("res",false);
            map.put("msg","邮箱或密码填写错误，请更改...");
        }
        else if (login.getStatus().equalsIgnoreCase("N")) {
            map.put("res",false);
            map.put("msg","该邮箱还没有被激活，请去查阅邮件激活...");

        }
        else {
            map.put("res",true);
            session.setAttribute("administer",login);
        }
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getWriter(),map);
    }


    public void changePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        HttpSession session = request.getSession();
        Administer administer = (Administer) session.getAttribute("administer");
        String newPasswolrd = request.getParameter("newPasswolrd");
        service.changePasswolrd(newPasswolrd,administer);
        Map<String,Object>map = new HashMap<>();
        map.put("res",true);
        mapper.writeValue(response.getWriter(),map);
    }
}
