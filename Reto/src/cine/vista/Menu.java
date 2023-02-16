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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
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
	private JPanel srPanel;

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
		clientes = new ArrayList<Cliente>();

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
		crearSeleccionRegistro();

		scPanel.setVisible(false);
		spPanel.setVisible(false);
		sprPanel.setVisible(false);
		rcPanel.setVisible(false);
		lPanel.setVisible(false);
		itPanel.setVisible(false);
		srPanel.setVisible(false);
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
		bPanelLogo.setBounds(960, 640, 35, 30);
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
		scLblCabecera.setForeground(Color.WHITE);
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
		scBtnFin.setBackground(Color.WHITE);
		scBtnFin.setForeground(SystemColor.textHighlight);
		scBtnFin.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		scBtnFin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				double subtotal = controlador.calcularSubtotal(rcTable, proyeccionesSeleccionadas);
				double descuento = controlador.calcularDescuento(proyeccionesSeleccionadas, subtotal);
				double total = controlador.calcularTotal(subtotal, descuento);

				controlador.finalizarSesion(proyeccionesSeleccionadas, scPanel, rcPanel, frame, rcTableModel,
						rcLblSubtotal1, rcLblDescuento1, rcLblTotal1, subtotal, descuento, total);
			}
		});
		scBtnFin.setBounds(792, 20, 169, 33);
		scPanel.add(scBtnFin);

		JButton scBtnConfirmar = new JButton("Confirmar");
		scBtnConfirmar.setBackground(SystemColor.textHighlight);
		scBtnConfirmar.setForeground(new Color(255, 255, 255));
		scBtnConfirmar.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		scBtnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scPanel.setVisible(false);
				cineSeleccionado = controlador.determinarCineSeleccionado(scComboCines, cines);
				peliculas = controlador.guardarArrayListPeliculas(cineSeleccionado);
				// controlador.anadirPeliculasAlCombo(spComboTitulos, peliculas);
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
		spLblCabecera.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		spLblCabecera.setForeground(new Color(194, 220, 241));
		spLblCabecera.setBounds(66, 43, 446, 56);
		spPanel.add(spLblCabecera);

		JLabel lblNewLabel = new JLabel("Haga clic sobre una película para seleccionarla.");
		lblNewLabel.setForeground(new Color(254, 255, 255));
		lblNewLabel.setBounds(70, 100, 400, 31);
		spPanel.add(lblNewLabel);

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

		Object[] spColumnas = { "Título", "Género", "Dur (min)" };

		JTableHeader spTableHeader = spTable.getTableHeader();
		spTableHeader.setBackground(Color.BLACK);
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
		sprLblCabecera.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		sprLblCabecera.setForeground(new Color(194, 220, 241));
		sprPanel.add(sprLblCabecera);

		JLabel sprLblCabeceraSesion = new JLabel("Seleccione una sesión:");
		sprLblCabeceraSesion.setBounds(66, 180, 370, 56);
		sprLblCabeceraSesion.setFont(new Font("Lucida Grande", Font.BOLD, 20));
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
				controlador.guardarSeleccionProyeccion(proyeccionSeleccionada, bPanel, sprPanel,
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

		Object[] sprColumnas = { "Película", "Hora", "Sala", "Precio (€)" };

		JTableHeader sprTableHeader = sprTable.getTableHeader();
		sprTableHeader.setBackground(Color.BLACK);
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
		rcPanel.setLayout(null);

		JButton rcBtnAtras = new JButton("Atrás");
		rcBtnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scPanel.setVisible(true);
				rcPanel.setVisible(false);
			}
		});
		rcBtnAtras.setBounds(6, 6, 70, 29);
		rcPanel.add(rcBtnAtras);

		JLabel rcLblCabecera = new JLabel("Resumen de la compra:");
		rcLblCabecera.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		rcLblCabecera.setForeground(new Color(72, 138, 246));
		rcLblCabecera.setBounds(66, 43, 329, 56);
		rcPanel.add(rcLblCabecera);

		JScrollPane rcScrollPane = new JScrollPane();
		rcScrollPane.setBorder(new LineBorder(Color.WHITE, 0));
		rcScrollPane.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		rcScrollPane.getViewport().setBackground(Color.WHITE);
		rcScrollPane.setBounds(70, 150, 850, 250);
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
		rcTable.setBackground(Color.WHITE);
		rcTable.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		rcTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		rcTable.setSelectionBackground(new Color(72, 138, 246));
		rcTable.setSelectionForeground(Color.WHITE);
		rcTable.setShowGrid(false);
		rcScrollPane.setViewportView(rcTable);

		Object[] rcColumnas = { "Película", "Fecha", "Sesión", "Sala", "Cine", "Precio (€)" };

		JTableHeader rcTableHeader = rcTable.getTableHeader();
		rcTableHeader.setBackground(Color.BLACK);
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
		rcLblSubtotal.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		rcLblSubtotal.setBounds(750, 422, 110, 29);
		rcPanel.add(rcLblSubtotal);

		rcLblSubtotal1 = new JLabel("");
		rcLblSubtotal1.setBounds(850, 422, 95, 29);
		rcPanel.add(rcLblSubtotal1);

		JLabel rcLblDescuento = new JLabel("Descuento:");
		rcLblDescuento.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		rcLblDescuento.setBounds(750, 463, 110, 29);
		rcPanel.add(rcLblDescuento);

		rcLblDescuento1 = new JLabel("");
		rcLblDescuento1.setBounds(850, 463, 95, 29);
		rcPanel.add(rcLblDescuento1);

		JLabel rcLblTotal = new JLabel("TOTAL:");
		rcLblTotal.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		rcLblTotal.setBounds(750, 504, 110, 29);
		rcPanel.add(rcLblTotal);

		rcLblTotal1 = new JLabel("");
		rcLblTotal1.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		rcLblTotal1.setBounds(850, 504, 95, 29);
		rcPanel.add(rcLblTotal1);

		JButton rcBtnCancelar = new JButton("Cancelar");
		rcBtnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.cancelarCompra(proyeccionesSeleccionadas, bPanel, rcPanel);
			}
		});
		rcBtnCancelar.setBounds(66, 558, 117, 29);
		rcPanel.add(rcBtnCancelar);

		JButton rcBtnConfirmar = new JButton("Confirmar");
		rcBtnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (proyeccionesSeleccionadas.size() == 0) {
					JOptionPane.showMessageDialog(null, "No hay entradas seleccionadas para proceder.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else if (null == clienteLogueado) {
					rcPanel.setVisible(false);
					lPanel.setVisible(true);
					clientes = controlador.guardarArrayListClientes();
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
		rcLblDescr.setBounds(70, 98, 566, 29);
		rcPanel.add(rcLblDescr);
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
		lBtnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lPanel.setVisible(false);
				rcPanel.setVisible(true);
			}
		});
		lBtnAtras.setBounds(24, 33, 85, 29);
		lPanel.add(lBtnAtras);

		JLabel lLblUsuario = new JLabel("Usuario:");
		lLblUsuario.setForeground(Color.WHITE);
		lLblUsuario.setFont(new Font("Dialog", Font.PLAIN, 15));
		lLblUsuario.setBounds(678, 114, 150, 56);
		lPanel.add(lLblUsuario);

		JTextField lTextFieldUsuario = new JTextField();
		lTextFieldUsuario.setBounds(678, 180, 150, 36);
		lPanel.add(lTextFieldUsuario);
		lTextFieldUsuario.setColumns(10);

		JLabel lLblContraseña = new JLabel("Contraseña:");
		lLblContraseña.setForeground(Color.WHITE);
		lLblContraseña.setFont(new Font("Dialog", Font.PLAIN, 15));
		lLblContraseña.setBounds(678, 226, 150, 56);
		lPanel.add(lLblContraseña);

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
		lBtnIniciarSesion.setFont(new Font("Tahoma", Font.BOLD, 10));
		lBtnIniciarSesion.setForeground(Color.WHITE);
		lBtnIniciarSesion.setBackground(Color.RED);
		lBtnIniciarSesion.setBounds(678, 338, 150, 36);
		lPanel.add(lBtnIniciarSesion);

		JLabel lLblNoTengoCuenta = new JLabel("No tengo cuenta");
		lLblNoTengoCuenta.setForeground(Color.WHITE);
		lLblNoTengoCuenta.setFont(new Font("Dialog", Font.PLAIN, 20));
		lLblNoTengoCuenta.setBounds(678, 481, 200, 56);
		lPanel.add(lLblNoTengoCuenta);

		JButton lBtnRegistrarse = new JButton("Registrarme");
		lBtnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lPanel.setVisible(false);
				srPanel.setVisible(true);
			}
		});
		lBtnRegistrarse.setForeground(Color.RED);
		lBtnRegistrarse.setFont(new Font("Tahoma", Font.BOLD, 10));
		lBtnRegistrarse.setBackground(Color.LIGHT_GRAY);
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
		itPanel.setBounds(0, 0, 1000, 672);
		frame.getContentPane().add(itPanel);
		itPanel.setLayout(null);

		JLabel itLblAgradecimiento = new JLabel("¡GRACIAS POR SU COMPRA!");
		itLblAgradecimiento.setForeground(Color.RED);
		itLblAgradecimiento.setFont(new Font("Dialog", Font.BOLD, 20));
		itLblAgradecimiento.setBounds(323, 41, 400, 56);
		itPanel.add(itLblAgradecimiento);

		JLabel itLblPregunta = new JLabel("¿Desea imprimir las entradas?");
		itLblPregunta.setFont(new Font("Dialog", Font.PLAIN, 20));
		itLblPregunta.setBounds(323, 369, 400, 56);
		itPanel.add(itLblPregunta);

		JButton itBtnSi = new JButton("SÍ");
		itBtnSi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.imprimirTicket(proyeccionesSeleccionadas, clienteLogueado, fechaCompra);
				itPanel.setVisible(false);
				bPanel.setVisible(true);
				proyeccionesSeleccionadas.removeAll(proyeccionesSeleccionadas);
				clienteLogueado = null;
			}
		});
		itBtnSi.setForeground(Color.WHITE);
		itBtnSi.setFont(new Font("Tahoma", Font.BOLD, 15));
		itBtnSi.setBackground(Color.RED);
		itBtnSi.setBounds(312, 435, 150, 36);
		itPanel.add(itBtnSi);

		JButton itBtnNo = new JButton("NO");
		itBtnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				itPanel.setVisible(false);
				bPanel.setVisible(true);
				proyeccionesSeleccionadas.removeAll(proyeccionesSeleccionadas);
				clienteLogueado = null;
			}
		});
		itBtnNo.setForeground(Color.WHITE);
		itBtnNo.setFont(new Font("Tahoma", Font.BOLD, 15));
		itBtnNo.setBackground(Color.RED);
		itBtnNo.setBounds(472, 435, 150, 36);
		itPanel.add(itBtnNo);
	}
	
	private void crearSeleccionRegistro() {
		srPanel = new JPanel();
		srPanel.setBounds(0, 0, 1000, 672);
		frame.getContentPane().add(srPanel);
		srPanel.setLayout(null);
		
		JLabel srLblRegistro = new JLabel("REGISTRO");
		srLblRegistro.setFont(new Font("Dialog", Font.PLAIN, 20));
		srLblRegistro.setBounds(411, 34, 118, 56);
		srPanel.add(srLblRegistro);
		
		JLabel srLblNombre = new JLabel("Nombre");
		srLblNombre.setFont(new Font("Dialog", Font.PLAIN, 20));
		srLblNombre.setBounds(94, 100, 300, 56);
		srPanel.add(srLblNombre);
		
		JTextField srTextFieldNombre = new JTextField();
		srTextFieldNombre.setColumns(10);
		srTextFieldNombre.setBounds(94, 166, 300, 36);
		srPanel.add(srTextFieldNombre);
		
		JLabel srLblApellidos = new JLabel("Apellidos");
		srLblApellidos.setFont(new Font("Dialog", Font.PLAIN, 20));
		srLblApellidos.setBounds(94, 212, 300, 56);
		srPanel.add(srLblApellidos);
		
		JTextField srTextFieldApellidos = new JTextField();
		srTextFieldApellidos.setColumns(10);
		srTextFieldApellidos.setBounds(94, 278, 300, 36);
		srPanel.add(srTextFieldApellidos);
		
		JLabel srLblDNI = new JLabel("DNI");
		srLblDNI.setFont(new Font("Dialog", Font.PLAIN, 20));
		srLblDNI.setBounds(94, 324, 300, 56);
		srPanel.add(srLblDNI);
		
		JTextField srTextFieldDNI = new JTextField();
		srTextFieldDNI.setColumns(10);
		srTextFieldDNI.setBounds(94, 390, 300, 36);
		srPanel.add(srTextFieldDNI);
		
		JLabel srLblDireccion = new JLabel("Dirección");
		srLblDireccion.setFont(new Font("Dialog", Font.PLAIN, 20));
		srLblDireccion.setBounds(94, 436, 150, 56);
		srPanel.add(srLblDireccion);
		
		JTextField srTextFieldDireccion = new JTextField();
		srTextFieldDireccion.setColumns(10);
		srTextFieldDireccion.setBounds(94, 502, 300, 36);
		srPanel.add(srTextFieldDireccion);
		
		JLabel srLblSexo = new JLabel("Sexo");
		srLblSexo.setFont(new Font("Dialog", Font.PLAIN, 20));
		srLblSexo.setBounds(538, 122, 150, 56);
		srPanel.add(srLblSexo);
		
		JComboBox<String> srComboSexo = new JComboBox<String>();
		srComboSexo.setModel(new DefaultComboBoxModel<String>(new String[] {"Hombre", "Mujer"}));
		srComboSexo.setBounds(538, 175, 191, 27);
		srPanel.add(srComboSexo);
		
		JLabel srLblUsuario = new JLabel("Usuario");
		srLblUsuario.setFont(new Font("Dialog", Font.PLAIN, 20));
		srLblUsuario.setBounds(538, 212, 300, 56);
		srPanel.add(srLblUsuario);
		
		JTextField srTextFieldUsuario = new JTextField();
		srTextFieldUsuario.setColumns(10);
		srTextFieldUsuario.setBounds(538, 278, 300, 36);
		srPanel.add(srTextFieldUsuario);
		
		JLabel srLblContraseña = new JLabel("Contraseña");
		srLblContraseña.setFont(new Font("Dialog", Font.PLAIN, 20));
		srLblContraseña.setBounds(538, 324, 300, 56);
		srPanel.add(srLblContraseña);
		
		JPasswordField srPasswordFieldContraseña = new JPasswordField();
		srPasswordFieldContraseña.setBounds(538, 390, 300, 36);
		srPanel.add(srPasswordFieldContraseña);
		
		JLabel srLblRepContra = new JLabel("Repetir Contraseña");
		srLblRepContra.setFont(new Font("Dialog", Font.PLAIN, 20));
		srLblRepContra.setBounds(538, 436, 300, 56);
		srPanel.add(srLblRepContra);
		
		JPasswordField srPasswordFieldRepContraseña = new JPasswordField();
		srPasswordFieldRepContraseña.setBounds(538, 502, 300, 36);
		srPanel.add(srPasswordFieldRepContraseña);
		
		JButton srBtnRegistrarme = new JButton("Registrarme");
		srBtnRegistrarme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean esCorrectoRegistro = true;
				Object pattern = Pattern.compile("^(\\d{8}[A-Z])$");
				Object matcher = ((Pattern) pattern).matcher(srTextFieldDNI.getText());
				Pattern letterPattern = Pattern.compile("^([A-Za-zÑñÁáÉéÍíÓóÚú]+['\\-]{0,1}[A-Za-zÑñÁáÉéÍíÓóÚú]+)(\\s+([A-Za-zÑñÁáÉéÍíÓóÚú]+['\\-]{0,1}[A-Za-zÑñÁáÉéÍíÓóÚú]+))*$");
				
				if ((srTextFieldNombre.getText().isEmpty()) || 
						(srTextFieldApellidos.getText().isEmpty()) || 
						(srTextFieldDNI.getText().isEmpty()) || 
						(srTextFieldDireccion.getText().isEmpty()) || 
						(srTextFieldUsuario.getText().isEmpty()) || 
						(srPasswordFieldContraseña.getPassword().length == 0) || 
						(srPasswordFieldRepContraseña.getPassword().length == 0)) {
					esCorrectoRegistro = false;
				} else if (! (new String(srPasswordFieldContraseña.getPassword()).equals(new String(srPasswordFieldRepContraseña.getPassword()))) ) {
					esCorrectoRegistro = false;
				} else if ((((Matcher) matcher).matches() && (srTextFieldDNI.getText().trim().length() == 9))) {
					for (int i = 0; i < clientes.size(); i++) {
						if ((srTextFieldDNI.getText().equals(clientes.get(i).getDni())) || (srTextFieldUsuario.getText().equals(clientes.get(i).getUsuario()))) {
							esCorrectoRegistro = false;
						}
					}
				} else if ( (!(letterPattern.matcher(srTextFieldNombre.getText()).matches())) || (!(letterPattern.matcher(srTextFieldApellidos.getText()).matches())) ) {
					esCorrectoRegistro = false;
				}
				
				if (esCorrectoRegistro == false) {
					JFrame jFrame = new JFrame();
					JOptionPane.showMessageDialog(jFrame, "Valores introducidos invalidos");
				} else {
					Controlador controlador = new Controlador();
					
					Cliente cliente = new Cliente ();
					cliente.setDni(srTextFieldDNI.getText());
					cliente.setNombre(srTextFieldNombre.getText());
					cliente.setApellidos(srTextFieldApellidos.getText());
					cliente.setUsuario(srTextFieldUsuario.getText());
					cliente.setContrasena(srPasswordFieldContraseña.getPassword().toString());
					String sexo = srComboSexo.getSelectedItem().toString();
					cliente.setSexo(sexo);
					cliente.setDireccion(srTextFieldDireccion.getText());
					
					controlador.insertCliente(cliente);
					
					srPanel.setVisible(false);
					lPanel.setVisible(true);
				}
			}
		});
		srBtnRegistrarme.setForeground(Color.WHITE);
		srBtnRegistrarme.setFont(new Font("Tahoma", Font.BOLD, 10));
		srBtnRegistrarme.setBackground(Color.RED);
		srBtnRegistrarme.setBounds(387, 587, 150, 36);
		srPanel.add(srBtnRegistrarme);
		
		JButton srBtnAtras = new JButton("Atrás");
		srBtnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				srPanel.setVisible(false);
				lPanel.setVisible(true);
			}
		});
		srBtnAtras.setFont(new Font("Tahoma", Font.BOLD, 10));
		srBtnAtras.setBounds(10, 578, 150, 36);
		srPanel.add(srBtnAtras);
	}
}
