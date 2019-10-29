package pojo;

import java.io.Serializable;
//POJO
public class Agenda implements Serializable {

    public int id;
    public String nome;
    public String telefone;
//Construtor vazio
    public Agenda() {
    }
//Construtor com assinatura
    public Agenda(int id, String nome, String telefone) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
    }
//Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
//Método para mostrar o nome na list view
    @Override
    public String toString() {
        return nome;
    }
//Retorna todos os dados de uma só vez
    public String getDados() {
        return "ID: " + id + "\n" +
                "Nome: " + nome + "\n" +
                "Telefone: " + telefone;
    }
}

