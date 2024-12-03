package ControleProdutos;

// Classe abstrata que representa um produto
public abstract class Produto {
    // Atributos do produto
    protected String nomeprod;         // Nome do produto
    protected float Custoprod;      // Custo do produto
    protected float Vendaprod;      // Preço de venda do produto

    // Construtor da classe Produto
    public Produto(String nomeprod, float custoprod, float Vendaprod) {
        setTitulo(nomeprod);          // Define o nome do produto
        setCustoprod(custoprod);      // Define o custo do produto
        setValorVenda(Vendaprod);     // Define o preço de venda do produto
    }

    // Método getter para obter o nome do produto
    public String getTitulo() {
        return nomeprod; // Retorna o nome do produto
    }

    // Método setter para definir o nome do produto
    public void setTitulo(String nomeprod) {
        // Verifica se o nome não é nulo ou vazio
        if (nomeprod == null || nomeprod.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do produto não pode estar vazio"); // Lança erro se o nome estiver vazio
        }
        this.nomeprod = nomeprod;  // Atribui o nome ao atributo
    }

    // Método getter para obter o custo do produto
    public float getCustoprod() {
        return Custoprod; // Retorna o custo do produto
    }

    // Método setter para definir o custo do produto
    public void setCustoprod(float custoprod) {
        // Verifica se o custo é maior que 0
        if (custoprod <= 0) {
            throw new IllegalArgumentException("O custo do produto deve ser maior que 0"); // Lança erro se o custo for inválido
        }
        this.Custoprod = custoprod;  // Atribui o custo ao atributo
    }

    // Método getter para obter o preço de venda do produto
    public float getValorVenda() {
        return Vendaprod; // Retorna o preço de venda do produto
    }

    // Método setter para definir o preço de venda do produto
    public void setValorVenda(float valorVenda) {
        // Verifica se o preço de venda é maior que 0
        if (valorVenda <= 0) {
            throw new IllegalArgumentException("O preço de venda do produto deve ser maior que 0"); // Lança erro se o preço for inválido
        }
        this.Vendaprod = valorVenda;  // Atribui o preço de venda ao atributo
    }

    // Método que retorna uma string com os detalhes do produto
    public String obterDetalhes() {
        return "Nome do Produto: " + nomeprod 
        		+ "\nCusto: " + Custoprod 
        		+ "\nPreço de Venda: " + Vendaprod; // Retorna os detalhes do produto
    }
    
    // Método abstrato que deve ser implementado nas subclasses para calcular a margem de lucro
    abstract void calcularMargemLucro();
}