package MediaCenter;

public class Utilizador {
    private String nome, pass;

    public Utilizador(String nome, String pass){
        this.nome = nome;
        this.pass = pass;
    }

    public String getNome() {
        return nome;
    }

    public String getPass() {
        return pass;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
