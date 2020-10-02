# Code to save images bytea to database postgresql

```java

	String path = "/home/yimibus/NetBeansProjects/Imagenes/src/imagenes/3.jpg";
        FileInputStream fis = null;

        ConnectionDatabase connection = new ConnectionDatabase();
        connection.createConnection();
        Connection conn = connection.getConnection();
        
        try {
            File file = new File(path);
            fis = new FileInputStream(file);
            
            String sql = "UPDATE usuario SET imagen = ? WHERE id_usuario = '79118157-f381-11ea-8a13-7a791911bcec'::UUID";

            PreparedStatement preparedStatement = conn.prepareStatement(sql); //Evitar inyeccion SQL
            preparedStatement.setBinaryStream(1, fis,(int) file.length());

            int result = preparedStatement.executeUpdate();
            
            System.out.println(result);
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        connection.closeConnection();

```
