/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.java;

/**
 *Classe Livros Artigos de Conferencia que vai herdar os metodos e atributos da classe Livro
 * @author Paulo Meira 2019215095
 */
public class LivroArtConferencia extends Livro {

    private String nomeConferencia;
    private int numeroArtigos;

    public LivroArtConferencia() {
    }

    public LivroArtConferencia(String nomeConferencia, int numeroArtigos, String editora, String livroISBN, String titulo, String palavraChave, String resumo, int anoPublicaçao, int dimensaoAudiencia) {
        super(editora, livroISBN, titulo, palavraChave, resumo, anoPublicaçao, dimensaoAudiencia);
        this.nomeConferencia = nomeConferencia;
        this.numeroArtigos = numeroArtigos;
        super.setType(type_LivroArtConferencia);
    }

    public String getNomeConferencia() {
        return nomeConferencia;
    }

    public void setNomeConferencia(String nomeConferencia) {
        this.nomeConferencia = nomeConferencia;
    }

    public int getNumeroArtigos() {
        return numeroArtigos;
    }

    public void setNumeroArtigos(int numeroArtigos) {
        this.numeroArtigos = numeroArtigos;
    }

    /*
    Fator A -> >=7.500
    Fator B -> >=2.500 && <7.500 
    Fator C -> <2.500
    */
    @Override
    public String fatorImpacto(int num) {
        String fator = "#";
        if (num >= 7500) {
            fator = "A";
        }
        if (num < 5500 && num >= 2500) {
            fator = "B";
        }
        if (num < 2500) {
            fator = "C";
        }
        return fator;
    }

    @Override
    public String toString() {
        String str = "";
        str += super.toString();
        str += "Nome da Conferencia: " + nomeConferencia + "\nNumero de Artigos: " + numeroArtigos + "\n";
        return str;
    }
}
