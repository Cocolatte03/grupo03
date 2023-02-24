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

	private JPanel bienvenidaPanel;
	private JPanel cinePanel;
	private JPanel peliPanel;
	private JPanel proyeccionPanel;
	private JPanel resumenPanel;
	private JPanel loginPanel;
	private JPanel ticketPanel;
	private JPanel registroPanel;
	private JPanel confirmacionPanel;

	public JComboBox<String> cineComboCines;
	private JComboBox<String> proyeccionComboFecha;

	private DefaultTableModel peliTableModel;
	private DefaultTableModel resumenTableModel;
	private JTable resumenTable;

	private JPanel proyeccionPanelImagen;
	private JLabel proyeccionLblImagen;
	private JLabel confirmacionLblImagen;
	private JPanel confirmacionPanelImagen;

	private JLabel resumenLblTextSubtotal;
	private JLabel resumenLblTextDescuento;
	private JLabel resumenLblTextTotal;

	private JButton registroBtnAtrasLogin;
	private JButton registroBtnAtrasCine;

	private JLabel confirmacionLblTextCine;
	private JLabel confirmacionLblTextSala;
	private JLabel confirmacionLblTextPeli;
	private JLabel confirmacionLblTextFecha;
	private JLabel confirmacionLblTextPrecio;
	private JLabel confirmacionLblTextSesion;

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

		peliculas = new ArrayList<Pelicula>();
		proyecciones = new ArrayList<Proyeccion>();

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
		crearPanelConfirmacion();
		crearPanelResumenCompra();
		crearPanelLogin();
		crearPanelImpresionTicket();
		crearPanelRegistro();

		cinePanel.setVisible(false);
		peliPanel.setVisible(false);
		proyeccionPanel.setVisible(false);
		confirmacionPanel.setVisible(false);
		resumenPanel.setVisible(false);
		loginPanel.setVisible(false);
		ticketPanel.setVisible(false);
		registroPanel.setVisible(false);
	}

	private void crearPanelBienvenida() {
		bienvenidaPanel = new JPanel();
		bienvenidaPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clientes = controlador.guardarArrayListClientes();
				cines = controlador.guardarArrayListCines();

				cinePanel.setVisible(true);
				bienvenidaPanel.setVisible(false);
				controlador.anadirCinesAlCombo(cineComboCines, cines);
			}
		});
		bienvenidaPanel.setBounds(0, 0, 1000, 672);
		frame.getContentPane().add(bienvenidaPanel);
		bienvenidaPanel.setLayout(null);

		JLabel bienvenidaLblCabecera = new JLabel("Bienvenid@ al Cine Elorrieta");
		bienvenidaLblCabecera.setHorizontalAlignment(SwingConstants.CENTER);
		bienvenidaLblCabecera.setForeground(new Color(72, 138, 246));
		bienvenidaLblCabecera.setFont(new Font("Corbel", Font.BOLD, 30));
		bienvenidaLblCabecera.setBounds(223, 250, 563, 48);
		bienvenidaPanel.add(bienvenidaLblCabecera);

		JPanel bienvenidaPanelLogo = new JPanel();
		bienvenidaPanelLogo.setBounds(910, 40, 35, 30);
		bienvenidaPanel.add(bienvenidaPanelLogo);
		bienvenidaPanelLogo.setLayout(new BorderLayout(0, 0));
		bienvenidaPanelLogo.setOpaque(false);

		JLabel bienvenidaLblLogo = new JLabel("");
		bienvenidaPanelLogo.add(bienvenidaLblLogo, BorderLayout.CENTER);

		JPanel bienvenidaPanelImg = new JPanel();
		bienvenidaPanelImg.setBounds(0, 0, 1000, 672);
		bienvenidaPanel.add(bienvenidaPanelImg);
		bienvenidaPanelImg.setLayout(new BorderLayout(0, 0));

		JLabel bienvenidaLblImg = new JLabel("");
		bienvenidaPanelImg.add(bienvenidaLblImg, BorderLayout.CENTER);

		controlador.anadirImagen(bienvenidaPanelImg, bienvenidaLblImg, "img/bbg.jpg");
		controlador.anadirImagen(bienvenidaPanelLogo, bienvenidaLblLogo, "img/icon.png");
	}

	private void crearPanelSeleccionCine() {

		cinePanel = new JPanel();
		cinePanel.setBounds(0, 0, 1000, 672);
		frame.getContentPane().add(cinePanel);
		cinePanel.setLayout(null);
		cinePanel.setBackground(Color.WHITE);

		JLabel cineLblCabecera = new JLabel("Seleccione un cine:");
		cineLblCabecera.setForeground(new Color(194, 220, 241));
		cineLblCabecera.setHorizontalAlignment(SwingConstants.CENTER);
		cineLblCabecera.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		cineLblCabecera.setBounds(30, 43, 230, 56);
		cinePanel.add(cineLblCabecera);

		JPanel cinePanelImagen = new JPanel();
		cinePanelImagen.setBounds(160, 170, 700, 490);
		cinePanel.add(cinePanelImagen);
		cinePanelImagen.setLayout(new BorderLayout(0, 0));
		cinePanelImagen.setOpaque(false);

		JLabel cineLblImagen = new JLabel("");
		cinePanelImagen.add(cineLblImagen, BorderLayout.CENTER);

		cineComboCines = new JComboBox<String>();
		cineComboCines.setBackground(Color.WHITE);
		cineComboCines.setForeground(SystemColor.textHighlight);
		cineComboCines.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		cineComboCines.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (null != cineComboCines.getSelectedItem()) {
					controlador.cambiarImagen(cineComboCines, cinePanelImagen, cineLblImagen);
				}
			}
		});
		cineComboCines.setBounds(53, 110, 230, 33);
		cinePanel.add(cineComboCines);

		JButton cineBtnFin = new JButton("Finalizar Sesión");
		cineBtnFin.setBackground(SystemColor.textHighlight);
		cineBtnFin.setForeground(Color.WHITE);
		cineBtnFin.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		cineBtnFin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				double subtotal = controlador.calcularSubtotal(proyeccionesSeleccionadas);
				double descuento = controlador.calcularDescuento(proyeccionesSeleccionadas, subtotal);
				double total = controlador.calcularTotal(subtotal, descuento);

				controlador.finalizarSesion(proyeccionesSeleccionadas, cinePanel, resumenPanel, frame,
						resumenTableModel, resumenLblTextSubtotal, resumenLblTextDescuento, resumenLblTextTotal, subtotal,
						descuento, total);
			}
		});
		cineBtnFin.setBounds(792, 20, 169, 33);
		cinePanel.add(cineBtnFin);

		JButton cineBtnInicioSesion = new JButton("REGISTRARME");
		cineBtnInicioSesion.setBackground(Color.WHITE);
		cineBtnInicioSesion.setForeground(SystemColor.textHighlight);
		cineBtnInicioSesion.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		cineBtnInicioSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (clienteLogueado == null) {
					registroPanel.setVisible(true);
					cinePanel.setVisible(false);

					registroBtnAtrasLogin.setVisible(false);
					registroBtnAtrasCine.setVisible(true);
				}
			}
		});
		cineBtnInicioSesion.setBounds(600, 20, 169, 33);
		cinePanel.add(cineBtnInicioSesion);

		JButton cineBtnConfirmar = new JButton("Confirmar");
		cineBtnConfirmar.setBackground(SystemColor.textHighlight);
		cineBtnConfirmar.setForeground(new Color(255, 255, 255));
		cineBtnConfirmar.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		cineBtnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cinePanel.setVisible(false);
				cineSeleccionado = controlador.determinarCineSeleccionado(cineComboCines, cines);
				peliculas = controlador.guardarArrayListPeliculas(cineSeleccionado);
				controlador.cargarTablaConPeliculas(peliTableModel, peliculas);
				peliPanel.setVisible(true);
			}
		});
		cineBtnConfirmar.setBounds(305, 110, 111, 33);
		cinePanel.add(cineBtnConfirmar);

		JPanel cinePanelCabecera = new JPanel();
		cinePanelCabecera.setBounds(0, 0, 1000, 180);
		cinePanel.add(cinePanelCabecera);
		cinePanelCabecera.setLayout(null);
		cinePanelCabecera.setBackground(Color.DARK_GRAY);
	}

	public void crearPanelSeleccionPelicula() {

		peliPanel = new JPanel();
		peliPanel.setBounds(0, 0, 1000, 672);
		peliPanel.setBackground(new Color(66, 66, 66));
		frame.getContentPane().add(peliPanel);
		peliPanel.setLayout(null);

		JLabel peliLblCabecera = new JLabel("Seleccione una película:");
		peliLblCabecera.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		peliLblCabecera.setForeground(new Color(194, 220, 241));
		peliLblCabecera.setBounds(66, 43, 446, 56);
		peliPanel.add(peliLblCabecera);

		JLabel peliLblDesc = new JLabel("Haga clic sobre una película para seleccionarla.");
		peliLblDesc.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		peliLblDesc.setForeground(new Color(254, 255, 255));
		peliLblDesc.setBounds(70, 100, 400, 31);
		peliPanel.add(peliLblDesc);

		JPanel peliPanelImagen = new JPanel();
		peliPanelImagen.setBackground(new Color(254, 251, 0));
		peliPanelImagen.setBounds(500, 0, 500, 675);
		peliPanel.add(peliPanelImagen);
		peliPanelImagen.setLayout(new BorderLayout(0, 0));

		JLabel peliLblImagen = new JLabel("");
		peliPanelImagen.add(peliLblImagen, BorderLayout.CENTER);

		JScrollPane peliScrollPane = new JScrollPane();
		peliScrollPane.setBorder(new LineBorder(Color.WHITE, 0));
		peliScrollPane.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		peliScrollPane.getViewport().setBackground(Color.WHITE);
		peliScrollPane.setBounds(70, 150, 400, 400);
		peliPanel.add(peliScrollPane);

		JTable peliTable = new JTable();
		peliTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				peliSeleccionada = controlador.guardarPeliSeleccionada(peliTable, peliculas);

				controlador.anadirFechasAlCombo(proyeccionComboFecha,
						controlador.guardarArrayListProyeccionesAgrupadas(cineSeleccionado, peliSeleccionada));
				controlador.anadirImagen(proyeccionPanelImagen, proyeccionLblImagen, peliSeleccionada.getCaratula());

				peliPanel.setVisible(false);
				proyeccionPanel.setVisible(true);
			}
		});
		peliTable.setRowHeight(25);
		peliTable.setBackground(new Color(254, 255, 255));
		peliTable.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		peliScrollPane.setViewportView(peliTable);
		peliTable.setSelectionBackground(new Color(72, 138, 246));
		peliTable.setSelectionForeground(Color.WHITE);
		peliTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		peliTable.setShowGrid(false);

		Object[] peliColumnasTabla = { "TÍTULO", "GÉNERO", "DUR (min)" };

		JTableHeader peliTableHeader = peliTable.getTableHeader();
		peliTableHeader.setBackground(SystemColor.textHighlight);
		peliTableHeader.setForeground(Color.WHITE);
		peliTableHeader.setFont(new Font("Trebuchet MS", Font.BOLD, 14));

		peliTable.getTableHeader().setPreferredSize(new Dimension(peliScrollPane.getWidth(), 30));

		peliTableModel = new DefaultTableModel() {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		peliTableModel.setColumnIdentifiers(peliColumnasTabla);
		peliTable.setModel(peliTableModel);

		peliTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
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

		JButton peliBtnAtras = new JButton("Atrás");
		peliBtnAtras.setBackground(SystemColor.textHighlight);
		peliBtnAtras.setForeground(Color.WHITE);
		peliBtnAtras.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		peliBtnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				peliPanel.setVisible(false);
				cinePanel.setVisible(true);
			}
		});
		peliBtnAtras.setBounds(6, 6, 80, 29);
		peliPanel.add(peliBtnAtras);

		controlador.anadirImagen(peliPanelImagen, peliLblImagen, "img/spr_bg.jpg");
		ajustarColumnasPeliculas(peliTable);
	}

	private void crearPanelSeleccionProyeccion() {
		proyeccionPanel = new JPanel();
		proyeccionPanel.setBounds(0, 0, 1000, 672);
		proyeccionPanel.setBackground(new Color(66, 66, 66));
		proyeccionPanel.setLayout(null);
		frame.getContentPane().add(proyeccionPanel);

		JButton proyeccionBtnAtras = new JButton("Atrás");
		proyeccionBtnAtras.setBackground(SystemColor.textHighlight);
		proyeccionBtnAtras.setForeground(Color.WHITE);
		proyeccionBtnAtras.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		proyeccionBtnAtras.setBounds(6, 6, 80, 29);
		proyeccionBtnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				peliPanel.setVisible(true);
				proyeccionPanel.setVisible(false);
			}
		});
		proyeccionPanel.add(proyeccionBtnAtras);

		JLabel proyeccionLblCabecera = new JLabel("Seleccione una fecha:");
		proyeccionLblCabecera.setBounds(66, 43, 329, 56);
		proyeccionLblCabecera.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		proyeccionLblCabecera.setForeground(new Color(194, 220, 241));
		proyeccionPanel.add(proyeccionLblCabecera);

		JLabel proyeccionLblCabeceraSesion = new JLabel("Seleccione una sesión:");
		proyeccionLblCabeceraSesion.setBounds(66, 180, 370, 56);
		proyeccionLblCabeceraSesion.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		proyeccionLblCabeceraSesion.setForeground(new Color(194, 220, 241));
		proyeccionPanel.add(proyeccionLblCabeceraSesion);

		JScrollPane proyeccionScrollPane = new JScrollPane();
		proyeccionScrollPane.setBorder(new LineBorder(Color.WHITE, 0));
		proyeccionScrollPane.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		proyeccionScrollPane.getViewport().setBackground(Color.WHITE);
		proyeccionScrollPane.setBounds(70, 250, 378, 150);
		proyeccionPanel.add(proyeccionScrollPane);

		JTable proyeccionTableSesiones = new JTable();
		proyeccionTableSesiones.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				proyeccionSeleccionada = controlador.guardarProyeccionSeleccionada(proyeccionTableSesiones,
						proyecciones);

				controlador.anadirImagen(confirmacionPanelImagen, confirmacionLblImagen,
						proyeccionSeleccionada.getPelicula().getCaratula());
				
				controlador.asignarValoresALabelsSeleccion(proyeccionSeleccionada, confirmacionLblTextCine, confirmacionLblTextSala,
						confirmacionLblTextPeli, confirmacionLblTextFecha, confirmacionLblTextSesion, confirmacionLblTextPrecio);

				confirmacionPanel.setVisible(true);
				proyeccionPanel.setVisible(false);
			}
		});
		proyeccionTableSesiones.setRowHeight(25);
		proyeccionTableSesiones.setBackground(new Color(254, 255, 255));
		proyeccionTableSesiones.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		proyeccionScrollPane.setViewportView(proyeccionTableSesiones);
		proyeccionTableSesiones.setSelectionBackground(new Color(72, 138, 246));
		proyeccionTableSesiones.setSelectionForeground(Color.WHITE);
		proyeccionTableSesiones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		proyeccionTableSesiones.setShowGrid(false);

		Object[] proyeccionColumnasTabla = { "PELÍCULA", "HORA", "SALA", "PRECIO (€)" };

		JTableHeader proyeccionTableHeader = proyeccionTableSesiones.getTableHeader();
		proyeccionTableHeader.setBackground(SystemColor.textHighlight);
		proyeccionTableHeader.setForeground(Color.WHITE);
		proyeccionTableHeader.setFont(new Font("Trebuchet MS", Font.BOLD, 14));

		proyeccionTableSesiones.getTableHeader().setPreferredSize(new Dimension(proyeccionScrollPane.getWidth(), 30));

		DefaultTableModel proyeccionTableModel = new DefaultTableModel() {

			private static final long serialVersionUID = -7613193437612014716L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		proyeccionTableModel.setColumnIdentifiers(proyeccionColumnasTabla);
		proyeccionTableSesiones.setModel(proyeccionTableModel);

		proyeccionTableSesiones.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

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

		proyeccionComboFecha = new JComboBox<String>();
		proyeccionComboFecha.setBackground(Color.WHITE);
		proyeccionComboFecha.setForeground(SystemColor.textHighlight);
		proyeccionComboFecha.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		proyeccionComboFecha.setBounds(66, 117, 230, 33);
		proyeccionComboFecha.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (null != proyeccionComboFecha.getSelectedItem()) {
					proyecciones = controlador.guardarArrayListProyecciones(proyeccionComboFecha, cineSeleccionado,
							peliSeleccionada);
					controlador.cargarTablaConSesiones(proyeccionTableModel, proyecciones, peliSeleccionada);
				}
			}
		});
		proyeccionPanel.add(proyeccionComboFecha);

		proyeccionPanelImagen = new JPanel();
		proyeccionPanelImagen.setBounds(500, 0, 500, 675);
		proyeccionPanel.add(proyeccionPanelImagen);
		proyeccionPanelImagen.setLayout(new BorderLayout(0, 0));

		proyeccionLblImagen = new JLabel("");
		proyeccionPanelImagen.add(proyeccionLblImagen, BorderLayout.CENTER);
	}

	private void crearPanelConfirmacion() {
		confirmacionPanel = new JPanel();
		confirmacionPanel.setBounds(0, 0, 1000, 672);
		confirmacionPanel.setBackground(new Color(66, 66, 66));
		frame.getContentPane().add(confirmacionPanel);
		confirmacionPanel.setLayout(null);

		JLabel confirmacionLblCabecera = new JLabel("¿Desea confirmar la pelicula seleccionada?");
		confirmacionLblCabecera.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		confirmacionLblCabecera.setForeground(new Color(194, 220, 241));
		confirmacionLblCabecera.setBounds(50, 43, 446, 56);
		confirmacionPanel.add(confirmacionLblCabecera);

		JLabel confirmacionLblCine = new JLabel("Cine:");
		confirmacionLblCine.setBounds(70, 231, 80, 30);
		confirmacionPanel.add(confirmacionLblCine);
		confirmacionLblCine.setForeground(new Color(255, 255, 255));
		confirmacionLblCine.setFont(new Font("Trebuchet MS", Font.BOLD, 14));

		JLabel confirmacionLblSala = new JLabel("Sala:");
		confirmacionLblSala.setBounds(70, 272, 80, 30);
		confirmacionPanel.add(confirmacionLblSala);
		confirmacionLblSala.setForeground(new Color(255, 255, 255));
		confirmacionLblSala.setFont(new Font("Trebuchet MS", Font.BOLD, 14));

		JLabel confirmacionLblPeli = new JLabel("Pelicula:");
		confirmacionLblPeli.setBounds(70, 313, 80, 30);
		confirmacionPanel.add(confirmacionLblPeli);
		confirmacionLblPeli.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		confirmacionLblPeli.setForeground(new Color(255, 255, 255));

		JLabel confirmacionLblFecha = new JLabel("Fecha:");
		confirmacionLblFecha.setBounds(70, 354, 80, 30);
		confirmacionPanel.add(confirmacionLblFecha);
		confirmacionLblFecha.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		confirmacionLblFecha.setForeground(new Color(255, 255, 255));

		JLabel confirmacionLblPrecio = new JLabel("Precio:");
		confirmacionLblPrecio.setBounds(70, 395, 80, 30);
		confirmacionPanel.add(confirmacionLblPrecio);
		confirmacionLblPrecio.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		confirmacionLblPrecio.setForeground(new Color(255, 255, 255));

		JLabel confirmacionLblSesion = new JLabel("Sesión:");
		confirmacionLblSesion.setBounds(70, 436, 80, 30);
		confirmacionPanel.add(confirmacionLblSesion);
		confirmacionLblSesion.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		confirmacionLblSesion.setForeground(new Color(255, 255, 255));

		confirmacionLblTextCine = new JLabel("");
		confirmacionLblTextCine.setBounds(150, 231, 170, 30);
		confirmacionPanel.add(confirmacionLblTextCine);
		confirmacionLblTextCine.setForeground(new Color(255, 255, 255));
		confirmacionLblTextCine.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));

		confirmacionLblTextSala = new JLabel("");
		confirmacionLblTextSala.setBounds(150, 272, 170, 30);
		confirmacionPanel.add(confirmacionLblTextSala);
		confirmacionLblTextSala.setForeground(new Color(255, 255, 255));
		confirmacionLblTextSala.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));

		confirmacionLblTextPeli = new JLabel("");
		confirmacionLblTextPeli.setBounds(150, 313, 170, 30);
		confirmacionPanel.add(confirmacionLblTextPeli);
		confirmacionLblTextPeli.setForeground(new Color(255, 255, 255));
		confirmacionLblTextPeli.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));

		confirmacionLblTextFecha = new JLabel("");
		confirmacionLblTextFecha.setBounds(150, 354, 170, 30);
		confirmacionPanel.add(confirmacionLblTextFecha);
		confirmacionLblTextFecha.setForeground(new Color(255, 255, 255));
		confirmacionLblTextFecha.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));

		confirmacionLblTextPrecio = new JLabel("");
		confirmacionLblTextPrecio.setBounds(150, 395, 170, 30);
		confirmacionPanel.add(confirmacionLblTextPrecio);
		confirmacionLblTextPrecio.setForeground(new Color(255, 255, 255));
		confirmacionLblTextPrecio.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));

		confirmacionLblTextSesion = new JLabel("");
		confirmacionLblTextSesion.setBounds(150, 436, 170, 30);
		confirmacionPanel.add(confirmacionLblTextSesion);
		confirmacionLblTextSesion.setForeground(new Color(255, 255, 255));
		confirmacionLblTextSesion.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));

		JButton confirmacionBtnConfirmar = new JButton("Confirmar");
		confirmacionBtnConfirmar.setForeground(SystemColor.textHighlight);
		confirmacionBtnConfirmar.setBackground(Color.WHITE);
		confirmacionBtnConfirmar.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		confirmacionBtnConfirmar.setBounds(70, 567, 130, 29);
		confirmacionBtnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				controlador.guardarProyeccionConfirmada(proyeccionSeleccionada, proyeccionesSeleccionadas);
				cinePanel.setVisible(true);
				confirmacionPanel.setVisible(false);
			}
		});
		confirmacionPanel.add(confirmacionBtnConfirmar);

		JButton confirmacionBtnAtras = new JButton("Atrás");
		confirmacionBtnAtras.setBackground(SystemColor.textHighlight);
		confirmacionBtnAtras.setForeground(Color.WHITE);
		confirmacionBtnAtras.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		confirmacionBtnAtras.setBounds(6, 6, 80, 29);
		confirmacionBtnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				proyeccionPanel.setVisible(true);
				confirmacionPanel.setVisible(false);
			}
		});
		confirmacionPanel.add(confirmacionBtnAtras);
		
		confirmacionPanelImagen = new JPanel();
		confirmacionPanelImagen.setBounds(500, 0, 500, 675);
		confirmacionPanel.add(confirmacionPanelImagen);
		confirmacionPanelImagen.setLayout(new BorderLayout(0, 0));

		confirmacionLblImagen = new JLabel("");
		confirmacionPanelImagen.add(confirmacionLblImagen, BorderLayout.CENTER);

	}

	private void crearPanelResumenCompra() {
		resumenPanel = new JPanel();
		resumenPanel.setBounds(0, 0, 1000, 672);
		frame.getContentPane().add(resumenPanel);
		resumenPanel.setBackground(Color.WHITE);
		resumenPanel.setLayout(null);

		JButton resumenBtnAtras = new JButton("Atrás");
		resumenBtnAtras.setBackground(SystemColor.textHighlight);
		resumenBtnAtras.setForeground(Color.WHITE);
		resumenBtnAtras.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		resumenBtnAtras.setBounds(6, 6, 80, 29);
		resumenBtnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cinePanel.setVisible(true);
				resumenPanel.setVisible(false);
			}
		});
		resumenPanel.add(resumenBtnAtras);

		JLabel resumenLblCabecera = new JLabel("Resumen de la compra:");
		resumenLblCabecera.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		resumenLblCabecera.setForeground(new Color(194, 220, 241));
		resumenLblCabecera.setBounds(66, 43, 329, 56);
		resumenPanel.add(resumenLblCabecera);

		JScrollPane resumenScrollPane = new JScrollPane();
		resumenScrollPane.setBorder(new LineBorder(Color.WHITE, 0));
		resumenScrollPane.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		resumenScrollPane.getViewport().setBackground(new Color(220, 230, 241));
		resumenScrollPane.setBounds(70, 250, 850, 150);
		resumenPanel.add(resumenScrollPane);

		resumenTable = new JTable();
		resumenTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controlador.eliminarProyeccionSel(proyeccionesSeleccionadas, resumenTable, resumenTableModel,
						resumenLblTextSubtotal, resumenLblTextDescuento, resumenLblTextTotal);
			}
		});
		resumenTable.setRowHeight(25);
		resumenTable.setBackground(new Color(220, 230, 241));
		resumenTable.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		resumenTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		resumenTable.setSelectionBackground(new Color(72, 138, 246));
		resumenTable.setSelectionForeground(Color.WHITE);
		resumenTable.setShowGrid(false);
		resumenScrollPane.setViewportView(resumenTable);

		Object[] resumenColumnasTabla = { "PELÍCULA", "FECHA", "SESIÓN", "SALA", "CINE", "PRECIO (€)" };

		JTableHeader resumenTableHeader = resumenTable.getTableHeader();
		resumenTableHeader.setBackground(SystemColor.textHighlight);
		resumenTableHeader.setForeground(Color.WHITE);
		resumenTableHeader.setFont(new Font("Trebuchet MS", Font.BOLD, 14));

		resumenTable.getTableHeader().setPreferredSize(new Dimension(resumenScrollPane.getWidth(), 30));

		resumenTableModel = new DefaultTableModel() {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		resumenTableModel.setColumnIdentifiers(resumenColumnasTabla);
		resumenTable.setModel(resumenTableModel);

		resumenTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
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

		ajustarColumnasResumenCompra(resumenTable);

		JLabel resumenLblSubtotal = new JLabel("Subtotal:");
		resumenLblSubtotal.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		resumenLblSubtotal.setBounds(750, 422, 110, 29);
		resumenPanel.add(resumenLblSubtotal);

		resumenLblTextSubtotal = new JLabel("");
		resumenLblTextSubtotal.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		resumenLblTextSubtotal.setBounds(850, 422, 95, 29);
		resumenPanel.add(resumenLblTextSubtotal);

		JLabel resumenLblDescuento = new JLabel("Descuento:");
		resumenLblDescuento.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		resumenLblDescuento.setBounds(750, 463, 110, 29);
		resumenPanel.add(resumenLblDescuento);

		resumenLblTextDescuento = new JLabel("");
		resumenLblTextDescuento.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		resumenLblTextDescuento.setBounds(850, 463, 95, 29);
		resumenPanel.add(resumenLblTextDescuento);

		JLabel resumenLblTotal = new JLabel("TOTAL:");
		resumenLblTotal.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		resumenLblTotal.setBounds(750, 504, 110, 29);
		resumenPanel.add(resumenLblTotal);

		resumenLblTextTotal = new JLabel("");
		resumenLblTextTotal.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		resumenLblTextTotal.setBounds(850, 504, 95, 29);
		resumenPanel.add(resumenLblTextTotal);

		JButton resumenBtnCancelar = new JButton("Cancelar");
		resumenBtnCancelar.setBackground(Color.BLACK);
		resumenBtnCancelar.setForeground(Color.WHITE);
		resumenBtnCancelar.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		resumenBtnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.cancelarCompra(proyeccionesSeleccionadas, bienvenidaPanel, resumenPanel);
			}
		});
		resumenBtnCancelar.setBounds(70, 558, 117, 29);
		resumenPanel.add(resumenBtnCancelar);

		JButton resumenBtnConfirmar = new JButton("Confirmar");
		resumenBtnConfirmar.setBackground(SystemColor.textHighlight);
		resumenBtnConfirmar.setForeground(Color.WHITE);
		resumenBtnConfirmar.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		resumenBtnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (proyeccionesSeleccionadas.size() == 0) {
					JOptionPane.showMessageDialog(null, "No hay entradas seleccionadas para proceder.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else if (null == clienteLogueado) {
					resumenPanel.setVisible(false);
					loginPanel.setVisible(true);
				} else if (null != clienteLogueado) {
					fechaCompra = LocalDateTime.now();
					controlador.crearEntradas(proyeccionesSeleccionadas, clienteLogueado);
					resumenPanel.setVisible(false);
					ticketPanel.setVisible(true);
				}
			}
		});
		resumenBtnConfirmar.setBounds(218, 558, 117, 29);
		resumenPanel.add(resumenBtnConfirmar);

		JLabel resumenLblDescr = new JLabel("Haga clic sobre una sesión para eliminarla.");
		resumenLblDescr.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		resumenLblDescr.setForeground(Color.WHITE);
		resumenLblDescr.setBounds(70, 98, 566, 29);
		resumenPanel.add(resumenLblDescr);

		JPanel resumenPanelImg = new JPanel();
		resumenPanelImg.setBounds(800, 50, 100, 100);
		resumenPanel.add(resumenPanelImg);
		resumenPanelImg.setOpaque(false);
		resumenPanelImg.setLayout(new BorderLayout(0, 0));

		JLabel resumenLblImg = new JLabel("");
		resumenPanelImg.add(resumenLblImg, BorderLayout.CENTER);

		controlador.anadirImagen(resumenPanelImg, resumenLblImg, "img/carrito.png");

		JPanel resumenPanelCabecera = new JPanel();
		resumenPanelCabecera.setBounds(0, 0, 1000, 180);
		resumenPanel.add(resumenPanelCabecera);
		resumenPanelCabecera.setLayout(null);
		resumenPanelCabecera.setBackground(Color.DARK_GRAY);
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
		loginPanel = new JPanel();
		loginPanel.setBounds(0, 0, 1000, 672);
		loginPanel.setBackground(new Color(66, 66, 66));
		frame.getContentPane().add(loginPanel);
		loginPanel.setLayout(null);

		JButton loginBtnAtras = new JButton("Atrás");
		loginBtnAtras.setBackground(SystemColor.textHighlight);
		loginBtnAtras.setForeground(Color.WHITE);
		loginBtnAtras.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		loginBtnAtras.setBounds(6, 6, 80, 29);
		loginBtnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginPanel.setVisible(false);
				resumenPanel.setVisible(true);
			}
		});
		loginPanel.add(loginBtnAtras);

		JLabel loginLblUsuario = new JLabel("Usuario:");
		loginLblUsuario.setForeground(Color.WHITE);
		loginLblUsuario.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		loginLblUsuario.setBounds(678, 134, 150, 36);
		loginPanel.add(loginLblUsuario);

		JTextField loginTextFieldUsuario = new JTextField();
		loginTextFieldUsuario.setBounds(678, 180, 150, 36);
		loginPanel.add(loginTextFieldUsuario);
		loginTextFieldUsuario.setColumns(10);

		JLabel loginLblContrasena = new JLabel("Contraseña:");
		loginLblContrasena.setForeground(Color.WHITE);
		loginLblContrasena.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		loginLblContrasena.setBounds(678, 246, 150, 36);
		loginPanel.add(loginLblContrasena);

		JPasswordField loginPasswordField = new JPasswordField();
		loginPasswordField.setColumns(10);
		loginPasswordField.setBounds(678, 292, 150, 36);
		loginPanel.add(loginPasswordField);

		JButton loginBtnIniciarSesion = new JButton("Iniciar Sesión");
		loginBtnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usuario = loginTextFieldUsuario.getText();
				String contrasena = String.valueOf(loginPasswordField.getPassword());
				clienteLogueado = controlador.guardarCliente(clientes, usuario);
				controlador.iniciarSesion(clienteLogueado, contrasena, loginPanel, resumenPanel, loginTextFieldUsuario,
						loginPasswordField);
			}
		});
		loginBtnIniciarSesion.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		loginBtnIniciarSesion.setForeground(Color.WHITE);
		loginBtnIniciarSesion.setBackground(SystemColor.textHighlight);
		loginBtnIniciarSesion.setBounds(678, 350, 150, 36);
		loginPanel.add(loginBtnIniciarSesion);

		JLabel loginLblNoTengoCuenta = new JLabel("No tengo cuenta");
		loginLblNoTengoCuenta.setHorizontalAlignment(SwingConstants.CENTER);
		loginLblNoTengoCuenta.setForeground(Color.WHITE);
		loginLblNoTengoCuenta.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		loginLblNoTengoCuenta.setBounds(665, 481, 177, 56);
		loginPanel.add(loginLblNoTengoCuenta);

		JButton loginBtnRegistrarse = new JButton("REGISTRARME");
		loginBtnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginPanel.setVisible(false);
				registroPanel.setVisible(true);
				registroBtnAtrasLogin.setVisible(true);
				registroBtnAtrasCine.setVisible(false);
			}
		});
		loginBtnRegistrarse.setForeground(SystemColor.textHighlight);
		loginBtnRegistrarse.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		loginBtnRegistrarse.setBackground(Color.WHITE);
		loginBtnRegistrarse.setBounds(678, 547, 150, 36);
		loginPanel.add(loginBtnRegistrarse);

		JPanel loginPanelImagen = new JPanel();
		loginPanelImagen.setBackground(new Color(254, 251, 0));
		loginPanelImagen.setBounds(0, 0, 500, 675);
		loginPanel.add(loginPanelImagen);
		loginPanelImagen.setLayout(new BorderLayout(0, 0));

		JLabel loginLblImagen = new JLabel("");
		loginPanelImagen.add(loginLblImagen, BorderLayout.CENTER);

		controlador.anadirImagen(loginPanelImagen, loginLblImagen, "img/l_bg.jpg");
	}

	private void crearPanelImpresionTicket() {
		ticketPanel = new JPanel();
		ticketPanel.setBackground(Color.WHITE);
		ticketPanel.setBounds(0, 0, 1000, 672);
		frame.getContentPane().add(ticketPanel);
		ticketPanel.setLayout(null);

		JLabel ticketLblCabecera = new JLabel("¡GRACIAS POR SU COMPRA!");
		ticketLblCabecera.setHorizontalAlignment(SwingConstants.CENTER);
		ticketLblCabecera.setForeground(SystemColor.textHighlight);
		ticketLblCabecera.setFont(new Font("Trebuchet MS", Font.BOLD, 25));
		ticketLblCabecera.setBounds(302, 41, 400, 56);
		ticketPanel.add(ticketLblCabecera);

		JLabel ticketLblPregunta = new JLabel("¿Desea imprimir las entradas?");
		ticketLblPregunta.setHorizontalAlignment(SwingConstants.CENTER);
		ticketLblPregunta.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		ticketLblPregunta.setBounds(302, 447, 400, 56);
		ticketPanel.add(ticketLblPregunta);

		JButton ticketBtnImprimir = new JButton("SÍ");
		ticketBtnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.imprimirTicket(proyeccionesSeleccionadas, clienteLogueado, fechaCompra);
				JOptionPane.showMessageDialog(null, ("Sus entradas se han impreso correctamente."));
				ticketPanel.setVisible(false);
				bienvenidaPanel.setVisible(true);
				controlador.reiniciarProyeccionesSeleccionadas(proyeccionesSeleccionadas);
				clienteLogueado = controlador.desloguearCliente();
			}
		});
		ticketBtnImprimir.setForeground(Color.WHITE);
		ticketBtnImprimir.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		ticketBtnImprimir.setBackground(SystemColor.textHighlight);
		ticketBtnImprimir.setBounds(413, 525, 66, 36);
		ticketPanel.add(ticketBtnImprimir);

		JButton ticketBtnNoImprimir = new JButton("NO");
		ticketBtnNoImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ticketPanel.setVisible(false);
				bienvenidaPanel.setVisible(true);
				controlador.reiniciarProyeccionesSeleccionadas(proyeccionesSeleccionadas);
				clienteLogueado = controlador.desloguearCliente();
			}
		});
		ticketBtnNoImprimir.setForeground(Color.WHITE);
		ticketBtnNoImprimir.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		ticketBtnNoImprimir.setBackground(Color.DARK_GRAY);
		ticketBtnNoImprimir.setBounds(539, 525, 66, 36);
		ticketPanel.add(ticketBtnNoImprimir);

		JPanel ticketPanelImagen = new JPanel();
		ticketPanelImagen.setBounds(312, 108, 390, 328);
		ticketPanel.add(ticketPanelImagen);
		ticketPanelImagen.setLayout(new BorderLayout(0, 0));

		JLabel ticketLblImagen = new JLabel("");
		ticketPanelImagen.add(ticketLblImagen, BorderLayout.CENTER);

		controlador.anadirImagen(ticketPanelImagen, ticketLblImagen, "img/ticket.jpg");
	}

	private void crearPanelRegistro() {
		registroPanel = new JPanel();
		registroPanel.setBounds(0, 0, 1000, 672);
		frame.getContentPane().add(registroPanel);
		registroPanel.setLayout(null);
		registroPanel.setBackground(new Color(66, 66, 66));

		JLabel registroLblCabecera = new JLabel("Regístrate:");
		registroLblCabecera.setBounds(66, 43, 329, 56);
		registroLblCabecera.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		registroLblCabecera.setForeground(new Color(194, 220, 241));
		registroPanel.add(registroLblCabecera);

		JLabel registroLblDescripcion = new JLabel("Rellene los campos del siguiente formulario.");
		registroLblDescripcion.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		registroLblDescripcion.setForeground(new Color(254, 255, 255));
		registroLblDescripcion.setBounds(70, 100, 400, 31);
		registroPanel.add(registroLblDescripcion);

		JLabel registroLblNombre = new JLabel("Nombre:");
		registroLblNombre.setForeground(Color.WHITE);
		registroLblNombre.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		registroLblNombre.setBounds(70, 140, 150, 36);
		registroPanel.add(registroLblNombre);

		JTextField registroTextFieldNombre = new JTextField();
		registroTextFieldNombre.setFont(new Font("Trebuchet MS", Font.PLAIN, 11));
		registroTextFieldNombre.setColumns(10);
		registroTextFieldNombre.setBounds(70, 175, 150, 26);
		registroPanel.add(registroTextFieldNombre);

		JLabel registroLblApellidos = new JLabel("Apellidos:");
		registroLblApellidos.setForeground(Color.WHITE);
		registroLblApellidos.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		registroLblApellidos.setBounds(70, 242, 150, 36);
		registroPanel.add(registroLblApellidos);

		JTextField registroTextFieldApellidos = new JTextField();
		registroTextFieldApellidos.setFont(new Font("Trebuchet MS", Font.PLAIN, 11));
		registroTextFieldApellidos.setColumns(10);
		registroTextFieldApellidos.setBounds(70, 278, 150, 26);
		registroPanel.add(registroTextFieldApellidos);

		JLabel registroLblDni = new JLabel("DNI:");
		registroLblDni.setForeground(Color.WHITE);
		registroLblDni.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		registroLblDni.setBounds(70, 355, 150, 36);
		registroPanel.add(registroLblDni);

		JTextField registroTextFieldDni = new JTextField();
		registroTextFieldDni.setFont(new Font("Trebuchet MS", Font.PLAIN, 11));
		registroTextFieldDni.setColumns(10);
		registroTextFieldDni.setBounds(70, 390, 150, 26);
		registroPanel.add(registroTextFieldDni);

		JLabel registroLblDireccion = new JLabel("Dirección:");
		registroLblDireccion.setForeground(Color.WHITE);
		registroLblDireccion.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		registroLblDireccion.setBounds(70, 467, 150, 36);
		registroPanel.add(registroLblDireccion);

		JTextField registroTextFieldDireccion = new JTextField();
		registroTextFieldDireccion.setFont(new Font("Trebuchet MS", Font.PLAIN, 11));
		registroTextFieldDireccion.setColumns(10);
		registroTextFieldDireccion.setBounds(70, 501, 150, 26);
		registroPanel.add(registroTextFieldDireccion);

		JLabel registroLblSexo = new JLabel("Sexo:");
		registroLblSexo.setForeground(Color.WHITE);
		registroLblSexo.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		registroLblSexo.setBounds(250, 140, 150, 36);
		registroPanel.add(registroLblSexo);

		JComboBox<String> registroComboSexo = new JComboBox<String>();
		registroComboSexo.setBackground(Color.WHITE);
		registroComboSexo.setForeground(SystemColor.textHighlight);
		registroComboSexo.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		registroComboSexo.addItem("Hombre");
		registroComboSexo.addItem("Mujer");
		registroComboSexo.addItem("Otro");
		registroComboSexo.setBounds(250, 175, 150, 26);
		registroPanel.add(registroComboSexo);

		JLabel registroLblUsuario = new JLabel("Usuario:");
		registroLblUsuario.setForeground(Color.WHITE);
		registroLblUsuario.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		registroLblUsuario.setBounds(250, 240, 150, 36);
		registroPanel.add(registroLblUsuario);

		JTextField registroTextFieldUsuario = new JTextField();
		registroTextFieldUsuario.setFont(new Font("Trebuchet MS", Font.PLAIN, 11));
		registroTextFieldUsuario.setColumns(10);
		registroTextFieldUsuario.setBounds(250, 278, 150, 26);
		registroPanel.add(registroTextFieldUsuario);

		JLabel registroLblContrasena = new JLabel("Contraseña:");
		registroLblContrasena.setForeground(Color.WHITE);
		registroLblContrasena.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		registroLblContrasena.setBounds(250, 353, 150, 36);
		registroPanel.add(registroLblContrasena);

		JPasswordField registroPasswordFieldContrasena = new JPasswordField();
		registroPasswordFieldContrasena.setFont(new Font("Trebuchet MS", Font.PLAIN, 11));
		registroPasswordFieldContrasena.setBounds(250, 390, 150, 26);
		registroPanel.add(registroPasswordFieldContrasena);

		JLabel registroLblRepContrasena = new JLabel("Repetir Contraseña:");
		registroLblRepContrasena.setForeground(Color.WHITE);
		registroLblRepContrasena.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		registroLblRepContrasena.setBounds(250, 467, 150, 36);
		registroPanel.add(registroLblRepContrasena);

		JPasswordField registroPasswordFieldRepContrasena = new JPasswordField();
		registroPasswordFieldRepContrasena.setFont(new Font("Trebuchet MS", Font.PLAIN, 11));
		registroPasswordFieldRepContrasena.setBounds(250, 502, 150, 26);
		registroPanel.add(registroPasswordFieldRepContrasena);

		JButton registroBtnRegistrarme = new JButton("Registrarme");
		registroBtnRegistrarme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.registrarNuevoCliente(clientes, registroComboSexo, registroTextFieldNombre,
						registroTextFieldApellidos, registroTextFieldDni, registroTextFieldDireccion,
						registroTextFieldUsuario, registroPasswordFieldContrasena, registroPasswordFieldRepContrasena);
				clientes = controlador.guardarArrayListClientes();
			}
		});
		registroBtnRegistrarme.setForeground(Color.WHITE);
		registroBtnRegistrarme.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		registroBtnRegistrarme.setBackground(SystemColor.textHighlight);
		registroBtnRegistrarme.setBounds(250, 576, 150, 36);
		registroPanel.add(registroBtnRegistrarme);

		registroBtnAtrasLogin = new JButton("Atrás");
		registroBtnAtrasLogin.setBackground(SystemColor.textHighlight);
		registroBtnAtrasLogin.setForeground(Color.WHITE);
		registroBtnAtrasLogin.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		registroBtnAtrasLogin.setBounds(6, 6, 80, 29);
		registroBtnAtrasLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registroPanel.setVisible(false);
				loginPanel.setVisible(true);
			}
		});
		registroPanel.add(registroBtnAtrasLogin);

		registroBtnAtrasCine = new JButton("Atrás");
		registroBtnAtrasCine.setBackground(SystemColor.textHighlight);
		registroBtnAtrasCine.setForeground(Color.WHITE);
		registroBtnAtrasCine.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		registroBtnAtrasCine.setBounds(6, 6, 80, 29);
		registroBtnAtrasCine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registroPanel.setVisible(false);
				cinePanel.setVisible(true);
			}
		});
		registroPanel.add(registroBtnAtrasCine);

		JPanel registroPanelImg = new JPanel();
		registroPanelImg.setBackground(new Color(66, 66, 66));
		registroPanelImg.setBounds(500, 0, 500, 675);
		registroPanelImg.setLayout(new BorderLayout(0, 0));
		registroPanel.add(registroPanelImg);

		JLabel rLblImg = new JLabel("");
		registroPanelImg.add(rLblImg, BorderLayout.CENTER);

		controlador.anadirImagen(registroPanelImg, rLblImg, "img/r_bg.jpg");

		registroBtnAtrasLogin.setVisible(false);
		registroBtnAtrasCine.setVisible(false);
	}
}
