package application;

import entities.Usuário;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

public class Program {

    public static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);


        try {
            boolean bContinue = true;
            while(bContinue){
                operacao(sc);
                System.out.print("Deseja Criar novo arquivo de lista? [s/n]: ");
                char n = sc.next().toUpperCase().charAt(0);
                bContinue = (n == 'S')?true:false;

            }
            System.out.println("Volte Sempre");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Erro básicos");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static int menu(Scanner sc){
        System.out.println();
        System.out.println("0 - Sair");
        System.out.println("1 - Inserir Lista de Usuários");
        System.out.println("2 - Listar Usuários Inseridos");
        System.out.println("3 - Arquivar lista Gerada");
        System.out.println("4 - Ler Arquivo Gerado");
        System.out.println("5 - Informar Quantidade de Usuários Acessou");//set da aula Set
        System.out.print("Informe a Operação: ");
        int op = sc.nextInt();
        return op;

    }
    public static void operacao(Scanner sc) throws ParseException, IOException {
        int op = -1;

        Usuário usuarios = new Usuário();
       // List<Usuário> lista = new ArrayList<>();

        while(op != 0){
            try{

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                op = menu(sc);
                switch(op)
                {
                    case 0:
                        System.out.println("Saindo do gerador de Lista");
                        break;
                    case 1:
                        System.out.print("Usuário: ");
                        String nome = sc.next();
                        sc.nextLine();
                        System.out.print("Data de Acesso 'dd/MM/yyyy HH:mm': ");
                        String dt =sc.nextLine();
                        Date date = sdf.parse(dt);
                       // lista.add(new Usuário(nome, date));
                        usuarios.setLista(new Usuário(nome,date));
                        break;
                    case 2:
                        System.out.println("//" + usuarios.toString());
                        System.out.println(usuarios.toString());
                        break;

                    case 3:
                        System.out.print("Informe o Caminho Para Arquivar: ");
                        String caminho = sc.next();
                        caminho += ".txt";

                        try(BufferedWriter bw = new BufferedWriter(new FileWriter(caminho,true))){

                            String []lines = {usuarios.toString()};

                            FileReader fr = new FileReader(caminho);
                            BufferedReader br = new BufferedReader(fr);
                            String confirma = br.readLine();

                            if(confirma == null){


                                for(String l:lines){
                                    bw.write(l);
                                    bw.newLine();
                                }
                                System.out.println("ATENÇÃO: GRAVADO COM SUCESSO!!!");

                            }else{
                                System.out.println("Necessário Gerar Nova Lista  para Arquivar!! ");
                            }


                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 4:
                        System.out.print("Informe o Caminho do Arquivo para Leitura: ");
                        caminho = sc.next();
                        caminho += ".txt";
                        FileReader fr = new FileReader(caminho);
                        BufferedReader br =new BufferedReader(fr);

                        try{


                            String line = br.readLine();
                            while(line != null){

                                System.out.println(line);
                                line = br.readLine();
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                            System.out.println("Houve um erro: " + e.getMessage());
                        }
                        break;
                    case 5:

                        //MODELO DE ARQUIVO .TXT
                        /*sarinha12 2021-12-15T19:35:05Z
                        renato@124 2021-11-28T22:53:03Z
                        elon_musk 2020-08-27T23:45:22Z
                        renatinha123#alves 2021-05-04T19:18:32Z
                        sarinha12 2021-11-26T15:58:11Z
                        elon_musk 2021-01-02T13:53:13Z*/

                        Set<Usuário> set = new HashSet<>();
                        System.out.print("Informe o Caminho do Arquivo para Comparar a Quantidade de Usuários: ");
                        caminho = sc.next();
                        caminho += ".txt";
                        fr = new FileReader(caminho);
                        br = new BufferedReader(fr);
                        String line = br.readLine();
                        /*Exemplo de formato de Arquivo:
                        * sarinha12 2021-12-15T19:35:05Z*/
                        while(line !=null){
                            //Separando nome da data do objeto
                            String[] split = line.split(" ");
                            String nom = split[0];

                            Date moment = Date.from(Instant.parse(split[1]));
                            //Inserir no set os dados
                            set.add(new Usuário(nom,moment));
                            line = br.readLine();
                        }
                       // System.out.println("06");
                        System.out.println("Possui " + set.size() + " Usuários");

                        break;
                }
            }catch(Exception e){
                e.getMessage();
                System.out.println(e);
            }

        }


    }


}
