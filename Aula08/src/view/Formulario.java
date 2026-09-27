package view;


import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import business.Aplicacao;

public class Formulario {

    private JFrame frmInvestimento;
    private JLabel lblValorAplicado;
    private JLabel lblPrazo;
    private JLabel lblIndexador;
    private JLabel lblResultado;

    private JTextField txtValorAplicado;
    private JTextField txtPrazo;

    private JButton btnCalcularRendimento;

    private JComboBox<String> cboIndexador;

    Aplicacao aplicacao = null;

    public Formulario() {
        inicializarComponentes();
    }

    private void inicializarComponentes() {

        // inicializa elemento do tipo da janela
        frmInvestimento = new JFrame("Investimento Financeiro");

        // define posição inicial e tamanho da janelinha
        frmInvestimento.setBounds(500, 250, 500, 350);

        // configura a operação ao fechar a janelinha dfo formulario
        frmInvestimento.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // elimina pre-configuraçoes de layout
        frmInvestimento.setLayout(null);

        // recupera instancia do painel de conteudo
        Container painelDeConteudo = frmInvestimento.getContentPane();


        // label do valor aplicado
        lblValorAplicado = new JLabel("Valor a ser aplicado:");
        lblValorAplicado.setBounds(40, 40, 150, 25);
        painelDeConteudo.add(lblValorAplicado);

        // textfield do valor aplicado
        txtValorAplicado = new JTextField();
        txtValorAplicado.setBounds(190, 40, 150, 25);

        // permitindo apenas numeros e ponto decimal
        txtValorAplicado.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

                char c = e.getKeyChar();

                if (!Character.isDigit(c) &&
                    c != '.' &&
                    !Character.isISOControl(c)) {

                    e.consume();
                }

                if (c == '.' && txtValorAplicado.getText().contains(".")) {
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

        });

        painelDeConteudo.add(txtValorAplicado);


        // label do prazo
        lblPrazo = new JLabel("Prazo da aplicação (meses):");
        lblPrazo.setBounds(40, 85, 180, 25);
        painelDeConteudo.add(lblPrazo);

        // campo de tezxto do prazo
        txtPrazo = new JTextField();
        txtPrazo.setBounds(220, 85, 120, 25);

        // permite apenas números
        txtPrazo.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

                char c = e.getKeyChar();

                if (!Character.isDigit(c) &&
                    !Character.isISOControl(c)) {

                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

        });

        painelDeConteudo.add(txtPrazo);


        // label do indexador
        lblIndexador = new JLabel("Tipo de aplicação:");
        lblIndexador.setBounds(40, 130, 150, 25);
        painelDeConteudo.add(lblIndexador);


        // combobox
        cboIndexador = new JComboBox<String>();
        cboIndexador.setBounds(190, 130, 150, 25);

        cboIndexador.addItem("Poupança");
        cboIndexador.addItem("CDI");
        cboIndexador.addItem("Tesouro Direto");

        painelDeConteudo.add(cboIndexador);


        // botao
        btnCalcularRendimento = new JButton("Calcular Rendimento");
        btnCalcularRendimento.setBounds(120, 180, 240, 35);

        btnCalcularRendimento.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (validarPreenchimento()) {

                    float valorAplicado =
                            Float.parseFloat(txtValorAplicado.getText());

                    int prazo =
                            Integer.parseInt(txtPrazo.getText());

                    float taxa = 0;

                    switch (cboIndexador.getSelectedIndex()) {

                        case 0:
                            taxa = 0.38f;
                            break;

                        case 1:
                            taxa = 0.53f;
                            break;

                        case 2:
                            taxa = 0.65f;
                            break;
                    }

                    aplicacao = new Aplicacao();

                    aplicacao.calcularRendimento(
                            valorAplicado,
                            prazo,
                            taxa
                    );

                    exibirResultado();
                }
            }
        });

        painelDeConteudo.add(btnCalcularRendimento);


        // laabel do resultado
        lblResultado = new JLabel("Rendimento:");
        lblResultado.setBounds(80, 240, 350, 40);
        painelDeConteudo.add(lblResultado);


        // exibe o formulario
        frmInvestimento.setVisible(true);
    }


    private boolean validarPreenchimento() {

        return txtValorAplicado.getText().length() > 0 &&
               txtPrazo.getText().length() > 0;
    }


    private void exibirResultado() {

        lblResultado.setText(
                String.format(
                        "Rendimento: R$ %.2f",
                        aplicacao.getRendimento()
                )
        );
    }

}