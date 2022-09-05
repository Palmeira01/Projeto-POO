/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.java;

/**
 *Classe Publicacao de Artigos de Conferencia que vai herdar os metodos e atributos da classe Publicacoes
 * @author Paulo Meira 2019215095
 */
public class PubArtConferencia extends Publicacoes {

    private String nome;
    private String data;
    private String localizaçaoConferencia;

    public PubArtConferencia() {
    }

    public PubArtConferencia(String nome, String data, String localizaçaoConferencia, String titulo, String palavraChave, String resumo, int anoPublicaçao, int dimensaoAudiencia) {
        super(titulo, palavraChave, resumo, anoPublicaçao, dimensaoAudiencia);
        this.nome = nome;
        this.data = data;
        this.localizaçaoConferencia = localizaçaoConferencia;
        super.setType(type_PubArtConferencia);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getLocalizaçaoConferencia() {
        return localizaçaoConferencia;
    }

    public void setLocalizaçaoConferencia(String localizaçaoConferencia) {
        this.localizaçaoConferencia = localizaçaoConferencia;
    }

    /*
    Fator A -> >=500
    Fator B -> >=200 && <500 
    Fator C -> <200
    */
    @Override
    public String fatorImpacto(int num) {
        String fator = "#";
        if (num >= 500) {
            fator = "A";
        }
        if (num < 500 && num >= 200) {
            fator = "B";
        }
        if (num < 200) {
            fator = "C";
        }
        return fator;
    }

    @Override
    public String toString() {
        String str = super.toString();
        str += "\nNome: " + nome + "\nData: " + data + "\nLocalizacao da Conferencia: " + localizaçaoConferencia + "\n";
        return str;
    }
}
