/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.java;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *Classe Publicacoes que será uma superclasse
 * @author Paulo Meira 2019215095
 */
public abstract class Publicacoes implements Comparable<Publicacoes>, Serializable{
    
    private String titulo;
    private String palavraChave;
    private int anoPublicacao;
    private int dimensaoAudiencia;
    private String resumo;
    private String type;
    
    public static final String type_PubArtRevista = "Publicacao artigo de revista";
    public static final String type_PubArtConferencia = "Publicacao artigo de conferencia";
    public static final String type_Livro = "Livro";
    public static final String type_CapitulosLivros = "Capitulos de Livros";
    public static final String type_LivroArtConferencia = "Livro de artigos de Conferencia";
    
    protected ArrayList<Investigadores>autores;
            
    public Publicacoes(){
        autores = new ArrayList<>();
    }

    public Publicacoes(String titulo, String palavraChave, String resumo, int anoPublicacao, int dimensaoAudiencia) {
        this();
        this.titulo = titulo;
        this.palavraChave = palavraChave;
        this.anoPublicacao = anoPublicacao;
        this.resumo = resumo;
        this.dimensaoAudiencia = dimensaoAudiencia;
    }

    /**
     * Metodo que converte a Dimensao da Audiencia no Fator de Impacto conforme cada tipo de Publicacao
     * @param num inteiro que corresponde a dimensao da audiencia
     * @return Fator de Impacto (A, B ou C)
     */
    public abstract String fatorImpacto(int num);
    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPalavraChave() {
        return palavraChave;
    }

    public void setPalavraChave(String palavraChave) {
        this.palavraChave = palavraChave;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public int getDimensaoAudiencia() {
        return dimensaoAudiencia;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDimensaoAudiencia(int dimensaoAudiencia) {
        this.dimensaoAudiencia = dimensaoAudiencia;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }
    
    public ArrayList<Investigadores> getAutores() {
        return autores;
    }

    /**
     * adiciona o investigador a ArrayList dos autores da Publicacao
     * @param investigador objeto que corresponde ao investigador
     */
    public void setAutores(Investigadores investigador) {
        autores.add(investigador);
    }
    
    /**
     * Metodo que vai devolver o nome dos Autores da Publicacao
     * @return string com os nomes dos Autores da Publicacao 
     */
    public String getNomeAutores() {
        String str = "";
        for (Investigadores inv: autores){
            str += inv.getNome()+"\n";
        }
        return str;
    }
   
    /**
     * Metodo que vai organizar as publicacoes primeiramente por ano, depois por tipo de publicação e por fim por fator de impacto
     * @param p objeto que corresponde à publicacao que vai ser comparada
     * @return inteiro que vai indicar se o obejto (publcacao) é maior ou menor e atravez disso vai ordenar a ArrayList
     */
    public int compareTo(Publicacoes p){
        int num;
        num = this.anoPublicacao-p.anoPublicacao;
        
        if (num==0){
            num = this.type.compareTo(p.type);
        }
        
        if (num==0){
            num = fatorImpacto(this.dimensaoAudiencia).compareTo(fatorImpacto(p.dimensaoAudiencia))*-1;             //para ficar com a ordem de menor audiencia para maior
        }
        
        return num;
    }
    
    public String toString(){
        String str = "";
        str += type+"\n"+titulo;
        str += "\nAutores:\n";
        for (Investigadores i: autores){
            str+="\t"+i.getNome()+"\n";
        }
        str += "Palavras Chave: "+palavraChave+"\nResumo: "+resumo+"\nAno de Publicacao: "+anoPublicacao+"\nFator de Impacto: "+fatorImpacto(dimensaoAudiencia);
        return str;
    }
    
}
