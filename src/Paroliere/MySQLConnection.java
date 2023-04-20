package Paroliere;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQLConnection extends JFrame {

    public Connection Connection() {
        Connection conn = null;
        try {
            // Connessione al database
            conn = DriverManager.getConnection("jdbc:mariadb://172.22.201.51:3306/Paroliere_GPO_Como","utentedb","Cobi_2022_$");
            System.out.println("Connessione al database avvenuta con successo.");
            return conn;
        } catch (SQLException e) {
            System.out.println("Errore di connessione al database: " + e.getMessage());

        }
        return conn;
    }

    public void Add(int sco, int nWords, int diffi, int size,String date){

        Connection conn = null;
        try {
            // Connessione al database
            conn = DriverManager.getConnection("jdbc:mariadb://172.22.201.51:3306/Paroliere_GPO_Como","utentedb","Cobi_2022_$");
            String query = "INSERT INTO Stats (Score, nWords, Difficulty, SizeTable , Date) VALUES (?, ?, ?, ?, ?)";

            // Prepara la query per l'esecuzione
                        PreparedStatement stmt = conn.prepareStatement(query);
            if(diffi == 1) {
                String s = "Easy";
                stmt.setString(3, s);
            }
            if(diffi == 2) {
                String s = "Normal";
                stmt.setString(3, s);
            }
            if(diffi == 3) {
                String s = "Hard";
                stmt.setString(3, s);
            }
            if(size == 5) {
                String s = "5x5";
                stmt.setString(4, s);
            }
            if(size == 10) {
                String s = "10x10";
                stmt.setString(4, s);
            }

            stmt.setInt(1, sco);
            stmt.setInt(2, nWords);
           stmt.setString(5, date);

            // Esegui la query di INSERT
            int rows = stmt.executeUpdate();

            // Chiudi la connessione al database
                        stmt.close();
                        conn.close();
        } catch (SQLException e) {
            System.out.println("Errore di connessione al database: " + e.getMessage());
        }

    }

    public void Get(DefaultTableModel model){
        // Crea un JTable utilizzando il modello dei dati
        JTable table = new JTable(model){
            public boolean isCellEditable(int row, int column) {
                // Impedisce la modifica di tutte le celle
                return false;
            }
        };
        table.setRowHeight(50);

// Crea un TableCellRenderer per personalizzare l'aspetto delle celle
        TableCellRenderer renderer = new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                ((JComponent) c).setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                ((JComponent) c).setBorder(BorderFactory.createLineBorder(Color.BLACK,4));
                c.setFont(new Font("Arial", Font.PLAIN, 12)); // Imposta la dimensione del font a 16
                ((JLabel) c).setHorizontalAlignment(SwingConstants.CENTER);
                return c;
            }
        };

        JTableHeader header = table.getTableHeader();
        header.getColumnModel().getColumn(0).setHeaderValue("ID");
        header.getColumnModel().getColumn(1).setHeaderValue("Score");
        header.getColumnModel().getColumn(2).setHeaderValue("nWords");
        header.getColumnModel().getColumn(3).setHeaderValue("Difficulty");
        header.getColumnModel().getColumn(4).setHeaderValue("SizeTable");
        header.getColumnModel().getColumn(5).setHeaderValue("Date");
        header.setBackground(Color.black);
        header.setForeground(Color.WHITE);
        header.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        header.setFont(new Font("Arial", Font.BOLD, 12));
        header.setPreferredSize(new Dimension(30, 50));


// Applica il renderer a tutte le colonne del JTable
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }

// Aggiungi un TableModelListener per ascoltare gli eventi di modifica del modello di dati
        model.addTableModelListener(new TableModelListener() {
            public void tableChanged(TableModelEvent e) {
                // Aggiorna tutte le celle del JTable con il renderer personalizzato
                for (int i = 0; i < table.getRowCount(); i++) {
                    for (int j = 0; j < table.getColumnCount(); j++) {
                        table.prepareRenderer(renderer, i, j);
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        JFrame frame = new JFrame("Dati della tabella stats");
        frame.add(scrollPane);
        frame.setSize(800, 450);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

    }

    public Connection conne() {
        Connection conn = null;
        try {
            // Connessione al database
            conn = DriverManager.getConnection("jdbc:mariadb://172.22.201.51:3306/Paroliere_GPO_Como","utentedb","Cobi_2022_$");
            System.out.println("Connessione al database avvenuta con successo.");
            return conn;
        } catch (SQLException e) {
            System.out.println("Errore di connessione al database: " + e.getMessage());
        }
        return conn;
    }

}
