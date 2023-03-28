package ufrn.edu.br.aula;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@Controller
public class TarefaController {


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

            //TAREFA PARA CASA
            //tenho um objeto de Tarefa pronto....
            //salvar Tarefa no banco de dados....
            //utilizando JDBC
            //para isso vão precisar adicionar o JDBC como dependencia do projeto Spring

            response.setContentType("text/HTML");
            var writer = response.getWriter();

            writer.println("<html>" +
                    "<body>"+
                    "<h1>" + t.getTexto() + "</h1>"+
                    "<p> Prioridade: " + t.getPrioridade() + "</p>" +
                    "<p> Data" + t.getDataCadastro() + "</p>"
            );

            for (int i = 0 ; i < 10; i++){
                writer.println("<p> Contagem " +
                        i +
                        "</p>");
            }

            writer.println("</body>"+
                    "</html>"
            );

            //LISTAR TODAS AS TAREFAS QUE ESTÃO NO BANCO
        }
}





