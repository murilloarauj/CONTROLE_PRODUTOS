package ControleProdutos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ControleProdutos.Produto;

// Classe que representa produtos de vestuário
public class ProdutoVestuario extends Produto {
    // Atributos específicos para o vestuário
    protected String tamanhoProduto; // Tamanho do produto
    protected String corProduto;     // Cor do produto
    protected String materialProduto; // Material do produto

    // Construtor que inicializa os atributos de um produto de vestuário
    public ProdutoVestuario(String nomeProduto, float custoProduto, float vendaProduto, 
                            String tamanhoProduto, String corProduto, String materialProduto) {
        super(nomeProduto, custoProduto, vendaProduto); // Chama o construtor da classe pai
        setTamanhoProduto(tamanhoProduto); // Define o tamanho do produto
        setCorProduto(corProduto); // Define a cor do produto
        setMaterialProduto(materialProduto); // Define o material do produto
    }

    // Método para obter o tamanho do produto
    public String getTamanhoProduto() {
        return tamanhoProduto;
    }

    // Método para definir o tamanho do produto, com validação
    public void setTamanhoProduto(String tamanhoProduto) {
        if (tamanhoProduto == null || tamanhoProduto.trim().isEmpty()) {
            throw new IllegalArgumentException("O tamanho não pode estar nulo"); // Valida se o tamanho está vazio
        }
        this.tamanhoProduto = tamanhoProduto; // Atribui o tamanho
    }

    // Método para obter a cor do produto
    public String getCorProduto() {
        return corProduto;
    }

    // Método para definir a cor do produto, com validação
    public void setCorProduto(String corProduto) {
        if (corProduto == null || corProduto.trim().isEmpty()) {
            throw new IllegalArgumentException("A cor não pode ser nula"); // Valida se a cor está vazia
        }
        this.corProduto = corProduto; // Atribui a cor
    }

    // Método para obter o material do produto
    public String getMaterialProduto() {
        return materialProduto;
    }

    // Método para definir o material do produto, com validação
    public void setMaterialProduto(String materialProduto) {
        if (materialProduto == null || materialProduto.trim().isEmpty()) {
            throw new IllegalArgumentException("O material não pode ser nulo"); // Valida se o material está vazio
        }
        this.materialProduto = materialProduto; // Atribui o material
    }

    // Método que retorna uma descrição completa do produto de vestuário
    public String descricao() {
        return super.obterDetalhes() + "\nTamanho: " + tamanhoProduto 
        		+ "\nCor: " + corProduto 
        		+ "\nMaterial: " + materialProduto; // Retorna detalhes do produto
    }

    // Método que calcula a margem de lucro do produto
    @Override
    void calcularMargemLucro() {
        float lucro = Vendaprod - Custoprod; // Cálculo do lucro
        System.out.println("Lucro da venda: " + lucro); // Exibe o lucro
    }

    // Método para gerar comando SQL para salvar o produto no banco de dados
    public String sProduto(int id) {
        return "INSERT INTO produtos_vestuario (id, nome, preco_custo, preco_venda, tamanho, cor, material) VALUES ("
                + id 
                + ", '" + getTitulo() 
                + "', " + getCustoprod()  
                + ", " + getValorVenda() 
                + ", '" + getTamanhoProduto() 
                + "', '" + getCorProduto() 
                + "', '" + getMaterialProduto() + "')";
    }

    // Método para gerar comando SQL para atualizar o produto no banco de dados
    public void salvarProduto(Connection conexao) {
		// Adiciona as informações
	    String sql = "INSERT INTO produtos_vestuario (nome, precoCusto, precoVenda, tamanho, cor, material) VALUES (?, ?, ?, ?, ?, ?)";
	    try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
	        stmt.setString(1, getTitulo()); 
	        stmt.setFloat(2, getCustoprod()); 
	        stmt.setFloat(3, getValorVenda()); 
	        stmt.setString(4, getTamanhoProduto()); 
	        stmt.setString(5, getCorProduto()); 
	        stmt.setString(6, getMaterialProduto()); 
	        
	        int rowsUpdated = stmt.executeUpdate(); // atualiza o banco de dados
	        if (rowsUpdated > 0) {
	            System.out.println("Produto gravado!."); 
	        }
	    } catch (SQLException e) {
	        System.err.println("Erro para gravar produto: " + e.getMessage());
	    }
	}

	public void atualizarProduto(Connection conexao, int id) {
		// Altera as informações
	    String sql = "UPDATE produtos_vestuario SET nome = ?, precoCusto = ?, precoVenda = ?, tamanho = ?, cor = ?, material = ? WHERE id = ?";
	    try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
	        stmt.setString(1, getTitulo()); 
	        stmt.setFloat(2, getCustoprod());
	        stmt.setFloat(3, getValorVenda());
	        stmt.setString(4, getTamanhoProduto());
	        stmt.setString(5, getCorProduto());
	        stmt.setString(6, getMaterialProduto());
	        stmt.setInt(7, id);
	        
	        int rowsUpdated = stmt.executeUpdate();
	        if (rowsUpdated > 0) {
	            System.out.println("Produto alterado.");
	        }
	    } catch (SQLException e) {
	        System.err.println("Erro para alterar produto: " + e.getMessage());
	    }
	}

	public static void deletarProduto(Connection conexao, int id) {
		// Apaga as informações
	    String sql = "DELETE FROM produtos_vestuario WHERE id = ?";
	    try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
	        stmt.setInt(1, id);
	        
	        int rowsUpdated = stmt.executeUpdate();
	        if (rowsUpdated > 0) {
	            System.out.println("Produto apagado");
	        } 
	    } catch (SQLException e) {
	        System.err.println("Erro para apagar produto: " + e.getMessage());
	    }
	}
}