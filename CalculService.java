import java.rmi.Remote;
import java.rmi.RemoteException;


public interface CalculService extends Remote {
    
    // Math functions
    double abs(double a) throws RemoteException;
    float abs(float a) throws RemoteException;
    int abs(int a) throws RemoteException;
    long abs(long a) throws RemoteException;

    String printMessage(String message) throws RemoteException;
}