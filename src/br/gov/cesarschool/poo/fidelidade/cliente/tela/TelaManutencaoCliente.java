package br.gov.cesarschool.poo.fidelidade.cliente.tela;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

import br.gov.cesarschool.poo.fidelidade.cliente.dao.ClienteDAO;
import br.gov.cesarschool.poo.fidelidade.cliente.entidade.Cliente;
import br.gov.cesarschool.poo.fidelidade.cliente.negocio.ClienteMediator;
import br.gov.cesarschool.poo.fidelidade.geral.entidade.Endereco;
import br.gov.cesarschool.poo.fidelidade.geral.entidade.Sexo;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;

import java.util.Date;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

public class TelaManutencaoCliente {
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	protected Shell shell;
	private Button btnNovo;
	private Button btnBuscar;
	private Button btnRadioButtonMasculino;
	private Button btnRadioButtonFeminino;
	private Combo cbTipoResgate;
	private Button btnIncluir;
	private Button btnLimpar;
	private Button btnVoltar;
	private Combo cbEstado;
	private Boolean isNew;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TelaManutencaoCliente window = new TelaManutencaoCliente();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 551);
		shell.setText("SWT Application");
		
		Label lblCPF = new Label(shell, SWT.NONE);
		lblCPF.setBounds(44, 22, 33, 20);
		lblCPF.setText("CPF");
		
		Text txtCPF = new Text(shell, SWT.BORDER);
		txtCPF.setBounds(91, 22, 149, 25);
		
		Button btnNovo = new Button(shell, SWT.NONE);
		this.btnNovo = btnNovo;
		
		btnNovo.setBounds(246, 22, 56, 25);
		formToolkit.adapt(btnNovo, true, true);
		btnNovo.setText("Novo");
		
		Button btnBuscar = new Button(shell, SWT.NONE);
		this.btnBuscar = btnBuscar;
	
		btnBuscar.setBounds(308, 22, 56, 25);
		formToolkit.adapt(btnBuscar, true, true);
		btnBuscar.setText("Buscar");
		
		Label lblNomeCompleto = new Label(shell, SWT.NONE);
		lblNomeCompleto.setBounds(44, 62, 111, 25);
		lblNomeCompleto.setText("Nome Completo");
		lblNomeCompleto.setVisible(false);
		
		Text txtNomeCompleto = new Text(shell, SWT.BORDER);
		txtNomeCompleto.setBounds(161, 60, 209, 25);
		txtNomeCompleto.setVisible(false);
		
		Label lblSexo = new Label(shell, SWT.NONE);
		lblSexo.setBounds(44, 102, 33, 25);
		lblSexo.setText("Sexo");
		lblSexo.setVisible(false);
		
		Button btnRadioButtonMasculino = new Button(shell, SWT.RADIO);
		this.btnRadioButtonMasculino = btnRadioButtonMasculino;
		btnRadioButtonMasculino.setBounds(101, 104, 33, 16);
		btnRadioButtonMasculino.setText("M");
		btnRadioButtonMasculino.setVisible(false);
		
		Button btnRadioButtonFeminino = new Button(shell, SWT.RADIO);
		this.btnRadioButtonFeminino = btnRadioButtonFeminino;
		btnRadioButtonFeminino.setBounds(151, 104, 33, 16);
		btnRadioButtonFeminino.setText("F");
		btnRadioButtonFeminino.setVisible(false);
		
		Label lblDataDeNascimento = new Label(shell, SWT.NONE);
		lblDataDeNascimento.setBounds(44, 142, 133, 25);
		lblDataDeNascimento.setText("Data de nascimento");
		lblDataDeNascimento.setVisible(false);
		
		Text txtDataDeNascimento = new Text(shell, SWT.BORDER);
		txtDataDeNascimento.setBounds(184, 142, 117, 25);
		txtDataDeNascimento.setVisible(false);
		
		Label lblRenda = new Label(shell, SWT.NONE);
		lblRenda.setBounds(44, 182, 42, 25);
		lblRenda.setText("Renda");
		lblRenda.setVisible(false);
		
		Text txtRenda = new Text(shell, SWT.BORDER);
		txtRenda.setBounds(101, 182, 99, 25);
		txtRenda.setVisible(false);
		
		Label lblLogradouro = new Label(shell, SWT.NONE);
		lblLogradouro.setBounds(44, 222, 78, 25);
		lblLogradouro.setText("Logradouro");
		lblLogradouro.setVisible(false);
		
		Text txtLogradouro = new Text(shell, SWT.BORDER);
		txtLogradouro.setBounds(132, 222, 209, 25);
		txtLogradouro.setVisible(false);
		
		Label lblNumero = new Label(shell, SWT.NONE);
		lblNumero.setBounds(44, 262, 56, 25);
		lblNumero.setText("Número");
		lblNumero.setVisible(false);
		
		Text txtNumero = new Text(shell, SWT.BORDER);
		txtNumero.setBounds(132, 262, 56, 25);
		txtNumero.setVisible(false);
		
		Label lblComplemento = new Label(shell, SWT.NONE);
		lblComplemento.setBounds(44, 302, 99, 25);
		lblComplemento.setText("Complemento");
		lblComplemento.setVisible(false);
		
		Text txtComplemento = new Text(shell, SWT.BORDER);
		txtComplemento.setBounds(155, 302, 209, 25);
		txtComplemento.setVisible(false);
		
		Label lblCep = new Label(shell, SWT.NONE);
		lblCep.setBounds(44, 342, 33, 25);
		lblCep.setText("CEP");
		lblCep.setVisible(false);
		
		Text txtCep = new Text(shell, SWT.BORDER);
		txtCep.setBounds(91, 342, 117, 25);
		txtCep.setVisible(false);
		
		Label lblCidade = new Label(shell, SWT.NONE);
		lblCidade.setBounds(44, 382, 47, 25);
		lblCidade.setText("Cidade");
		lblCidade.setVisible(false);
		
		Text txtCidade = new Text(shell, SWT.BORDER);
		txtCidade.setBounds(101, 382, 117, 25);
		txtCidade.setVisible(false);
		
		Label lblEstado = new Label(shell, SWT.NONE);
		lblEstado.setBounds(44, 422, 56, 25);
		lblEstado.setText("Estado");
		lblEstado.setVisible(false);
		
		Combo cbEstado = new Combo(shell, SWT.NONE);
		cbEstado.setItems(new String[] {"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"});
		cbEstado.setToolTipText("");
		cbEstado.setEnabled(true);
		this.cbEstado = cbEstado;
		cbEstado.setBounds(132, 422, 203, 23);
		cbEstado.setText("Escolha...");
		cbEstado.setVisible(false);
		
		Button btnIncluir = new Button(shell, SWT.NONE);
		this.btnIncluir = btnIncluir;
		
		btnIncluir.setBounds(101, 462, 56, 25);
		formToolkit.adapt(btnIncluir, true, true);
		btnIncluir.setText("Incluir");
		btnIncluir.setVisible(false);
		
		Button btnLimpar = new Button(shell, SWT.NONE);
		this.btnLimpar = btnLimpar;
		
		btnLimpar.setBounds(171, 462, 56, 25);
		formToolkit.adapt(btnLimpar, true, true);
		btnLimpar.setText("Limpar");
		btnLimpar.setVisible(false);
		
		Button btnVoltar = new Button(shell, SWT.NONE);
		this.btnVoltar = btnVoltar;
		
		btnVoltar.setBounds(241, 462, 56, 25);
		formToolkit.adapt(btnVoltar, true, true);
		btnVoltar.setText("Voltar");
		btnVoltar.setVisible(false);
		
		btnNovo.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				String CPF = txtCPF.getText();
				if(ClienteDAO.buscar(CPF) == null) {
					isNew = true;
					lblCPF.setVisible(true);
					txtCPF.setVisible(true);
					btnNovo.setVisible(true);
					btnBuscar.setVisible(true);
					lblNomeCompleto.setVisible(true);
					txtNomeCompleto.setVisible(true);
					lblSexo.setVisible(true);
					btnRadioButtonMasculino.setVisible(true);
					btnRadioButtonFeminino.setVisible(true);
					lblDataDeNascimento.setVisible(true);
					txtDataDeNascimento.setVisible(true);
					lblRenda.setVisible(true);
					txtRenda.setVisible(true);
					lblLogradouro.setVisible(true);
					txtLogradouro.setVisible(true);
					lblNumero.setVisible(true);
					txtNumero.setVisible(true);
					lblComplemento.setVisible(true);
					txtComplemento.setVisible(true);
					lblCep.setVisible(true);
					txtCep.setVisible(true);
					lblCidade.setVisible(true);
					txtCidade.setVisible(true);
					lblEstado.setVisible(true);
					cbEstado.setVisible(true);
					btnIncluir.setVisible(true);
					btnLimpar.setVisible(true);
					btnVoltar.setVisible(true);
				}
			}
		});
		
		btnBuscar.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				String CPF = txtCPF.getText();
				if(ClienteDAO.buscar(CPF) != null) {
					isNew = false;
					Cliente cliente = ClienteDAO.buscar(CPF);
					lblCPF.setVisible(true);
					txtCPF.setVisible(true);
					btnNovo.setVisible(true);
					btnBuscar.setVisible(true);
					lblNomeCompleto.setVisible(true);
					txtNomeCompleto.setVisible(true);
					txtNomeCompleto.setText(cliente.getNomeCompleto());
					lblSexo.setVisible(true);
					if(cliente.getSexo().equals("M")) {
						btnRadioButtonMasculino.setEnabled(true);
					}else if(cliente.getSexo().equals("F")) {
						btnRadioButtonFeminino.setEnabled(true);
					}
					btnRadioButtonMasculino.setVisible(true);
					btnRadioButtonFeminino.setVisible(true);
					lblDataDeNascimento.setVisible(true);
					txtDataDeNascimento.setVisible(true);
					txtDataDeNascimento.setText(cliente.getDataNascimento().toString());
					lblRenda.setVisible(true);
					txtRenda.setVisible(true);
					txtRenda.setText(String.valueOf(cliente.getRenda()));
					lblLogradouro.setVisible(true);
					txtLogradouro.setVisible(true);
					lblNumero.setVisible(true);
					txtNumero.setVisible(true);
					lblComplemento.setVisible(true);
					txtComplemento.setVisible(true);
					lblCep.setVisible(true);
					txtCep.setVisible(true);
					lblCidade.setVisible(true);
					txtCidade.setVisible(true);
					lblEstado.setVisible(true);
					cbEstado.setVisible(true);
					btnIncluir.setVisible(true);
					btnIncluir.setText("Alterar");
					btnLimpar.setVisible(true);
					btnVoltar.setVisible(true);
				}
			}
		});
		
		btnLimpar.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				txtCep.setText("");
				txtComplemento.setText("");
				txtCidade.setText("");
				cbEstado.setText("");
				txtLogradouro.setText("");
				txtRenda.setText("");
				txtNomeCompleto.setText("");
				txtNumero.setText("");
				txtDataDeNascimento.setText("");
				btnRadioButtonMasculino.setSelection(false);
				btnRadioButtonFeminino.setSelection(false);
			}
		});
		
		btnVoltar.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				txtCep.setText("");
				txtComplemento.setText("");
				txtCidade.setText("");
				cbEstado.setText("");
				txtCPF.setText("");
				txtLogradouro.setText("");
				txtRenda.setText("");
				txtNomeCompleto.setText("");
				txtNumero.setText("");
				txtDataDeNascimento.setText("");
				btnRadioButtonMasculino.setSelection(false);
				btnRadioButtonFeminino.setSelection(false);
				lblCPF.setVisible(false);
				txtCPF.setVisible(false);
				btnNovo.setVisible(false);
				btnBuscar.setVisible(false);
				lblNomeCompleto.setVisible(false);
				txtNomeCompleto.setVisible(false);
				lblSexo.setVisible(false);
				btnRadioButtonMasculino.setVisible(false);
				btnRadioButtonFeminino.setVisible(false);
				lblDataDeNascimento.setVisible(false);
				txtDataDeNascimento.setVisible(false);
				lblRenda.setVisible(false);
				txtRenda.setVisible(false);
				lblLogradouro.setVisible(false);
				txtLogradouro.setVisible(false);
				lblNumero.setVisible(false);
				txtNumero.setVisible(false);
				lblComplemento.setVisible(false);
				txtComplemento.setVisible(false);
				lblCep.setVisible(false);
				txtCep.setVisible(false);
				lblCidade.setVisible(false);
				txtCidade.setVisible(false);
				lblEstado.setVisible(false);
				cbEstado.setVisible(false);
				btnIncluir.setVisible(false);
				btnLimpar.setVisible(false);
				btnVoltar.setVisible(false);
				txtCPF.setVisible(true);
				lblCPF.setVisible(true);
				btnBuscar.setVisible(true);
				btnNovo.setVisible(true);
			}
		});
		
		btnIncluir.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				String
				CPF = txtCPF.getText();
				if(CPF.length()!= 11) {
				  JOptionPane.showMessageDialog(null, "Formato inválido");
				  return;
				}
				String dataDeNascimento = txtDataDeNascimento.getText();
				if(dataDeNascimento.length()!= 10) {
				  JOptionPane.showMessageDialog(null, "Formato inválido");
				  return;
				}
				else {
				  if(dataDeNascimento.charAt(2) != '/' || dataDeNascimento.charAt(5) != '/'){
				    JOptionPane.showMessageDialog(null, "Formato inválido");
				    return;
				  }
				  else {
				    StringTokenizer splitDataDeNascimento = new StringTokenizer(dataDeNascimento, "/");
				    int contSplitDataNascimento = 0;
				    while(contSplitDataNascimento < 3) {
			    	  int copia = Integer.parseInt(splitDataDeNascimento.nextToken());
				      if((copia<= 0 || copia > 31) && contSplitDataNascimento == 0){
				        JOptionPane.showMessageDialog(null, "Formato inválido");
				        return;
				      }
				      if((copia <= 0 || copia > 12)&&contSplitDataNascimento == 1){
				        JOptionPane.showMessageDialog(null, "Formato inválido");
				        return;
				      }
				      contSplitDataNascimento++;
				    }
				  }
				}
				String renda = txtRenda.getText();
				StringTokenizer splitRenda = new StringTokenizer(renda, ".");
				int contSplitRenda = 0;
				while(splitRenda.hasMoreTokens()) {
				  String copia = splitRenda.nextToken();
				  if(contSplitRenda == 0) {
				    if(copia.length() <= 10 && copia.length() >= 1) {
				      for(int i = 0; i < copia.length(); i++) {
				        if(!Character.isDigit(copia.charAt(i))){
				          JOptionPane.showMessageDialog(null, "Formato inválido");
				          return;
				        }
				      }
				    }
				    else {
				      JOptionPane.showMessageDialog(null, "Formato inválido");
				      return;
				    }
				  }
				  if(contSplitRenda == 1) {
				    if(copia.length() == 2) {
				      for(int i = 0; i < copia.length(); i++) {
				        if(!Character.isDigit(copia.charAt(i))){
				          JOptionPane.showMessageDialog(null, "Formato inválido");
				          return;
				        }
				      }
				    }
				    else {
				      JOptionPane.showMessageDialog(null, "Formato inválido");
				      return;
				    }
				  }
				  contSplitRenda++;
				}
				String numero = txtNumero.getText();
				if(numero.length() <= 7 && numero.length() >= 1) {
				  for(int i = 0; i < numero.length(); i++) {
				    if(!Character.isDigit(numero.charAt(i))){
				      JOptionPane.showMessageDialog(null, "Formato inválido");
				      return;
				    }
				  }
				}
				else {
				  JOptionPane.showMessageDialog(null, "Formato inválido");
				  return;
				}
				String
				cep = txtCep.getText();
				if(cep.length() == 8) {
				  for(int i = 0; i < cep.length(); i++) {
				    if(
				      !Character.isDigit(
				        cep.charAt(i)
				      )
				    )
				    {
				      JOptionPane.showMessageDialog(null, "Formato inválido");
				      return;
				    }
				  }
				}
				else {
				  JOptionPane.showMessageDialog(null, "Formato inválido");
				  return;
				}
				
				Endereco endereco = new Endereco(txtLogradouro.getText(), Integer.valueOf(txtNumero.getText()), txtComplemento.getText(), txtCep.getText(), txtCidade.getText(), cbEstado.getText(), "Brasil");
				
				Date data = new Date(txtDataDeNascimento.getText());	
				ClienteMediator.getInstance();
				if(isNew) {
					if(btnRadioButtonMasculino.getSelection()) {
						Cliente clienteM = new Cliente(txtCPF.getText(), txtNomeCompleto.getText(), Sexo.MASCULINO, data, Double.parseDouble(txtRenda.getText()), endereco);
						if(ClienteMediator.incluir(clienteM).getMensagemErroValidacao() == null) {
					        JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!");
					        return;
						}else {
							JOptionPane.showMessageDialog(null, 
									ClienteMediator.incluir(clienteM).getMensagemErroValidacao());
							return;
						}
					}else {
						Cliente clienteF = new Cliente(txtCPF.getText(), txtNomeCompleto.getText(), Sexo.FEMININO, data, Double.parseDouble(txtRenda.getText()), endereco);
						if(ClienteMediator.incluir(clienteF).getMensagemErroValidacao() == null) {
					        JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!");
					        return;
						}else {
							JOptionPane.showMessageDialog(null, 
									ClienteMediator.incluir(clienteF).getMensagemErroValidacao());
							return;
						}
					}
				}else {
					Cliente clienteAlterar = ClienteDAO.buscar(txtCPF.getText());
					clienteAlterar.setDataNascimento(data);
					clienteAlterar.setEndereco(endereco);
					clienteAlterar.setNomeCompleto(txtNomeCompleto.getText());
					clienteAlterar.setEndereco(endereco);
					clienteAlterar.setRenda(Double.parseDouble(txtRenda.getText()));
					if(btnRadioButtonMasculino.getSelection()) {
						clienteAlterar.setSexo(Sexo.MASCULINO);
					}else {
						clienteAlterar.setSexo(Sexo.FEMININO);
					}
					
					if(ClienteMediator.alterar(clienteAlterar) == null) {
				        JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");
				        return;
					}else {
						JOptionPane.showMessageDialog(null, 
								ClienteMediator.alterar(clienteAlterar));
						return;
					}
					
				}
				
			}
			
		});
	}

}
