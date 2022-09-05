/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.java;

/**
 *Classe Estudante que vai herdar os metodos e atributos da classe Investigadores
 * @author Paulo Meira 2019215095
 */
public class Estudante extends Investigadores{
    private String tituloTese;
    private String dataConclusao;
    private String InvestigadorOrientador;
    
    public Estudante(){
    }
    
    public Estudante( String nome, String email, String grupoInvestigaçao, String tituloTese, String dataConclusao, String InvestigadorOrientador) {
        super(nome, email, grupoInvestigaçao);
        this.tituloTese = tituloTese;
        this.dataConclusao = dataConclusao;
        this.InvestigadorOrientador = InvestigadorOrientador;
        super.setType(type_Estudante);
    }

    public String getTituloTese() {
        return tituloTese;
    }

    public void setTituloTese(String tituloTese) {
        this.tituloTese = tituloTese;
    }

    public String getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(String dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public String getInvestigadorOrientador() {
        return InvestigadorOrientador;
    }

    public void setInvestigadorOrientador(String InvestigadorOrientador) {
        this.InvestigadorOrientador = InvestigadorOrientador;
    }
    
    @Override
    public String nomePublicacao(String nome) {
        String[] n = nome.split(" ");
        String str = nome.charAt(0)+". "+n[n.length-1];             //vai buscar a primeira letra do primeiros nome, adiciona um ponto e por ultimo adiciona o ultimo nome
        return str;
    }
    
    @Override
    public String toString(){
        String str = super.toString();
        str += "\nTitulo da tese: "+tituloTese+"\nData de conclusao: "+dataConclusao+"\nInvestigador Orientador: "+InvestigadorOrientador+"\n";
        return str;
    }

    
}
