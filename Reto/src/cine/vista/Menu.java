package cine.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import cine.bbdd.pojos.Cine;
import cine.bbdd.pojos.Pelicula;
import cine.bbdd.pojos.Proyeccion;
import cine.controlador.Controlador;

public class Menu {

	public JFrame frame;

	private JPanel bPanel;
	private JPanel scPanel;
	private JPanel spPanel;
	private JPanel sprPanel;
	private JPanel rcPanel;

	private JComboBox<String> scComboCines;
	private JComboBox<String> spComboTitulos;
	private JComboBox<String> sprComboFecha;

	private DefaultTableModel rcTableModel;
	private JTable rcTable;

	private JLabel rcLblSubtotal1;
	private JLabel rcLblDescuento1;
	private JLabel rcLblTotal1;
	private JLabel sprLblSala1;
	private JLabel sprLblPrecio1;

	private Controlador controlador = null;

	private ArrayList<Cine> cines = null;
	private ArrayList<Pelicula> peliculas = null;
	private ArrayList<Proyeccion> proyecciones = null;

	private ArrayList<Proyeccion> proyeccionesSeleccionadas = null;

	private Cine cineSeleccionado = null;
	private Pelicula peliSeleccionada = null;
	private Proyeccion proyeccionSeleccionada = null;

	/**
	 * Create the application.
	 */
	public Menu() {
		controlador = new Controlador();

		cines = controlador.guardarArrayListCines();
		peliculas = new ArrayList<Pelicula>();
		proyecciones = new ArrayList<Proyeccion>();

		proyeccionesSeleccionadas = new ArrayList<Proyeccion>();

		cineSeleccionado = new Cine();
		peliSeleccionada = new Pelicula();
		proyeccionSeleccionada = new Proyeccion();

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
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

		
		scPanel.setVisible(false);
		spPanel.setVisible(false);
		sprPanel.setVisible(false);
		rcPanel.setVisible(false);

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
		bLblCabecera.setFont(new Font("Lucida Grande", Font.BOLD, 30));
		bLblCabecera.setBounds(281, 250, 439, 36);
		bPanel.add(bLblCabecera);

		JPanel bPanelImg = new JPanel();
		bPanelImg.setBounds(0, 0, 1000, 672);
		bPanel.add(bPanelImg);
		bPanelImg.setLayout(new BorderLayout(0, 0));

		JLabel bLblImg = new JLabel("");
		bPanelImg.add(bLblImg, BorderLayout.CENTER);

		controlador.anadirImagen(bPanelImg, bLblImg, "img/bbg.jpg");
	}

