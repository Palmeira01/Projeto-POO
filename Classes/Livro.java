/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.java;

/**
 *Classe Livro que sera uma superclasse, porem vai herdar metodos e atributos da classe Publicacoes
 * @author Paulo Meira 2019215095
 */
public class Livro extends Publicacoes {

    private String editora;
    private String livroISBN;

    public Livro() {
    }

    public Livro(String editora, String livroISBN, String titulo, String palavraChave, String resumo, int anoPublicaçao, int dimensaoAudiencia) {
        super(titulo, palavraChave, resumo, anoPublicaçao, dimensaoAudiencia);
        this.editora = editora;
        this.livroISBN = livroISBN;
        super.setType(type_Livro);
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getLivroISBN() {
        return livroISBN;
    }

    public void setLivroISBN(String livroISBN) {
        this.livroISBN = livroISBN;
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
        str += "\nEditora: " + editora + "\nNumero do ISBN do Livro: " + livroISBN + "\n";
        return str;
    }

}
