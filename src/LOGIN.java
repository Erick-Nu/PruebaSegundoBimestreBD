import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;


public class LOGIN {
    private JLabel Title;
    public JPanel VentanaLogin;
    private JTextField usuario;
    private JPasswordField contrasena;
    private JButton Ingresar;

    public LOGIN() {
        Ingresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                /* Conexion a la base de datos calificaciones2025 */

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

                    String sql = "SELECT * FROM usuarios WHERE username = ? AND password = ?";

                    /* Obetner los datos de los textfeld*/
                    String user = usuario.getText();
                    String pass = contrasena.getText();

                    ps = conn.prepareStatement(sql);
                    ps.setString(1, user);
                    ps.setString(2, pass);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        System.out.println("Inicio de sesión exitoso");

                        /* Cerramos la ventana  */
                        JFrame loginFrame = (JFrame) SwingUtilities.getWindowAncestor(VentanaLogin);
                        loginFrame.dispose();

                        /* Ventana 2: Table */
                        JFrame tableFrame = new JFrame("Regitro");
                        tableFrame.setContentPane(new CALIFICACIONES().VentanaRegistro);
                        tableFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        tableFrame.setSize(600, 600);
                        tableFrame.setPreferredSize(new Dimension(400, 600));
                        tableFrame.pack();
                        tableFrame.setVisible(true);

                    } else {
                        JOptionPane.showMessageDialog(VentanaLogin, "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
                    }


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
