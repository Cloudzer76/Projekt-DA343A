package Client;

public class ClientStarter {

    public static void main (String [] args) {

        Appliances laptop = new Appliances("Laptop" , 30);
        Appliances ledlight = new Appliances("LED light bulb", 15);
        Appliances lightBulb = new Appliances("Incandescent Bulb", 40);
        Appliances deskcomp = new Appliances("Desktop computer", 200);
        Appliances tv = new Appliances("TV (LED)" , 150);
        Appliances refrigerator = new Appliances("Refrigerator", 250);
        Appliances microwave= new Appliances("Microwave Oven", 1000);


        ClientGUI gui = new ClientGUI(laptop);







    }

}
