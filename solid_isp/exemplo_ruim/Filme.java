/**
 * EXEMPLO RUIM - Filme é OBRIGADO a implementar listarEpisodios() e
 * getTemporadas(), que não fazem sentido. Para não quebrar, é forçado a
 * lançar UnsupportedOperationException - sinal claro de violação de ISP.
 */
package exemplo_ruim;

public class Filme implements MidiaService {

    @Override
    public void reproduzir() {
        System.out.println("Reproduzindo filme...");
    }

    @Override
    public void listarEpisodios() {
        throw new UnsupportedOperationException("Filme não tem episódios!");
    }

    @Override
    public int getTemporadas() {
        throw new UnsupportedOperationException("Filme não tem temporadas!");
    }

    @Override
    public int getDuracaoMinutos() {
        return 128;
    }

    @Override
    public void baixarParaOffline() {
        throw new UnsupportedOperationException("Download não disponível.");
    }
}