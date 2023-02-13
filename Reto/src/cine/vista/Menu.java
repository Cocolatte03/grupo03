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
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import cine.bbdd.pojos.Cine;
import cine.bbdd.pojos.Pelicula;
import cine.bbdd.pojos.Proyeccion;
import cine.controlador.Controlador;


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

	public JComboBox<String> scComboCines;
	private JComboBox<String> sprComboFecha;

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

	private ArrayList<Proyeccion> proyeccionesSeleccionadas = null;

	private Cine cineSeleccionado = null;
	private Pelicula peliSeleccionada = null;
	private Proyeccion proyeccionSeleccionada = null;

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
				// controlador.anadirPeliculasAlCombo(spComboTitulos, peliculas);
				controlador.cargarTablaConPeliculas(spTableModel, peliculas);
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
		spBtnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				spPanel.setVisible(false);
				scPanel.setVisible(true);
			}
		});
		spBtnAtras.setBounds(6, 6, 68, 29);
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
		sprComboFecha.setBounds(66, 117, 225, 27);
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
		rcBtnCancelar.setBounds(66, 558, 117, 29);
		rcPanel.add(rcBtnCancelar);

		JButton rcBtnConfirmar = new JButton("Confirmar");
		rcBtnConfirmar.setBounds(218, 558, 117, 29);
		rcPanel.add(rcBtnConfirmar);

		JLabel rcLblError = new JLabel("Botones deshabilitados actualmente");
		rcLblError.setForeground(new Color(255, 38, 0));
		rcLblError.setHorizontalAlignment(SwingConstants.CENTER);
		rcLblError.setBounds(70, 517, 254, 29);
		rcPanel.add(rcLblError);

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
}
