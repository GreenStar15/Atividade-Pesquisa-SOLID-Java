/* 5. Código ruim para análise inicial
Todos os grupos deverão analisar este exemplo e dizer quais princípios SOLID ele pode estar violando: */

public class SistemaLoja {
    public void cadastrarProduto(String nome, double preco) {
        System.out.println("Produto cadastrado: " + nome);
    }

    public void calcularImposto(double preco) {
        System.out.println("Imposto: " + (preco * 0.10));
    }

    public void salvarNoBanco() {
        System.out.println("Salvando no banco de dados...");
    }

    public void enviarEmail() {
        System.out.println("Enviando e-mail para o cliente...");
    }

    public void imprimirRelatorio() {
        System.out.println("Relatório impresso...");
    }
}

/* Pergunta: como essa classe poderia ser dividida em classes menores e mais organizadas?
O SistemaLoja é um exemplo clássico de código que funciona, mas foi mal organizado. Olhando com a lente do SOLID, ele viola quase todos os princípios de uma vez:

•	SRP (Single Responsibility): a classe tem cinco motivos diferentes para mudar cadastro de produto, cálculo de imposto, persistência no banco, envio de e-mail e geração de relatório. 
    Cada uma dessas responsabilidades deveria estar em uma classe própria.

•	OCP (Open/Closed): qualquer novo tipo de imposto, novo canal de notificação (SMS, push) ou novo formato de relatório exigiria alterar a classe, em vez de estender o comportamento. 
    A classe não está "aberta para extensão, fechada para modificação".

•	DIP (Dependency Inversion): a classe não depende de nenhuma abstração não há interfaces como Notificador, Repositorio ou CalculadoraImposto. 
    Tudo é feito "na mão", o que impede substituir a implementação (por exemplo, trocar e-mail por SMS) sem reescrever o código.

•	ISP (nosso foco): se esse código fosse reorganizado numa interface única do tipo SistemaLojaService, com esses cinco métodos, 
    qualquer classe que a implementasse seria forçada a implementar métodos que não usa. 
    Por exemplo, uma classe de "cadastro simples" teria que implementar enviarEmail() e imprimirRelatorio() mesmo que não fizesse sentido pra ela. É exatamente o problema que o ISP resolve.

E como então o ISP se aplica aqui? A violação de ISP no SistemaLoja não está explícita no código (porque ele nem usa interfaces), mas está implícita no design: 
a classe concentra responsabilidades heterogêneas num único ponto de contrato. Se fôssemos transformar o SistemaLoja em uma interface, ela seria gorda — e forçaria qualquer implementação a assumir os cinco comportamentos.
A solução do ISP é segregar essa "interface gorda" em interfaces pequenas, cada uma com um propósito coeso: */

public interface Cadastravel {
    void cadastrarProduto(String nome, double preco);
}

public interface Tributavel {
    double calcularImposto(double preco);
}

public interface Persistivel {
    void salvar();
}

public interface Notificavel {
    void enviarNotificacao(String destinatario, String mensagem);
}

public interface Relatoravel {
    void imprimirRelatorio();
}

/* Agora uma classe que só cadastra produtos implementa apenas Cadastravel não é forçada a ter enviarNotificacao() nem imprimirRelatorio(). E uma classe de notificação por e-mail implementa só Notificavel, sem carregar o peso do cadastro.
Essa foi a forma que consideramos que o SistemaLojas seria melhor dividido, aplicando o SRP (para dividir) junto com o ISP (para projetar os contratos), o resultado seria: */

// Cada classe tem uma responsabilidade única e implementa
// apenas a interface que faz sentido para ela. 

public class CadastroProduto implements Cadastravel {
    @Override
    public void cadastrarProduto(String nome, double preco) {
        System.out.println("Produto cadastrado: " + nome);
    }
}

public class CalculadoraImposto implements Tributavel {
    @Override
    public double calcularImposto(double preco) {
        return preco * 0.10;
    }
}

public class RepositorioBanco implements Persistivel {
    @Override
    public void salvar() {
        System.out.println("Salvando no banco de dados...");
    }
}

public class NotificadorEmail implements Notificavel {
    @Override
    public void enviarNotificacao(String destinatario, String mensagem) {
        System.out.println("Enviando e-mail para " + destinatario + "...");
    }
}

public class RelatorioSimples implements Relatoravel {
    @Override
    public void imprimirRelatorio() {
        System.out.println("Relatório impresso...");
    }
}
// E o Main continua fazendo o trabalho de coordenar as peças, mas agora cada peça tem um contrato claro, pequeno e reutilizável:

public class Main {
    public static void main(String[] args) {
        Cadastravel cadastro    = new CadastroProduto();
        Tributavel imposto      = new CalculadoraImposto();
        Persistivel repositorio = new RepositorioBanco();
        Notificavel email       = new NotificadorEmail();
        Relatoravel relatorio   = new RelatorioSimples();

        cadastro.cadastrarProduto("Notebook", 3500.00);
        System.out.println("Imposto: " + imposto.calcularImposto(3500.00));
        repositorio.salvar();
        email.enviarNotificacao("cliente@email.com", "Compra confirmada!");
        relatorio.imprimirRelatorio();
    }
}

/* O que a gente ganha com isso seria então que:
•	Cada classe tem um motivo único para mudar (SRP aplicado).
•	Cada interface tem um contrato específico (ISP aplicado).
•	Trocar a implementação fica fácil (DIP também aparece de graça — se amanhã o envio for por SMS, é só criar NotificadorSMS implements Notificavel e o resto do código continua igual).
•	Testar cada peça fica trivial — dá pra testar o cálculo de imposto isolado, sem precisar de banco nem e-mail. */
