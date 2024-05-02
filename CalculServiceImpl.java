import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalculServiceImpl extends UnicastRemoteObject implements
CalculService {
    protected CalculServiceImpl() throws RemoteException {
        super();
    }

    public double abs(double a) throws RemoteException {
        return Math.abs(a);
    }

    public float abs(float a) throws RemoteException {
        return Math.abs(a);
    }

    public int abs(int a) throws RemoteException {
        return Math.abs(a);
    }

    public long abs(long a) throws RemoteException {
        return Math.abs(a);
    }

    public String printMessage(String message) throws RemoteException {
        return message;
    }
}
