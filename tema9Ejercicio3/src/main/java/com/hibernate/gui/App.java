package com.hibernate.gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.hibernate.dao.SerieDAO;
import com.hibernate.model.Serie;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class App {

	private JFrame frame;
	private JTable table;
	private JTextField textFieldId;
	private JTextField textFieldNombre;
	private JTextField textFieldTemporadas;
	private JTextField textFieldCapitulos;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	public App() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		Serie serie=new Serie();
		SerieDAO serieDAO=new SerieDAO();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 750, 380);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		DefaultTableModel modelTable = new DefaultTableModel();
		modelTable.addColumn("Id");
		modelTable.addColumn("Nombre");
		modelTable.addColumn("Temporadas");
		modelTable.addColumn("Capitulos");
		
		table = new JTable(modelTable);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
				TableModel model = table.getModel();
				textFieldId.setText(model.getValueAt(index, 0).toString());
				textFieldNombre.setText(model.getValueAt(index, 1).toString());
				textFieldTemporadas.setText(model.getValueAt(index, 2).toString());
				textFieldCapitulos.setText(model.getValueAt(index, 3).toString());
			}
		});
		table.setBounds(12, 12, 375, 200);
		frame.getContentPane().add(table);
		
		List<Serie> series = serieDAO.selectAllSeries();
		
		for (Serie s : series) {
			Object[] row=new Object[4];
			row[0]=s.getId();
			row[1]=s.getNombre();
			row[2]=s.getTemporada();
			row[3]=s.getCapitulo();
			modelTable.addRow(row);
		}
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(12, 12, 726, 200);
		frame.getContentPane().add(scrollPane);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(12, 226, 70, 15);
		frame.getContentPane().add(lblId);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(12, 253, 70, 15);
		frame.getContentPane().add(lblNombre);
		
		JLabel lblTemporadas = new JLabel("Temporadas:");
		lblTemporadas.setBounds(12, 280, 100, 15);
		frame.getContentPane().add(lblTemporadas);
		
		JLabel lblCapitulos = new JLabel("Capitulos:");
		lblCapitulos.setBounds(12, 307, 100, 15);
		frame.getContentPane().add(lblCapitulos);
		
		textFieldId = new JTextField();
		textFieldId.setBounds(100, 224, 200, 19);
		frame.getContentPane().add(textFieldId);
		textFieldId.setColumns(10);
		
		textFieldId.setEditable(false);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(100, 251, 200, 19);
		frame.getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldTemporadas = new JTextField();
		textFieldTemporadas.setBounds(130, 278, 170, 19);
		frame.getContentPane().add(textFieldTemporadas);
		textFieldTemporadas.setColumns(10);
		
		textFieldCapitulos = new JTextField();
		textFieldCapitulos.setBounds(130, 305, 170, 19);
		frame.getContentPane().add(textFieldCapitulos);
		textFieldCapitulos.setColumns(10);
		
		JButton botonGuardar = new JButton("Guardar");
		botonGuardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Serie serie=new Serie();
				SerieDAO serieDAO=new SerieDAO();
				
				String nombre=textFieldNombre.getText();
				
				String temporadasTexto=textFieldTemporadas.getText();
				int temporadas=Integer.parseInt(temporadasTexto);
				
				String capitulosTexto=textFieldCapitulos.getText();
				int capitulos=Integer.parseInt(capitulosTexto);
				
				serie=new Serie(nombre, temporadas, capitulos);
				
				serieDAO.insertSerie(serie);
				
				textFieldNombre.setText("");
				textFieldTemporadas.setText("");
				textFieldCapitulos.setText("");
				
				List<Serie> series = serieDAO.selectAllSeries();
				
				modelTable.setRowCount(0);
				
				for (Serie s : series) {
					Object[] row=new Object[4];
					row[0]=s.getId();
					row[1]=s.getNombre();
					row[2]=s.getTemporada();
					row[3]=s.getCapitulo();
					modelTable.addRow(row);
				}
			}
		});
		botonGuardar.setBounds(363, 224, 117, 25);
		frame.getContentPane().add(botonGuardar);
		
		JButton botonActualizar = new JButton("Actualizar");
		botonActualizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Serie serie=new Serie();
				SerieDAO serieDAO=new SerieDAO();
				
				String idTexto=textFieldId.getText();
				int id=Integer.parseInt(idTexto);
				
				String nombre=textFieldNombre.getText();
				
				String temporadasTexto=textFieldTemporadas.getText();
				int temporadas=Integer.parseInt(temporadasTexto);
				
				String capitulosTexto=textFieldCapitulos.getText();
				int capitulos=Integer.parseInt(capitulosTexto);
				
				serie=serieDAO.selectSerieById(id);
				
				serie.setNombre(nombre);
				serie.setTemporada(temporadas);
				serie.setCapitulo(capitulos);
				
				serieDAO.updateSerie(serie);
				
				textFieldNombre.setText("");
				textFieldTemporadas.setText("");
				textFieldCapitulos.setText("");
				
				List<Serie> series = serieDAO.selectAllSeries();
				
				modelTable.setRowCount(0);
				
				for (Serie s : series) {
					Object[] row=new Object[4];
					row[0]=s.getId();
					row[1]=s.getNombre();
					row[2]=s.getTemporada();
					row[3]=s.getCapitulo();
					modelTable.addRow(row);
				}
			}
		});
		botonActualizar.setBounds(492, 224, 117, 25);
		frame.getContentPane().add(botonActualizar);
		
		JButton botonBorrar = new JButton("Borrar");
		botonBorrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Serie serie=new Serie();
				SerieDAO serieDAO=new SerieDAO();
				
				String idTexto=textFieldId.getText();
				int id=Integer.parseInt(idTexto);
				
				String nombre=textFieldNombre.getText();
				
				String temporadasTexto=textFieldTemporadas.getText();
				int temporadas=Integer.parseInt(temporadasTexto);
				
				String capitulosTexto=textFieldCapitulos.getText();
				int capitulos=Integer.parseInt(capitulosTexto);
				
				serieDAO.deleteSerie(id);
				
				textFieldNombre.setText("");
				textFieldTemporadas.setText("");
				textFieldCapitulos.setText("");
				
				List<Serie> series = serieDAO.selectAllSeries();
				
				modelTable.setRowCount(0);
				
				for (Serie s : series) {
					Object[] row=new Object[4];
					row[0]=s.getId();
					row[1]=s.getNombre();
					row[2]=s.getTemporada();
					row[3]=s.getCapitulo();
					modelTable.addRow(row);
				}
			}
		});
		botonBorrar.setBounds(621, 224, 117, 25);
		frame.getContentPane().add(botonBorrar);
	}
}