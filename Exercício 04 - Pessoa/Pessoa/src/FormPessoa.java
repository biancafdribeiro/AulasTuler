//Bianca Fonseca Dantas Ribeiro - CB3025683

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FormPessoa {
    private JFrame frame;
    private JTextField txtNome, txtIdade, txtNumero;
    private JRadioButton rdbMasculino, rdbFeminino;
    private JButton btnOk, btnLimpar, btnMostrar, btnSair;
    private Pessoa[] umaPessoa;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                FormPessoa window = new FormPessoa();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public FormPessoa() {
        umaPessoa = new Pessoa[1];
        
        frame = new JFrame();
        frame.setBounds(100, 100, 400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        BoxLayout boxLayout = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS);
        frame.getContentPane().setLayout(boxLayout);
        
        JPanel panelCampos = new JPanel();
        panelCampos.setLayout(new BoxLayout(panelCampos, BoxLayout.Y_AXIS));
        
        JPanel nomePanel = new JPanel();
        nomePanel.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        nomePanel.add(txtNome);
        txtNome.setColumns(20);
        panelCampos.add(nomePanel);
        
        JPanel sexoPanel = new JPanel();
        sexoPanel.add(new JLabel("Sexo:"));
        rdbMasculino = new JRadioButton("Masculino");
        rdbFeminino = new JRadioButton("Feminino");
        ButtonGroup grupoSexo = new ButtonGroup();
        grupoSexo.add(rdbMasculino);
        grupoSexo.add(rdbFeminino);
        sexoPanel.add(rdbMasculino);
        sexoPanel.add(rdbFeminino);
        panelCampos.add(sexoPanel);
        
        JPanel idadePanel = new JPanel();
        idadePanel.add(new JLabel("Idade:"));
        txtIdade = new JTextField();
        idadePanel.add(txtIdade);
        txtIdade.setColumns(20);
        panelCampos.add(idadePanel);
        
        JPanel numeroPanel = new JPanel();
        numeroPanel.add(new JLabel("Número:"));
        txtNumero = new JTextField();
        txtNumero.setEditable(false);
        numeroPanel.add(txtNumero);
        txtNumero.setColumns(10);
        panelCampos.add(numeroPanel);

        frame.getContentPane().add(panelCampos);
        
        JPanel panelBotoes = new JPanel();
        panelBotoes.setLayout(new FlowLayout());
        
        btnOk = new JButton("OK");
        panelBotoes.add(btnOk);
        
        btnLimpar = new JButton("Limpar");
        panelBotoes.add(btnLimpar);
        
        btnMostrar = new JButton("Mostrar");
        panelBotoes.add(btnMostrar);
        
        btnSair = new JButton("Sair");
        panelBotoes.add(btnSair);
        
        frame.getContentPane().add(panelBotoes);
        
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nome = txtNome.getText().trim();
                    if (nome.isEmpty()) {
                        throw new IllegalArgumentException("O campo Nome é obrigatório.");
                    }
                    if (!nome.matches("[a-zA-Záàãâäéèêëíìîïóòõôöúùûüç]+")) {
                        throw new IllegalArgumentException("O campo Nome deve conter apenas letras.");
                    }

                    // Verificar qual sexo foi selecionado
                    char sexo = ' ';
                    if (rdbMasculino.isSelected()) {
                        sexo = 'M';
                    } else if (rdbFeminino.isSelected()) {
                        sexo = 'F';
                    } else {
                        throw new IllegalArgumentException("O campo Sexo é obrigatório.");
                    }

                    String idadeInput = txtIdade.getText().trim();
                    if (idadeInput.isEmpty()) {
                        throw new IllegalArgumentException("O campo Idade é obrigatório.");
                    }
                    int idade;
                    try {
                        idade = Integer.parseInt(idadeInput);
                        if (idade <= 0) {
                            throw new IllegalArgumentException("A Idade deve ser um número maior que 0.");
                        }
                    } catch (NumberFormatException ex) {
                        throw new IllegalArgumentException("O campo Idade deve conter apenas números.");
                    }

                    umaPessoa[0] = new Pessoa(nome, sexo, idade);
                    txtNumero.setText(String.valueOf(Pessoa.getKp()));

                    JOptionPane.showMessageDialog(frame, "Pessoa cadastrada com sucesso!");
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Erro de Validação", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtNome.setText("");
                txtIdade.setText("");
                txtNumero.setText("");
                rdbMasculino.setSelected(false);
                rdbFeminino.setSelected(false);
            }
        });

        btnMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (umaPessoa[0] != null) {
                    JOptionPane.showMessageDialog(frame,
                            "Nome: " + umaPessoa[0].getNome() +
                                    "\nSexo: " + (umaPessoa[0].getSexo() == 'M' ? "Masculino" : "Feminino") +
                                    "\nIdade: " + umaPessoa[0].getIdade() +
                                    "\nNúmero: " + umaPessoa[0].getKp());
                } else {
                    JOptionPane.showMessageDialog(frame, "Nenhuma pessoa cadastrada.");
                }
            }
        });

        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
