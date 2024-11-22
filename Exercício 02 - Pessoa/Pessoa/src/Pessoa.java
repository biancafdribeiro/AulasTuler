public class Pessoa {
    private static int kp = 0; 
    private String nome;
    private char sexo;
    private int idade;

    public Pessoa() {
        kp++;
    }

    public Pessoa(String nome, char sexo, int idade) {
        this();
        setNome(nome);
        setSexo(sexo);
        setIdade(idade);
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório.");
        }
        this.nome = nome;
    }

    public void setSexo(char sexo) {
        if (sexo != 'M' && sexo != 'F') {
            throw new IllegalArgumentException("Sexo deve ser 'M' ou 'F'.");
        }
        this.sexo = sexo;
    }

    public void setIdade(int idade) {
        if (idade < 0) {
            throw new IllegalArgumentException("Idade não pode ser negativa.");
        }
        this.idade = idade;
    }

    public static int getKp() {
        return kp;
    }

    public String getNome() {
        return nome;
    }

    public char getSexo() {
        return sexo;
    }

    public int getIdade() {
        return idade;
    }
}
