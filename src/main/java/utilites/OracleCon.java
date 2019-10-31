package utilites;


import java.sql.*;
import java.util.Objects;

class OracleCon {
    private static Connection connect(){
        try {
            return DriverManager.getConnection("jdbc:oracle:thin:@STSrvURSKBDBOra12:1521:OBALFATEST", "OB", "ob");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String args[]) {
        try {
            String query = "select * \n" +
                    "      from OBPOTCLIENTTEMPINPUTPARAM pr\n" +
                    "     inner join OBPOTCLIENTTEMP c \n" +
                    "        on pr.POTCLIENTTEMPID = c.POTCLIENTTEMPID    \n" +
                    "    where c.UNP = 193313655\n" +
                    "       and pr.NUMPARAM = 23";
            Statement stmt = Objects.requireNonNull(connect()).createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String unpName = rs.getString("UNP");
                String nameParam = rs.getString("NAMEPARAM");
                String valueParam = rs.getString("VALUEPARAM");
                System.out.println(unpName + "\n" + nameParam + "\n" + valueParam);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void _main(String args[]) {
        try {
            String query = "select * \n" +
                    "      from OBPOTCLIENTTEMPINPUTPARAM pr\n" +
                    "     inner join OBPOTCLIENTTEMP c \n" +
                    "        on pr.POTCLIENTTEMPID = c.POTCLIENTTEMPID    \n" +
                    "    where c.POTCLIENTTEMPID = 1\n"; // +
//                    "       and pr.NUMPARAM = 23";
            Statement stmt = Objects.requireNonNull(connect()).createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                //String unpName = rs.getString("UNP");
                String nameParam = rs.getString("NAMEPARAM");
                String valueParam = rs.getString("VALUEPARAM");
                System.out.println( "\n" + nameParam + "\n" + valueParam);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}