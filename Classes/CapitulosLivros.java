/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.java;

/**
 *Classe Capitulos de Livros que vai herdar os metodos e atributos da classe Livro
 * @author Paulo Meira 2019215095
 */
public class CapitulosLivros extends Livro {
    private String nomeCapitulo;
    private int pagInicio;
    private int pagFim;

    public CapitulosLivros() {
    }

    public CapitulosLivros(String nomeCapitulo, int pagInicio, int pagFim, String editora, String livroISBN, String titulo, String palavraChave, String resumo, int anoPublicaçao, int dimensaoAudiencia) {
        super(editora, livroISBN, titulo, palavraChave, resumo, anoPublicaçao, dimensaoAudiencia);
        this.nomeCapitulo = nomeCapitulo;
        this.pagInicio = pagInicio;
        this.pagFim = pagFim;
        super.setType(type_CapitulosLivros);
    }

    public String getNomeCapitulo() {
        return nomeCapitulo;
    }

    public void setNomeCapitulo(String nomeCapitulo) {
        this.nomeCapitulo = nomeCapitulo;
    }

    public int getPagInicio() {
        return pagInicio;
    }

    public void setPagInicio(int pagInicio) {
        this.pagInicio = pagInicio;
    }

    public int getPagFim() {
        return pagFim;
    }

    public void setPagFim(int pagFim) {
        this.pagFim = pagFim;
    }
    
    /*
    Fator A -> >=10.000
    Fator B -> >=5.000 && <10.000 
    Fator C -> <5.000
    */
    @Override
    public String fatorImpacto(int num) {
        String fator = "#";
        if (num >= 10000) {
            fator = "A";
        }
        if (num < 10000 && num >= 5000) {
            fator = "B";
        }
        if (num < 5000) {
            fator = "C";
        }
        return fator;
    }

    @Override
    public String toString() {
        String str = "";
        str += super.toString();
        str += "Nome do Capitulo: " + nomeCapitulo + "\nPagina Inicial: " + pagInicio + "\nPagina Final: " + pagFim + "\n";
        return str;
    }

}
