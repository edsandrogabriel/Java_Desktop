package julho_crud_com_login_com_bancodados;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;




public  class TabelaVisualizacaoDados {
    
  private String Classe_TVD_codigoBarrras;
  private String Classe_TVD_descricaoItem;
  private String Classe_TVD_quantidadeEstoque;
  private String Classe_TVD_precoItem;

    public TabelaVisualizacaoDados(){
        // sobrecarregar o construtor , apenas para criar objetos desse tipo
    }
  
    public TabelaVisualizacaoDados(String Classe_TVD_codigoBarrras, String Classe_TVD_descricaoItem, String Classe_TVD_quantidadeEstoque, String Classe_TVD_precoItem) {
        this.Classe_TVD_codigoBarrras = Classe_TVD_codigoBarrras;
        this.Classe_TVD_descricaoItem = Classe_TVD_descricaoItem;
        this.Classe_TVD_quantidadeEstoque = Classe_TVD_quantidadeEstoque;
        this.Classe_TVD_precoItem = Classe_TVD_precoItem;
    }

    public String getClasse_TVD_codigoBarrras() {
        return Classe_TVD_codigoBarrras;
    }

    public void setClasse_TVD_codigoBarrras(String Classe_TVD_codigoBarrras) {
        this.Classe_TVD_codigoBarrras = Classe_TVD_codigoBarrras;
    }

    public String getClasse_TVD_descricaoItem() {
        return Classe_TVD_descricaoItem;
    }

    public void setClasse_TVD_descricaoItem(String Classe_TVD_descricaoItem) {
        this.Classe_TVD_descricaoItem = Classe_TVD_descricaoItem;
    }

    public String getClasse_TVD_quantidadeEstoque() {
        return Classe_TVD_quantidadeEstoque;
    }

    public void setClasse_TVD_quantidadeEstoque(String Classe_TVD_quantidadeEstoque) {
        this.Classe_TVD_quantidadeEstoque = Classe_TVD_quantidadeEstoque;
    }

    public String getClasse_TVD_precoItem() {
        return Classe_TVD_precoItem;
    }

    public void setClasse_TVD_precoItem(String Classe_TVD_precoItem) {
        this.Classe_TVD_precoItem = Classe_TVD_precoItem;
    }
  
  
  
  
}
