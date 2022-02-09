package Principal;

public class Produto {
	private int idProduto;
	private String nome;
	private String descricao;
	private String preco;
	private String categoria;
	
	public Produto(int idProduto, String nome, String dEscricao, String pReco, String categoria) {
		super();
		this.idProduto = idProduto;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = pReco;
		this.categoria = categoria;
	}
	
	public Produto() {
	}


	@Override
	public String toString() {
		return nome+"\nPreço: R$ "+preco+"\n"+descricao+"\nCódigo do produto: "+idProduto+
				"\n---------------------------------------------------\n---------------------------------------------------";
	}

	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String dEscricao) {
		descricao = dEscricao;
	}

	public String getPreco() {
		return preco;
	}

	public void setPreco(String pReco) {
		preco = pReco;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
}
