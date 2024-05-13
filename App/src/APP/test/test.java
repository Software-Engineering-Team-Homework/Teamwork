package APP.test;

public class test {
    public static void main(String[] args) {
        String n1="123";
        String n2="123";
        UserServices userServices=new UserServices();
        userServices.registerUser(n1,n2);
        userServices.registerUser(n1,n2);
        userServices.registerUser(n1,n2);
//        System.out.println(userServices);
        System.out.println(userServices.validateLogin("123","123"));
    }
}
