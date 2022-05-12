package first;

import java.time.LocalDateTime;
import java.time.Period;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;



class Product implements Serializable {

                private String productName;

                private transient String productId;

                public String brandName;

                public static int brandId = 10001;

 

                public Product(String productName, String productId) {

                                this.productName = productName;

                                this.productId = productId;

                                brandName = "Infy";

                                brandId++;

                }

 

                @Override

                public String toString() {

                                return "Account [productName =" + productName + ", productId =" + productId + ", brandName=" + brandName + ", brandId= "

                                                                + brandId + "]";

                }

}



class Base {

    public static void show()

{System.out.println("Base::show() called");}

}

class Derived extends Base {

    public static void show() //line 1

{System.out.println("Derived::show() called");}

}

//private interface InterfaceDemo {//only public /default and abstract are permitted
//
//     default void defaultMethod1() {//only public/private/default and abstract/default/static methods are allowed	
//     public static default void defaultMethod1() {//only one of abstract or default or static is allowed not the combo of them are allowed
//
//}
//}

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Integer val1 = 100;
        Integer val2 = 2;
        Integer val3 = val1%=3 + ++val1 + val2;//1+2+2=5
        System.out.println(val3%=7);
        LocalDateTime date1=LocalDateTime.now();//line 1

        System.out.println(date1.minus(Period.ofDays(-0)));//line 2
        Product product = new Product("Laptop", "20345678");

        ObjectOutputStream objOutStream = new ObjectOutputStream(new FileOutputStream("product.dat"));

        objOutStream.writeObject(product);

        Product product2 = new Product("Laptop", "20345678");

        ObjectInputStream objInStream = new ObjectInputStream(new FileInputStream("product.dat"));

        Product prodRead = (Product) objInStream.readObject();

        System.out.println(prodRead);

        objOutStream.close();

        objInStream.close();






    Base b = new Derived();

    b.show();

    }}

