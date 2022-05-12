package first;
import java.util.Scanner;

class GeneratePANAndAadharNumber {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String PANNo = null;
        String aadharNo = null;
        String personName = scan.nextLine();
        Integer age = scan.nextInt();
        Long mobileNo = scan.nextLong();
        Person person = new Person(personName, age, mobileNo);

        if (validate(person)) {
            PANNo = generatePANNumber(person);
            aadharNo = generateAadharNumber(person);
            System.out.println("Success: Your PAN Number is:" + PANNo + " and Aadhar Number is:" + aadharNo + ".");

        } else {
            System.out.println("Error: Your PAN and Aadhar Number can't be generated.");
        }

        scan.close();

    }

    public static Boolean validate(Person person) {
        
        
        return null;

    }

    public static String generatePANNumber(Person person) {
        return null;
    }

    public static String generateAadharNumber(Person person) {
        return null;

    }

}

class Person {
    private String name;
    private Integer age;
    private Long mobileNo;

    public Person(String name, Integer age, Long mobileNo) {
        super();
        this.name = name;
        this.age = age;
        this.mobileNo = mobileNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(Long mobileNo) {
        this.mobileNo = mobileNo;
    }

}