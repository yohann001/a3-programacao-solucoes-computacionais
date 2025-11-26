package Controller;

import DAO.ProdutoDAO;
import Model.Produto;
import View.CadastroProduto;
import View.Mensagens;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.util.List;



public class ProdutoControllers {

    private ProdutoDAO dao;

    public ProdutoControllers() {
        this.dao = new ProdutoDAO();
    }
    public List<Produto> buscarProdutosPorTermo(String termo) {
  
    return this.dao.buscarProdutosPorTermo(termo);
}

    public void CadastrarProduto(JTextField c_nome_produto, JTextField c_descricao, JTextField c_quantidade, JTextField c_preco, int id_fornecedor) {
        try {
            // recebendo e validando dados da interface gr�fica.
            String nome = "";
            String descricao = "";
            int qntEstoque = 0;
            double preco = 0;

            if (c_nome_produto.getText().length() < 2) {
                throw new Mensagens("Nome deve conter ao menos 2 caracteres.");
            } else {
                nome = c_nome_produto.getText();
            }

            if (c_descricao.getText().length() <= 0) {
                throw new Mensagens("Descrição deve conter ao menos 2 caracteres.");
            } else {
                descricao = c_descricao.getText();

            }

            if (c_quantidade.getText().length() < 0) {
                throw new Mensagens("Quantidade deve ser maior ou igual a 0.");
            } else {
                qntEstoque = Integer.parseInt(c_quantidade.getText());
            }

            if (c_preco.getText().length() <= 0) {
                throw new Mensagens("Preço deve ser maior ou igual a 0.");
            } else {
                preco = Double.parseDouble(c_preco.getText());
            }
            int id = this.dao.maiorID() + 1;

            Produto objProduto = new Produto(id, nome, descricao, qntEstoque, preco, id_fornecedor);
            // envia os dados para o Controlador cadastrar
            if (this.dao.InsertProdutoBD(objProduto)) {
                JOptionPane.showMessageDialog(null, "Produto Cadastrado com Sucesso!");

                // limpa campos da interface
                c_nome_produto.setText("");
                c_descricao.setText("");
                c_quantidade.setText("");
                c_preco.setText("");

            }


        } catch (Mensagens erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        } catch (NumberFormatException erro2) {
            JOptionPane.showMessageDialog(null, "Informe um número.");
        } catch (SQLException ex) {
            Logger.getLogger(CadastroProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void alterarProduto(JTable jTableProdutos, JTextField c_nome_produto, JTextField c_descricao, JTextField c_quantidade,JTextField c_preco, int id_fornecedor){
        try {
            // recebendo e validando dados da interface gr�fica.
            String nome = "";
            String descricao = "";
            int qntEstoque = 0;
            double preco = 0;
            int id = 0;

            if (c_nome_produto.getText().length() < 2) {
                throw new Mensagens("Nome deve conter ao menos 2 caracteres.");
            } else {
                nome = c_nome_produto.getText();
            }

            if (c_descricao.getText().length() <= 0) {
                throw new Mensagens("Descrição deve conter ao menos 2 caracteres.");
            } else {
                descricao = c_descricao.getText();

            }

            if (c_quantidade.getText().length() <= 0) {
                throw new Mensagens("Quantidade deve ser maior ou igual a 0.");
            } else {
                qntEstoque = Integer.parseInt(c_quantidade.getText());
            }

            if (c_preco.getText().length() <= 0) {
                throw new Mensagens("Preço deve ser maior ou igual a 0.");
            } else {
                preco = Double.parseDouble(c_preco.getText());
            }

            if (jTableProdutos.getSelectedRow() == -1) {
                throw new Mensagens("Primeiro Selecione um Produto para Alterar");
            } else {
                id = Integer.parseInt(jTableProdutos.getValueAt(jTableProdutos.getSelectedRow(), 0).toString());
            }

            Produto objProduto = new Produto(id,nome,descricao,qntEstoque,preco, id_fornecedor);
         
            if (this.dao.UpdateProdutoBD(objProduto)) {

                // limpa os campos
                c_nome_produto.setText("");
                c_descricao.setText("");
                c_quantidade.setText("");
                c_preco.setText("");
                JOptionPane.showMessageDialog(null, "Produto Alterado com Sucesso!");

            }
            System.out.println(objProduto.toString());
        } catch (Mensagens erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        } catch (NumberFormatException erro2) {
            JOptionPane.showMessageDialog(null, "Informe um número.");
        } finally {
            this.carregaTabela(jTableProdutos);
        }
    
    }

    @SuppressWarnings("unchecked")
    public void carregaTabela(JTable jTableProdutos) {

        DefaultTableModel modelo = (DefaultTableModel) jTableProdutos.getModel();
        modelo.setNumRows(0);

        ArrayList<Produto> minhalista = new ArrayList<>();
        minhalista = dao.getMinhaLista();

        for (Produto a : minhalista) {
            modelo.addRow(new Object[]{
                a.getId_produto(),
                a.getNome_produto(),
                a.getDescricao_produto(),
                a.getQuantidade_estoque(),
                a.getPreco(),
                a.getData_cadastro(),
                a.getData_Atualizacao(),
                a.getId_fornecedor()
            });
        }
    }

    public void ApagarProduto(JTable jTableProdutos, JTextField c_nome_produto, JTextField c_descricao, JTextField c_quantidade, JTextField c_preco) {
        try {
            // validando dados da interface gr�fica.
            int id = 0;
            if (jTableProdutos.getSelectedRow() == -1) {
                throw new Mensagens("Primeiro Selecione um Produto para APAGAR");
            } else {
                id = Integer.parseInt(jTableProdutos.getValueAt(jTableProdutos.getSelectedRow(), 0).toString());
            }

            // retorna 0 -> primeiro bot�o | 1 -> segundo bot�o | 2 -> terceiro bot�o
            int resposta_usuario = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja APAGAR este Produto ?");

            if (resposta_usuario == 0) {// clicou em SIM

                // envia os dados para o Aluno processar
                if (this.dao.DeleteProdutoBD(id)) {

                    // limpa os campos
                    c_nome_produto.setText("");
                    c_descricao.setText("");
                    c_quantidade.setText("");
                    c_preco.setText("");
                    JOptionPane.showMessageDialog(null, "Produto Apagado com Sucesso!");
                }
            }

            System.out.println(this.dao.getMinhaLista().toString());

        } catch (Mensagens erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        } finally {
            // atualiza a tabela.
            this.carregaTabela(jTableProdutos);
        }
    }
    

    public void carregaTabelaOrdenada(JTable jTableProdutos, boolean crescente) {
        DefaultTableModel modelo = (DefaultTableModel) jTableProdutos.getModel();
        modelo.setNumRows(0); 

        ArrayList<Produto> listaOrdenada = dao.getMinhaListaOrdenadaPorPreco(crescente);

        for (Produto a : listaOrdenada) {
            modelo.addRow(new Object[]{
                a.getId_produto(),
                a.getNome_produto(),
                a.getDescricao_produto(),
                a.getQuantidade_estoque(),
                a.getPreco(),
                a.getData_cadastro(),
                a.getData_Atualizacao(),
                a.getId_fornecedor()
            });
        }
    }
}