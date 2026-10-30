package exemplo_bom;
public class MainBom {
    public static void main(String[] args) {
        Reproduzivel filme = new Filme();
        Reproduzivel serie = new Serie();
        Seriado serieCompleta = new Serie();
        FilmeLonga filmeCompleto = new Filme();

        System.out.println("--- Reproducao (interface comum a todos) ---");
        filme.reproduzir();
        serie.reproduzir();

        System.out.println("\n--- Metodos especificos de cada tipo ---");
        System.out.println("Temporadas da serie: " + serieCompleta.getTemporadas());
        serieCompleta.listarEpisodios();
        System.out.println("Duracao do filme: " + filmeCompleto.getDuracaoMinutos() + " min");

        System.out.println("\n>> Cada classe implementa apenas o que faz sentido.");
        System.out.println(">> Filme nao tem 'listarEpisodios' e Serie nao tem 'getDuracaoMinutos'.");
        System.out.println(">> Interface segregada: principio ISP respeitado!");
    }
}