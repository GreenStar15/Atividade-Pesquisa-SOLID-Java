/**
 * EXEMPLO BOM - Serie implementa APENAS as interfaces que fazem sentido:
 * Reproduzivel + Seriado. NÃO é forçada a ter getDuracaoMinutos().
 */
package exemplo_bom;
public class Serie implements Reproduzivel, Seriado {

    @Override
    public void reproduzir() {
        System.out.println("Reproduzindo série...");
    }

    @Override
    public void listarEpisodios() {
        System.out.println("Listando episódios da série...");
    }

    @Override
    public int getTemporadas() {
        return 3;
    }
}