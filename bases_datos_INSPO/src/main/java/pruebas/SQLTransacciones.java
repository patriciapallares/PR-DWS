package pruebas;

import java.sql.*;

public class SQLTransacciones {
    public static void main(String[] args) throws SQLException {
        String usuario = "postgres";
        String contra = "postgres";
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mi-base", usuario, contra);
        try {
            con.setAutoCommit(false);
            Statement st = con.createStatement();
            System.out.println("ANTES DE DELETE");
            st.executeUpdate("INSERT INTO actores VALUES (2, 'Bea', 16)");
            st.executeUpdate("UPDATE actores SET edad = 24 WHERE ID = 1");
            con.commit();
            ResultSet res = st.executeQuery("SELECT * FROM actores");
            while (res.next()) {
                Integer edad = res.getInt("edad");
                String nom = res.getString("nombre");
                System.out.println(nom + ", " + edad);
            }
            st.executeUpdate("DELETE FROM actores WHERE id = 2");
            System.out.println("DESPUES DE DELETE");
            ResultSet res2 = st.executeQuery("SELECT * FROM actores");
            while (res2.next()) {
                Integer edad = res2.getInt("edad");
                String nom = res2.getString("nombre");
                System.out.println(nom + ", " + edad);
            }
        } catch (SQLException ex) {
            if (con!=null) {
                try {
                    con.rollback();
                } catch (SQLException e) {
                    System.out.println(e.toString());
                }
            }
        }
    }
}
