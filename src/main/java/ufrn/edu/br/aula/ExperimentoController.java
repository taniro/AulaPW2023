package ufrn.edu.br.aula;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@Controller
public class ExperimentoController {

    @RequestMapping(method = RequestMethod.POST, value = "/doLogin")
    public void doLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login.equals("taniro") && password.equals("123")){
            HttpSession session = request.getSession();
            session.setAttribute("logado", true);

            response.sendRedirect("/doTestes");

        }else{
            response.sendRedirect("loginpage.html");
        }

    }


    @RequestMapping(value = "/doTestes", method = RequestMethod.GET)
    public void doTestes(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();

        String pessoa = request.getParameter("pessoa");

        Cookie c = new Cookie("nome", pessoa);
        c.setMaxAge(60);

        response.addCookie(c);

        Cookie[] cookies = request.getCookies();

        for (Cookie c1: cookies){
            response.getWriter().println(c1.getValue());
        }

        /*
        Integer contador = (Integer) session.getAttribute("contador");

        if (contador != null){
            contador = 0;
        }else{
            contador++;
        }
        */

        //LOGIN
        Boolean logado = (Boolean) session.getAttribute("logado");

        if (logado != null && logado == true){
            response.getWriter().println("Logado");
        }else{
            response.getWriter().println("Deslogado");
        }

        //FIM_LOGIN

        //session.setAttribute("contador", contador);

        //response.getWriter().println(contador);
    }

    @RequestMapping(value = "/aula", method = RequestMethod.GET)
    public void exemploRedirecionar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        response.getWriter().println("HOJE TEM AULA");

        //response.sendRedirect("aula");

        /*
        Tarefa t = new Tarefa();
        t.setTexto("experimento");
        t.setPrioridade(1);

        request.setAttribute("tarefa", t);

        RequestDispatcher dispatcher = request.getRequestDispatcher("doListar");
        dispatcher.forward(request, response);

         */
    }
}
