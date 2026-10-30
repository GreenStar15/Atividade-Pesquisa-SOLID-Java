/**
 * EXEMPLO BOM - Filme implementa APENAS as interfaces que fazem sentido:
 * Reproduzivel + FilmeLonga. NÃO é forçado a ter listarEpisodios().
 */
package exemplo_bom;
public class Filme implements Reproduzivel, FilmeLonga {

    @Override
    public void reproduzir() {
        System.out.println("Reproduzindo filme...");
    }

    @Override
    public int getDuracaoMinutos() {
        return 128;
    }
}