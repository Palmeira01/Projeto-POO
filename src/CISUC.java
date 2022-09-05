/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *Classe Principal
 * @author Paulo Meira 2019215095
 */
public class CISUC {
    ArrayList<GrupoInvestigacao> grupos;
    ArrayList<Investigadores> investigadores;
    ArrayList<Publicacoes> publicacoes;

    String date = "1/1/2021";
    String[] data = date.split("/");
    
    int diaAtual = Integer.parseInt(data[0]);
    int mesAtual = Integer.parseInt(data[1]);
    int anoAtual = Integer.parseInt(data[2]);
    
    String typeInv[] = {Investigadores.type_Estudante, Investigadores.type_MembroEfetivo};
    String impacto[] = {"C", "B", "A"};
    String tipoPublicacoes[] = {"Publicacao artigo de revista", "Publicacao artigo de conferencia",
                                "Livro", "Capitulos de Livros", "Livro de artigos de Conferencia"};

    int ultimosAnos = 5;            //caso o programador decida mudar por exemplo as publicacoes dos ultimos 5 anos para os ultimos 6 basta alterar esta var de 5 para 6

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new CISUC();
    }
    
    /**
     * Metodo que corresponde ao Menu que tem como funcao organizar as seções internas de modo a facilitar e efetuar o pedido do utilizador.
     */
    private void menu() {
        int escolha;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("\n\n===== MENU =====");
            System.out.println("1 - Lista dos Indicadores gerais do CISUC");
            System.out.println("2 - Lista de Publicacoes organizadas de um Grupo de Investigacao nos ultimos anos");
            System.out.println("3 - Lista dos Membros de um grupo de investigacao agrupada");
            System.out.println("4 - Lista de Publicacoes de um Investigador");
            System.out.println("5 - Lista dos grupos de Investigacao");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            escolha = sc.nextInt();
            System.out.println("\n\n");
            switch (escolha) {
                case 1:
                    indicadoresCISUC();
                    break;
                    
                case 2:
                    System.out.print("Insira o nome do grupo: ");
                    String gr = sc.next();
                    System.out.println("");
                    publicacoesGruposUltimosAnos(gr);
                    break;
                    
                case 3:
                    System.out.print("Insira o nome do grupo: ");
                    String gr2 = sc.next();
                    System.out.println("");
                    membrosGrupos(gr2);
                    break;
                    
                case 4:
                    System.out.print("Insira o nome do Investigador: ");
                    sc.nextLine();                                          //correcao do erro do nextLine que nao aceita a escrita de duas palavras
                    String gr3 = sc.nextLine();
                    System.out.println("");
                    agrupamentoInvestigadores(gr3);
                    break;
                    
                case 5:
                    listarGrupos();
                    break;
                    
                case 0:
                    
            }
        } while (escolha != 0);             //caso o utilizador insira 0, este sai do menu
    }
    
    /**
     * Verificacao dos ficheiros de entrada e caso nao existam ficheiros de Objetos, os dados vao ser carregados a partir dos ficheiros de texto.
     * @param fog nome do ficheiro de objetos dos Grupos de Investigacao
     * @param foi nome do ficheiro de objetos dos Investigadores
     * @param fop nome do ficheiro de objetos das Publicacoes
     * @param ftg nome do ficheiro de texto dos Grupos de Investigacao
     * @param fti nome do ficheiro de texto dos Investigadores
     * @param ftp nome do ficheiro de texto das Publicacoes
     */
    private void leituraFicheiros(String fog, String foi, String fop, String ftg, String fti, String ftp) {
        if (existeFicObj(foi) == true) {
            lerFicObjInves(foi);
            System.out.println("Dados dos Investigadores carregados a partir do ficheiro de objetos");
        } else {
            lerFictxtInves(fti);
            System.out.println("Dados dos Investigadores carregados a partir do ficheiro de texto");
        }
        
        if (existeFicObj(fog) == true) {
            lerFicObjGrupos(fog);
            System.out.println("Dados dos Grupos de Investigacao carregados a partir do ficheiro de objetos");
        } else {
            lerFictxtGrupos(ftg);
            System.out.println("Dados dos Grupos de Investigacao carregados a partir do ficheiro de texto");
        }
        
        if (existeFicObj(fop) == true) {
            lerFicObjPublicacoes(fop);
            System.out.println("Dados das Publicacoes carregados a partir do ficheiro de objetos");
        } else {
            lerFictxtPublicacoes(ftp);
            System.out.println("Dados das Publicacoes carregados a partir do ficheiro de texto");
        }
    }
    
    /**
     * Verifica se o ficheiro introduzido como parametro existe ou nao
     * @param nome string que representa o ficheiro em questao
     * @return se o ficheiro existe retorna true, caso contrario retorna false
     */
    private boolean existeFicObj(String nome) {
        File f = new File(nome);
        try {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ois.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    
    /**
     * Leitura do ficheiro de Objetos dos Grupos de Investigacao e os dados serao guardados na ArrayList 
     * corresponde aos Grupos de Investigacao
     * @param nome string que representa o nome do ficheiro
     */
    private void lerFicObjGrupos(String nome) {
        File f = new File(nome);
        try {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            
            GrupoInvestigacao obj;
            while ((obj = (GrupoInvestigacao) ois.readObject()) != null) {      //percorre os elementos todos do ficheiro
                grupos.add(obj);                                                //adiciona o obejto a ArrayList
            }
            
            ois.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Erro a abrir ficheiro.");
        } catch (IOException ex) {

        } catch (ClassNotFoundException ex) {
            System.out.println("Erro a converter objeto.");
        }
    }
    
    /**
     * Leitura do ficheiro de Objetos dos Investigadores e os dados serao guardados na ArrayList 
     * correspondente aos Investigadores
     * @param nome string que representa o nome do ficheiro
     */
    private void lerFicObjInves(String nome) {
        File f = new File(nome);
        try {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            
            Investigadores obj;
            while ((obj = (Investigadores) ois.readObject()) != null) {         //percorre os elementos todos do ficheiro
                investigadores.add(obj);                                        //adiciona o obejto a ArrayList
            }

            ois.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Erro a abrir ficheiro.");
        } catch (IOException ex) {

        } catch (ClassNotFoundException ex) {
            System.out.println("Erro a converter objeto.");
        }
    }

    /**
     * Leitura do ficheiro de Objetos das Publicacoese os dados serao guardados na ArrayList 
     * correspondente ás Publicacoes
     * @param nome string que representa o nome do ficheiro
     */
    private void lerFicObjPublicacoes(String nome) {
        File f = new File(nome);
        try {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);

            Publicacoes obj;
            while ((obj = (Publicacoes) ois.readObject()) != null) {            //percorre os elementos todos do ficheiro
                publicacoes.add(obj);                                           //adiciona o obejto a ArrayList
            }

            ois.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Erro a abrir ficheiro.");
        } catch (IOException ex) {

        } catch (ClassNotFoundException ex) {
            System.out.println("Erro a converter objeto.");
        }
    }

    /**
     * Leitura do ficheiro de Texto dos Grupos de Investigacao e os dados serao guardados na ArrayList correspondente aos Grupos de Investigacao
     * @param nome string que representa o nome do ficheiro
     */
    private void lerFictxtGrupos(String nome) {
        File f = new File(nome);
        if (f.exists() && f.isFile()) {                                         //verifica se o ficherio existe ou nao
            try {
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);

                String line;
                while ((line = br.readLine()) != null) {
                    String[] linha = line.split(" , ");                             //array

                    GrupoInvestigacao g = new GrupoInvestigacao(linha[0], linha[1], linha[2]);
                    grupos.add(g);                                                  //adiciona o objeto da linha anterior ao ArrayList dos Grupos de Investigacao
                    
                    //esta funcao percorre os Investigadores e adiciona-os a ArrayList do Grupo de Investigacao correspondente 
                    for (Investigadores inv : investigadores) {
                        if (linha[1].equals(inv.getGrupoInvestigacao())) {          //verifica se o segundo elemento do array é igual ao acronimo do Grupo de Investigacao do investigador
                            g.setInvestigadores(inv);
                        }
                    }
                }

                br.close();
            } catch (FileNotFoundException ex) {
                System.out.println("Erro a abrir ficheiro de texto.");
            } catch (IOException ex) {
                System.out.println("Erro a ler ficheiro de texto.");
            }
        } else {
            System.out.println("Ficheiro não existe.");
        }
    }

    /**
     * Leitura do ficheiro de Texto dos Investigadores e os dados serao guardados na ArrayList correspondente aos Investigadores
     * @param nome string que representa o nome do ficheiro
     */
    private void lerFictxtInves(String nome) {
        File f = new File(nome);
        if (f.exists() && f.isFile()) {
            try {
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);

                String line;
                while ((line = br.readLine()) != null) {

                    String[] linha = line.split(" , ");
                    
                    //esta sequencia de if e elses serve para diferenciar se o investigador e Estudante ou Membro Efetivo ja que o Estudante tem 5 parametro enquanto que o Membro Efetivo so tem 6
                    if (linha.length == 5) {
                        MembroEfetivo e = new MembroEfetivo(linha[0], linha[1], linha[2], linha[3], linha[4]);
                        investigadores.add(e);
                    } else if (linha.length == 6) {
                        Estudante e = new Estudante(linha[0], linha[1], linha[2], linha[3], linha[4], linha[5]);
                        investigadores.add(e);
                    } else {               //caso os dados do investigador sejam mal inseridos no ficheiro aparece a msg para ser corrigido o erro  
                        System.out.println(" -- Dados do investigador mal inseridos -- ");
                    }
                }

                br.close();
            } catch (FileNotFoundException ex) {
                System.out.println("Erro a abrir ficheiro de texto.");
            } catch (IOException ex) {
                System.out.println("Erro a ler ficheiro de texto.");
            }
        } else {
            System.out.println("Ficheiro não existe.");
        }
    }

    /**
     * Leitura do ficheiro de Texto das Publicacoes e os dados serao guardados na ArrayList correspondente ás Publicacoes
     * @param nome string que representa o nome do ficheiro
     */
    private void lerFictxtPublicacoes(String nome) {
        File f = new File(nome);
        if (f.exists() && f.isFile()) {
            try {
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);

                String line;
                while ((line = br.readLine()) != null) {
                    line = br.readLine();                                           //passa para a linha segunte (ignorando a linha "Publicacao X"
                    String[] autores;
                    switch (line) {
                        case "Publicacao artigo de revista":        //9 linhas 
                            String[] rev = new String[9];
                            //este "for" percorre linha a linha e vai guardando a informacao das linhas no array
                            for (int i = 0; i < rev.length; ++i) {
                                String l = br.readLine();
                                rev[i] = l;
                            }
                            //o uso do substring deve-se para ignorar a informacao inutil incial
                            PubArtRevista p = new PubArtRevista(rev[6].substring(6), rev[7].substring(6), rev[8].substring(24), rev[1].substring(8), rev[2].substring(16), rev[3].substring(8), Integer.parseInt(rev[4].substring(19)), Integer.parseInt(rev[5].substring(23)));
                            autores = rev[0].substring(9).split(" e ");
                            //pega nos nomes dos autores da publicacao, faz a correspondencia ao devido Investigador e adiciona esses investigador a ArrayList dos Autores de cada Publicacao como tambem adiciona a Publicacao a ArrayList do Investigador correspondente
                            for (int i = 0; i < autores.length; ++i) {
                                for (Investigadores inv : investigadores) {
                                    if (autores[i].equals(inv.nomePublicacao(inv.getNome()))) {             //polimorfismo
                                        p.setAutores(inv);
                                        inv.setPublicacoes(p);
                                    }
                                }
                            }
                            //adiciona a publicacao ao Grupo de Investigacao correspondente 
                            adicionaPublicaoGrupo(p);
                            publicacoes.add(p);
                            break;
                            
                        case "Publicacao artigo de conferencia":    //9 linhas
                            String[] con = new String[9];
                            //este "for" percorre linha a linha e vai guardando a informacao das linhas no array
                            for (int i = 0; i < con.length; ++i) {
                                String l = br.readLine();
                                con[i] = l;
                            }
                            //o uso do substring deve-se para ignorar a informacao inutil incial
                            PubArtConferencia c = new PubArtConferencia(con[6].substring(6), con[7].substring(6), con[8].substring(28), con[1].substring(8), con[2].substring(16), con[3].substring(8), Integer.parseInt(con[4].substring(19)), Integer.parseInt(con[5].substring(23)));
                            autores = con[0].substring(9).split(" e ");
                            //pega nos nomes dos autores da publicacao, faz a correspondencia ao devido Investigador e adiciona esses investigador a ArrayList dos Autores de cada Publicacao como tambem adiciona a Publicacao a ArrayList do Investigador correspondente
                            for (int i = 0; i < autores.length; ++i) {
                                for (Investigadores inv : investigadores) {
                                    if (autores[i].equals(inv.nomePublicacao(inv.getNome()))) {             //polimorfismo
                                        c.setAutores(inv);
                                        inv.setPublicacoes(c);
                                    }
                                }
                            }
                            //adiciona a publicacao ao Grupo de Investigacao correspondente
                            adicionaPublicaoGrupo(c);
                            publicacoes.add(c);
                            break;

                        case "Livro":                               //8 linhas
                            String[] liv = new String[8];
                            //este "for" percorre linha a linha e vai guardando a informacao das linhas no array
                            for (int i = 0; i < liv.length; ++i) {
                                String l = br.readLine();
                                liv[i] = l;
                            }
                            //o uso do substring deve-se para ignorar a informacao inutil incial
                            Livro livro = new Livro(liv[6].substring(9), liv[7].substring(15), liv[1].substring(8), liv[2].substring(16), liv[3].substring(8), Integer.parseInt(liv[4].substring(19)), Integer.parseInt(liv[5].substring(23)));
                            autores = liv[0].substring(9).split(" e ");
                            //pega nos nomes dos autores da publicacao, faz a correspondencia ao devido Investigador e adiciona esses investigador a ArrayList dos Autores de cada Publicacao como tambem adiciona a Publicacao a ArrayList do Investigador correspondente
                            for (int i = 0; i < autores.length; ++i) {
                                for (Investigadores inv : investigadores) {
                                    if (autores[i].equals(inv.nomePublicacao(inv.getNome()))) {             //polimorfismo
                                        livro.setAutores(inv);
                                        inv.setPublicacoes(livro);
                                    }
                                }
                            }
                            //adiciona a publicacao ao Grupo de Investigacao correspondente
                            adicionaPublicaoGrupo(livro);
                            publicacoes.add(livro);
                            break;

                        case "Capitulos de Livros":                 //11 linhas
                            String[] cap = new String[11];
                            //este "for" percorre linha a linha e vai guardando a informacao das linhas no array
                            for (int i = 0; i < cap.length; ++i) {
                                String l = br.readLine();
                                cap[i] = l;
                            }
                            //o uso do substring deve-se para ignorar a informacao inutil incial
                            CapitulosLivros capliv = new CapitulosLivros(cap[8].substring(18), Integer.parseInt(cap[9].substring(18)), Integer.parseInt(cap[10].substring(27)), cap[6].substring(9), cap[7].substring(15), cap[1].substring(8), cap[2].substring(16), cap[3].substring(8), Integer.parseInt(cap[4].substring(19)), Integer.parseInt(cap[5].substring(23)));
                            autores = cap[0].substring(9).split(" e ");
                            //pega nos nomes dos autores da publicacao, faz a correspondencia ao devido Investigador e adiciona esses investigador a ArrayList dos Autores de cada Publicacao como tambem adiciona a Publicacao a ArrayList do Investigador correspondente
                            for (int i = 0; i < autores.length; ++i) {
                                for (Investigadores inv : investigadores) {
                                    if (autores[i].equals(inv.nomePublicacao(inv.getNome()))) {             //polimorfismo
                                        capliv.setAutores(inv);
                                        inv.setPublicacoes(capliv);
                                    }
                                }
                            }
                            //adiciona a publicacao ao Grupo de Investigacao correspondente
                            adicionaPublicaoGrupo(capliv);
                            publicacoes.add(capliv);
                            break;

                        case "Livro de artigos de Conferencia":     //10 linhas
                            String[] lcon = new String[10];
                            //este "for" percorre linha a linha e vai guardando a informacao das linhas no array
                            for (int i = 0; i < lcon.length; ++i) {
                                String l = br.readLine();
                                lcon[i] = l;
                            }
                            //o uso do substring deve-se para ignorar a informacao inutil incial
                            LivroArtConferencia lc = new LivroArtConferencia(lcon[8].substring(21), Integer.parseInt(lcon[9].substring(19)), lcon[6].substring(9), lcon[7].substring(15), lcon[1].substring(8), lcon[2].substring(16), lcon[3].substring(8), Integer.parseInt(lcon[4].substring(19)), Integer.parseInt(lcon[5].substring(23)));
                            autores = lcon[0].substring(9).split(" e ");
                            //pega nos nomes dos autores da publicacao, faz a correspondencia ao devido Investigador e adiciona esses investigador a ArrayList dos Autores de cada Publicacao como tambem adiciona a Publicacao a ArrayList do Investigador correspondente
                            for (int i = 0; i < autores.length; ++i) {
                                for (Investigadores inv : investigadores) {
                                    if (autores[i].equals(inv.nomePublicacao(inv.getNome()))) {             //polimorfismo
                                        lc.setAutores(inv);
                                        inv.setPublicacoes(lc);
                                    }
                                }
                            }
                            //adiciona a publicacao ao Grupo de Investigacao correspondente
                            adicionaPublicaoGrupo(lc);
                            publicacoes.add(lc);
                            break;
                    }
                    line = br.readLine();                                           //passa a frente a linha em branco
                }
                br.close();
            } catch (FileNotFoundException ex) {
                System.out.println("Erro a abrir ficheiro de texto.");
            } catch (IOException ex) {
                System.out.println("Erro a ler ficheiro de texto.");
            }
        } else {
            System.out.println("Ficheiro não existe.");
        }
    }

    /**
     * Escrita no ficheiro de Objetos dos Grupos de Investigacao e caso ele nao exista, é criado esse ficheiro (com o nome guardado no parametro de entrada deste metodo) e posteriormente é guardada a informacao
     * @param nome string que representa o nome do ficheiro
     */
    private void escreverFicObjGrupos(String nome) {
        File f = new File(nome);
        try {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            for (GrupoInvestigacao gi : grupos) {
                oos.writeObject(gi);
            }

            oos.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Erro a criar ficheiro.");
        } catch (IOException ex) {
            System.out.println("Erro a escrever para o ficheiro.");
        }
    }

    /**
     * Escrita no ficheiro de Objetos dos Investigadores e caso ele nao exista, é criado esse ficheiro (com o nome guardado no parametro de entrada deste metodo) e posteriormente é guardada a informacao
     * @param nome string que representa o nome do ficheiro
     */
    private void escreverFicObjInvestigadores(String nome) {
        File f = new File(nome);
        try {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            for (Investigadores i : investigadores) {
                oos.writeObject(i);
            }

            oos.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Erro a criar ficheiro.");
        } catch (IOException ex) {
            System.out.println("Erro a escrever para o ficheiro.");
        }
    }

    /**
     * Escrita no ficheiro de Objetos das Publicacoes e caso ele nao exista, é criado esse ficheiro (com o nome guardado no parametro de entrada deste metodo) e posteriormente é guardada a informacao
     * @param nome string que representa o nome do ficheiro
     */
    private void escreverFicObjPublicacoes(String nome) {
        File f = new File(nome);
        try {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            for (Publicacoes p : publicacoes) {
                oos.writeObject(p);
            }

            oos.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Erro a criar ficheiro.");
        } catch (IOException ex) {
            System.out.println("Erro a escrever para o ficheiro.");
        }
    }

    /**
     * Metodo que adiciona as publicacoes ao Grupo de Investigacao correspondente
     * @param p objeto que corresponde a Publicacao
     */
    private void adicionaPublicaoGrupo(Publicacoes p) {
        for (Investigadores inv : p.getAutores()) {                                 //percorre os autores da publicacao inserida por parametro
            for (GrupoInvestigacao gi : grupos) {                                   //percorre os Grupos de Investigacao
                if (inv.getGrupoInvestigacao().equals(gi.getAcronimo())) {          //verifica se o Grupo de Investigacao corresponde ao Grupo de Investigacao do Investigador
                    if (gi.getPublicacoes().indexOf(p) == -1) {                     //verifica se as Publicacoes do Grupo de Investigacao nao contem ja esta Publicacao
                        gi.setPublicacoes(p);
                    }
                }
            }
        }
    }

    /**
     * Metodo que faz a listagens dos indicadores do CISUC como o total de membros, o numero de membros
     * de cada categoria, o total de publicações dos últimos 5 anos e o numero de publicações de cada tipo
     */
    private void indicadoresCISUC() {
        System.out.println("==== Indicadores do CISUC ====");
        //Número de membros de cada categoria
        System.out.println("  >>MEMBROS<<");
        for (GrupoInvestigacao g : grupos) {
            System.out.println("Membros do " + g.getAcronimo() + ": " + g.getInvestigadores().size());
        }
        //numero Total de membros
        System.out.println("Total: " + investigadores.size() + " investigadores.\n");
        //Total de publicações dos últimos 5 anos
        int count = 0;
        for (Publicacoes g : publicacoes) {
            if (g.getAnoPublicacao() >= (anoAtual - ultimosAnos)) {
                count += 1;
            }
        }
        System.out.println("  >>PUBLICACOES<<");
        System.out.println("Numero de Publicacoes nos ultimos " + ultimosAnos + " anos: " + count);
        //Número de publicações de cada tipo
        int num[] = {0, 0, 0, 0, 0};
        String pub[] = {"Publicacao artigo de revista", "Publicacao artigo de conferencia", "Livro", "Capitulos de Livros", "Livro de artigos de Conferencia"};
        
        for (Publicacoes p : publicacoes) {                         //este for serve para contar o numero de Publicacoes existe de cada tipo
            for (int i = 0; i < pub.length; ++i) {
                if (p.getType().equals(pub[i])) {
                    num[i] += 1;
                }
            }
        }
        for (int j = 0; j < pub.length; ++j) {
            System.out.println(pub[j] + ": " + num[j]);
        }
        System.out.println("");
    }
    
    /**
     * Metodo que lista as Publiccoes de um Grupo de Investigacao nos ultimos 5 anos ordenada por ano, por tipo de publicação e por fator de impacto
     * @param gr string que representa o acronimo do Grupo de Investigacao 
     */
    private void publicacoesGruposUltimosAnos(String gr) {
        for (GrupoInvestigacao gi : grupos) {
            if (gr.equals(gi.getAcronimo())) {
                Collections.sort(gi.getPublicacoes());                          //ordena o ArrayList
                System.out.println("\n================================");
                System.out.println("\t>>> " + gi.getAcronimo() + " <<<");
                System.out.println("================================");
                for (Publicacoes p : gi.getPublicacoes()) {
                    if (p.getAnoPublicacao() >= (anoAtual - ultimosAnos)) {     //printa apenas as Publicaces dos "ultimos anos"
                        System.out.println("-> " + p + "\n");
                    }
                }
            }
        }
    }

    /**
     * Metodo que lista os membros de um grupo agrupadas por tipo de Investigador (Estudante e Membro Efetivo)
     * @param gr String que representa o acronimo de um Grupo de Investigacao
     */
    private void membrosGrupos(String gr) {
        String type[] = {Investigadores.type_Estudante, Investigadores.type_MembroEfetivo};
        for (GrupoInvestigacao gi : grupos) {                                   //percorre todos os Grupos de Investigacao
            if (gr.equals(gi.getAcronimo())) {                                  //restringe apenas a listagem ao Grupo de Invetigacao inserido por parametro
                for (int i = 0; i < type.length; ++i) {                         //percorre o array criado no inicio do metodo
                    System.out.println(type[i].toUpperCase());
                    System.out.println("=============================");
                    for (Investigadores inv : gi.getInvestigadores()) {
                        if (type[i].equals(inv.getType())) {                    //verifica o tipo de Investigador do Grupo de Investigacao em questao
                            System.out.println(inv);
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Metodo que faz a listagem das Publicacoes de um Investigacoes agrupadas por ano, tipo de publicação e fator de impacto
     * @param nome String que representa o nome de um Investigador 
     */
    private void agrupamentoInvestigadores(String nome) {
        int aux;
        int typeAux[] = {0, 0, 0, 0, 0};                                        //array para dar suporte ao array "tipoPublicacoes"
        int dimAux[] = {0, 0, 0};                                               //array para dar suporte ao array "impacto"

        for (Investigadores inv : investigadores) {
            if (nome.equals(inv.getNome())) {
                System.out.println(">>> AGRUPAMENTO <<<\n\n**POR ANO**");
                for (int i = 1950; i < anoAtual + 1; ++i) {                     //1950 foi um ano colocado para servir de referencia minima
                    aux = 0;
                    //esta funcao verifica se ha publicacoes no ano "i"
                    for (Publicacoes p : inv.getPublicacoes()) {
                        if (p.getAnoPublicacao() == i) {
                            aux += 1;
                        }
                    }
                    //se a variavel aux for 0 significa que nao ha publicacoes nesse anos e entao nao ha necessidade de colocar nada relativamente a esse ano
                    if (aux > 0) {
                        System.out.println(i + ":");
                        System.out.println("=========================");
                        for (Publicacoes p : inv.getPublicacoes()) {
                            if (p.getAnoPublicacao() == i) {
                                System.out.println("->" + p + "\n");
                            }
                        }
                    }
                }
                System.out.println("\n****POR TIPO****");
                //verifica em cada posicao do array (typeAux) se ha publicacoes de cada tipo
                for (Publicacoes p : inv.getPublicacoes()) {
                    for (int j = 0; j < tipoPublicacoes.length; ++j) {
                        if (p.getType().equals(tipoPublicacoes[j])) {
                            typeAux[j] += 1;
                        }
                    }
                }
                for (int j = 0; j < tipoPublicacoes.length; ++j) {
                    if (typeAux[j] != 0) {                                          //verifica se a posicao do array nao é 0, ou seja, se ha publicacoes desse tipo
                        System.out.println(tipoPublicacoes[j]);
                        System.out.println("==============================");
                        for (Publicacoes pub : inv.getPublicacoes()) {
                            if (tipoPublicacoes[j].equals(pub.getType())) {
                                System.out.println("->" + pub + "\n");
                            }
                        }
                    }
                }
                System.out.println("\n****POR DIMENSAO****");
                //verifica em cada posicao do array (dimAux) se ha publicacoes de cada tipo de fator de impacto
                for (Publicacoes p : inv.getPublicacoes()) {
                    for (int j = 0; j < impacto.length; ++j) {
                        if (p.fatorImpacto(p.getDimensaoAudiencia()).equals(impacto[j])) {              //polimorfismo
                            dimAux[j] += 1;
                        }
                    }
                }
                for (int j = 0; j < impacto.length; ++j) {
                    if (dimAux[j] != 0) {                                           //se esta posicao for 0 significa que nao ha publicacoes deste Investigador com este tipo de fator de impacto
                        System.out.println(impacto[j]);
                        System.out.println("==============================");
                        for (Publicacoes pub : inv.getPublicacoes()) {
                            if (impacto[j].equals(pub.fatorImpacto(pub.getDimensaoAudiencia()))) {    //verifica as Publicacoes que tenham o fator de impacto pretendido
                                System.out.println("->" + pub + "\n");
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Este Metodo lista todos os grupos de investigação, e apresenta para cada grupo
     * o total de membros,
     * o numero de membros de cada categoria,
     * o total de publicacoes dos ultimos 5 anos
     * e o numero de publicacoes dos ultimos 5 anos, agrupadas por ano, tipo de publicacao e fator de impacto
     */
    private void listarGrupos() {
        int count;
        System.out.println(">> GRUPOS DE INVESTIGACAO <<");
        for (GrupoInvestigacao gi : grupos) {
            System.out.println("\n==================");
            System.out.println("   >>> " + gi.getAcronimo() + " <<<");
            System.out.println("==================");
            System.out.println(gi);                                                 //printa o toString do Grupo de Investigacao
            System.out.println("Membros:");
            for (int i = 0; i < typeInv.length; ++i) {
                count = 0;
                for (Investigadores inv : gi.getInvestigadores()) {                 //conta o numero de membros de cada tipo
                    if (typeInv[i].equals(inv.getType())) {
                        count += 1;
                    }
                }
                System.out.println("\t" + typeInv[i] + ": " + count);
            }
            System.out.println("\tTotal: " + gi.getInvestigadores().size());        //total de membros do Grupo de Investigacao
            count = 0;
            //conta o numero de publicacoes nos ultimos anos 
            for (Publicacoes g : gi.getPublicacoes()) {
                if (g.getAnoPublicacao() >= (anoAtual - ultimosAnos)) {
                    count += 1;
                }
            }
            System.out.println("Numero Total de publicacoes nos ultimos " + ultimosAnos + " anos: " + count);
            System.out.println("Numero de Publicacoes em: ");
            for (int i = 1950; i < anoAtual + 1; ++i) {                             //1950 volta a ser usado como valor minimo de referencia
                count = 0;
                //verifica se ha publicacoes no ano "i"
                for (Publicacoes p : gi.getPublicacoes()) {
                    if (p.getAnoPublicacao() == i && p.getAnoPublicacao() >= anoAtual - ultimosAnos) {
                        count += 1;
                    }
                }
                if (count > 0) {
                    System.out.println("\t-> " + i + " = " + count);
                }
            }
            System.out.println("Numero de Publicacoes de:");
            int typeAux[] = {0, 0, 0, 0, 0};                                        //array para dar suporte ao array "tipoPublicacoes"
            int impAux[] = {0, 0, 0};                                               //array para dar suporte ao array "impacto"
            //verifica quantas ha publicacoes de cada tipo nos ultimos 5 anos
            for (Publicacoes p : gi.getPublicacoes()) {
                for (int j = 0; j < tipoPublicacoes.length; ++j) {
                    if (p.getType().equals(tipoPublicacoes[j]) && p.getAnoPublicacao() >= anoAtual - ultimosAnos) {
                        typeAux[j] += 1;
                    }
                }
            }
            for (int j = 0; j < typeAux.length; ++j) {
                System.out.println("\t-> " + tipoPublicacoes[j] + " = " + typeAux[j]);
            }
            System.out.println("Numero de Publicacoes de Fator de Impacto:");
            //verifica quantas ha publicacoes de cada tipo de Fator de Impacto nos ultimos 5 anos
            for (Publicacoes p : gi.getPublicacoes()) {
                for (int j = 0; j < impacto.length; ++j) {
                    if (p.fatorImpacto(p.getDimensaoAudiencia()).equals(impacto[j]) && p.getAnoPublicacao() >= anoAtual - ultimosAnos) {                //polimorfismo
                        impAux[j] += 1;
                    }
                }
            }
            for (int j = 0; j < impacto.length; ++j) {
                System.out.println("\t-> " + impacto[j] + " = " + impAux[j]);
            }
        }
    }
    
    /**
     * Metodo principal deste Projeto
     */
    public CISUC() {
        System.out.println("---- Start ----");

        grupos = new ArrayList<>();                             //ArrayList onde vai ser guardado os Grupos de Investigacao
        investigadores = new ArrayList<>();                     //ArrayList onde vai ser guardado os Investigadores
        publicacoes = new ArrayList<>();                        //ArrayList onde vai ser guardado as Publicacoes

        String fog = "Grupos_Investigacao_Obj.obj";
        String foi = "Investigadores_Obj.obj";
        String fop = "Publicacoes_Obj.obj";
        String ftg = "Grupos_Investigacao.txt";
        String fti = "Investigadores.txt";
        String ftp = "Publicacoes.txt";

        //leitura dos Ficheiros
        leituraFicheiros(fog, foi, fop, ftg, fti, ftp);
        
        menu();

        //guarda os dados nos ficheiros de objetos 
        escreverFicObjGrupos(fog);
        escreverFicObjInvestigadores(foi);
        escreverFicObjPublicacoes(fop);
        
        System.out.println("---- Finish ----");
    }
}
