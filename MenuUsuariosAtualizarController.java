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

public class MenuUsuariosAtualizarController implements Initializable {

    @FXML
    private TextField UsuarioAtualizar;
    @FXML
    private TextField senhaAtualizar;
     @FXML
    private TextField UsuarioAtualizar1;
    @FXML
    private TextField senhaAtualizar1;
     @FXML
    private TextField ID;

    @FXML
    public void botaoAtualizarUsuario(ActionEvent event) {

        ClassePrincipalProjeto.TrocarTelaMenuUsuarios_Atualizar("apertouBotaoAtualizarUsuario");
        this.menuUsuario_AtualizarUsuarioExistente();

    }

    @FXML
    public void botaoCancelar_AtualizarUsuario(ActionEvent event) {

        ClassePrincipalProjeto.TrocarTelas("TelaCRUD");

    }

    public void menuUsuario_AtualizarUsuarioExistente_BuscarID() {

        Login_com_banco_dados_e_CRUDController pegarConexao = new Login_com_banco_dados_e_CRUDController();

        PreparedStatement prSTMT = null;
        ResultSet rst = null;
        String consultarUsuario = null;
        String consultarSenha = null;

        Connection conn = pegarConexao.conexaoPrincipal_com_Banco_de_Dados();
        String querySQL_buscarID = "SELECT * FROM crud.usuarios WHERE  usuario = ? and senha = ?";

        try {
            prSTMT = (PreparedStatement) conn.prepareStatement(querySQL_buscarID);
            prSTMT.setString(1, UsuarioAtualizar.getText());
            prSTMT.setString(2, senhaAtualizar.getText());
            rst = prSTMT.executeQuery();
            if (rst.next()) {
                Alert cptDaddos = new Alert(Alert.AlertType.INFORMATION);
                cptDaddos.setHeaderText("Anote o ID apresentado e insira no campo ID e clique em Atualizar");
                cptDaddos.setContentText(rst.getString("idusuarios"));
                cptDaddos.show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Alert erroFecharConexao = new Alert(Alert.AlertType.ERROR);
            erroFecharConexao.setHeaderText("Erro ao Buscar ID do Usuário para Atualizar");
            erroFecharConexao.show();
        }
    }

    public void menuUsuario_AtualizarUsuarioExistente() {

        Statement stmtAtualizarUsuario = null;

        String AtualizarusuarioID = ID.getText();
         String atualizarUsuario = UsuarioAtualizar1.getText();
        String atualizarSenha = senhaAtualizar1.getText();
        

        String mySQLexecutarAcaoDeletar = "UPDATE `crud`.`usuarios` SET `usuario`= ' " + atualizarUsuario + "' , `senha`=' " + atualizarSenha + "' WHERE  `idusuarios`= ' " + AtualizarusuarioID + "';";
        //UPDATE `crud`.`usuarios` SET `usuario`='ademir', `senha`=' " + AtualizarusuarioID + "' WHERE `idusuarios`='549';
      
        MUsuarios_NovoUsuario varRefMUsuarios1 = new MUsuarios_NovoUsuario();
        Connection conn = varRefMUsuarios1.conexaoPrincipal_com_Banco_de_Dados();

        try {

            stmtAtualizarUsuario = conn.createStatement();
            stmtAtualizarUsuario.execute(mySQLexecutarAcaoDeletar);
            Alert deletadoSucesso = new Alert(Alert.AlertType.INFORMATION);
            deletadoSucesso.setHeaderText("Usuário : " + "[---- " + UsuarioAtualizar.getText() + " ----]" + " Atualizado com Sucesso!");
            deletadoSucesso.show();
        } catch (SQLException e) {
            e.printStackTrace();
            Alert erroFecharConexao = new Alert(Alert.AlertType.ERROR);
            erroFecharConexao.setHeaderText("Erro ao Atualizar Usuário");
            erroFecharConexao.show();
        }
        varRefMUsuarios1.fecharConexoes(conn, stmtAtualizarUsuario);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
