import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Serveur {
    public static void main(String[] args) {

        int port = Integer.parseInt(args[0]);
        
        try {
            CalculService calculService = new CalculServiceImpl();
            Registry registry = LocateRegistry.createRegistry(port);
            registry.bind("CalculService", calculService);
            System.out.println("Serveur prêt sur le port " + port + ".");
        } catch (Exception e) {
            System.err.println("Erreur sur le serveur : " + e.toString());
            e.printStackTrace();
        }
    }
}