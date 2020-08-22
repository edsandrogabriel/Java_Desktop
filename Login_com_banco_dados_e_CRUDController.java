package julho_crud_com_login_com_bancodados;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Login_com_banco_dados_e_CRUDController implements Initializable {

    @FXML
    private TextField campo_Usuario;
    @FXML
    private TextField campo_Senha;
    @FXML
    private Button botao_Entrar;
    @FXML
    private Button botao_Sair;

    @FXML
    private void BotaoEntrar(ActionEvent event) {

        String pegarUsuario;
        String pegarSenha;

        pegarUsuario = campo_Usuario.getText();
        pegarSenha = campo_Senha.getText();

        Login_com_banco_dados_e_CRUDController.this.pegarUsuario_e_Senha_Tela_Login();

    }

    @FXML
    private void BotaoSair(ActionEvent event) {
        Alert erroConexao = new Alert(AlertType.INFORMATION);
        erroConexao.setHeaderText("Você Clicou em Sair, Deseja Realmente Fechar o Programa? ");
        erroConexao.show();
        // botao_Sair.setOnMousePressed();
    }

    public Connection conexaoPrincipal_com_Banco_de_Dados() {

        String usuario = "root";
        String senha = null;
        String conectorMYSQL = "jdbc:mysql://localhost:3306/crud";

        try {

            Connection conexaoPrincipal = DriverManager.getConnection(conectorMYSQL, usuario, senha);

            return conexaoPrincipal;

        } catch (SQLException e) {
            e.printStackTrace();
            Alert erroConexao = new Alert(AlertType.ERROR);
            erroConexao.setHeaderText("Erro de Conexão com o Banco de Dados, Verifique Usuário e senha");
            erroConexao.setContentText("Verifique também o driver de acesso ao banco de dados, contate o Admin do Sistema");
            erroConexao.show();
        }

        return null;
    }

    public void fecharConexoes(Connection fecharConexao, PreparedStatement fecharStatement, ResultSet fecharResultSet) {

        if (fecharConexao != null && fecharStatement != null && fecharResultSet != null) {
            try {
                fecharConexao.close();
                fecharStatement.close();
                fecharResultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
                Alert erroFecharConexao = new Alert(AlertType.ERROR);
                erroFecharConexao.setHeaderText("Erro ao fechar conexões do Banco de dados");
                erroFecharConexao.show();
            }
        }

    }

    public void pegarUsuario_e_Senha_Tela_Login() {

        PreparedStatement prSTMT = null;

        ResultSet rst = null;

        Connection conexaoParaPegarUser_Pass = Login_com_banco_dados_e_CRUDController.this.conexaoPrincipal_com_Banco_de_Dados();;

        String codigoSQLparaExecutarAcao = "SELECT * FROM crud.usuarios WHERE  usuario = ? and senha = ?";

        try {

            prSTMT = (PreparedStatement) conexaoParaPegarUser_Pass.prepareStatement(codigoSQLparaExecutarAcao);
            prSTMT.setString(1, campo_Usuario.getText());
            prSTMT.setString(2, campo_Senha.getText());
            rst = prSTMT.executeQuery();

            if (rst.next()) {

                ClassePrincipalProjeto.TrocarTelas("Telalogin");
                
              // Alert BemVindo = new Alert(AlertType.INFORMATION);
               //BemVindo.setHeaderText("Usuário e Senha corretos, Bem Vindo ");
             //  BemVindo.show();
                

            } else {
                Alert erroConexao = new Alert(AlertType.ERROR);
                erroConexao.setHeaderText("Erro de Conexão com o Banco de Dados, Verifique Usuário e senha");
                erroConexao.setContentText("Caso Ainda Não for Cadastrado Solicite ao Administrador o seu Cadastro");
                erroConexao.show();
            }

            Login_com_banco_dados_e_CRUDController.this.fecharConexoes(conexaoParaPegarUser_Pass, prSTMT, rst);

        } catch (SQLException e) {

            e.printStackTrace();
            Alert erroFecharConexao = new Alert(AlertType.ERROR);
            erroFecharConexao.setHeaderText("Erro ao fechar conexões do Banco de dados");
            erroFecharConexao.show();

        }

    }

    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
