package libreria;
import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Libreria {
    public static void main(String[] args) {
        String user="root";
        String password="12345";
        String user2="sebaspaniaguai"; //db4free.net
        String password2="2169383";//db4free.net
        String ced="0",newname="0",newautor="0",newyear="0",newcode="0",newarea="0";
        int idPrestado=0,newcantPrestado=0;
        int newcant=0,id=0;
        int op;
        Scanner teclado=new Scanner(System.in);
        try {
            System.out.println("Intentando conectar a la base de datos...");
            Class.forName("com.mysql.jdbc.Driver");
            //Connection con = DriverManager.getConnection("jdbc:mysql://localhost/libreria", user, password);
            Connection con = DriverManager.getConnection("jdbc:mysql://db4free.net/libreriasp", user2, password2);//Para db4free.net
            System.out.println("Conexión existosa.");
            Statement estado= con.createStatement();
            do{
                System.out.println("*****************MENU*****************");
                System.out.println("1.Ingresar Libro");
                System.out.println("2.Actualizar Libro");
                System.out.println("3.Eliminar Libro");
                System.out.println("4.Buscar Libro");
                System.out.println("5.Prestar Libro");
                System.out.println("6.Devolver Libro");
                System.out.println("7.Visualizar libros prestados");
                System.out.println("0.Salir");
                op=teclado.nextInt();
                teclado.nextLine();
                switch(op){
                    case 1:
                        System.out.println("Vas a ingresar un libro");
                        //Ingresar libro
                        System.out.println("Ingrese nombre");
                        String name=teclado.nextLine();
                        System.out.println("Ingrese autor");
                        String autor=teclado.nextLine();
                        System.out.println("Ingrese año de publicación");
                        String year=teclado.nextLine();
                        System.out.println("Ingrese código");
                        String code=teclado.nextLine();
                        System.out.println("Ingrese cantidad");
                        int cant=teclado.nextInt();
                        System.out.println("Ingrese area: \n1.Química\n2.Física\n3.Tecnología\n4.Cálculo\n5.Programación");
                        String area="0";
                        int op2=0;
                        do{
                        op2=teclado.nextInt();
                        switch(op2){
                            case 1: area="Química";
                                break;
                            case 2: area="Física";
                                break;
                            case 3: area="Tecnologia";
                                break;
                            case 4: area="Cálculo";
                                break;
                            case 5: area="Programación";
                                break;    
                            default: System.out.println("No es una opción válida por ahora. Ingrese otra: ");
                                break;
                        }}while(op2<1 || op2>5);
                        System.out.println("Esta seguro que desea continuar? [y/n]");
                        if(teclado.next().compareTo("y")==0){
                            estado.executeUpdate("INSERT INTO `libros` VALUES (NULL, '"+name+"', '"+autor+"','"+year+"', '"+code+"', '"+cant+"','"+area+"')");                            
                            System.out.println("Libro ingresado con éxito");
                            break;
                        }
                        else{ 
                            System.out.println("Ha decidido salir.");
                            break;
                        }    
                    case 2:
                        System.out.println("Esta a punto de cambiar los datos de un libro: ");                        
                        do{
                            System.out.println("ingrese el nombre del libro que desea modificar...");
                            name=teclado.nextLine();
                            ResultSet resultado=estado.executeQuery("SELECT * FROM `libros` WHERE `nombre` LIKE '"+name+"'");
                            if(resultado.next()){
                            int a=0;
                            resultado.beforeFirst();
                            while(resultado.next())
                                a=resultado.getInt("id");
                            System.out.println("1. Cambiar nombre del libro");
                            System.out.println("2. Cambiar nombre del autor");
                            System.out.println("3. Cambiar año de la publicación");
                            System.out.println("4. Cambiar cantidad de libros disponibles");
                            System.out.println("5. Cambiar código del libro");
                            System.out.println("6. Cambiar area del libro");
                            System.out.println("0. Salir");
                            op2=teclado.nextInt();
                            teclado.nextLine();
                            switch(op2){
                                case 0: break;
                                case 1: 
                                    System.out.println("Ingrese el nuevo nombre del libro:");
                                    newname=teclado.nextLine();
                                    System.out.println("Esta seguro de realizar esta accion? [y/n]");
                                    if(teclado.next().compareTo("y")==0){
                                        estado.executeUpdate("UPDATE `libreriasp`.`libros` SET `nombre` = '"+newname+"' WHERE `libros`.`id` = '"+a+"';");
                                        System.out.println("El libro ha sido actualizado con éxito, el nuevo nombre es: "+newname+". ");
                                        op2=0;
                                        break;
                                    }
                                    else break;
                                case 2:
                                    System.out.println("Ingrese el nuevo nombre del autor del libro:");
                                    newautor=teclado.nextLine();
                                    System.out.println("Esta seguro de realizar esta accion? [y/n]");
                                    if(teclado.next().compareTo("y")==0){
                                        estado.executeUpdate("UPDATE `libreriasp`.`libros` SET `autor` = '"+newautor+"' WHERE `libros`.`id` = '"+a+"';");
                                        System.out.println("El libro ha sido actualizado con éxito, el nuevo autor es: "+newautor+". ");
                                        op2=0;
                                        break;
                                    }
                                    else break;
                                case 3:
                                    System.out.println("Ingrese el nuevo año de publicacion del libro:");
                                    newyear=teclado.nextLine();
                                    System.out.println("Esta seguro de realizar esta accion? [y/n]");
                                    if(teclado.next().compareTo("y")==0){
                                        estado.executeUpdate("UPDATE `libreriasp`.`libros` SET `year` = '"+newyear+"' WHERE `libros`.`id` = '"+a+"';");
                                        System.out.println("El libro ha sido actualizado con éxito, el nuevo año de publicacion es: "+newyear+". ");
                                        op2=0;
                                        break;
                                    }
                                    else break;
                                case 4:
                                    System.out.println("Ingrese la nueva cantidad de libros disponibles:");
                                    newcant=teclado.nextInt();
                                    System.out.println("Esta seguro de realizar esta accion? [y/n]");
                                    if(teclado.next().compareTo("y")==0){
                                        estado.executeUpdate("UPDATE `libreriasp`.`libros` SET `cant` = '"+newcant+"' WHERE `libros`.`id` = '"+a+"';");
                                        System.out.println("La cantidad de libros ha sido actualizada con éxito, ahora hay: "+newcant+" disponibles.");
                                        op2=0;
                                        break;
                                    }
                                    else break;
                                case 5:
                                    System.out.println("Ingrese el nuevo codigo del libro:");
                                    newcode=teclado.nextLine();
                                    System.out.println("Esta seguro de realizar esta accion? [y/n]");
                                    if(teclado.next().compareTo("y")==0){
                                        estado.executeUpdate("UPDATE `libreriasp`.`libros` SET `code` = '"+newcode+"' WHERE `libros`.`id` = '"+a+"';");
                                        System.out.println("El libro ha sido actualizado con éxito, el nuevo codigo es: "+newcode+". ");
                                        op2=0;
                                        break;
                                    }
                                    else break;
                                case 6:
                                    newarea="0";
                                    do{
                                        System.out.println("Ingrese la nueva area del libro: \n1.Química\n2.Física\n3.Tecnología\n4.Cálculo\n5.Programación\n");
                                        op=teclado.nextInt();
                                        switch(op){
                                            case 1:
                                                newarea="Quimica";
                                                break;
                                            case 2:
                                                newarea="Fisica";
                                                break;
                                            case 3:
                                                newarea="Tecnologia";
                                                break;
                                            case 4:
                                                newarea="Calculo";
                                                break;
                                            case 5:
                                                newarea="Programacion";
                                                break;
                                            default:
                                                System.out.println("Ha ingresado una opcion invalida, presione cualquier tecla para reintentar");
                                                break;
                                        }
                                    }while(op<1 || op>5);
                                    System.out.println("Esta seguro de realizar esta accion? [y/n]");
                                    if(teclado.next().compareTo("y")==0){
                                        estado.executeUpdate("UPDATE `libreriasp`.`libros` SET `area` = '"+newarea+"' WHERE `libros`.`id` = '"+a+"';");
                                        System.out.println("El libro ha sido actualizado con éxito, la nueva area es: "+newarea+". ");
                                        op2=0;
                                        break;
                                    }
                                    else break;
                            }
                            }else{
                                System.out.println("El libro no aparece en la base de datos.");
                                break;
                            }
                        }while(op!=0);
                        break;
                    case 3:
                        System.out.println("Esta a punto de eliminar un libro de la base de datos...");
                        System.out.println("Ingrese el nombre del libro que desea eliminar: ");
                        name=teclado.nextLine();
                        ResultSet resultado=estado.executeQuery("SELECT * FROM `libros` WHERE `nombre` LIKE '%"+name+"%'");
                        System.out.println("Esta seguro de que desea realizar esta operacion? [y/n]");
                        if(teclado.next()=="y"){
                            if(resultado.next()){
                            resultado.beforeFirst();
                            estado.executeUpdate("DELETE FROM `libros` WHERE `nombre` LIKE '"+name+"'");
                            System.out.println("Libro eliminado con exito.");
                            break;
                            }
                            else{
                                System.out.println("El libro no aparece en la base de datos");
                                break;
                            }
                        }
                        else break;
                    case 4:
                        System.out.println("Por favor ingrese el nombre del libro: ");
                        name=teclado.nextLine();
                        resultado=estado.executeQuery("SELECT * FROM `libros` WHERE `nombre` LIKE '%"+name+"%'");
                        if(resultado.next()){
                            resultado.beforeFirst();
                        while(resultado.next()){
                            System.out.println(resultado.getString("id")+"\t"+resultado.getString("nombre")+"\t\t"+resultado.getString("autor")+"\t"+resultado.getString("year")+"\t"+resultado.getString("code")+"\t"+resultado.getString("cant")+"\t"+resultado.getString("area"));
                        }
                        }
                        else{
                            System.out.println("El libro no aparece en nuestra base de datos.");
                            break;
                        }
                        break;
                    case 5:
                        System.out.println("Para continuar por favor ingrese su numero de identificacion: ");
                        ced=teclado.nextLine();
                        System.out.println("Por favor ingrese el nombre del libro a prestar: ");
                        name=teclado.nextLine();
                        boolean flag=false;
                        resultado=estado.executeQuery("SELECT * FROM `libros` WHERE `nombre` LIKE '"+name+"'");    
                        if(resultado.next()){
                            resultado.beforeFirst();
                        while(resultado.next()){
                            if(resultado.getInt("cant")==0) {flag=true;break;}
                            newcant=resultado.getInt("cant")-1;
                            id=resultado.getInt("id");
                            newname=resultado.getString("nombre");
                            newautor=resultado.getString("autor");
                            newyear=resultado.getString("year");
                            newcode=resultado.getString("code");
                            newname=resultado.getString("nombre");
                            newarea=resultado.getString("area");
                        }
                        if(flag){System.out.println("No hay unidades disponibles");break;}
                        //Busco si el libro ya está prestado por ese usuario
                        ResultSet resultado2=estado.executeQuery("SELECT * FROM `librosPrestados` WHERE `nombre` LIKE '"+name+"' AND `cedula` LIKE '"+ced+"'");    
                        while(resultado2.next()){
                            idPrestado=resultado2.getInt("id");
                            newcantPrestado=resultado2.getInt("cant")+1;
                            flag=true;
                        }
                        System.out.println("Esta seguro que desea realizar esta operacion? [y/n]");
                        if(teclado.next().compareTo("y")==0){
                            estado.executeUpdate("UPDATE `libreriasp`.`libros` SET `cant` = '"+newcant+"' WHERE `libros`.`id` = '"+id+"';");
                            if(flag)
                                estado.executeUpdate("UPDATE `libreriasp`.`librosPrestados` SET `cant` = '"+newcantPrestado+"' WHERE `librosPrestados`.`id` = '"+idPrestado+"';");
                            else
                                estado.executeUpdate("INSERT INTO `librosPrestados` VALUES (NULL,'"+ced+"', '"+newname+"', '"+newautor+"','"+newyear+"', '"+newcode+"', '"+1+"','"+newarea+"')");                            
                        }
                        else break;
                        }
                        else{
                            System.out.println("El libro no aparece en nuestra base de datos.");
                            break;
                        }
                        break;
                    case 6:
                        System.out.println("Vas a Devolver un libro ");
                        System.out.println("Para continuar por favor ingrese su numero de identificacion: ");
                        ced=teclado.nextLine();                            System.out.println("El libro no aparece en nuestra base de datos.");

                        System.out.println("Por favor ingrese el nombre del libro a devolver: ");
                        name=teclado.nextLine();
                        resultado=estado.executeQuery("SELECT * FROM `librosPrestados` WHERE `nombre` LIKE '"+name+"' AND `cedula` LIKE '"+ced+"'");    
                        if(resultado.next()){
                            resultado.beforeFirst();
                        while(resultado.next()){
                            idPrestado=resultado.getInt("id");
                            newcantPrestado=resultado.getInt("cant")-1;
                        }
                        System.out.println("Esta seguro que desea realizar esta operacion? [y/n]");
                        if(teclado.next().compareTo("y")==0){
                            estado.executeUpdate("UPDATE `libreriasp`.`librosPrestados` SET `cant` = '"+newcantPrestado+"' WHERE `librosPrestados`.`id` = '"+idPrestado+"';");
                            resultado=estado.executeQuery("SELECT * FROM `libros` WHERE `nombre` LIKE '"+name+"'");    
                            while(resultado.next()){
                                newcant=resultado.getInt("cant")+1;
                            }
                            estado.executeUpdate("UPDATE `libreriasp`.`libros` SET `cant` = '"+newcant+"' WHERE `libros`.`id` = '"+id+"';");
                            estado.executeUpdate("DELETE FROM `librosPrestados` WHERE `cant` LIKE '0'");
                        }
                        else break;
                        }
                        else{
                            System.out.println("No tienes ese libro registrado en tu lista de prestamos");
                        }
                        break;
                    case 7:
                        System.out.println("Por favor ingrese su numero de cedula: ");
                        ced=teclado.nextLine();
                        flag=false;
                        resultado=estado.executeQuery("SELECT * FROM `librosPrestados` WHERE `cedula` LIKE '"+ced+"'");    
                        if(resultado.next()){
                            resultado.beforeFirst();
                            System.out.println("Listado de libros prestados asignados a su numero de identificacion:  ");
                        while(resultado.next()){
                            System.out.println(resultado.getString("nombre")+"\t\t"+resultado.getString("autor")+"\t"+resultado.getString("year")+"\t"+resultado.getString("code")+"\t"+resultado.getString("cant")+"\t"+resultado.getString("area"));
                        }
                        break;
                        }
                        else{
                            System.out.println("No se registran libros prestados para esa identificación.");
                            break;
                        }
                    case 0:
                        break;
                }
            } while(op!=0);
        } catch (SQLException ex) {
            System.out.println("Error de mysql");
        } catch (Exception e) {
            System.out.println("Se ha encontrado un error que es: "+e.getMessage());
        }
    }
    
}