	private void crearPanelSeleccionCine() {

		scPanel = new JPanel();
		scPanel.setBounds(0, 0, 1000, 672);
		frame.getContentPane().add(scPanel);
		scPanel.setLayout(null);

		JLabel scLblCabecera = new JLabel("Seleccione un cine:");
		scLblCabecera.setForeground(new Color(72, 138, 246));
		scLblCabecera.setHorizontalAlignment(SwingConstants.CENTER);
		scLblCabecera.setFont(new Font("Dialog", Font.BOLD, 20));
		scLblCabecera.setBounds(30, 43, 230, 56);
		scPanel.add(scLblCabecera);

		JPanel scPanelImg = new JPanel();
		scPanelImg.setBounds(160, 170, 700, 490);
		scPanel.add(scPanelImg);
		scPanelImg.setLayout(new BorderLayout(0, 0));

		JLabel scLblImg = new JLabel("");
		scPanelImg.add(scLblImg, BorderLayout.CENTER);

		scComboCines = new JComboBox<String>();
		scComboCines.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (null != scComboCines.getSelectedItem()) {
					controlador.cambiarImagen(scComboCines, scPanelImg, scLblImg);
				}
			}
		});
		scComboCines.setBounds(53, 116, 230, 27);
		scPanel.add(scComboCines);

		JButton scBtnFin = new JButton("Finalizar Sesión");
		scBtnFin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				double subtotal = controlador.calcularSubtotal(rcTable, proyeccionesSeleccionadas);
				double descuento = controlador.calcularDescuento(proyeccionesSeleccionadas, subtotal);
				double total = controlador.calcularTotal(subtotal, descuento);

				controlador.finalizarSesion(proyeccionesSeleccionadas, scPanel, rcPanel, frame, rcTableModel,
						rcLblSubtotal1, rcLblDescuento1, rcLblTotal1, subtotal, descuento, total);
			}
		});
		scBtnFin.setBounds(869, 6, 125, 27);
		scPanel.add(scBtnFin);

		JButton scBtnConfirmar = new JButton("Confirmar");
		scBtnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scPanel.setVisible(false);
				cineSeleccionado = controlador.determinarCineSeleccionado(scComboCines, cines);
				peliculas = controlador.guardarArrayListPeliculas(cineSeleccionado);
				controlador.anadirPeliculasAlCombo(spComboTitulos, peliculas);
				spPanel.setVisible(true);
			}
		});
		scBtnConfirmar.setBounds(305, 116, 100, 27);
		scPanel.add(scBtnConfirmar);

	}

	public void crearPanelSeleccionPelicula() {

		spPanel = new JPanel();
		spPanel.setBounds(0, 0, 1000, 672);
		frame.getContentPane().add(spPanel);
		spPanel.setLayout(null);

		JLabel spLblCabecera = new JLabel("Seleccione una película:");
		spLblCabecera.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		spLblCabecera.setForeground(new Color(72, 138, 246));
		spLblCabecera.setBounds(66, 43, 446, 56);
		spPanel.add(spLblCabecera);

		JPanel spPanelImg = new JPanel();
		spPanelImg.setBackground(new Color(254, 251, 0));
		spPanelImg.setBounds(551, 20, 426, 613);
		spPanel.add(spPanelImg);
		spPanelImg.setLayout(new BorderLayout(0, 0));

		JLabel spLblImg = new JLabel("");
		spPanelImg.add(spLblImg, BorderLayout.CENTER);

		JPanel spPanelInfo = new JPanel();
		spPanelInfo.setBounds(74, 267, 411, 348);
		spPanel.add(spPanelInfo);
		spPanelInfo.setLayout(null);

		JLabel spLblTitulo = new JLabel("Titulo: ");
		spLblTitulo.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		spLblTitulo.setBounds(6, 25, 77, 16);
		spPanelInfo.add(spLblTitulo);

		JLabel spLblGenero = new JLabel("Género:");
		spLblGenero.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		spLblGenero.setBounds(6, 68, 77, 16);
		spPanelInfo.add(spLblGenero);

		JLabel spLblDuracion = new JLabel("Duración:");
		spLblDuracion.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		spLblDuracion.setBounds(6, 115, 80, 16);
		spPanelInfo.add(spLblDuracion);

		JLabel spLblTitulo1 = new JLabel("");
		spLblTitulo1.setBounds(103, 25, 284, 16);
		spPanelInfo.add(spLblTitulo1);

		JLabel spLblGenero1 = new JLabel("");
		spLblGenero1.setBounds(103, 68, 284, 16);
		spPanelInfo.add(spLblGenero1);

		JLabel spLblDuracion1 = new JLabel("");
		spLblDuracion1.setBounds(103, 115, 284, 16);
		spPanelInfo.add(spLblDuracion1);

		spComboTitulos = new JComboBox<String>();
		spComboTitulos.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (null != spComboTitulos.getSelectedItem()) {
					controlador.cambiarCaratulaPorSeleccion(peliculas, spComboTitulos, spPanelImg, spLblImg);
					controlador.cambiarInformacionPorSeleccion(peliculas, spComboTitulos, spLblTitulo1, spLblGenero1,
							spLblDuracion1);
				}
			}
		});
		spComboTitulos.setBounds(66, 162, 237, 27);
		spPanel.add(spComboTitulos);

		JButton spBtnContinuar = new JButton("Continuar");
		spBtnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				peliSeleccionada = controlador.determinarPeliSeleccionada(spComboTitulos, peliculas);
				controlador.anadirFechasAlCombo(sprComboFecha, cineSeleccionado, peliSeleccionada);
				spPanel.setVisible(false);
				sprPanel.setVisible(true);
			}
		});
		spBtnContinuar.setBounds(359, 161, 117, 29);
		spPanel.add(spBtnContinuar);

		JButton spBtnAtras = new JButton("Atrás");
		spBtnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				spPanel.setVisible(false);
				scPanel.setVisible(true);
			}
		});
		spBtnAtras.setBounds(6, 6, 68, 29);
		spPanel.add(spBtnAtras);
	}

	private void crearPanelSeleccionProyeccion() {
		sprPanel = new JPanel();
		sprPanel.setBounds(0, 0, 1000, 672);
		frame.getContentPane().add(sprPanel);

		JButton sprBtnAtras = new JButton("Atrás");
		sprBtnAtras.setBounds(6, 6, 70, 29);
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
		sprLblCabecera.setForeground(new Color(72, 138, 246));
		sprPanel.add(sprLblCabecera);

		JLabel sprLblCabeceraSesion = new JLabel("Seleccione una sesión:");
		sprLblCabeceraSesion.setBounds(66, 180, 370, 56);
		sprLblCabeceraSesion.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		sprLblCabeceraSesion.setForeground(new Color(72, 138, 246));
		sprPanel.add(sprLblCabeceraSesion);

		JComboBox<String> sprComboSesion = new JComboBox<String>();
		sprComboSesion.setBounds(66, 258, 225, 27);
		sprComboSesion.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (null != sprComboSesion.getSelectedItem()) {
					controlador.anadirInformacionSesion(sprComboSesion, proyecciones,sprLblSala1, sprLblPrecio1);
				}
			}
		});
		sprPanel.add(sprComboSesion);

		sprComboFecha = new JComboBox<String>();
		sprComboFecha.setBounds(66, 117, 225, 27);
		sprComboFecha.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (null != sprComboFecha.getSelectedItem()) {
					proyecciones = controlador.guardarArrayListProyecciones(sprComboFecha, cineSeleccionado,
							peliSeleccionada);
					controlador.anadirSesionesAlCombo(sprComboSesion, proyecciones);
				}
			}
		});
		sprPanel.add(sprComboFecha);

		JButton sprBtnSesion = new JButton("Seleccionar");
		sprBtnSesion.setBounds(303, 257, 117, 29);
		sprBtnSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				proyeccionSeleccionada = controlador.guardarProyeccionSeleccionada(sprComboSesion, proyecciones);
				controlador.guardarSeleccionProyeccion(proyeccionSeleccionada, bPanel, sprPanel,
						proyeccionesSeleccionadas);
			}
		});
		sprPanel.add(sprBtnSesion);

		controlador.anadirFechasAlCombo(sprComboFecha, cineSeleccionado, peliSeleccionada);
		
		sprLblSala1 = new JLabel("");
		sprLblSala1.setBounds(150, 349, 147, 29);
		sprLblSala1.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		sprLblSala1.setForeground(Color.WHITE);
		sprPanel.add(sprLblSala1);
		
		sprLblPrecio1 = new JLabel("");
		sprLblPrecio1.setBounds(150, 435, 147, 29);
		sprLblPrecio1.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		sprLblPrecio1.setForeground(Color.WHITE);
		sprPanel.add(sprLblPrecio1);
		
		JPanel sprPanelImgSala = new JPanel();
		sprPanelImgSala.setBounds(66, 335, 56, 56);
		sprPanel.add(sprPanelImgSala);
		sprPanelImgSala.setLayout(new BorderLayout(0, 0));
		sprPanelImgSala.setOpaque(false);
		
		JLabel sprLblImgSala = new JLabel("");
		sprPanelImgSala.add(sprLblImgSala, BorderLayout.CENTER);
		
		JPanel sprPanelImgPrecio = new JPanel();
		sprPanelImgPrecio.setBounds(66, 420, 56, 56);
		sprPanel.add(sprPanelImgPrecio);
		sprPanelImgPrecio.setLayout(new BorderLayout(0, 0));
		sprPanelImgPrecio.setOpaque(false);
		
		JLabel sprLblImgPrecio = new JLabel("");
		sprPanelImgPrecio.add(sprLblImgPrecio, BorderLayout.CENTER);
		
		controlador.anadirImagen(sprPanelImgSala, sprLblImgSala, "img/sala.png");
		controlador.anadirImagen(sprPanelImgPrecio, sprLblImgPrecio, "img/dinero.png");

		crearPanelFondoProyeccion();
		
	}

	private void crearPanelFondoProyeccion() {

		JPanel sprPanelImg = new JPanel();
		sprPanelImg.setBounds(0, 0, 1000, 672);
		sprPanel.add(sprPanelImg);
		sprPanelImg.setLayout(new BorderLayout(0, 0));

		JLabel sprLblImg = new JLabel("");
		sprPanelImg.add(sprLblImg, BorderLayout.CENTER);

		controlador.anadirImagen(sprPanelImg, sprLblImg, "img/sFecha.jpg");
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
		rcScrollPane.setBorder(new LineBorder(new Color(72, 138, 246), 2));
		rcScrollPane.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		rcScrollPane.getViewport().setBackground(Color.WHITE);
		rcScrollPane.setBounds(70, 150, 850, 250);
		rcPanel.add(rcScrollPane);

		rcTable = new JTable();
		rcTable.setRowHeight(25);
		rcTable.setBackground(Color.WHITE);
		rcTable.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		rcScrollPane.setViewportView(rcTable);

		Object[] rcColumnas = { "Película", "Fecha", "Sesión", "Sala", "Cine", "Precio (€)" };

		JTableHeader rcTableHeader = rcTable.getTableHeader();
		rcTableHeader.setBackground(Color.WHITE);
		rcTableHeader.setForeground(Color.BLACK);
		rcTableHeader.setFont(new Font("Trebuchet MS", Font.BOLD, 14));

		rcTableModel = new DefaultTableModel() {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		rcTableModel.setColumnIdentifiers(rcColumnas);
		rcTable.setModel(rcTableModel);

		ajustarColumnas(rcTable);

		JLabel rcLblSubtotal = new JLabel("Subtotal (€):");
		rcLblSubtotal.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		rcLblSubtotal.setBounds(688, 422, 110, 29);
		rcPanel.add(rcLblSubtotal);

		rcLblSubtotal1 = new JLabel("");
		rcLblSubtotal1.setBounds(825, 422, 95, 29);
		rcPanel.add(rcLblSubtotal1);

		JLabel rcLblDescuento = new JLabel("Descuento (€):");
		rcLblDescuento.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		rcLblDescuento.setBounds(688, 463, 110, 29);
		rcPanel.add(rcLblDescuento);

		rcLblDescuento1 = new JLabel("");
		rcLblDescuento1.setBounds(825, 463, 95, 29);
		rcPanel.add(rcLblDescuento1);

		JLabel rcLblTotal = new JLabel("Total (€):");
		rcLblTotal.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		rcLblTotal.setBounds(688, 504, 110, 29);
		rcPanel.add(rcLblTotal);

		rcLblTotal1 = new JLabel("");
		rcLblTotal1.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		rcLblTotal1.setBounds(825, 504, 95, 29);
		rcPanel.add(rcLblTotal1);

		JButton rcBtnCancelar = new JButton("Cancelar");
		rcBtnCancelar.setBounds(66, 558, 117, 29);
		rcPanel.add(rcBtnCancelar);

		JButton rcBtnConfirmar = new JButton("Confirmar");
		rcBtnConfirmar.setBounds(218, 558, 117, 29);
		rcPanel.add(rcBtnConfirmar);
	}

	public void ajustarColumnas(JTable table) {
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		table.getColumnModel().getColumn(0).setMinWidth(200);
		table.getColumnModel().getColumn(1).setMinWidth(100);
		table.getColumnModel().getColumn(2).setMinWidth(100);
		table.getColumnModel().getColumn(3).setMinWidth(100);
		table.getColumnModel().getColumn(4).setMinWidth(200);
	}
}
