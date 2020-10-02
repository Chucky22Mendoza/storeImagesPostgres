/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagenes;

import imagenes.ConnectionDatabase;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.imageio.ImageIO;

/**
 *
 * @author yimibus
 */
public class Imagenes {
    
    public static byte[] extractBytes (String ImageName) throws IOException {
        // open image
        File imgPath = new File(ImageName);
        BufferedImage bufferedImage = ImageIO.read(imgPath);

        // get DataBufferBytes from Raster
        WritableRaster raster = bufferedImage.getRaster();
        DataBufferByte data = (DataBufferByte) raster.getDataBuffer();

        return ( data.getData() );
    }
    
    public static void main(String[] args) throws IOException {
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

    }
    
}
