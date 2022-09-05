/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.java;

/**
 *Classe Publicacao de Artigos de Revista que vai herdar os metodos e atributos da classe Publicacoes
 * @author Paulo Meira 2019215095
 */
public class PubArtRevista extends Publicacoes {

    private String nome;
    private String data;
    private String localizaçaoRevista;

    public PubArtRevista() {
    }

    public PubArtRevista(String nome, String data, String localizaçaoRevista, String titulo, String palavraChave, String resumo, int anoPublicaçao, int dimensaoAudiencia) {
        super(titulo, palavraChave, resumo, anoPublicaçao, dimensaoAudiencia);
        this.nome = nome;
        this.data = data;
        this.localizaçaoRevista = localizaçaoRevista;
        super.setType(type_PubArtRevista);
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

    public String getLocalizaçaoRevista() {
        return localizaçaoRevista;
    }

    public void setLocalizaçaoRevista(String localizaçaoRevista) {
        this.localizaçaoRevista = localizaçaoRevista;
    }

    /*
    Fator A -> >=1.000
    Fator B -> >=500 && <1.000 
    Fator C -> <500
    */
    @Override
    public String fatorImpacto(int num) {
        String fator = "#";
        if (num >= 1000) {
            fator = "A";
        }
        if (num < 1000 && num >= 500) {
            fator = "B";
        }
        if (num < 500) {
            fator = "C";
        }
        return fator;
    }

    @Override
    public String toString() {
        String str = super.toString();
        str += "\nNome: " + nome + "\nData: " + data + "\nLocalizacao da Revista: " + localizaçaoRevista + "\n";
        return str;
    }
}
