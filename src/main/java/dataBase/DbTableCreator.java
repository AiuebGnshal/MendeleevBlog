package dataBase;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DbTableCreator {

    public static void main(String[] args) {
        Connection c = null;
        Statement stmt = null;
        String sql = "";
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/postgres",
                            "postgres", "123");

            stmt = c.createStatement();

            sql = "CREATE SCHEMA IF NOT EXISTS JAVA_TASK ";
            stmt.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS JAVA_TASK.USER "
                    + " ( "
                    + " ID                      SERIAL PRIMARY KEY     NOT NULL, "
                    + " FIRST_NAME              VARCHAR(32)            NOT NULL, "
                    + " LAST_NAME               VARCHAR(32),  "
                    + " LOGIN                   VARCHAR(10)            NOT NULL,  "
                    + " PASSWORD                VARCHAR(15)            NOT NULL  "
                    + " ) ";
            stmt.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS JAVA_TASK.POST "
                    + " ( "
                    + " ID                      SERIAL PRIMARY KEY     NOT NULL, "
                    + " DATE_TIME               TIMESTAMP              NOT NULL, "
                    + " USER_ID                 INT                    NOT NULL, "
                    + " TITLE                   VARCHAR(100)           NOT NULL, "
                    + " PUBLICATION             VARCHAR(500)           NOT NULL  "
                    + " ) ";
            stmt.executeUpdate(sql);

            sql = "ALTER TABLE JAVA_TASK.POST "
                    + " ADD CONSTRAINT FK_USER "
                    + " FOREIGN KEY (USER_ID) REFERENCES JAVA_TASK.USER (ID) MATCH FULL "
                    + " ";
            stmt.executeUpdate(sql);

            stmt.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+ e.getMessage());
            System.exit(0);
        }
        System.out.println("Tables are created successfully");
    }

}