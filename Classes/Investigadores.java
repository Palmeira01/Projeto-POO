/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.java;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *Classe Investigadores que sera uma superclasse com metodos abstract
 * @author Paulo Meira 2019215095
 */
public abstract class Investigadores implements Serializable{
    private String nome;
    private String email;
    private String grupoInvestigacao;
    private String type;
    
    public static final String type_Estudante = "Estudante";
    public static final String type_MembroEfetivo = "Membro Efetivo";
    
    private ArrayList<Publicacoes>publicacoes;
    
    public Investigadores(){
        publicacoes = new ArrayList<>();
    }

    public Investigadores(String nome, String email, String grupoInvestigacao) {
        this();
        this.nome = nome;
        this.email = email;
        this.grupoInvestigacao = grupoInvestigacao;
    }
    
    /**
     * Metodo que vai usar o nome de entrada para o converter no nome de Publicacao que vai estar presente nos detalhes da Publicacao
     * @param nome String que corresponde ao nome do Investigador
     * @return Nome de Publicacao do Investigador 
     */
    public abstract String nomePublicacao(String nome);

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGrupoInvestigacao() {
        return grupoInvestigacao;
    }

    public void setGrupoInvestigacao(String grupoInvestigacao) {
        this.grupoInvestigacao = grupoInvestigacao;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Publicacoes> getPublicacoes() {
        return publicacoes;
    }

    /**
     * adiciona a publicacao as publicacoes do investigador
     * @param publicacoes objeto que corresponde á publicacao a ser adicionada ao ArrayList
     */
    public void setPublicacoes(Publicacoes publicacoes) {
        this.publicacoes.add(publicacoes);
    }
    
    @Override
    public String toString(){
        String str = "";
        str += "Nome: "+nome+"\nEmail: "+email+"\nGrupo de Investigacao: "+grupoInvestigacao;
        return str;
    }
    
}
