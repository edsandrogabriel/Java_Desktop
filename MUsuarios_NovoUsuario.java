package julho_crud_com_login_com_bancodados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class MUsuarios_NovoUsuario {

    public Connection conexaoPrincipal_com_Banco_de_Dados() {

        String usuario = "root";
        String senha = null;
        String conectorMYSQL = "jdbc:mysql://localhost:3306/crud";

        try {

            Connection conexaoPrincipal = DriverManager.getConnection(conectorMYSQL, usuario, senha);

            return conexaoPrincipal;

        } catch (SQLException e) {
            e.printStackTrace();
            Alert erroConexao = new Alert(Alert.AlertType.ERROR);
            erroConexao.setHeaderText("Erro de Conexão com o Banco de Dados, Verifique Usuário e senha");
            erroConexao.setContentText("Verifique também o driver de acesso ao banco de dados, contate o Admin do Sistema");
            erroConexao.show();
        }

        return null;
    }

    public void fecharConexoes(Connection fecharConexao, Statement fecharStatement) {

        if (fecharConexao != null && fecharStatement != null) {
            try {
                fecharConexao.close();
                fecharStatement.close();

            } catch (SQLException e) {
                e.printStackTrace();
                Alert erroFecharConexao = new Alert(Alert.AlertType.ERROR);
                erroFecharConexao.setHeaderText("Erro ao fechar conexões do Banco de dados");
                erroFecharConexao.show();
            }
        }

    }

    
}
