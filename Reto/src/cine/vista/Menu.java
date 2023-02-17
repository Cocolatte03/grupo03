package cine.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import cine.bbdd.pojos.Cine;
import cine.bbdd.pojos.Cliente;
import cine.bbdd.pojos.Pelicula;
import cine.bbdd.pojos.Proyeccion;
import cine.controlador.Controlador;
import java.awt.SystemColor;

/**
 * Esta clase contiene los elementos visuales de la interfaz.
 * 
 * @author leire
 *
 */
public class Menu {

	public JFrame frame;

	private JPanel bPanel;
	private JPanel scPanel;
	private JPanel spPanel;
	private JPanel sprPanel;
	private JPanel rcPanel;
	private JPanel lPanel;
	private JPanel itPanel;
	private JPanel rPanel;

	public JComboBox<String> scComboCines;
	private JComboBox<String> sprComboFecha;
	public JComboBox<String> srComboSexo;

	private DefaultTableModel spTableModel;
	private DefaultTableModel rcTableModel;
	private JTable rcTable;

	private JPanel sprPanelImg;
	private JLabel sprLblImg;

	private JLabel rcLblSubtotal1;
	private JLabel rcLblDescuento1;
	private JLabel rcLblTotal1;
	
	private JButton rBtnAtrasLogin;
	private JButton rBtnAtrasCine;

	private Controlador controlador = null;

	private ArrayList<Cine> cines = null;
	private ArrayList<Pelicula> peliculas = null;
	private ArrayList<Proyeccion> proyecciones = null;
	private ArrayList<Cliente> clientes = null;

	private ArrayList<Proyeccion> proyeccionesSeleccionadas = null;

	private Cine cineSeleccionado = null;
	private Pelicula peliSeleccionada = null;
	private Proyeccion proyeccionSeleccionada = null;
	private Cliente clienteLogueado = null;

	private LocalDateTime fechaCompra = null;

	public Menu() {
		controlador = new Controlador();

		cines = controlador.guardarArrayListCines();
		peliculas = new ArrayList<Pelicula>();
		proyecciones = new ArrayList<Proyeccion>();
		clientes = controlador.guardarArrayListClientes();

		proyeccionesSeleccionadas = new ArrayList<Proyeccion>();

		cineSeleccionado = new Cine();
		peliSeleccionada = new Pelicula();
		proyeccionSeleccionada = new Proyeccion();

		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Cine Elorrieta");
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		crearPanelBienvenida();
		crearPanelSeleccionCine();
		crearPanelSeleccionPelicula();
		crearPanelSeleccionProyeccion();
		crearPanelResumenCompra();
		crearPanelLogin();
		crearPanelImpresionTicket();
		crearPanelRegistro();

		scPanel.setVisible(false);
		spPanel.setVisible(false);
		sprPanel.setVisible(false);
		rcPanel.setVisible(false);
		lPanel.setVisible(false);
		itPanel.setVisible(false);
		rPanel.setVisible(false);
	}

