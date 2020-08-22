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
import javafx.scene.control.TextField;

public class MenuTabela_AtualizarItemController implements Initializable {

    @FXML
    private TextField cdgbarras;
    @FXML
    private TextField dscItem;
    @FXML
    private TextField qtdEstoque;
    @FXML
    private TextField preco;
    @FXML
    private TextField ID;

    public String controleDeIDInterno = null;
    
    public void botaoCancelar_AtualizarItem(ActionEvent event) {

        ClassePrincipalProjeto.TrocarTelas("TelaCRUD");

    }
    
    public void menuUsuario_AtualizarItemExistente_BuscarID() {

        ConexaoPrincipalBancoDadosTabela pegarConexao = new ConexaoPrincipalBancoDadosTabela();

        PreparedStatement prSTMT = null;
        ResultSet rst = null;
        

        Connection conn = pegarConexao.conexaoPrincipal_com_Banco_de_Dados_Para_Usar_Tabela();
        String querySQL_buscarID = "SELECT * FROM controle_de_estoque.tabela_de_controle WHERE  codigo_de_barras = ? and descricao_do_item = ?";

        try {
            prSTMT = (PreparedStatement) conn.prepareStatement(querySQL_buscarID);
            prSTMT.setString(1, cdgbarras.getText());
            prSTMT.setString(2, dscItem.getText());
            rst = prSTMT.executeQuery();
            if (rst.next()) {
                
                Alert cptDaddos = new Alert(Alert.AlertType.INFORMATION);
                cptDaddos.setHeaderText("Anote o ID apresentado e insira no campo ID e clique em Atualizar");
                cptDaddos.setContentText(rst.getString("idTabela_de_controle"));
                controleDeIDInterno = rst.getString("idTabela_de_controle");
                cptDaddos.show();
               
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Alert erroFecharConexao = new Alert(Alert.AlertType.ERROR);
            erroFecharConexao.setHeaderText("Erro ao Buscar ID Item para Atualizar Insira o Código de Barras e Descrição do Item Corretos");
            erroFecharConexao.show();
        }
    }

    public void menuUsuario_AtualizarItemExistente() {

        menuUsuario_AtualizarItemExistente_BuscarID();
        String controleDeIDInternoAtualizado = controleDeIDInterno;
        
        
        Statement stmtAtualizarUsuario = null;

        String AtualizarusuarioID = ID.getText();
        String atualizarCGB = cdgbarras.getText();
        String atualizarDSCRItem = dscItem.getText();
        String atualizarQTDEsq = qtdEstoque.getText();
        String atualizarPreco = preco.getText();
        
        
        
        String mySQLexecutarAcaoDeletar = "UPDATE controle_de_estoque.tabela_de_controle SET `codigo_de_barras`= ' " + atualizarCGB + "' , `descricao_do_item`=' " + atualizarDSCRItem + "'"
                + ", `quantidade_em_estoque`=' " + atualizarQTDEsq + "', `preço_do_item`=' " + atualizarPreco + "' WHERE  `idTabela_de_controle`= ' " + controleDeIDInternoAtualizado + "';";
        //UPDATE `crud`.`usuarios` SET `usuario`='ademir', `senha`=' " + AtualizarusuarioID + "' WHERE `idusuarios`='549';
        ConexaoPrincipalBancoDadosTabela varRefMUsuarios1 = new ConexaoPrincipalBancoDadosTabela();
        Connection conn = varRefMUsuarios1.conexaoPrincipal_com_Banco_de_Dados_Para_Usar_Tabela();

        try {

            stmtAtualizarUsuario = conn.createStatement();
            stmtAtualizarUsuario.execute(mySQLexecutarAcaoDeletar);
            Alert deletadoSucesso = new Alert(Alert.AlertType.INFORMATION);
            deletadoSucesso.setHeaderText("Item : " + "[---- " + dscItem.getText() + " ----]" + " Atualizado com Sucesso!");
            deletadoSucesso.show();
        } catch (SQLException e) {
            e.printStackTrace();
            Alert erroFecharConexao = new Alert(Alert.AlertType.ERROR);
            erroFecharConexao.setHeaderText("Erro ao Atualizar Item");
            erroFecharConexao.show();
        }
        
        

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
