package julho_crud_com_login_com_bancodados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.Alert;

public class ConexaoPrincipalBancoDadosTabela extends TabelaVisualizacaoDados {

    TabelaVisualizacaoDados varRfObjTVD_resultSet = new TabelaVisualizacaoDados();
    TabelaVisualizacaoDados varRfObjTVD_resultSet1 = new TabelaVisualizacaoDados();
    TabelaVisualizacaoDados varRfObjTVD_resultSet2 = new TabelaVisualizacaoDados();
    TabelaVisualizacaoDados varRfObjTVD_resultSet3 = new TabelaVisualizacaoDados();

    public Connection conexaoPrincipal_com_Banco_de_Dados_Para_Usar_Tabela() {

        String usuario = "root";
        String senha = null;
        String conectorMYSQL = "jdbc:mysql://localhost:3306/controle_de_estoque";

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

    public void fecharConexoes_da_Tabela(Connection fecharConexao, Statement fecharStatement, ResultSet fecharResultSet) {

        if (fecharConexao != null && fecharStatement != null && fecharResultSet != null) {
            try {
                fecharConexao.close();
                fecharStatement.close();
                fecharResultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
                Alert erroFecharConexao = new Alert(Alert.AlertType.ERROR);
                erroFecharConexao.setHeaderText("Erro ao fechar conexões do Banco de dados");
                erroFecharConexao.show();
            }
        }

    }
/*
        // Rotinas para ir buscar no banco de dados foi tudo feito no construtor do controlador
    public void buscarDadosNoBancoDados_controle_de_estoque() {

        Statement stmt_buscarDadosNoBancoDados = null;
        ResultSet rst_buscarDadosNoBancoDados = null;
        Connection conn_buscarDadosNoBancoDados = null;
        String sql_Para_executar_acao_do_statement = "SELECT * FROM controle_de_estoque.tabela_de_controle";

        //conexao para criar o statement
        try {
            stmt_buscarDadosNoBancoDados = conn_buscarDadosNoBancoDados.createStatement();

            rst_buscarDadosNoBancoDados = stmt_buscarDadosNoBancoDados.executeQuery(sql_Para_executar_acao_do_statement);

            while (rst_buscarDadosNoBancoDados.next()) {
/*
                varRfObjTVD_resultSet.setClasse_TVD_codigoBarrras(rst_buscarDadosNoBancoDados.getString("codigo_de_barras"));
                varRfObjTVD_resultSet1.setClasse_TVD_descricaoItem(rst_buscarDadosNoBancoDados.getString("descricao_do_item"));
                varRfObjTVD_resultSet2.setClasse_TVD_precoItem(rst_buscarDadosNoBancoDados.getString("quantidade_em_estoque"));
                varRfObjTVD_resultSet3.setClasse_TVD_quantidadeEstoque(rst_buscarDadosNoBancoDados.getString("preço_do_item"));

               
}

            fecharConexoes_da_Tabela(conn_buscarDadosNoBancoDados, stmt_buscarDadosNoBancoDados, rst_buscarDadosNoBancoDados);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    } */

} // fim da classe
