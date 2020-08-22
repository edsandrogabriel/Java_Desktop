package julho_crud_com_login_com_bancodados;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClassePrincipalProjeto extends Application {

    private static Scene telaLogin;
    private static Scene telaCRUD;
    private static Stage stageDeTroca;
    private static Scene telaMenuUsuarios_DeletarUsuario;
    private static Scene telaMenuUsuarios_InserirNovo;
    private static Scene telaMenuUsuarios_AtualizarUsuario;
    private static Scene telaMenuTabela_InserirNovo;
    private static Scene telaMenuTabelA_DeletarItem;
    private static Scene telaMenuTabela_AtualizarItem;

    @Override
    public void start(Stage stage) throws Exception {

        stageDeTroca = stage;

        Parent root = FXMLLoader.load(getClass().getResource("Login_com_banco_dados_e_CRUD.fxml"));
        telaLogin = new Scene(root);
        //Scene scene = new Scene(root);

        Parent segundaTelaParent = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        telaCRUD = new Scene(segundaTelaParent, 1190, 2000);

        Parent menuUsuarios = FXMLLoader.load(getClass().getResource("TelaMenuUsuariosCadastroNovo.fxml"));
        telaMenuUsuarios_InserirNovo = new Scene(menuUsuarios, 640, 480);

        Parent menuUsuariosdeletar = FXMLLoader.load(getClass().getResource("Menu_Usuarios_DeletarUser.fxml"));
        telaMenuUsuarios_DeletarUsuario = new Scene(menuUsuariosdeletar, 640, 480);

        Parent menuUsuariosAtualizar = FXMLLoader.load(getClass().getResource("MenuUsuariosAtualizar.fxml"));
        telaMenuUsuarios_AtualizarUsuario = new Scene(menuUsuariosAtualizar, 740, 500);

        Parent menuTabelaNovo = FXMLLoader.load(getClass().getResource("MenuTabela_InserirNovo.fxml"));
        telaMenuTabela_InserirNovo = new Scene(menuTabelaNovo, 900, 700);
                
        Parent menuTabelaAtualizarItem = FXMLLoader.load(getClass().getResource("menuTabela_AtualizarItem.fxml"));
        telaMenuTabela_AtualizarItem = new Scene(menuTabelaAtualizarItem, 900, 780);
        
        Parent menuTabelaDeletar = FXMLLoader.load(getClass().getResource("MenuTabelaDeletarItem.fxml"));
        telaMenuTabelA_DeletarItem = new Scene(menuTabelaDeletar, 900, 780);
        /*stage.setScene(telaLogin);
        stage.setResizable(true);
        stage.setMaximized(true);
        stage.show();*/
        estagioPrincipal(stage);
        

    }

    public void estagioPrincipal(Stage stage1) {
        stage1.setScene(telaLogin);
        stage1.setMaximized(true);
        stage1.show();
    }

    public void estagios(Stage stage2) {
        stage2.setScene(telaCRUD);
        stage2.setMaximized(true);
        stage2.show();
    }

    public static void TrocarTelas(String controleMudanca) {

        switch (controleMudanca) {

            case "Telalogin":
                stageDeTroca.setScene(telaLogin);

            case "TelaCRUD":
                stageDeTroca.setScene(telaCRUD);
                stageDeTroca.setMaximized(true);

        }

    }

    public static void TrocarTelaMenuUsuarios(String controleMudancaMenuUsuarios) {

        switch (controleMudancaMenuUsuarios) {

            case "menuUsuarioInserirCadastrarNovo":
                stageDeTroca.setScene(telaMenuUsuarios_InserirNovo);
                stageDeTroca.setMaximized(true);
        }
    }

    public static void TrocarTelaMenuUsuarios_Deletar(String controleMudancaDeletar) {
        switch (controleMudancaDeletar) {
            case "apertouBotaoDeletarUsuario":
                stageDeTroca.setScene(telaMenuUsuarios_DeletarUsuario);
                stageDeTroca.setMaximized(true);
        }
    }

    public static void TrocarTelaMenuUsuarios_Atualizar(String controleMudancaAtualizar) {
        switch (controleMudancaAtualizar) {
            case "apertouBotaoAtualizarUsuario":
                stageDeTroca.setScene(telaMenuUsuarios_AtualizarUsuario);
                stageDeTroca.setMaximized(true);
        }
    }

    public static void TrocarTelaMenuTabela_Novo(String controleMudancaInserirDeletar) {
        switch (controleMudancaInserirDeletar) {
            case "apertouBotaoNovoItem":
                stageDeTroca.setScene(telaMenuTabela_InserirNovo);
                
            case "apertouBotaoDeletarItem":
                stageDeTroca.setScene(telaMenuTabelA_DeletarItem);
                
        }
    }

      public static void TrocarTelaMenuTabela_AtualizarItem(String controleMudancaAtualizarItem) {
        switch (controleMudancaAtualizarItem) {
                 case "apertouBotaoAtualizarItem":
                stageDeTroca.setScene(telaMenuTabela_AtualizarItem);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
