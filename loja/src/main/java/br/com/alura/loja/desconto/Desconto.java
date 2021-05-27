package br.com.alura.loja.desconto;

import br.com.alura.loja.orcamento.Orcamento;

import java.math.BigDecimal;

public abstract class Desconto
{
    public Desconto proximo;

    public Desconto(Desconto proximo)
    {
        this.proximo = proximo;
    }

    public BigDecimal calcular(Orcamento orcamento)
    {
        if (deveAplicar(orcamento))
        {
            return efetuarCalculo(orcamento);
        }
        else
        {
            return proximo.calcular(orcamento);
        }
    }

    protected abstract BigDecimal efetuarCalculo(Orcamento orcamento);
    public abstract boolean deveAplicar(Orcamento orcamento);
}
