package julho_crud_com_login_com_bancodados;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import julho_crud_com_login_com_bancodados.TabelaVisualizacaoDados;
import julho_crud_com_login_com_bancodados.MUsuarios_NovoUsuario;

public class FXMLController implements Initializable {

    @FXML
    private TableView<TabelaVisualizacaoDados> Tv_tabela = new TableView<TabelaVisualizacaoDados>();
    @FXML
    private TableColumn< TabelaVisualizacaoDados, String> Tc_codigoDeBarras;
    @FXML
    private TableColumn< TabelaVisualizacaoDados, String> Tc_descricaoDoItem;
    @FXML
    private TableColumn< TabelaVisualizacaoDados, String> Tc_quantidadeEmEstoque;
    @FXML
    private TableColumn< TabelaVisualizacaoDados, String> Tc_preco;
  

    @FXML
    private void menuUsuariosNovoUser(ActionEvent event) {

       
        if (event != null) {
            ClassePrincipalProjeto.TrocarTelaMenuUsuarios("menuUsuarioInserirCadastrarNovo");

        } else {
            ClassePrincipalProjeto.TrocarTelaMenuUsuarios(null);
        }

    }

    @FXML
    private void menuUsuariosDeletarUser(ActionEvent event) {
        if (event != null) {
            ClassePrincipalProjeto.TrocarTelaMenuUsuarios_Deletar("apertouBotaoDeletarUsuario");

        } else {
            ClassePrincipalProjeto.TrocarTelaMenuUsuarios_Deletar(null);
        }
    }

    @FXML
    private void menuUsuariosAtualizarUser(ActionEvent event) {
        if (event != null) {
            ClassePrincipalProjeto.TrocarTelaMenuUsuarios_Atualizar("apertouBotaoAtualizarUsuario");

        } else {
            ClassePrincipalProjeto.TrocarTelaMenuUsuarios_Deletar(null);
        }
    }

    @FXML
    private void menuTabelaInserirItem(ActionEvent event) {

        ClassePrincipalProjeto.TrocarTelaMenuTabela_Novo("apertouBotaoNovoItem");

    }

    @FXML
    private void menuTabelaDeletarItem(ActionEvent event1) {
        ClassePrincipalProjeto.TrocarTelaMenuTabela_Novo("apertouBotaoDeletarItem");
    }
    
     @FXML
    private void menuTabelaAtualizarItem(ActionEvent event1) {
        ClassePrincipalProjeto.TrocarTelaMenuTabela_AtualizarItem("apertouBotaoAtualizarItem");
    }

    ObservableList<TabelaVisualizacaoDados> dadosQueSeraoInseridosTabela = FXCollections.observableArrayList( //new TabelaVisualizacaoDados(cdgBrr, dscIt, qtEtq, prItm)
            ); // fim do ObservableList

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // aqui no construtor do controlador que e chamado primeiro colocar as implementacoes

        Statement stmt_buscarDadosNoBancoDados = null;
        ResultSet rst_buscarDadosNoBancoDados = null;
        ConexaoPrincipalBancoDadosTabela vrctlInitConn = new ConexaoPrincipalBancoDadosTabela();

        try {
            Connection conn = vrctlInitConn.conexaoPrincipal_com_Banco_de_Dados_Para_Usar_Tabela();
            rst_buscarDadosNoBancoDados = conn.createStatement().executeQuery("SELECT * FROM controle_de_estoque.tabela_de_controle");

            while (rst_buscarDadosNoBancoDados.next()) {

                dadosQueSeraoInseridosTabela.add(new TabelaVisualizacaoDados(rst_buscarDadosNoBancoDados.getString("codigo_de_barras"), rst_buscarDadosNoBancoDados.getString("descricao_do_item"),
                        rst_buscarDadosNoBancoDados.getString("quantidade_em_estoque"), rst_buscarDadosNoBancoDados.getString("preço_do_item")
                ));
            }

            vrctlInitConn.fecharConexoes_da_Tabela(conn, stmt_buscarDadosNoBancoDados, rst_buscarDadosNoBancoDados);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Tc_codigoDeBarras.setCellValueFactory(new PropertyValueFactory<>("Classe_TVD_codigoBarrras"));
        Tc_descricaoDoItem.setCellValueFactory(new PropertyValueFactory<>("Classe_TVD_descricaoItem"));
        Tc_quantidadeEmEstoque.setCellValueFactory(new PropertyValueFactory<>("Classe_TVD_quantidadeEstoque"));
        Tc_preco.setCellValueFactory(new PropertyValueFactory<>("Classe_TVD_precoItem"));

        Tv_tabela.setItems(dadosQueSeraoInseridosTabela);
        // Tv_tabela.refresh();

    }

}
