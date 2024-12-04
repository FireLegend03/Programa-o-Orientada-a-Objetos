import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Fitness implements Serializable{
    private Map <String, Utilizador> utilizadores;

    public Fitness() {
        this.utilizadores = new HashMap<>();
    }

    public void addUtilizador(Utilizador u) {
        this.utilizadores.put(u.getEmail(), u);
    }

    public Utilizador getUtilizador(String email) {
        if(this.utilizadores.containsKey(email)){
            return this.utilizadores.get(email);
        }else{
            return null;
        }
    }

    public Map<String, Utilizador> getUtilizadores() {
        return this.utilizadores;
    }

    public void RemoveUtilizador(String email) {
        this.utilizadores.remove(email);
    }

    public void printUtilizadores() {
        for(Utilizador u : this.utilizadores.values()){
            System.out.println(u.toString());
        }
    }
    
    public void grava(String filename)  throws IOException {
        
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(filename));
            os.writeObject(this);
            os.close();    
    
    }

    public static Fitness carrega(String filename) throws IOException, ClassNotFoundException {
        Fitness f = null;
        ObjectInputStream is = new ObjectInputStream(new FileInputStream(filename));
        f = (Fitness) is.readObject();
        is.close();
        return f;
    }
    
}
