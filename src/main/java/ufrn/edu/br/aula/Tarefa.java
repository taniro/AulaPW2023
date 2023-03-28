package ufrn.edu.br.aula;

import java.util.Date;

public class Tarefa {
    private String texto;
    private Integer prioridade;
    private final Date dataCadastro;

    public Tarefa(){
        this.dataCadastro = new Date();
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Integer getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Integer prioridade) {
        this.prioridade = prioridade;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }
}
