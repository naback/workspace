package br.com.alura.tdd.service;

import br.com.alura.tdd.modelo.Desempenho;
import br.com.alura.tdd.modelo.Funcionario;

import java.math.BigDecimal;

public class ReajusteService
{

    public void concederReajuste(Funcionario funcionario, Desempenho desempenho)
    {
        if (desempenho == Desempenho.A_DESEJAR)
        {
            BigDecimal bonus = funcionario.getSalario().multiply(new BigDecimal("0.03"));
            funcionario.reajustarSalario(bonus);
        }

        if (desempenho == Desempenho.BOM)
        {
            BigDecimal bonus = funcionario.getSalario().multiply(new BigDecimal("0.15"));
            funcionario.reajustarSalario(bonus);
        }

        if (desempenho == Desempenho.OTIMO)
        {
            BigDecimal bonus = funcionario.getSalario().multiply(new BigDecimal("0.20"));
            funcionario.reajustarSalario(bonus);
        }
    }
}
