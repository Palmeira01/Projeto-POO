/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.java;

/**
 *Classe Membro Efetivo que vai herdar os metodos e atributos da classe Investigadores
 * @author Paulo Meira 2019215095
 */
public class MembroEfetivo extends Investigadores{
    private String numeroGabinete;
    private String numeroTelefoneDEI;
    
    public MembroEfetivo(){
    }

    public MembroEfetivo(String nome, String email, String grupoInvestigaçao, String numeroGabinete, String numeroTelefoneDEI) {
        super(nome, email, grupoInvestigaçao);
        this.numeroGabinete = numeroGabinete;
        this.numeroTelefoneDEI = numeroTelefoneDEI;
        super.setType(type_MembroEfetivo);
    }

    public String getNumeroGabinete() {
        return numeroGabinete;
    }

    public void setNumeroGabinete(String numeroGabinete) {
        this.numeroGabinete = numeroGabinete;
    }

    public String getNumeroTelefoneDEI() {
        return numeroTelefoneDEI;
    }

    public void setNumeroTelefoneDEI(String numeroTelefoneDEI) {
        this.numeroTelefoneDEI = numeroTelefoneDEI;
    }
    
    @Override
    public String nomePublicacao(String nome) {
        String[] n = nome.split(" ");
        String str = "Professor "+n[0]+" "+n[n.length-1];           //inicialmente adiciona "Professor" e depois so adiciona o primeiro e ultimo nome
        return str;
    }
    
    
    public String toString(){
        String str = super.toString();
        str += "\nNumero de Gabinete: "+numeroGabinete+"\nNumero de telefone do DEI: "+numeroTelefoneDEI+"\n";
        return str;
    }
    
}
