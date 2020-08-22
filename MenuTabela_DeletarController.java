package julho_crud_com_login_com_bancodados;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;


public class MenuTabela_DeletarController implements Initializable {

    @FXML
    private TextField codigoBarras;
    @FXML
    private TextField descricaoItem;
    @FXML
    private TextField quantidadeEstoque;
    @FXML
    private TextField preco;

    @FXML
    private void botaoInserirItem(ActionEvent event) {
        this.inserirItem();
        ClassePrincipalProjeto.TrocarTelas("TelaCRUD");
        Alert erroFecharConexao = new Alert(Alert.AlertType.INFORMATION);
        erroFecharConexao.setHeaderText("Item Inserido");
        erroFecharConexao.show();

    }

    @FXML
    private void botaoCancelar(ActionEvent event) {
        ClassePrincipalProjeto.TrocarTelas("TelaCRUD");
    }

    private void inserirItem() {
        Statement stmt = null;
        ConexaoPrincipalBancoDadosTabela vrctlConn = new ConexaoPrincipalBancoDadosTabela();
        Connection conn = vrctlConn.conexaoPrincipal_com_Banco_de_Dados_Para_Usar_Tabela();
        String cdgBar = codigoBarras.getText();
        String desItm = descricaoItem.getText();
        String qtdEsq = quantidadeEstoque.getText();
        String prcItm = preco.getText();

        String querySQL = "INSERT INTO `controle_de_estoque`.`tabela_de_controle` (`codigo_de_barras`, `descricao_do_item`, `quantidade_em_estoque`, `preço_do_item`) VALUES ('" + cdgBar + "', '" + desItm + "', '" + qtdEsq + "' , '" + prcItm + "'); ";

        try {
            stmt = conn.createStatement();
            stmt.execute(querySQL);
        } catch (SQLException e) {
            e.printStackTrace();
            Alert erroFecharConexao = new Alert(Alert.AlertType.ERROR);
            erroFecharConexao.setHeaderText("Erro ao Inserir Item");
            erroFecharConexao.show();
        }
        vrctlConn.fecharConexoes_da_Tabela(conn, stmt, null);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}

