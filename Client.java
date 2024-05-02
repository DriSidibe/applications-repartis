import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Client {

    public static boolean test_server_availability(String host, String port){
        try {
            Registry registry = LocateRegistry.getRegistry(host, Integer.parseInt(port));
            CalculService calculService = (CalculService)
            registry.lookup("CalculService");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {

        String[] host = {"localhost", "localhost", "localhost"};
        String[] port = {"1099", "1098", "1097"};
        Map<Integer, String> availableServers = new HashMap<>();
        List<Registry> registries = new ArrayList<>();
        Map<Registry, CalculService> serversInfo = new HashMap<>();
        int serverCount = 0;

        
        for (int i = 0; i < host.length; i++) {
            if (test_server_availability(host[i], port[i])) {
                availableServers.put(Integer.parseInt(port[i]), host[i]);
            }
        }

        try {
            // affichage agreables des infos
            System.out.println("----------------------\n  --- Client RMI ---\n----------------------\n\n");
            System.out.println("--------------------------\n# Connexion aux serveurs #\n--------------------------\n");
            for (Map.Entry<Integer, String> availableServer : availableServers.entrySet()) {
                if (registries.add(LocateRegistry.getRegistry(availableServer.getValue(), availableServer.getKey()))) {
                    serverCount++;
                }
            }
            System.out.println("Nombre de serveurs disponibles : " + serverCount + "\n\n");





            
            // Execution des operations
            System.out.println("--------------------------\n# Execution des operations #\n--------------------------\n");

            for (Registry registry : registries) {
                CalculService calculService = (CalculService) registry.lookup("CalculService");
                serversInfo.put(registry, calculService);
            }

            Thread t = new Thread(new Runnable() {
                public void run() {
                    try {
                        for (Map.Entry<Registry, CalculService> serverInfo : serversInfo.entrySet()) {
                            System.out.println(serverInfo.getValue().printMessage(serverInfo.getKey().toString()));
                        }
                    } catch (Exception e) {
                        System.err.println("Erreur sur le serveur : " + e.toString());
                        e.printStackTrace();
                    }
                }
            });
            t.start();

            for (Map.Entry<Registry, CalculService> serverInfo : serversInfo.entrySet()) {
                serverInfo.getKey().unbind("CalculService");
            }
            
            // ------------------------


        } catch (Exception e) {
            System.err.println("Erreur sur le client : " + e.toString());
            e.printStackTrace();
        }
        
    }
}