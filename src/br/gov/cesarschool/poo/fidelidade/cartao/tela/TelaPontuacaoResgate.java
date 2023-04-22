package br.gov.cesarschool.poo.fidelidade.cartao.tela;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;



import javax.swing.JOptionPane;

import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

import br.gov.cesarschool.poo.fidelidade.cartao.dao.CartaoFidelidadeDAO;
import br.gov.cesarschool.poo.fidelidade.cartao.entidade.CartaoFidelidade;
import org.eclipse.swt.widgets.Combo;

public class TelaPontuacaoResgate {

	protected Shell shell;
	private Text txNumeroCartao;
	private Button btnBuscar;
	private Button btnRadioButton;
	private Button btnRadioButton_1;
	private Text txSaldoAtual;
	private Text txValor;
	private Combo cbTipoResgate;
	private Button btnPontuarResgatar;
	private Button btnVoltar;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TelaPontuacaoResgate window = new TelaPontuacaoResgate();
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
		shell.setEnabled(false);
		shell.setSize(450, 397);
		shell.setText("SWT Application");
		
		txNumeroCartao = new Text(shell, SWT.BORDER);
		txNumeroCartao.setBounds(136, 22, 139, 21);
		
		Label lblNmeroDoCarto = new Label(shell, SWT.NONE);
		lblNmeroDoCarto.setBounds(30, 25, 100, 15);
		lblNmeroDoCarto.setText("Número do Cartão");
		
		Button btnBuscar = new Button(shell, SWT.NONE);
		this.btnBuscar = btnBuscar;
		btnBuscar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String numeroCartao = txNumeroCartao.getText();
				CartaoFidelidadeDAO cartaoFidelidadeDAO = new CartaoFidelidadeDAO();
				CartaoFidelidade cartaoFidelidade = cartaoFidelidadeDAO.buscar(Long.parseLong(numeroCartao));
				if(numeroCartao == null || cartaoFidelidade == null) {
					JOptionPane.showMessageDialog(null, 
							"Cartão Inválido");
					return;
				}
				txNumeroCartao.setEnabled(false);
				btnRadioButton.setEnabled(false);
				btnRadioButton_1.setEnabled(false);
				btnBuscar.setEnabled(false);
				if (btnRadioButton_1.getSelection()) {
					cbTipoResgate.setEnabled(true);
					btnPontuarResgatar.setEnabled(true);
					btnVoltar.setEnabled(true);	
					txSaldoAtual.setText(String.valueOf(cartaoFidelidade.getSaldo()));
					btnPontuarResgatar.setText("Resgatar");
				}
				
				
				
				
				
				
			}
		});
		btnBuscar.setBounds(285, 20, 75, 25);
		btnBuscar.setText("Buscar");
		
		Button btnRadioButton = new Button(shell, SWT.RADIO);
		this.btnRadioButton = btnRadioButton;
		btnRadioButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnRadioButton.setBounds(136, 69, 90, 16);
		btnRadioButton.setText("Pontuação");

		
		Button btnRadioButton_1 = new Button(shell, SWT.RADIO);
		this.btnRadioButton_1 = btnRadioButton_1;
		btnRadioButton_1.setBounds(136, 91, 90, 16);
		btnRadioButton_1.setText("Resgate");
		
		Label lblOperao = new Label(shell, SWT.NONE);
		lblOperao.setBounds(30, 70, 55, 15);
		lblOperao.setText("Operação");
		
		txSaldoAtual = new Text(shell, SWT.BORDER);
		txSaldoAtual.setEditable(false);
		txSaldoAtual.setBounds(136, 126, 139, 21);
		
		Label lblSaldoAtual = new Label(shell, SWT.NONE);
		lblSaldoAtual.setBounds(30, 129, 75, 15);
		lblSaldoAtual.setText("Saldo Atual");
		
		Combo cbTipoResgate = new Combo(shell, SWT.NONE);
		this.cbTipoResgate = cbTipoResgate;
		cbTipoResgate.setBounds(136, 164, 203, 23);
		
		Label lblTipoDeResgate = new Label(shell, SWT.NONE);
		lblTipoDeResgate.setBounds(30, 167, 100, 15);
		lblTipoDeResgate.setText("Tipo de Resgate");
		
		txValor = new Text(shell, SWT.BORDER);
		txValor.setEnabled(false);
		txValor.setEditable(false);
		txValor.setText("");
		txValor.setBounds(136, 211, 139, 21);
		
		Label lblValor = new Label(shell, SWT.NONE);
		lblValor.setBounds(30, 214, 55, 15);
		lblValor.setText("Valor");
		
		Button btnPontuarResgatar = new Button(shell, SWT.NONE);
		this.btnPontuarResgatar = btnPontuarResgatar;
		btnPontuarResgatar.setBounds(61, 281, 139, 25);
		btnPontuarResgatar.setText("Pontuar/Resgatar");
		
		Button btnVoltar = new Button(shell, SWT.NONE);
		this.btnVoltar = btnVoltar;
		btnVoltar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				txSaldoAtual.setText("");
				txValor.setText("");
				txNumeroCartao.setText("");
				cbTipoResgate.setText(null);
				btnRadioButton.setSelection(false);
				btnRadioButton_1.setSelection(false);
				
				txNumeroCartao.setEnabled(true);
				btnRadioButton.setEnabled(true);
				btnRadioButton_1.setEnabled(true);
				btnBuscar.setEnabled(true);
				
				cbTipoResgate.setEnabled(false);
				btnPontuarResgatar.setEnabled(false);
				btnVoltar.setEnabled(false);	

			}
		});
		btnVoltar.setBounds(221, 281, 139, 25);
		btnVoltar.setText("Voltar");

	}
}
