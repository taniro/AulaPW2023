package ufrn.edu.br.aula;

import java.util.Date;

public class Tarefa {

    private Integer id;
    private String texto;
    private Integer prioridade;
    private final Date dataCadastro;

    public Tarefa(Integer id, String texto, Integer prioridade, Date dataCadastro) {
        this.id = id;
        this.texto = texto;
        this.prioridade = prioridade;
        this.dataCadastro = dataCadastro;
    }

    public Tarefa(){
        this.dataCadastro = new Date();
    }

    public Tarefa(Date data, Integer id){
        this.id = id;
        this.dataCadastro = data;
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

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Tarefa{" +
                "id=" + id +
                ", texto='" + texto + '\'' +
                ", prioridade=" + prioridade +
                ", dataCadastro=" + dataCadastro +
                '}';
    }
}
