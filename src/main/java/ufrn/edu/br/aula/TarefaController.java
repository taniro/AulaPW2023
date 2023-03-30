package ufrn.edu.br.aula;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.Date;

@Controller
public class TarefaController {


        @RequestMapping(value = "/doBuscar", method = RequestMethod.POST)
        public void doBuscar(HttpServletRequest request, HttpServletResponse response) throws IOException {
            var id = Integer.parseInt(request.getParameter("id"));

            var dao = new TarefaDao();

            Tarefa t = dao.getTarefaById(id);

            var writer = response.getWriter();
            writer.println("<html>");
            writer.println("<body>");
            if (t != null){
                writer.println("<hr /> <p>" +t.getTexto() + "</p>");
                writer.println("<p>" +t.getPrioridade() + "</p>");
                writer.println("<p>" + t.getDataCadastro() + "</p>");
            }else{
                writer.println("<p> Não encontrado </p>");
            }
            writer.println("</body>");
            writer.println("</html>");
        }
        @RequestMapping(method = RequestMethod.GET, value = "/cadastrar")
        public void getTarefas(HttpServletRequest request, HttpServletResponse response) throws IOException {
            response.getWriter().println("Acesse o formulário de cadastro");
        }

        @RequestMapping( method = RequestMethod.POST, value = "/cadastrar")
        public void cadastraTarefa(HttpServletRequest request, HttpServletResponse response) throws IOException {
            var t = new Tarefa();
            var texto = request.getParameter("texto");
            var prioridade = Integer.parseInt(request.getParameter("prioridade"));

            t.setTexto(texto);
            t.setPrioridade(prioridade);

            TarefaDao dao = new TarefaDao();
            dao.cadastrarTarefa(t);

            response.setContentType("text/HTML");
            var writer = response.getWriter();

            writer.println("<html>" +
                    "<body>"+
                    "<h1>" + t.getTexto() + "</h1>"+
                    "<p> Prioridade: " + t.getPrioridade() + "</p>" +
                    "<p> Data" + t.getDataCadastro() + "</p>"
            );

            var listarTarefas = dao.listarTodasTarefas();

            for (var t1 : listarTarefas){
                writer.println("<hr /> <p>" +t1.getTexto() + "</p>");
                writer.println("<p>" +t1.getPrioridade() + "</p>");
                writer.println("<p>" + t1.getDataCadastro() + "</p>");
            }

            writer.println("</body>"+
                    "</html>"
            );

        }
}





