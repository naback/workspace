package br.com.alura.loja;

import br.com.alura.loja.desconto.CalculadoraDeDescontos;
import br.com.alura.loja.orcamento.Orcamento;

import java.math.BigDecimal;

public class TesteDescontos
{
    public static void main(String[] args)
    {
        Orcamento orcamento1 = new Orcamento(new BigDecimal("200"), 6);
        CalculadoraDeDescontos calculadora = new CalculadoraDeDescontos();
        System.out.println("Desconto 1: " + calculadora.calcular(orcamento1));

        Orcamento orcamento2 = new Orcamento(new BigDecimal("1000"), 1);
        System.out.println("Desconto 2: " + calculadora.calcular(orcamento2));
    }
}
