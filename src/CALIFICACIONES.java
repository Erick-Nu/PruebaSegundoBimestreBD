import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class CALIFICACIONES {
    private JButton registrar;
    private JTextField name;
    private JTextField apellido;
    private JTextField cedula;
    public JPanel VentanaRegistro;
    private JLabel messegExit;
    private JLabel quimica;
    private JLabel matematica;
    private JLabel fisica;
    private JLabel lenguaje;
    private JLabel programacion;
    private JTextField mtQuimica;
    private JTextField mtMatematica;
    private JTextField mtFisica;
    private JTextField mtLenguaje;
    private JTextField mtProgramacion;


    public CALIFICACIONES() {
        registrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/calificaciones2025"; // jdbc = java database conector
                String username = "root";
                String password = "1234";

                Connection conn = null;
                PreparedStatement ps = null;
                try {
                    /* Conexion a la base de datos */
                    conn = DriverManager.getConnection(url, username, password);
                    System.out.println("Conexión exitosa a la base de datos");

                    /* Consulta sql */

                    String sql = "INSERT INTO estudiantes (cedula, nombre, apellido, matematica, quimica, fisica, lenguaje, programacion, promedio) VALUES (?,?,?,?,?,?,?,?,?)";

                    /* Obetner los datos de los textfeld*/
                    String nombre = name.getText();
                    String lastname = apellido.getText();
                    String dni = cedula.getText();
                    int quimica = Integer.parseInt(mtQuimica.getText());
                    int matematica = Integer.parseInt(mtMatematica.getText());
                    int fisica = Integer.parseInt(mtFisica.getText());
                    int lenguaje = Integer.parseInt(mtLenguaje.getText());
                    int programacion = Integer.parseInt(mtProgramacion.getText());

                     /* Calculo promedio */

                     double promedio = (double) ( quimica + matematica + fisica + lenguaje + programacion)/5;

                    ps = conn.prepareStatement(sql);
                    ps.setString(1, dni);
                    ps.setString(2, nombre);
                    ps.setString(3, lastname);
                    ps.setInt(4, quimica);
                    ps.setInt(5, matematica);
                    ps.setInt(6, fisica);
                    ps.setInt(7, lenguaje);
                    ps.setInt(8, programacion);
                    ps.setDouble(9, promedio);
                    int filasinset = ps.executeUpdate();
                    System.out.println("[FILAS INSERTADAS]: " + filasinset);

                    messegExit.setText("REGISTRO GUARDADO CON EXITO");



                } catch (SQLException ex) {
                    System.err.println("Error de conexión");
                } finally {
                    /* Aseguramos que la conexión se cierra al finalizar */
                    if (conn != null) {
                        try {
                            conn.close();
                        } catch (SQLException e1) {
                            System.err.println("Error al cerrar la conexión");
                        }
                    }
                }
            }
        });
    }
}
