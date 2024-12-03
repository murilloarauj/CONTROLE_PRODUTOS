package ControleProdutos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

// Classe que representa produtos alimentícios
public class ProdutoAlimenticio extends Produto {
    // Atributos que guardam informações nutricionais e validade do produto
    protected int caloriasTotais;     // Total de calorias do produto
    protected int teorProteico;       // Quantidade de proteínas
    protected int teorGordura;        // Quantidade de gorduras
    protected int teorCarboidrato;    // Quantidade de carboidratos
    protected LocalDate validadeProduto; // Data de validade do produto
    
    // Construtor da classe ProdutoAlimenticio
    public ProdutoAlimenticio(String descricaoProduto, float custoInicial, float valorMercado,
                               int caloriasTotais, int teorProteico, int teorGordura,
                               int teorCarboidrato, LocalDate validadeProduto) {
        super(descricaoProduto, custoInicial, valorMercado); // Chama o construtor da classe pai
        setCaloriasTotais(caloriasTotais); // Define o total de calorias
        setTeorProteico(teorProteico);      // Define a quantidade de proteínas
        setTeorGordura(teorGordura);        // Define a quantidade de gorduras
        setTeorCarboidrato(teorCarboidrato);// Define a quantidade de carboidratos
        setValidadeProduto(validadeProduto); // Define a data de validade do produto
    }
    
    // Métodos para acessar e modificar as informações nutricionais
    public int getCaloriasTotais() {
        return caloriasTotais;
    }

    public void setCaloriasTotais(int caloriasTotais) {
        if(caloriasTotais < 0) {
            throw new IllegalArgumentException("As calorias não podem ser negativas"); // Validação para evitar valores negativos
        }
        this.caloriasTotais = caloriasTotais; // Atribui o valor de calorias
    }

    public int getTeorProteico() {
        return teorProteico;
    }

    public void setTeorProteico(int teorProteico) {
        if(teorProteico < 0) {
            throw new IllegalArgumentException("O teor de proteína não pode ser negativo"); // Validação para evitar valores negativos
        }
        this.teorProteico = teorProteico; // Atribui o valor do teor proteico
    }

    public int getTeorGordura() {
        return teorGordura;
    }

    public void setTeorGordura(int teorGordura) {
        if(teorGordura < 0) {
            throw new IllegalArgumentException("O teor de gordura não pode ser negativo"); // Validação para evitar valores negativos
        }
        this.teorGordura = teorGordura; // Atribui o valor do teor de gordura
    }

    public int getTeorCarboidrato() {
        return teorCarboidrato;
    }

    public void setTeorCarboidrato(int teorCarboidrato) {
        if(teorCarboidrato < 0) {
            throw new IllegalArgumentException("O teor de carboidrato não pode ser negativo"); // Validação para evitar valores negativos
        }
        this.teorCarboidrato = teorCarboidrato; // Atribui o valor do teor de carboidrato
    }

    public LocalDate getValidadeProduto() {
        return validadeProduto;
    }

    public void setValidadeProduto(LocalDate validadeProduto) {
        if(validadeProduto == null) {
            throw new IllegalArgumentException("A validade do produto deve ser informada"); // Validação para verificar se a data foi informada
        }
        this.validadeProduto = validadeProduto; // Atribui a data de validade do produto
    }

    // Método para obter todas as informações detalhadas do produto
    public String obterDetalhes()  {
        return super.obterDetalhes() + "\nInformações Nutricionais: " + 
        		"\nGordura: " + teorGordura + 
                "\nCarboidratos: " + teorCarboidrato + 
        		"\nCalorias: " + caloriasTotais + 
        		"\nProteínas: " + teorProteico + 
              	"\nValidade: " + validadeProduto; // Retorna todos os detalhes do produto
    }

    @Override
    void calcularMargemLucro() {
        // Calcula a margem de lucro do produto
        float margemLucro = Vendaprod - Custoprod; // Cálculo da margem de lucro
        System.out.println("Margem de lucro: " + margemLucro); // Exibe o valor da margem de lucro
    }

    // Método para gerar o comando SQL para inserir um novo produto no banco de dados
    public void salvarProduto(Connection conexao) {
		// Adiciona as informações
	    String sql = "INSERT INTO produtos_alimenticio (Titulo, Custoprod, ValorVenda, CaloriasTotais, TeorProteico, TeorGordura, TeorCarboidrato, ValidadeProduto) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	    try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
	        stmt.setString(1, getTitulo()); 
	        stmt.setFloat(2, getCustoprod()); 
	        stmt.setFloat(3, getValorVenda()); 
	        stmt.setInt(4, getCaloriasTotais()); 
	        stmt.setInt(5, getTeorProteico()); 
	        stmt.setInt(6, getTeorGordura()); 
	        stmt.setInt(7, getTeorCarboidrato()); 
	        stmt.setObject(8, getValidadeProduto());  

	        int rowsUpdated = stmt.executeUpdate(); // atualiza o banco de dados
	        if (rowsUpdated > 0) {
	            System.out.println("Produto alimentício gravado!");
	        }
	    } catch (SQLException e) {
	        System.err.println("Erro para gravar produto alimentício: " + e.getMessage());
	    }
	}
    
	public void atualizarProduto(Connection conexao, int id) {
		// Altera as informações
	    String sql = "UPDATE produtos_alimenticio SET Titulo = ?, Custoprod = ?, ValorVenda = ?, CaloriasTotais = ?, TeorProteico = ?, TeorGordura = ?, TeorCarboidrato = ?, ValidadeProduto = ? WHERE id = ?";
	    try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
	        stmt.setString(1, getTitulo());
	        stmt.setFloat(2, getCustoprod());
	        stmt.setFloat(3, getValorVenda());
	        stmt.setInt(4, getCaloriasTotais());
	        stmt.setInt(5, getTeorProteico());
	        stmt.setInt(6, getTeorGordura());
	        stmt.setInt(7, getTeorCarboidrato());
	        stmt.setObject(8, getValidadeProduto());
	        stmt.setInt(9, id);

	        int rowsUpdated = stmt.executeUpdate();
	        if (rowsUpdated > 0) {
	            System.out.println("Produto alimentício alterado.");
	        }
	    } catch (SQLException e) {
	        System.err.println("Erro para alterar produto alimentício: " + e.getMessage());
	    }
	}
	// Apaga as informações
	public static void deletarProduto(Connection conexao, int id) {
	    String sql = "DELETE FROM produtos_alimenticio WHERE id = ?";
	    try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
	        stmt.setInt(1, id);

	        int rowsUpdated = stmt.executeUpdate();
	        if (rowsUpdated > 0) {
	            System.out.println("Produto alimentício apagado.");
	        }
	    } catch (SQLException e) {
	        System.err.println("Erro para apagar produto alimentício: " + e.getMessage());
	    }
	}
}