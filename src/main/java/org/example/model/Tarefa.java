package org.example.model;

import org.example.utils.DateUtils;

import java.util.UUID;

public class Tarefa {

    private String id;
    private String titulo;
    private String descricao;
    private boolean concluida;
    private String dataCriacao;

    public Tarefa(String titulo, String descricao){
        this.id = UUID.randomUUID().toString();
        this.titulo = titulo;
        this.descricao = descricao;
        this.concluida = false;
        this.dataCriacao = DateUtils.obterTimestampAtual();
    }


    /**
     * Construtor vazio para Jackson converter JSON - OBJECT
     */
    public Tarefa() {
    }


    // GETTERS E SETTERS
    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }


    /**
     * toString para facilitar debug e visualização
     */

    public String toString() {
        return "Tarefa{" +
                "id='" + id + '\'' +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", concluida=" + concluida +
                ", dataCriacao='" + dataCriacao + '\'' +
                '}';
    }

}
