package julho_crud_com_login_com_bancodados;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import julho_crud_com_login_com_bancodados.MUsuarios_NovoUsuario;

public class TelaMenuUsuariosCadastroNovoController implements Initializable {

    @FXML
    private TextField novoUsuario = null;
    @FXML
    private PasswordField novaSenha = null;

    @FXML
    private Button cadastrar;

    @FXML
    private void botaoCadastrar(ActionEvent event) {
        MUsuarios_NovoUsuario vrNU = new MUsuarios_NovoUsuario();

        this.menuUsuario_CadastrarNovoUsuario();

        if(event != null){
         ClassePrincipalProjeto.TrocarTelas("TelaCRUD");
         Alert usuarioCadastroSucesso = new Alert(AlertType.INFORMATION);
         usuarioCadastroSucesso.setHeaderText("Novo Usuário Cadastrado Com Sucesso!");
         usuarioCadastroSucesso.setContentText("Se der erro ao logar insira espaço no campo usuário antes do nome Cadastrado / Contate o administrador");
         usuarioCadastroSucesso.show();
        
        }
    }

    @FXML
    private void botaoCancelarMenuUsuarios(ActionEvent event) {
        ClassePrincipalProjeto.TrocarTelas("TelaCRUD");

    }

    private String n_str_User;
    private String n_str_Password;
    private int id = 1;
    private int idAux = 0;

    public int getIdAux() {
        return idAux;
    }

    public void setIdAux(int idAux) {
        this.idAux = idAux;
    }

    public TextField getNovoUsuario() {
        return novoUsuario;
    }

    public void setNovoUsuario(TextField novoUsuario) {
        this.novoUsuario = novoUsuario;
    }

    public PasswordField getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(PasswordField novaSenha) {
        this.novaSenha = novaSenha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getN_str_User() {
        return n_str_User;
    }

    public void setN_str_User(String n_str_User) {
        this.n_str_User = n_str_User;
    }

    public String getN_str_Password() {
        return n_str_Password;
    }

    public void setN_str_Password(String n_str_Password) {
        this.n_str_Password = n_str_Password;
    }

    public void menuUsuario_CadastrarNovoUsuario() {

        MUsuarios_NovoUsuario varRefMUsuarios = new MUsuarios_NovoUsuario();

        Statement stmtNovoUsuario = null;
        String usuarioNovo = null;
        String senhaUsuarioNovo = null;

        for (int i = 0; i < 2; i++) {

            Random idAlea = new Random();
            int idAux1 = idAlea.nextInt(1000);
            this.setIdAux(idAux1);
        }

        usuarioNovo = novoUsuario.getText();
        senhaUsuarioNovo = novaSenha.getText();

        String mySQLexecutarAcaoEscrever = "INSERT INTO `crud`.`usuarios` (`idusuarios`,`usuario`, `senha`) VALUES ('  " + this.getIdAux() + " ',' " + usuarioNovo + "', '" + senhaUsuarioNovo + "');";

        Connection conn = varRefMUsuarios.conexaoPrincipal_com_Banco_de_Dados();

        try {

            stmtNovoUsuario = conn.createStatement();
            stmtNovoUsuario.execute(mySQLexecutarAcaoEscrever);

        } catch (SQLException e) {
            e.printStackTrace();
            Alert erroFecharConexao = new Alert(Alert.AlertType.ERROR);
            erroFecharConexao.setHeaderText("Erro ao Inserir novo Usuário");
            erroFecharConexao.show();
        }

        varRefMUsuarios.fecharConexoes(conn, stmtNovoUsuario);
    }
    
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

} // fim da classe initializable
