package nicolashoefling.imposto;

import java.util.Scanner; //entrada

public class ImpostoRenda {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in); //teclado

        double rendaSalario = 0.0;
        double rendaServicos = 0.0;
        double rendaCapital = 0.0;
        double gastoMedicos = 0.0;
        double gastoEducacao = 0.0;

        //leitura de dados
        rendaSalario = valorValido (scanner, "\nDigite sua renda anual com salário: R$ ");
        rendaServicos = valorValido (scanner, "Digite sua renda anual com prestação de serviços: R$ ");
        rendaCapital = valorValido (scanner, "Digite sua renda anual com ganho de capital: R$ ");
        gastoMedicos = valorValido (scanner, "Digite os gastos no ano com Médicos(Saúde): R$ ");
        gastoEducacao = valorValido (scanner, "Digite os gastos no ano com Educação: R$ ");

        //calculo dos impostos (rendas)
        double impostoSalario = calcularImpostoSalario(rendaSalario);
        double impostoServicos = rendaServicos * 0.15;
        double impostoCapital = rendaCapital * 0.20;

        // calculo do imposto bruto total
        double impostoBrutoTotal = impostoSalario + impostoCapital + impostoServicos;

        // gastos geral (medico + educação)
        double gastosEduMed = gastoMedicos + gastoEducacao;

        // calculo das deduções
        double maxDedutivel = impostoBrutoTotal * 0.30;
        double abatimento = Math.min(maxDedutivel, gastosEduMed);// metedo estatico math.min pega o menor valor

        // calculo do imposto devido
        double impostoDevido = impostoBrutoTotal - abatimento;

        // relatorio
        System.out.println("\n^---------------------------------------------^");
        System.out.println("        RELATÓRIO DE IMPOSTO DE RENDA");
        System.out.println("\nCONSOLIDADO DE RENDA:");
        System.out.printf(" Imposto sobre salário: R$ %.2f%n", impostoSalario);
        System.out.printf(" Imposto sobre serviços: R$ %.2f%n", impostoServicos);
        System.out.printf(" Imposto sobre ganho de capital: R$ %.2f%n", impostoCapital);
        System.out.println("\nDEDUÇÕES:");
        System.out.printf(" Máximo dedutível: R$ %.2f%n", maxDedutivel);
        System.out.printf(" Gastos dedutíveis: R$ %.2f%n", gastosEduMed);
        System.out.println("\nRESUMO:");
        System.out.printf(" Imposto bruto total: R$ %.2f%n", impostoBrutoTotal);
        System.out.printf(" Abatimento: R$ %.2f%n", abatimento);
        System.out.printf(" Imposto devido: R$ %.2f%n", impostoDevido);
        System.out.println("^---------------------------------------------^");
        scanner.close();
    }

    // validar valores incorretos
    private static double valorValido(Scanner scanner, String mensagem) {
        double valor = 0.0;
        boolean valido = false; //indica se a entrada do usuario é valido

        while (!valido) {
            System.out.print(mensagem);
            String entrada = scanner.next();

            // substitui vírgulas por pontos
            entrada = entrada.replace(",", ".");

            try {
                valor = Double.parseDouble(entrada);

                if (valor < 0) {
                    System.out.println("Erro: o valor não pode ser negativo.");
                } else {
                    valido = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Entrada inválida. Insira um número válido.");
            }
        }

        return valor;
    }

    //calcular o imposto em relação ao salario
    private static double calcularImpostoSalario(double rendaAnual) {
        // renda anual p/ mensal
        double salarioMensal = rendaAnual / 12.0;
        double imposto = 0.0;

        if (salarioMensal < 3000.0) {
            imposto = 0.0; // isento
        } else if (salarioMensal < 5000.0) {
            imposto = rendaAnual * 0.10; //10%
        } else { // salarioMensal >= 5000.0
            imposto = rendaAnual * 0.20; //20%
        }

        return imposto;
    }
}