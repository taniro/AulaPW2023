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


        @RequestMapping(method = RequestMethod.GET, value = "/doEditarPage")
        public void doEditarPage(HttpServletRequest request, HttpServletResponse response) throws IOException {

            var id = Integer.parseInt(request.getParameter("id"));
            var dao = new TarefaDao();

            Tarefa t = dao.getTarefaById(id);

            var writer = response.getWriter();

            writer.println("<html>" +
                    "<body>" +
                    "<form action='doAtualizar' method='post'>");
            writer.println("<input type='hidden' name='id' value='"+t.getId()+"'>");
            writer.println("<input type='text' name='texto' value='"+t.getTexto()+"'>");
            writer.println("<input type='number' name='prioridade' value='"+t.getPrioridade()+"'>");
            writer.println("<input type='hidden' name='dataCadastro' value='"+t.getDataCadastro().getTime()+"'>");
            writer.println("<button type='submit'>Enviar</button");


            writer.println("</form>" +
                    "</body>" +
                    "<html>");
        }
        @RequestMapping(method = RequestMethod.POST, value = "/doAtualizar")
        public void doAtualizar(HttpServletRequest request, HttpServletResponse response) throws IOException {

            var id = Integer.parseInt(request.getParameter("id"));
            var texto = request.getParameter("texto");
            var prioridade = Integer.parseInt(request.getParameter("prioridade"));
            var dataCadastro = Long.parseLong(request.getParameter("dataCadastro"));

            var tarefa = new Tarefa(id, texto, prioridade, new Date(dataCadastro));

            var dao = new TarefaDao();

            dao.updateTarefa(tarefa);

            response.sendRedirect("doListar");
        }

        @RequestMapping(value = "/doBuscar", method = RequestMethod.GET)
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

        @RequestMapping( method = RequestMethod.POST, value = "/doCadastrar")
        public void cadastraTarefa(HttpServletRequest request, HttpServletResponse response) throws IOException {
            var t = new Tarefa();
            var texto = request.getParameter("texto");
            var prioridade = Integer.parseInt(request.getParameter("prioridade"));

            //var prefs = request.getParameterValues("prefs");
            //System.out.println(prefs);

            /*
            for (String s:prefs) {
                System.out.println(s);
            }*/

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
            writer.println("</body>"+
                    "</html>"
            );

        }

        @RequestMapping(method = RequestMethod.GET, value = "/doListar")
        public void listarTarefas(HttpServletRequest request, HttpServletResponse response) throws IOException {

            /*Tarefa t = (Tarefa) request.getAttribute("tarefa");
            System.out.println(t.toString());*/

            var dao = new TarefaDao();
            var writer = response.getWriter();

            String browser = request.getHeader("pipoca");


            var listarTarefas = dao.listarTodasTarefas();
            response.setContentType("text/HTML");

            writer.println("<html>" +
                    "<body>");
            writer.println(browser);

            for (var t1 : listarTarefas){
                writer.println("<p>" +t1.getTexto() + "</p>");
                writer.println("<p>" +t1.getPrioridade() + "</p>");
                writer.println("<p>" + t1.getDataCadastro() + "</p> ");
                writer.println("<a href='doEditarPage?id="+t1.getId()+"'>Editar</a>");
                writer.println("<a href='doExcluir?id="+t1.getId()+"'>Excluir</a>");
            }

            writer.println("</html>" +
                    "</body>");
        }
}





