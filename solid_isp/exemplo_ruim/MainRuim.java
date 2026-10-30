package exemplo_ruim;
public class MainRuim {
    public static void main(String[] args) {
        MidiaService filme = new Filme();
        MidiaService serie = new Serie();

        filme.reproduzir();
        serie.reproduzir();

        System.out.println("\n--- Tentando usar métodos que NÃO fazem sentido ---");
        try {
            filme.listarEpisodios();   // Filme NÃO TEM episódios
        } catch (UnsupportedOperationException e) {
            System.out.println("Erro em Filme: " + e.getMessage());
        }
        try {
            serie.getDuracaoMinutos(); // Série NÃO TEM duração fixa
        } catch (UnsupportedOperationException e) {
            System.out.println("Erro em Serie: " + e.getMessage());
        }

        System.out.println("\n>> Interface mal projetada força classes a");
        System.out.println(">> implementar métodos inúteis. Violacao de ISP!");
    }
}