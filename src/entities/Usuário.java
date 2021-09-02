package entities;

import java.text.SimpleDateFormat;
import java.util.*;


public class Usuário implements Comparable<Usuário>{
    private String usuario;
    private Date data;

    private List<Usuário>lista = new ArrayList<>();

    private  static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");


    public Usuário() {
    }

    public Usuário(String usuario, Date data) {
        this.usuario = usuario;
        this.data = data;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }


    public List<Usuário> getLista() {

        return lista;
    }

    public void setLista(Usuário usu) {
        lista.add(usu);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuário)) return false;
        Usuário usuário = (Usuário) o;
        return getUsuario().equals(usuário.getUsuario());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsuario());
    }

    @Override
    public int compareTo(Usuário o) {
        return usuario.toUpperCase().compareTo(o.getUsuario().toUpperCase());
    }

    @Override
    public String toString() {
      StringBuilder s = new StringBuilder();
      for(Usuário x:lista){

          s.append(x.getUsuario());
          s.append(", ");
          s.append(sdf.format(x.getData().getTime()));
          s.append("\n");
      }


      return s.toString();
    }
}
