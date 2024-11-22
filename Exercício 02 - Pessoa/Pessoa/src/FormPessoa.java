import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormPessoa {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Formulário Pessoa");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new BorderLayout());

        JPanel panelCampos = new JPanel(new GridLayout(4, 2, 5, 5));

        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JLabel lblNumero = new JLabel("Número: ");
        JTextField txtNumero = new JTextField(String.valueOf(Pessoa.getKp()));
        txtNumero.setEditable(false); 

        JLabel lblNome = new JLabel("Nome: ");
        JTextField txtNome = new JTextField();

        JLabel lblSexo = new JLabel("Sexo (M/F): ");
        JTextField txtSexo = new JTextField();

        JLabel lblIdade = new JLabel("Idade: ");
        JTextField txtIdade = new JTextField();

        JButton btnOk = new JButton("OK");
        JButton btnLimpar = new JButton("Limpar");
        JButton btnMostrar = new JButton("Mostrar");
        JButton btnSair = new JButton("Sair");

        panelCampos.add(lblNumero);
        panelCampos.add(txtNumero);
        panelCampos.add(lblNome);
        panelCampos.add(txtNome);
        panelCampos.add(lblSexo);
        panelCampos.add(txtSexo);
        panelCampos.add(lblIdade);
        panelCampos.add(txtIdade);

        panelBotoes.add(btnOk);
        panelBotoes.add(btnLimpar);
        panelBotoes.add(btnMostrar);
        panelBotoes.add(btnSair);

        frame.add(panelCampos, BorderLayout.CENTER);
        frame.add(panelBotoes, BorderLayout.SOUTH);

        Pessoa[] umaPessoa = {null};

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
        
                    String sexoInput = txtSexo.getText().trim().toUpperCase(); 
                    if (sexoInput.isEmpty() || !(sexoInput.equals("M") || sexoInput.equals("F"))) {
                        throw new IllegalArgumentException("O campo Sexo deve ser 'M' ou 'F'.");
                    }
                    char sexo = sexoInput.charAt(0); 
        
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
                txtSexo.setText("");
                txtIdade.setText("");
            }
        });

        btnMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (umaPessoa[0] != null) {
                    Pessoa p = umaPessoa[0];
                    JOptionPane.showMessageDialog(frame,
                            "Dados da Pessoa:\n" +
                                    "Nome: " + p.getNome() + "\n" +
                                    "Sexo: " + p.getSexo() + "\n" +
                                    "Idade: " + p.getIdade() + "\n" +
                                    "Total Pessoas: " + Pessoa.getKp());
                } else {
                    JOptionPane.showMessageDialog(frame, "Nenhuma pessoa cadastrada.", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); 
            }
        });

        frame.setLocationRelativeTo(null); 
        frame.setVisible(true);
    }
}
