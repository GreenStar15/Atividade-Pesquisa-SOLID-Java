/**
 * EXEMPLO RUIM - viola o Interface Segregation Principle.
 * Esta interface tenta cobrir TODAS as mídias do catálogo ao mesmo tempo,
 * obrigando qualquer classe que a implemente a fornecer TODOS os métodos,
 * mesmo aqueles que não fazem sentido para ela.
 */

package exemplo_ruim;
public interface MidiaService {

    void reproduzir();              // faz sentido para filme E série

    void listarEpisodios();         // SÓ faz sentido para série
    int getTemporadas();            // SÓ faz sentido para série

    int getDuracaoMinutos();        // SÓ faz sentido para filme

    void baixarParaOffline();       // SÓ faz sentido para plano Premium
}