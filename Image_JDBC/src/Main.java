import javax.xml.transform.Result;
import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        String url="jdbc:mysql://localhost:3306/mydatabase";
        String username="root";
        String password="Gaurav@123";
        String folder_path ="C:\\Users\\mannu\\OneDrive\\Desktop\\JBDC_Image\\";
        String query="SELECT image_data from image_table where image_id =(?)";

        //We Load the Drivers
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Loaded Successfully");

        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        // We Estsblished the Connection
        try{
            Connection con=DriverManager.getConnection(url,username,password);
            System.out.println("Connection Established Succesfully");
//
//            // Image Cobert into binary
//            FileInputStream fileInputStream=new FileInputStream(image_path);
//            byte[] imageData=new byte[fileInputStream.available()];
//            fileInputStream.read(imageData);
//            // Insert Data
//            PreparedStatement preparedStatement=con.prepareStatement(query);
//            preparedStatement.setBytes(1,imageData);
//            int Affecetd_row=preparedStatement.executeUpdate();
//
//            if(Affecetd_row>0){
//                System.out.println("Image Insertion Successfully !!!");
//            }else{
//                System.out.println("Insertion Failed !!!");
//            }

              PreparedStatement preparedStatement= con.prepareStatement(query);
              preparedStatement.setInt(1,1);
              ResultSet resultSet=preparedStatement.executeQuery();

              if(resultSet.next()){
                  byte[] image_data=resultSet.getBytes("image_data");
                  String image_path=folder_path+"extractedImage.jpg";
                  OutputStream outputStream=new FileOutputStream(image_path);
                  outputStream.write(image_data);
              }else{
                  System.out.println("Image not Found");
              }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

    }
}