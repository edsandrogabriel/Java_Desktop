package julho_crud_com_login_com_bancodados;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class MenuTabelaDeletarItemController implements Initializable {

      
    @FXML
    TextField codigoBarras;
    @FXML
    TextField descricaoItem;
    @FXML
    private TextField ID;

    @FXML
    private void botaoDeletar(ActionEvent event) {
        deletandoItem(); 
    }

    @FXML
    private void botaoCancelar(ActionEvent event) {
        ClassePrincipalProjeto.TrocarTelas("TelaCRUD");
    }

    private void deletandoItem() {
        ConexaoPrincipalBancoDadosTabela varRefDel = new ConexaoPrincipalBancoDadosTabela();
        Connection conn = varRefDel.conexaoPrincipal_com_Banco_de_Dados_Para_Usar_Tabela();
        Statement stmtDeletarUsuario = null;
        ResultSet rst = null;
        String receberID = ID.getText();
        String querySQL_deletar = "DELETE FROM `controle_de_estoque`.`tabela_de_controle` WHERE `idTabela_de_controle`='" + receberID + "'; ";

        try {

            stmtDeletarUsuario = conn.createStatement();
            stmtDeletarUsuario.execute(querySQL_deletar);
            Alert deletadoSucesso = new Alert(Alert.AlertType.INFORMATION);
            deletadoSucesso.setHeaderText("Item : " + "[---- " + descricaoItem.getText() + " ----]" + " Deletado com Sucesso!");
            deletadoSucesso.show();
        } catch (SQLException e) {
            e.printStackTrace();
            Alert erroFecharConexao = new Alert(Alert.AlertType.ERROR);
            erroFecharConexao.setHeaderText("Erro ao Deletar Item, Verifique Código de Barras e Descrição Inseridas ");
            erroFecharConexao.show();
        }
        varRefDel.fecharConexoes_da_Tabela(conn, stmtDeletarUsuario, rst);

    }

    @FXML
    private void buscarID_item() {
        ConexaoPrincipalBancoDadosTabela varRefDel = new ConexaoPrincipalBancoDadosTabela();
        Connection conn = varRefDel.conexaoPrincipal_com_Banco_de_Dados_Para_Usar_Tabela();
        PreparedStatement prSTMT = null;
        ResultSet rst = null;
        String receberCodigoBarras = codigoBarras.getText();
        String receberDescricaoitem = descricaoItem.getText();
        String querySQL_buscar = "SELECT * FROM `controle_de_estoque`.`tabela_de_controle` WHERE codigo_de_barras =? and descricao_do_item=?";

        try {

            prSTMT = (PreparedStatement) conn.prepareStatement(querySQL_buscar);
            prSTMT.setString(1, codigoBarras.getText());
            prSTMT.setString(2, descricaoItem.getText());
            rst = prSTMT.executeQuery();

            if (rst.next()) {
                Alert cptDaddos1 = new Alert(Alert.AlertType.INFORMATION);
                cptDaddos1.setHeaderText("Anote o ID apresentado e insira no campo ID e clique em Deletar");
                cptDaddos1.setContentText(rst.getString("idTabela_de_controle"));
                cptDaddos1.show();

            }

        } catch (SQLException e) {
            e.printStackTrace();
            Alert erroFecharConexao1 = new Alert(Alert.AlertType.ERROR);
            erroFecharConexao1.setHeaderText("Erro ao Buscar Dados do Usuário");
            erroFecharConexao1.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
