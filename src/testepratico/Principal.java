/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package testepratico;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 *
 * @author maian
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    
    public static void exibirListaFuncionarios(List<Funcionario> funcionarios) {
        System.out.println("Nome|Data Nascimento|Salário|Função\n");
        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario.getNome() +
                    " " + formatarData(funcionario.getDataNascimento()) +
                    " " + formatarSalario(funcionario.getSalario()) +
                    " " + funcionario.getFuncao());
        }
    }
    
    public static void removerFuncionario(List<Funcionario> funcionarios, String nome) {
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals(nome));
    }
    
    public static String formatarData(LocalDate data){
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formatador);
    }
    
    public static String formatarSalario(BigDecimal salario) {
        NumberFormat formatador = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
        formatador.setMinimumFractionDigits(2);
        formatador.setMaximumFractionDigits(2);
        return formatador.format(salario);
    }
    
    public static void aumentarSalarios(List<Funcionario> funcionarios) {
        for (Funcionario funcionario : funcionarios) {
            funcionario.setSalario(funcionario.getSalario().multiply(new BigDecimal("1.10")));
        }
    }
    
    public static void agruparFuncionariosPorFuncao(List<Funcionario> funcionarios) {
        Map<String, List<Funcionario>> funcionariosPorFuncao = new HashMap<>();
       
        for (Funcionario funcionario : funcionarios) {
           String funcao = funcionario.getFuncao();
           if (!funcionariosPorFuncao.containsKey(funcao)) {
               funcionariosPorFuncao.put(funcao, new ArrayList<>());
           }
           funcionariosPorFuncao.get(funcao).add(funcionario);
       }      
       System.out.println("\nFuncionários agrupados por função:");
       System.out.println(funcionariosPorFuncao);
    }
    
    public static void exibirAniversariantes(List<Funcionario> funcionarios) {
        System.out.println("\nAniversariantes do mês 10 e 12:\n");
        for (Funcionario funcionario : funcionarios) {
            int mes = funcionario.getDataNascimento().getMonthValue();
            if (mes == 10 || mes == 12) {
                System.out.println(funcionario);
            }
        }
    }
    
    public static void verificarFuncMaisVelho(List<Funcionario> funcionarios) {
        Funcionario funcMaisVelho = funcionarios.get(0);
   
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getDataNascimento().isBefore(funcMaisVelho.getDataNascimento())) {
                funcMaisVelho = funcionario;
            }
        }
        
        int idadeAtual = Period.between(funcMaisVelho.getDataNascimento(), LocalDate.now()).getYears();
       
        System.out.println("\nFuncionário com a maior idade:\n");
        System.out.println("Nome - " + funcMaisVelho.getNome());
        System.out.println("Idade - " + idadeAtual);
    }
     
    public static void exibirListaEmOrdemAlfabetica(List<Funcionario> funcionarios) {
        funcionarios.sort(Comparator.comparing(Funcionario::getNome));
       
        System.out.println("\nLista de funcionários por ordem alfabética:\n");
        exibirListaFuncionarios(funcionarios);
    }
    
    public static void exibirTotalSalarios(List<Funcionario> funcionarios) {
        BigDecimal totalSalarios = BigDecimal.ZERO;
        
        for (Funcionario funcionario : funcionarios) {
            totalSalarios = totalSalarios.add(funcionario.getSalario());
        }
        System.out.println("\nTotal dos salários dos funcionários: " + formatarSalario(totalSalarios));
    }
    
    public static void verificarQtdSalariosMinimos(List<Funcionario> funcionarios) {
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        
        System.out.println("\nQuantos salários mínimos cada funcionário ganha:\n");
        for (Funcionario funcionario : funcionarios) {
            BigDecimal qtdSalariosMinimos = funcionario.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);
            System.out.println(funcionario.getNome() + ": " + qtdSalariosMinimos + " salários mínimos");
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        List<Funcionario> funcionarios = new ArrayList<>();     
        
        // 3.1 – Inserir todos os funcionários, na mesma ordem e informações da tabela disponibilizada
        
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 9, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

        // 3.2 – Remover o funcionário “João” da lista
        /*
            Preferi fazer a remoção do funcionário "João" pelo índice, pois como não temos
            uma chave primária na lista, caso houvesse mais de um funcionário chamado João,
            todos poderiam ser excluídos. Dessa forma, somente o funcionário que está
            no índice 1 é removido.
            Para simular a remoção pelo nome, criei também uma função que recebe a lista
            de funcionários e o nome exato do funcionário que queremos remover:
        
            removerFuncionario(funcionarios, "João");
        */
        
        funcionarios.remove(1);
        
        // 3.3 – Imprimir todos os funcionários com todas suas informações, sendo que:
        // informação de data deve ser exibido no formato dd/mm/aaaa
        // informação de valor numérico deve ser exibida no formatado com separador de milhar como ponto e decimal como vírgula
        
        exibirListaFuncionarios(funcionarios);
        
        // 3.4 – Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor
        
        aumentarSalarios(funcionarios);
        
        // 3.5 – Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”
        // 3.6 – Imprimir os funcionários, agrupados por função.
        agruparFuncionariosPorFuncao(funcionarios);
        
        // 3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12
        
        exibirAniversariantes(funcionarios);
        
        // 3.9 – Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade

        verificarFuncMaisVelho(funcionarios);
        
        // 3.10 – Imprimir a lista de funcionários por ordem alfabética

        exibirListaEmOrdemAlfabetica(funcionarios);

        // 3.11 – Imprimir o total dos salários dos funcionários
        
        exibirTotalSalarios(funcionarios);
        
        // 3.12 – Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00
        
        verificarQtdSalariosMinimos(funcionarios);
    }
    
}
