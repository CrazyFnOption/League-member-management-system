package Servlet;

import Beans.Member;
import Beans.pageList;
import Service.memberServiceIm;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@WebServlet("/member/*")
public class memberServlet extends BaseServlet {
    private memberServiceIm service = new memberServiceIm();

    public void listshow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        Map<String, String[]> map = request.getParameterMap();
        String curr = map.get("current")[0];
        String srow = map.get("row")[0];
        String name = request.getParameter("ob");
        HttpSession session = request.getSession();

        String ob = (String) session.getAttribute("ob");
        String com = (String) session.getAttribute("com");

        String tmp = null;
        String[] res = null;
        if (name != null && !name.equals("")){
            tmp = URLDecoder.decode(request.getParameter("com"), StandardCharsets.UTF_8);
            System.out.println(tmp);
            res = tmp.split("-");
        }
        else if (ob != null && !ob.equals("")) {
            name = ob;
            res = com.split("-");
        }

        if (curr == null) curr = "1";
        if (srow == null) srow = "8";

        int current = Integer.parseInt(curr);
        int row = Integer.parseInt(srow);
        row = 10;
        int total = service.getAllNumber(res,name).intValue();

        int pages = total / row;
        if (total % row != 0) pages++;

//        request.removeAttribute("pageList");
        List<Member> query = service.query(row, current, res,name);
        pageList<Member> pageList = new pageList<>(total,current,pages,row,query);


        request.setAttribute("pageList",pageList);
        request.getRequestDispatcher("/jsp/ShowAll.jsp").forward(request,response);
    }


    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String srow = request.getParameter("row");
        int row = Integer.parseInt(srow);
        String scurrent = request.getParameter("current");
        int current = Integer.parseInt(scurrent);
        String sid = request.getParameter("id");
        service.delete(Integer.parseInt(sid));
        response.sendRedirect(request.getContextPath() + "/member/listshow?row="+row+"&current="+current);
    }

    public void findMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sid = request.getParameter("id");
        Member member = service.findMemberById(Integer.parseInt(sid));
        request.setAttribute("member",member);
        request.getRequestDispatcher("/jsp/update.jsp").forward(request,response);
    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Map<String, String[]> map = request.getParameterMap();
        String sid = request.getParameter("id");
        Member member = new Member();
        member.setId(Integer.parseInt(sid));

        try {
            BeanUtils.populate(member,map);
            service.update(member);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/member/listshow?row=7&current=1");
    }
}
