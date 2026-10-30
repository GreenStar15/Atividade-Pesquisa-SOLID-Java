/**
 * EXEMPLO RUIM - Serie é OBRIGADA a implementar getDuracaoMinutos() e
 * baixarParaOffline(), que não fazem sentido. Mesma violação.
 */
package exemplo_ruim;
public class Serie implements MidiaService {

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

    @Override
    public int getDuracaoMinutos() {
        throw new UnsupportedOperationException("Série não tem duração fixa!");
    }

    @Override
    public void baixarParaOffline() {
        throw new UnsupportedOperationException("Download não disponível.");
    }
}