/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.java;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *Classe Grupo de Investigacao que será uma superclasse
 * @author Paulo Meira 2019215095
 */
public class GrupoInvestigacao implements Serializable{
    private String nome;
    private String acronimo;
    private String membroEfetivo;
    
    private ArrayList<Investigadores>investigadores;
    private ArrayList<Publicacoes>publicacoes;
    
    public GrupoInvestigacao(){
        investigadores = new ArrayList<>();
        publicacoes = new ArrayList<>();
    }

    public GrupoInvestigacao(String nome, String acronimo, String membroEfetivo) {
        this();
        this.nome = nome;
        this.acronimo = acronimo;
        this.membroEfetivo = membroEfetivo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAcronimo() {
        return acronimo;
    }

    public void setAcronimo(String acronimo) {
        this.acronimo = acronimo;
    }

    public String getMembroEfetivo() {
        return membroEfetivo;
    }

    public void setMembroEfetivo(String membroEfetivo) {
        this.membroEfetivo = membroEfetivo;
    }

    public ArrayList<Investigadores> getInvestigadores() {
        return investigadores;
    }

    public void setInvestigadores(Investigadores investigadores) {
        this.investigadores.add(investigadores);
    }

    public ArrayList<Publicacoes> getPublicacoes() {
        return publicacoes;
    }

    /**
     * adiciona a publicacao as publicacoes do Grupo de Investigacao
     * @param publicacoes objeto que corresponde á publicacao a ser adicionada ao ArrayList
     */
    public void setPublicacoes(Publicacoes publicacoes) {
        this.publicacoes.add(publicacoes);
    }
    
    @Override
    public String toString(){
        String str = "";
        str += "Nome: "+nome+"\nAcronimo: "+acronimo+"\nMembro efetivo: "+membroEfetivo;
        return str;
    }
}