	private void crearPanelBienvenida() {
		bPanel = new JPanel();
		bPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				scPanel.setVisible(true);
				bPanel.setVisible(false);
				controlador.anadirCinesAlCombo(scComboCines, cines);
			}
		});
		bPanel.setBounds(0, 0, 1000, 672);
		frame.getContentPane().add(bPanel);
		bPanel.setLayout(null);

		JLabel bLblCabecera = new JLabel("Bienvenid@ al Cine Elorrieta");
		bLblCabecera.setHorizontalAlignment(SwingConstants.CENTER);
		bLblCabecera.setForeground(new Color(72, 138, 246));
		bLblCabecera.setFont(new Font("Corbel", Font.BOLD, 30));
		bLblCabecera.setBounds(223, 250, 563, 48);
		bPanel.add(bLblCabecera);

		JPanel bPanelLogo = new JPanel();
		bPanelLogo.setBounds(910, 40, 35, 30);
		bPanel.add(bPanelLogo);
		bPanelLogo.setLayout(new BorderLayout(0, 0));
		bPanelLogo.setOpaque(false);

		JLabel bLblLogo = new JLabel("");
		bPanelLogo.add(bLblLogo, BorderLayout.CENTER);

		JPanel bPanelImg = new JPanel();
		bPanelImg.setBounds(0, 0, 1000, 672);
		bPanel.add(bPanelImg);
		bPanelImg.setLayout(new BorderLayout(0, 0));

		JLabel bLblImg = new JLabel("");
		bPanelImg.add(bLblImg, BorderLayout.CENTER);

		controlador.anadirImagen(bPanelImg, bLblImg, "img/bbg.jpg");
		controlador.anadirImagen(bPanelLogo, bLblLogo, "img/icon.png");
	}

	private void crearPanelSeleccionCine() {

		scPanel = new JPanel();
		scPanel.setBounds(0, 0, 1000, 672);
		frame.getContentPane().add(scPanel);
		scPanel.setLayout(null);
		scPanel.setBackground(Color.WHITE);

		JLabel scLblCabecera = new JLabel("Seleccione un cine:");
		scLblCabecera.setForeground(new Color(194, 220, 241));
		scLblCabecera.setHorizontalAlignment(SwingConstants.CENTER);
		scLblCabecera.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		scLblCabecera.setBounds(30, 43, 230, 56);
		scPanel.add(scLblCabecera);

		JPanel scPanelImg = new JPanel();
		scPanelImg.setBounds(160, 170, 700, 490);
		scPanel.add(scPanelImg);
		scPanelImg.setLayout(new BorderLayout(0, 0));
		scPanelImg.setOpaque(false);

		JLabel scLblImg = new JLabel("");
		scPanelImg.add(scLblImg, BorderLayout.CENTER);

		scComboCines = new JComboBox<String>();
		scComboCines.setBackground(Color.WHITE);
		scComboCines.setForeground(SystemColor.textHighlight);
		scComboCines.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		scComboCines.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (null != scComboCines.getSelectedItem()) {
					controlador.cambiarImagen(scComboCines, scPanelImg, scLblImg);
				}
			}
		});
		scComboCines.setBounds(53, 110, 230, 33);
		scPanel.add(scComboCines);

		JButton scBtnFin = new JButton("Finalizar Sesión");
		scBtnFin.setBackground(SystemColor.textHighlight);
		scBtnFin.setForeground(Color.WHITE);
		scBtnFin.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		scBtnFin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				double subtotal = controlador.calcularSubtotal(proyeccionesSeleccionadas);
				double descuento = controlador.calcularDescuento(proyeccionesSeleccionadas, subtotal);
				double total = controlador.calcularTotal(subtotal, descuento);

				controlador.finalizarSesion(proyeccionesSeleccionadas, scPanel, rcPanel, frame, rcTableModel,
						rcLblSubtotal1, rcLblDescuento1, rcLblTotal1, subtotal, descuento, total);
			}
		});
		scBtnFin.setBounds(792, 20, 169, 33);
		scPanel.add(scBtnFin);
		
		JButton scBtnInicioSesion= new JButton("REGISTRARME");
		scBtnInicioSesion.setBackground(Color.WHITE);
		scBtnInicioSesion.setForeground(SystemColor.textHighlight);
		scBtnInicioSesion.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		scBtnInicioSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (clienteLogueado == null) {
					rPanel.setVisible(true);
					scPanel.setVisible(false);
					
					rBtnAtrasLogin.setVisible(false);
					rBtnAtrasCine.setVisible(true);
				}
			}
		});
		scBtnInicioSesion.setBounds(600, 20, 169, 33);
		scPanel.add(scBtnInicioSesion);

		JButton scBtnConfirmar = new JButton("Confirmar");
		scBtnConfirmar.setBackground(SystemColor.textHighlight);
		scBtnConfirmar.setForeground(new Color(255, 255, 255));
		scBtnConfirmar.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		scBtnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scPanel.setVisible(false);
				cineSeleccionado = controlador.determinarCineSeleccionado(scComboCines, cines);
				peliculas = controlador.guardarArrayListPeliculas(cineSeleccionado);
				controlador.cargarTablaConPeliculas(spTableModel, peliculas);
				spPanel.setVisible(true);
			}
		});
		scBtnConfirmar.setBounds(305, 110, 111, 33);
		scPanel.add(scBtnConfirmar);

		JPanel scPanelCabecera = new JPanel();
		scPanelCabecera.setBounds(0, 0, 1000, 180);
		scPanel.add(scPanelCabecera);
		scPanelCabecera.setLayout(null);
		scPanelCabecera.setBackground(Color.DARK_GRAY);
	}

	public void crearPanelSeleccionPelicula() {

		spPanel = new JPanel();
		spPanel.setBounds(0, 0, 1000, 672);
		frame.getContentPane().add(spPanel);
		spPanel.setLayout(null);

		JLabel spLblCabecera = new JLabel("Seleccione una película:");
		spLblCabecera.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		spLblCabecera.setForeground(new Color(194, 220, 241));
		spLblCabecera.setBounds(66, 43, 446, 56);
		spPanel.add(spLblCabecera);

		JLabel spLblDesc = new JLabel("Haga clic sobre una película para seleccionarla.");
		spLblDesc.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		spLblDesc.setForeground(new Color(254, 255, 255));
		spLblDesc.setBounds(70, 100, 400, 31);
		spPanel.add(spLblDesc);

		JPanel spPanelImg = new JPanel();
		spPanelImg.setBackground(new Color(254, 251, 0));
		spPanelImg.setBounds(500, 0, 500, 675);
		spPanel.add(spPanelImg);
		spPanelImg.setLayout(new BorderLayout(0, 0));

		JLabel spLblImg = new JLabel("");
		spPanelImg.add(spLblImg, BorderLayout.CENTER);

		JScrollPane spScrollPane = new JScrollPane();
		spScrollPane.setBorder(new LineBorder(Color.WHITE, 0));
		spScrollPane.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		spScrollPane.getViewport().setBackground(Color.WHITE);
		spScrollPane.setBounds(70, 150, 400, 400);
		spPanel.add(spScrollPane);

		JTable spTable = new JTable();
		spTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				peliSeleccionada = controlador.guardarPeliSeleccionada(spTable, peliculas);

				controlador.anadirFechasAlCombo(sprComboFecha,
						controlador.guardarArrayListProyeccionesAgrupadas(cineSeleccionado, peliSeleccionada));
				controlador.anadirImagen(sprPanelImg, sprLblImg, peliSeleccionada.getCaratula());

				spPanel.setVisible(false);
				sprPanel.setVisible(true);
			}
		});
		spTable.setRowHeight(25);
		spTable.setBackground(new Color(254, 255, 255));
		spTable.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		spScrollPane.setViewportView(spTable);
		spTable.setSelectionBackground(new Color(72, 138, 246));
		spTable.setSelectionForeground(Color.WHITE);
		spTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		spTable.setShowGrid(false);

		Object[] spColumnas = { "TÍTULO", "GÉNERO", "DUR (min)" };

		JTableHeader spTableHeader = spTable.getTableHeader();
		spTableHeader.setBackground(SystemColor.textHighlight);
		spTableHeader.setForeground(Color.WHITE);
		spTableHeader.setFont(new Font("Trebuchet MS", Font.BOLD, 14));

		spTable.getTableHeader().setPreferredSize(new Dimension(spScrollPane.getWidth(), 30));

		spTableModel = new DefaultTableModel() {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		spTableModel.setColumnIdentifiers(spColumnas);
		spTable.setModel(spTableModel);

		spTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			private static final long serialVersionUID = 1852554938143426518L;

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				if (isSelected) {
					hasFocus = false;
				}
				return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			}
		});

		JButton spBtnAtras = new JButton("Atrás");
		spBtnAtras.setBackground(SystemColor.textHighlight);
		spBtnAtras.setForeground(Color.WHITE);
		spBtnAtras.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		spBtnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				spPanel.setVisible(false);
				scPanel.setVisible(true);
			}
		});
		spBtnAtras.setBounds(6, 6, 80, 29);
		spPanel.add(spBtnAtras);

		JPanel spPanelFondo = new JPanel();
		spPanelFondo.setBackground(new Color(66, 66, 66));
		spPanelFondo.setBounds(0, 0, 500, 675);
		spPanel.add(spPanelFondo);

		controlador.anadirImagen(spPanelImg, spLblImg, "img/spr_bg.jpg");
		ajustarColumnasPeliculas(spTable);
	}

	private void crearPanelSeleccionProyeccion() {
		sprPanel = new JPanel();
		sprPanel.setBounds(0, 0, 1000, 672);
		frame.getContentPane().add(sprPanel);

		JButton sprBtnAtras = new JButton("Atrás");
		sprBtnAtras.setBackground(SystemColor.textHighlight);
		sprBtnAtras.setForeground(Color.WHITE);
		sprBtnAtras.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		sprBtnAtras.setBounds(6, 6, 80, 29);
		sprBtnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				spPanel.setVisible(true);
				sprPanel.setVisible(false);
			}
		});
		sprPanel.setLayout(null);
		sprPanel.add(sprBtnAtras);

		JLabel sprLblCabecera = new JLabel("Seleccione una fecha:");
		sprLblCabecera.setBounds(66, 43, 329, 56);
		sprLblCabecera.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		sprLblCabecera.setForeground(new Color(194, 220, 241));
		sprPanel.add(sprLblCabecera);

		JLabel sprLblCabeceraSesion = new JLabel("Seleccione una sesión:");
		sprLblCabeceraSesion.setBounds(66, 180, 370, 56);
		sprLblCabeceraSesion.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		sprLblCabeceraSesion.setForeground(new Color(194, 220, 241));
		sprPanel.add(sprLblCabeceraSesion);

		JScrollPane sprScrollPane = new JScrollPane();
		sprScrollPane.setBorder(new LineBorder(Color.WHITE, 0));
		sprScrollPane.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		sprScrollPane.getViewport().setBackground(Color.WHITE);
		sprScrollPane.setBounds(70, 250, 378, 150);
		sprPanel.add(sprScrollPane);

		JTable sprTable = new JTable();
		sprTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				proyeccionSeleccionada = controlador.guardarProyeccionSeleccionada(sprTable, proyecciones);
				controlador.guardarSeleccionProyeccion(proyeccionSeleccionada, scPanel, sprPanel,
						proyeccionesSeleccionadas);
			}
		});
		sprTable.setRowHeight(25);
		sprTable.setBackground(new Color(254, 255, 255));
		sprTable.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		sprScrollPane.setViewportView(sprTable);
		sprTable.setSelectionBackground(new Color(72, 138, 246));
		sprTable.setSelectionForeground(Color.WHITE);
		sprTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sprTable.setShowGrid(false);

		Object[] sprColumnas = { "PELÍCULA", "HORA", "SALA", "PRECIO (€)" };

		JTableHeader sprTableHeader = sprTable.getTableHeader();
		sprTableHeader.setBackground(SystemColor.textHighlight);
		sprTableHeader.setForeground(Color.WHITE);
		sprTableHeader.setFont(new Font("Trebuchet MS", Font.BOLD, 14));

		sprTable.getTableHeader().setPreferredSize(new Dimension(sprScrollPane.getWidth(), 30));

		DefaultTableModel sprTableModel = new DefaultTableModel() {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		sprTableModel.setColumnIdentifiers(sprColumnas);
		sprTable.setModel(sprTableModel);

		sprTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			private static final long serialVersionUID = 1852554938143426518L;

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				if (isSelected) {
					hasFocus = false;
				}
				return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			}
		});

		sprComboFecha = new JComboBox<String>();
		sprComboFecha.setBackground(Color.WHITE);
		sprComboFecha.setForeground(SystemColor.textHighlight);
		sprComboFecha.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		sprComboFecha.setBounds(66, 117, 230, 33);
		sprComboFecha.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (null != sprComboFecha.getSelectedItem()) {
					proyecciones = controlador.guardarArrayListProyecciones(sprComboFecha, cineSeleccionado,
							peliSeleccionada);
					controlador.cargarTablaConSesiones(sprTableModel, proyecciones, peliSeleccionada);
				}
			}
		});
		sprPanel.add(sprComboFecha);

		sprPanelImg = new JPanel();
		sprPanelImg.setBackground(new Color(254, 251, 0));
		sprPanelImg.setBounds(500, 0, 500, 675);
		sprPanel.add(sprPanelImg);
		sprPanelImg.setLayout(new BorderLayout(0, 0));

		sprLblImg = new JLabel("");
		sprPanelImg.add(sprLblImg, BorderLayout.CENTER);

		JPanel sprPanelFondo = new JPanel();
		sprPanelFondo.setBackground(new Color(66, 66, 66));
		sprPanelFondo.setBounds(0, 0, 500, 675);
		sprPanel.add(sprPanelFondo);

	}

	private void crearPanelResumenCompra() {
		rcPanel = new JPanel();
		rcPanel.setBounds(0, 0, 1000, 672);
		frame.getContentPane().add(rcPanel);
		rcPanel.setBackground(Color.WHITE);
		rcPanel.setLayout(null);

		JButton rcBtnAtras = new JButton("Atrás");
		rcBtnAtras.setBackground(SystemColor.textHighlight);
		rcBtnAtras.setForeground(Color.WHITE);
		rcBtnAtras.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		rcBtnAtras.setBounds(6, 6, 80, 29);
		rcBtnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scPanel.setVisible(true);
				rcPanel.setVisible(false);
			}
		});
		rcPanel.add(rcBtnAtras);

		JLabel rcLblCabecera = new JLabel("Resumen de la compra:");
		rcLblCabecera.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		rcLblCabecera.setForeground(new Color(194, 220, 241));
		rcLblCabecera.setBounds(66, 43, 329, 56);
		rcPanel.add(rcLblCabecera);

		JScrollPane rcScrollPane = new JScrollPane();
		rcScrollPane.setBorder(new LineBorder(Color.WHITE, 0));
		rcScrollPane.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		rcScrollPane.getViewport().setBackground(new Color(220, 230, 241));
		rcScrollPane.setBounds(70, 250, 850, 150);
		rcPanel.add(rcScrollPane);

		rcTable = new JTable();
		rcTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controlador.eliminarProyeccionSel(proyeccionesSeleccionadas, rcTable, rcTableModel, rcLblSubtotal1,
						rcLblDescuento1, rcLblTotal1);
			}
		});
		rcTable.setRowHeight(25);
		rcTable.setBackground(new Color(220, 230, 241));
		rcTable.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		rcTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		rcTable.setSelectionBackground(new Color(72, 138, 246));
		rcTable.setSelectionForeground(Color.WHITE);
		rcTable.setShowGrid(false);
		rcScrollPane.setViewportView(rcTable);

		Object[] rcColumnas = { "PELÍCULA", "FECHA", "SESIÓN", "SALA", "CINE", "PRECIO (€)" };

		JTableHeader rcTableHeader = rcTable.getTableHeader();
		rcTableHeader.setBackground(SystemColor.textHighlight);
		rcTableHeader.setForeground(Color.WHITE);
		rcTableHeader.setFont(new Font("Trebuchet MS", Font.BOLD, 14));

		rcTable.getTableHeader().setPreferredSize(new Dimension(rcScrollPane.getWidth(), 30));

		rcTableModel = new DefaultTableModel() {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		rcTableModel.setColumnIdentifiers(rcColumnas);
		rcTable.setModel(rcTableModel);

		rcTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			private static final long serialVersionUID = 1852554938143426518L;

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				if (isSelected) {
					hasFocus = false;
				}
				return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			}
		});

		ajustarColumnasResumenCompra(rcTable);

		JLabel rcLblSubtotal = new JLabel("Subtotal:");
		rcLblSubtotal.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		rcLblSubtotal.setBounds(750, 422, 110, 29);
		rcPanel.add(rcLblSubtotal);

		rcLblSubtotal1 = new JLabel("");
		rcLblSubtotal1.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		rcLblSubtotal1.setBounds(850, 422, 95, 29);
		rcPanel.add(rcLblSubtotal1);

		JLabel rcLblDescuento = new JLabel("Descuento:");
		rcLblDescuento.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		rcLblDescuento.setBounds(750, 463, 110, 29);
		rcPanel.add(rcLblDescuento);

		rcLblDescuento1 = new JLabel("");
		rcLblDescuento1.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		rcLblDescuento1.setBounds(850, 463, 95, 29);
		rcPanel.add(rcLblDescuento1);

		JLabel rcLblTotal = new JLabel("TOTAL:");
		rcLblTotal.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		rcLblTotal.setBounds(750, 504, 110, 29);
		rcPanel.add(rcLblTotal);

		rcLblTotal1 = new JLabel("");
		rcLblTotal1.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		rcLblTotal1.setBounds(850, 504, 95, 29);
		rcPanel.add(rcLblTotal1);

		JButton rcBtnCancelar = new JButton("Cancelar");
		rcBtnCancelar.setBackground(Color.BLACK);
		rcBtnCancelar.setForeground(Color.WHITE);
		rcBtnCancelar.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		rcBtnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.cancelarCompra(proyeccionesSeleccionadas, bPanel, rcPanel);
			}
		});
		rcBtnCancelar.setBounds(70, 558, 117, 29);
		rcPanel.add(rcBtnCancelar);

		JButton rcBtnConfirmar = new JButton("Confirmar");
		rcBtnConfirmar.setBackground(SystemColor.textHighlight);
		rcBtnConfirmar.setForeground(Color.WHITE);
		rcBtnConfirmar.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		rcBtnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (proyeccionesSeleccionadas.size() == 0) {
					JOptionPane.showMessageDialog(null, "No hay entradas seleccionadas para proceder.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else if (null == clienteLogueado) {
					rcPanel.setVisible(false);
					lPanel.setVisible(true);
				} else if (null != clienteLogueado) {
					fechaCompra = LocalDateTime.now();
					controlador.crearEntradas(proyeccionesSeleccionadas, clienteLogueado);
					rcPanel.setVisible(false);
					itPanel.setVisible(true);
				}
			}
		});
		rcBtnConfirmar.setBounds(218, 558, 117, 29);
		rcPanel.add(rcBtnConfirmar);

		JLabel rcLblDescr = new JLabel("Haga clic sobre una sesión para eliminarla.");
		rcLblDescr.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		rcLblDescr.setForeground(Color.WHITE);
		rcLblDescr.setBounds(70, 98, 566, 29);
		rcPanel.add(rcLblDescr);
		
		JPanel rcPanelImg = new JPanel();
		rcPanelImg.setBounds(800, 50, 100, 100);
		rcPanel.add(rcPanelImg);
		rcPanelImg.setOpaque(false);
		rcPanelImg.setLayout(new BorderLayout(0, 0));
		

		JLabel rcLblImg = new JLabel("");
		rcPanelImg.add(rcLblImg, BorderLayout.CENTER);
		
		controlador.anadirImagen(rcPanelImg, rcLblImg, "img/carrito.png");

		JPanel rcPanelCabecera = new JPanel();
		rcPanelCabecera.setBounds(0, 0, 1000, 180);
		rcPanel.add(rcPanelCabecera);
		rcPanelCabecera.setLayout(null);
		rcPanelCabecera.setBackground(Color.DARK_GRAY);
	}

	public void ajustarColumnasResumenCompra(JTable table) {
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		table.getColumnModel().getColumn(0).setMinWidth(200);
		table.getColumnModel().getColumn(1).setMinWidth(100);
		table.getColumnModel().getColumn(2).setMinWidth(100);
		table.getColumnModel().getColumn(3).setMinWidth(100);
		table.getColumnModel().getColumn(4).setMinWidth(200);
	}

	public void ajustarColumnasPeliculas(JTable table) {
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		table.getColumnModel().getColumn(0).setMinWidth(200);
		table.getColumnModel().getColumn(1).setMinWidth(100);
	}

	private void crearPanelLogin() {
		lPanel = new JPanel();
		lPanel.setBounds(0, 0, 1000, 672);
		frame.getContentPane().add(lPanel);
		lPanel.setLayout(null);

		JButton lBtnAtras = new JButton("Atrás");
		lBtnAtras.setBackground(SystemColor.textHighlight);
		lBtnAtras.setForeground(Color.WHITE);
		lBtnAtras.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		lBtnAtras.setBounds(6, 6, 80, 29);
		lBtnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lPanel.setVisible(false);
				rcPanel.setVisible(true);
			}
		});
		lPanel.add(lBtnAtras);

		JLabel lLblUsuario = new JLabel("Usuario:");
		lLblUsuario.setForeground(Color.WHITE);
		lLblUsuario.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		lLblUsuario.setBounds(678, 134, 150, 36);
		lPanel.add(lLblUsuario);

		JTextField lTextFieldUsuario = new JTextField();
		lTextFieldUsuario.setBounds(678, 180, 150, 36);
		lPanel.add(lTextFieldUsuario);
		lTextFieldUsuario.setColumns(10);

		JLabel lLblContrasena = new JLabel("Contraseña:");
		lLblContrasena.setForeground(Color.WHITE);
		lLblContrasena.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		lLblContrasena.setBounds(678, 246, 150, 36);
		lPanel.add(lLblContrasena);

		JPasswordField lPasswordField = new JPasswordField();
		lPasswordField.setColumns(10);
		lPasswordField.setBounds(678, 292, 150, 36);
		lPanel.add(lPasswordField);

		JButton lBtnIniciarSesion = new JButton("Iniciar Sesión");
		lBtnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usuario = lTextFieldUsuario.getText();
				String contrasena = String.valueOf(lPasswordField.getPassword());
				clienteLogueado = controlador.guardarCliente(clientes, usuario);
				controlador.iniciarSesion(clienteLogueado, contrasena, lPanel, rcPanel, lTextFieldUsuario,
						lPasswordField);
			}
		});
		lBtnIniciarSesion.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		lBtnIniciarSesion.setForeground(Color.WHITE);
		lBtnIniciarSesion.setBackground(SystemColor.textHighlight);
		lBtnIniciarSesion.setBounds(678, 350, 150, 36);
		lPanel.add(lBtnIniciarSesion);

		JLabel lLblNoTengoCuenta = new JLabel("No tengo cuenta");
		lLblNoTengoCuenta.setHorizontalAlignment(SwingConstants.CENTER);
		lLblNoTengoCuenta.setForeground(Color.WHITE);
		lLblNoTengoCuenta.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		lLblNoTengoCuenta.setBounds(665, 481, 177, 56);
		lPanel.add(lLblNoTengoCuenta);

		JButton lBtnRegistrarse = new JButton("REGISTRARME");
		lBtnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lPanel.setVisible(false);
				rPanel.setVisible(true);
				rBtnAtrasLogin.setVisible(true);
				rBtnAtrasCine.setVisible(false);
			}
		});
		lBtnRegistrarse.setForeground(SystemColor.textHighlight);
		lBtnRegistrarse.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		lBtnRegistrarse.setBackground(Color.WHITE);
		lBtnRegistrarse.setBounds(678, 547, 150, 36);
		lPanel.add(lBtnRegistrarse);

		JPanel lPanelImg = new JPanel();
		lPanelImg.setBackground(new Color(254, 251, 0));
		lPanelImg.setBounds(0, 0, 500, 675);
		lPanel.add(lPanelImg);
		lPanelImg.setLayout(new BorderLayout(0, 0));

		JLabel lLblImg = new JLabel("");
		lPanelImg.add(lLblImg, BorderLayout.CENTER);

		JPanel lPanelFondo = new JPanel();
		lPanelFondo.setBackground(new Color(66, 66, 66));
		lPanelFondo.setBounds(500, 0, 500, 675);
		lPanel.add(lPanelFondo);

		controlador.anadirImagen(lPanelImg, lLblImg, "img/l_bg.jpg");
	}

	private void crearPanelImpresionTicket() {
		itPanel = new JPanel();
		itPanel.setBackground(Color.WHITE);
		itPanel.setBounds(0, 0, 1000, 672);
		frame.getContentPane().add(itPanel);
		itPanel.setLayout(null);

		JLabel itLblAgradecimiento = new JLabel("¡GRACIAS POR SU COMPRA!");
		itLblAgradecimiento.setHorizontalAlignment(SwingConstants.CENTER);
		itLblAgradecimiento.setForeground(SystemColor.textHighlight);
		itLblAgradecimiento.setFont(new Font("Trebuchet MS", Font.BOLD, 25));
		itLblAgradecimiento.setBounds(302, 41, 400, 56);
		itPanel.add(itLblAgradecimiento);

		JLabel itLblPregunta = new JLabel("¿Desea imprimir las entradas?");
		itLblPregunta.setHorizontalAlignment(SwingConstants.CENTER);
		itLblPregunta.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		itLblPregunta.setBounds(302, 447, 400, 56);
		itPanel.add(itLblPregunta);

		JButton itBtnSi = new JButton("SÍ");
		itBtnSi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.imprimirTicket(proyeccionesSeleccionadas, clienteLogueado, fechaCompra);
				JOptionPane.showMessageDialog(null, ("Sus entradas se han impreso correctamente."));
				itPanel.setVisible(false);
				bPanel.setVisible(true);
				controlador.reiniciarProyeccionesSeleccionadas(proyeccionesSeleccionadas);
				clienteLogueado = controlador.desloguearCliente();
			}
		});
		itBtnSi.setForeground(Color.WHITE);
		itBtnSi.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		itBtnSi.setBackground(SystemColor.textHighlight);
		itBtnSi.setBounds(413, 525, 66, 36);
		itPanel.add(itBtnSi);

		JButton itBtnNo = new JButton("NO");
		itBtnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				itPanel.setVisible(false);
				bPanel.setVisible(true);
				controlador.reiniciarProyeccionesSeleccionadas(proyeccionesSeleccionadas);
				clienteLogueado = controlador.desloguearCliente();
			}
		});
		itBtnNo.setForeground(Color.WHITE);
		itBtnNo.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		itBtnNo.setBackground(Color.DARK_GRAY);
		itBtnNo.setBounds(539, 525, 66, 36);
		itPanel.add(itBtnNo);

		JPanel itPanelImg = new JPanel();
		itPanelImg.setBounds(312, 108, 390, 328);
		itPanel.add(itPanelImg);
		itPanelImg.setLayout(new BorderLayout(0, 0));

		JLabel itLblImg = new JLabel("");
		itPanelImg.add(itLblImg, BorderLayout.CENTER);

		controlador.anadirImagen(itPanelImg, itLblImg, "img/ticket.jpg");
	}

	private void crearPanelRegistro() {
		rPanel = new JPanel();
		rPanel.setBounds(0, 0, 1000, 672);
		frame.getContentPane().add(rPanel);
		rPanel.setLayout(null);
		rPanel.setBackground(new Color(66, 66, 66));

		JLabel rLblRegistro = new JLabel("Regístrate:");
		rLblRegistro.setBounds(66, 43, 329, 56);
		rLblRegistro.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		rLblRegistro.setForeground(new Color(194, 220, 241));
		rPanel.add(rLblRegistro);

		JLabel rLblDesc = new JLabel("Rellene los campos del siguiente formulario.");
		rLblDesc.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		rLblDesc.setForeground(new Color(254, 255, 255));
		rLblDesc.setBounds(70, 100, 400, 31);
		rPanel.add(rLblDesc);

		JLabel rLblNombre = new JLabel("Nombre:");
		rLblNombre.setForeground(Color.WHITE);
		rLblNombre.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		rLblNombre.setBounds(70, 140, 150, 36);
		rPanel.add(rLblNombre);

		JTextField rTextFieldNombre = new JTextField();
		rTextFieldNombre.setFont(new Font("Trebuchet MS", Font.PLAIN, 11));
		rTextFieldNombre.setColumns(10);
		rTextFieldNombre.setBounds(70, 175, 150, 26);
		rPanel.add(rTextFieldNombre);

		JLabel rLblApellidos = new JLabel("Apellidos:");
		rLblApellidos.setForeground(Color.WHITE);
		rLblApellidos.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		rLblApellidos.setBounds(70, 242, 150, 36);
		rPanel.add(rLblApellidos);

		JTextField rTextFieldApellidos = new JTextField();
		rTextFieldApellidos.setFont(new Font("Trebuchet MS", Font.PLAIN, 11));
		rTextFieldApellidos.setColumns(10);
		rTextFieldApellidos.setBounds(70, 278, 150, 26);
		rPanel.add(rTextFieldApellidos);

		JLabel rLblDni = new JLabel("DNI:");
		rLblDni.setForeground(Color.WHITE);
		rLblDni.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		rLblDni.setBounds(70, 355, 150, 36);
		rPanel.add(rLblDni);

		JTextField rTextFieldDni = new JTextField();
		rTextFieldDni.setFont(new Font("Trebuchet MS", Font.PLAIN, 11));
		rTextFieldDni.setColumns(10);
		rTextFieldDni.setBounds(70, 390, 150, 26);
		rPanel.add(rTextFieldDni);

		JLabel rLblDireccion = new JLabel("Dirección:");
		rLblDireccion.setForeground(Color.WHITE);
		rLblDireccion.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		rLblDireccion.setBounds(70, 467, 150, 36);
		rPanel.add(rLblDireccion);

		JTextField rTextFieldDireccion = new JTextField();
		rTextFieldDireccion.setFont(new Font("Trebuchet MS", Font.PLAIN, 11));
		rTextFieldDireccion.setColumns(10);
		rTextFieldDireccion.setBounds(70, 501, 150, 26);
		rPanel.add(rTextFieldDireccion);

		JLabel rLblSexo = new JLabel("Sexo:");
		rLblSexo.setForeground(Color.WHITE);
		rLblSexo.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		rLblSexo.setBounds(250, 140, 150, 36);
		rPanel.add(rLblSexo);

		JComboBox<String> rComboSexo = new JComboBox<String>();
		rComboSexo.setBackground(Color.WHITE);
		rComboSexo.setForeground(SystemColor.textHighlight);
		rComboSexo.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		rComboSexo.addItem("Hombre");
		rComboSexo.addItem("Mujer");
		rComboSexo.addItem("Otro");
		rComboSexo.setBounds(250, 175, 150, 26);
		rPanel.add(rComboSexo);

		JLabel rLblUsuario = new JLabel("Usuario:");
		rLblUsuario.setForeground(Color.WHITE);
		rLblUsuario.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		rLblUsuario.setBounds(250, 240, 150, 36);
		rPanel.add(rLblUsuario);

		JTextField rTextFieldUsuario = new JTextField();
		rTextFieldUsuario.setFont(new Font("Trebuchet MS", Font.PLAIN, 11));
		rTextFieldUsuario.setColumns(10);
		rTextFieldUsuario.setBounds(250, 278, 150, 26);
		rPanel.add(rTextFieldUsuario);

		JLabel rLblContrasena = new JLabel("Contraseña:");
		rLblContrasena.setForeground(Color.WHITE);
		rLblContrasena.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		rLblContrasena.setBounds(250, 353, 150, 36);
		rPanel.add(rLblContrasena);

		JPasswordField rPasswordFieldContrasena = new JPasswordField();
		rPasswordFieldContrasena.setFont(new Font("Trebuchet MS", Font.PLAIN, 11));
		rPasswordFieldContrasena.setBounds(250, 390, 150, 26);
		rPanel.add(rPasswordFieldContrasena);

		JLabel rLblRepContrasena = new JLabel("Repetir Contraseña:");
		rLblRepContrasena.setForeground(Color.WHITE);
		rLblRepContrasena.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		rLblRepContrasena.setBounds(250, 467, 150, 36);
		rPanel.add(rLblRepContrasena);

		JPasswordField rPasswordFieldRepContrasena = new JPasswordField();
		rPasswordFieldRepContrasena.setFont(new Font("Trebuchet MS", Font.PLAIN, 11));
		rPasswordFieldRepContrasena.setBounds(250, 502, 150, 26);
		rPanel.add(rPasswordFieldRepContrasena);

		JButton rBtnRegistrarme = new JButton("Registrarme");
		rBtnRegistrarme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.registrarNuevoCliente(clientes, rComboSexo, rTextFieldNombre, rTextFieldApellidos,
						rTextFieldDni, rTextFieldDireccion, rTextFieldUsuario, rPasswordFieldContrasena,
						rPasswordFieldRepContrasena);
				clientes = controlador.guardarArrayListClientes();
			}
		});
		rBtnRegistrarme.setForeground(Color.WHITE);
		rBtnRegistrarme.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		rBtnRegistrarme.setBackground(SystemColor.textHighlight);
		rBtnRegistrarme.setBounds(250, 576, 150, 36);
		rPanel.add(rBtnRegistrarme);

		rBtnAtrasLogin = new JButton("Atrás");
		rBtnAtrasLogin.setBackground(SystemColor.textHighlight);
		rBtnAtrasLogin.setForeground(Color.WHITE);
		rBtnAtrasLogin.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		rBtnAtrasLogin.setBounds(6, 6, 80, 29);
		rBtnAtrasLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rPanel.setVisible(false);
				lPanel.setVisible(true);
			}
		});
		rPanel.add(rBtnAtrasLogin);
		
		rBtnAtrasCine = new JButton("Atrás");
		rBtnAtrasCine.setBackground(SystemColor.textHighlight);
		rBtnAtrasCine.setForeground(Color.WHITE);
		rBtnAtrasCine.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		rBtnAtrasCine.setBounds(6, 6, 80, 29);
		rBtnAtrasCine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rPanel.setVisible(false);
				scPanel.setVisible(true);
			}
		});
		rPanel.add(rBtnAtrasCine);
		
		JPanel rPanelImg = new JPanel();
		rPanelImg.setBackground(new Color(66, 66, 66));
		rPanelImg.setBounds(500, 0, 500, 675);
		rPanelImg.setLayout(new BorderLayout(0, 0));
		rPanel.add(rPanelImg);
		
		JLabel rLblImg = new JLabel("");
		rPanelImg.add(rLblImg, BorderLayout.CENTER);

		controlador.anadirImagen(rPanelImg, rLblImg, "img/r_bg.jpg");
		
		rBtnAtrasLogin.setVisible(false);
		rBtnAtrasCine.setVisible(false);
	}
}
