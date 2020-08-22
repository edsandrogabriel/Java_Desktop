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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Menu_Usuarios_DeletarUserController implements Initializable {

    @FXML
    private TextField UsuarioDeletar;
    @FXML
    private TextField ID;
    @FXML
    private PasswordField senhaDeletar;
    //@FXML
    //private Label capturaDadosID_Senhauser;

    @FXML
    public void botaoDeletarUsuario(ActionEvent event) {

        ClassePrincipalProjeto.TrocarTelaMenuUsuarios_Deletar("apertouBotaoDeletarUsuario");
        this.menuUsuario_DeletarUsuarioExistente();

    }

    @FXML
    public void botaoCancelar_DeletarUsuario(ActionEvent event) {

        ClassePrincipalProjeto.TrocarTelas("TelaCRUD");

    }

    public void menuUsuario_DeletarUsuarioExistente() {
        Statement stmtDeletarUsuario = null;

        String DeletarusuarioID = null;

        DeletarusuarioID = ID.getText();

        //DELETE FROM  `crud`.`usuarios`(`usuario`,`senha` ) WHERE usuario = ? and senha = ?
        //SELECT * FROM crud.usuarios WHERE  usuario = ? and senha = ?    DELETE FROM `crud`.`usuarios` WHERE `idusuarios`='206';
        String mySQLexecutarAcaoDeletar = "DELETE FROM `crud`.`usuarios` WHERE `idusuarios`= ' " + DeletarusuarioID + "';";

        //DELETE FROM `crud`.`usuarios` WHERE `usuario`= ' " + Deletarusuario + "' and `senha`= ' " + Deletarsenha + "' ;
        //"DELETE usuario,senha WHERE `usuario`=' " +   + " ' IN (SELECT * FROM crud.usuarios WHERE  usuario = ? and senha = ?  )";
        //(SELECT * FROM crud.usuarios WHERE  usuario = ? and senha = ?) and (DELETE FROM `crud`.`usuarios` WHERE `idusuarios`='206'; )
        //SELECT idusuarios FROM crud.usuarios WHERE  usuario = ? and senha = ?
        MUsuarios_NovoUsuario varRefMUsuarios1 = new MUsuarios_NovoUsuario();
        Connection conn = varRefMUsuarios1.conexaoPrincipal_com_Banco_de_Dados();

        try {

            stmtDeletarUsuario = conn.createStatement();
            stmtDeletarUsuario.execute(mySQLexecutarAcaoDeletar);
            Alert deletadoSucesso = new Alert(Alert.AlertType.INFORMATION);
            deletadoSucesso.setHeaderText("Usuário : " + "[---- "+ UsuarioDeletar.getText() + " ----]" +" Deletado com Sucesso!");
            deletadoSucesso.show();
        } catch (SQLException e) {
            e.printStackTrace();
            Alert erroFecharConexao = new Alert(Alert.AlertType.ERROR);
            erroFecharConexao.setHeaderText("Erro ao Deletar Usuário");
            erroFecharConexao.show();
        }
        varRefMUsuarios1.fecharConexoes(conn, stmtDeletarUsuario);
    }

    public void menuUsuario_Consultar_ID() {
        PreparedStatement prSTMT = null;
       // PreparedStatement prSTMT1 = null;
        ResultSet rst = null;
       // ResultSet rst1 = null;
        String consultarUsuario = null;
        String consultarSenha = null;
        int id = 0;
        consultarUsuario = UsuarioDeletar.getText();
        consultarSenha = senhaDeletar.getText();
        //`idusuarios`= ' " + null + "' and `idusuarios`= ' " + Deletarusuario + "' ";
        //String querySQL_buscarID = "SELECT idusuarios,usuario,senha FROM `crud`.`usuarios` WHERE `idusuarios`= ' " + id + "' and `usuario`= ' " + consultarUsuario + "' and `senha`= ' " + consultarSenha + "'  ";
        
        //"SELECT * FROM `crud`.`usuarios` ";
        MUsuarios_NovoUsuario varRefMUsuarios1 = new MUsuarios_NovoUsuario();
        Connection conn = varRefMUsuarios1.conexaoPrincipal_com_Banco_de_Dados();
        String querySQL_buscarID = "SELECT * FROM crud.usuarios WHERE  usuario = ? and senha = ?";
       // String querySQL_Deletar = "DELETE * FROM crud.usuarios WHERE usuario = ' " + UsuarioDeletar.getText() + "' and senha = ' " + senhaDeletar.getText() + "' ";
        // "DELETE * FROM crud.usuarios WHERE usuario = ' " + UsuarioDeletar.getText() + "' and senha = ' " + senhaDeletar.getText() + "'; "
        try {
            // stmtConsultarID = conn.createStatement();
            //capturarID = stmtConsultarID.executeQuery(querySQL_buscarID);

            prSTMT = (PreparedStatement) conn.prepareStatement(querySQL_buscarID);
            prSTMT.setString(1, UsuarioDeletar.getText());
            prSTMT.setString(2, senhaDeletar.getText());
            rst = prSTMT.executeQuery();

            
            if (rst.next()) {
               Alert cptDaddos = new Alert(AlertType.INFORMATION);
                cptDaddos.setHeaderText("Anote o ID apresentado e insira no campo ID e clique em Deletar");
                //System.out.println("ID" + rst.getInt("idusuarios"));
                cptDaddos.setContentText(rst.getString("idusuarios"));
               // cptDaddos.setContentText(rst.getString("usuario" ));
               // cptDaddos.setContentText(rst.getString("senha"));
                cptDaddos.show();
                
               // System.out.println("Encontrado");
                
                /* 
                Poderia fazer isso para fazer uma segunda ida ao banco e executar o query de  deletar
                try {
                    conn = varRefMUsuarios1.conexaoPrincipal_com_Banco_de_Dados();
                    prSTMT = (PreparedStatement) conn.prepareStatement(querySQL_Deletar);
                    prSTMT.setString(1, UsuarioDeletar.getText());
                    prSTMT.setString(2, senhaDeletar.getText());
                    rst = prSTMT.executeQuery();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                varRefMUsuarios1.fecharConexoes(conn, prSTMT);
                
                */
               
            }
           
        } catch (SQLException e) {
            e.printStackTrace();
            Alert erroFecharConexao = new Alert(Alert.AlertType.ERROR);
            erroFecharConexao.setHeaderText("Erro ao Buscar Dados do Usuário");
            erroFecharConexao.show();
        }
        }
    
        
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
