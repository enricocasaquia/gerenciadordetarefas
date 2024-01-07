package trabalhonp1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;

@SuppressWarnings("serial")
public class GerenciadorTarefas extends JFrame {

	private JPanel JPanelPrincipal;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GerenciadorTarefas frame = new GerenciadorTarefas();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GerenciadorTarefas() {
		setTitle("Gerenciador de Tarefas");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 806, 559);
		
		/////////////////////////////////////////////////////////////////////////////////////////////// DEFINE O PAINEL PRINCIPAL
		JPanelPrincipal = new JPanel();
		JPanelPrincipal.setBackground(new Color(204, 255, 255));
		JPanelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(JPanelPrincipal);
		JPanelPrincipal.setLayout(new GridLayout(3, 1, 0, 0));
		
		/////////////////////////////////////////////////////////////////////////////////////////////// DEFINE O SUBPAINEL DE TÍTULO
		
		JPanel JPanelTitulo = new JPanel();
		JPanelTitulo.setBackground(new Color(204, 255, 255));
		JPanelPrincipal.add(JPanelTitulo);
		JPanelTitulo.setLayout(null);
		JLabel JLabelTitulo = new JLabel("Gerenciador de Tarefas");
		JLabelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		JLabelTitulo.setBounds(0, 0, 780, 170);
		JLabelTitulo.setFont(new Font("Verdana", Font.BOLD, 41));
		JPanelTitulo.add(JLabelTitulo);
		
		/////////////////////////////////////////////////////////////////////////////////////////////// DEFINE O SUBPAINEL DA LISTA E O SCROLLPANE PARA OS ITEMS DA LISTA
		
		JPanel JPanelLista = new JPanel();
		JPanelLista.setBackground(new Color(204, 255, 255));
		JPanelPrincipal.add(JPanelLista);
		JPanelLista.setLayout(null);
		
		JScrollPane JScrollPaneTarefa = new JScrollPane();
		JScrollPaneTarefa.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));    
		JScrollPaneTarefa.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		JScrollPaneTarefa.setBounds(185, 11, 405, 148);
		JPanelLista.add(JScrollPaneTarefa);
		
		
		/////////////////////////////////////////////////////////////////////////////////////////////// JLIST CRIADA UTILIZANDO UM MODELO DE "TAREFA" DA LISTA
		
		JList<String> JListTarefas = new JList<String>();
		JListTarefas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		JScrollPaneTarefa.setViewportView(JListTarefas);
		JListTarefas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		DefaultListModel<String> tarefa = new DefaultListModel<String>();
		JListTarefas.setModel(tarefa);
		
		/////////////////////////////////////////////////////////////////////////////////////////////// BOTAO PARA DESLOCAR O ITEM PARA CIMA
		
		JButton JButtonSubirItem = new JButton("\u2191");
		JButtonSubirItem.setBorder(new CompoundBorder());
		JButtonSubirItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int indexTarefaInicial = JListTarefas.getSelectedIndex();
				if (indexTarefaInicial != 0) {
					JListTarefas.setSelectedIndex(indexTarefaInicial - 1);
					String valorTarefaDescer = (String) JListTarefas.getSelectedValue();
					tarefa.removeElement(valorTarefaDescer);
					tarefa.add(indexTarefaInicial, valorTarefaDescer);
				}
			}
		});
		JButtonSubirItem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		JButtonSubirItem.setBackground(UIManager.getColor("Button.background"));
		JButtonSubirItem.setFocusPainted(false);
		JButtonSubirItem.setBounds(605, 56, 15, 22);
		JPanelLista.add(JButtonSubirItem);
		
		/////////////////////////////////////////////////////////////////////////////////////////////// BOTAO PARA DESLOCAR O ITEM PARA BAIXO
		
		JButton JButtonDescerItem = new JButton("\u2193");
		JButtonDescerItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int indexTarefaInicial = JListTarefas.getSelectedIndex();
				if (JListTarefas.getSelectedIndex() >= 0) {
					JListTarefas.setSelectedIndex(indexTarefaInicial + 1);
					String valorTarefaSubir = (String) JListTarefas.getSelectedValue();
					tarefa.removeElement(valorTarefaSubir);
					tarefa.add(indexTarefaInicial, valorTarefaSubir);
				}
			}
		});
		JButtonDescerItem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		JButtonDescerItem.setFocusPainted(false);
		JButtonDescerItem.setBorder(new CompoundBorder());
		JButtonDescerItem.setBackground(UIManager.getColor("Button.background"));
		JButtonDescerItem.setBounds(605, 81, 15, 22);
		JPanelLista.add(JButtonDescerItem);
		
		/////////////////////////////////////////////////////////////////////////////////////////////// DEFINE O SUBPAINEL PARA OS 4 BOTÔES INFERIORES
		
		JPanel JPanelBotao = new JPanel();
		JPanelBotao.setBackground(new Color(204, 255, 255));
		JPanelPrincipal.add(JPanelBotao);
		
		/////////////////////////////////////////////////////////////////////////////////////////////// BOTAO DE ADICIONAR ITEM
		
		JButton JButtonAdicionarItem = new JButton("Adicionar tarefa");
		JButtonAdicionarItem.setActionCommand("Adicionar tarefa");
		JButtonAdicionarItem.setFocusPainted(false);
		JButtonAdicionarItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String entrada = JOptionPane.showInputDialog("Digite uma nova tarefa:");
				tarefa.addElement(entrada);
			}	
		});
		JButtonAdicionarItem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		JPanelBotao.add(JButtonAdicionarItem);
		
		/////////////////////////////////////////////////////////////////////////////////////////////// BOTAO PARA REMOVER ITEM SELECIONADO
		
		JButton JButtonConcluirItem = new JButton("Remover tarefa");
		JButtonConcluirItem.setBackground(UIManager.getColor("Button.background"));
		JButtonConcluirItem.setFocusPainted(false);
		JButtonConcluirItem.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				tarefa.removeElement(JListTarefas.getSelectedValue());
			}
		});
		JButtonConcluirItem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		JPanelBotao.add(JButtonConcluirItem);
		
		/////////////////////////////////////////////////////////////////////////////////////////////// BOTAO PARA REMOVER TODOS OS ITEMS
		
		JButton JButtonRemoverItem = new JButton("Remover todas as tarefas");
		JButtonRemoverItem.setFocusPainted(false);
		JButtonRemoverItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tarefa.clear();;
			}
		});
		JButtonRemoverItem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		JPanelBotao.add(JButtonRemoverItem);
		
		/////////////////////////////////////////////////////////////////////////////////////////////// BOTAO PARA RENOMEAR ITEM SELECIONADO
		
		JButton JButtonEditarItem = new JButton("Renomear tarefa");
		JButtonEditarItem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		JButtonEditarItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JListTarefas.getSelectedValue() != null) {
					int indexTarefa = JListTarefas.getSelectedIndex();
					String novoTexto = JOptionPane.showInputDialog("Digite no campo o novo nome:");
					tarefa.removeElement(JListTarefas.getSelectedValue());
					tarefa.add(indexTarefa, novoTexto);
				}
			}
		});
		JButtonEditarItem.setFocusPainted(false);
		JPanelBotao.add(JButtonEditarItem);
	}
}
