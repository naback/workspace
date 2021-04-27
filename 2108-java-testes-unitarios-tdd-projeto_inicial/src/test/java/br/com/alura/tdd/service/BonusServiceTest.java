package br.com.alura.tdd.service;

import br.com.alura.tdd.modelo.Funcionario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BonusServiceTest
{
    @Test
    void bonusDeveriaSerZeroParaFuncionarioComSalarioMuitoAlto()
    {
        BonusService service = new BonusService();
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> service.calcularBonus(new Funcionario("Rodrigo", LocalDate.now(), new BigDecimal("25000"))));

        // ou faz try catch tb
    }

    @Test
    void bonusDeveriaSerDezPorCentoDoSalario()
    {
        BonusService service = new BonusService();
        BigDecimal bonus = service.calcularBonus(new Funcionario("Rodrigo", LocalDate.now(), new BigDecimal("2500")));

        Assertions.assertEquals(new BigDecimal("250.00"), bonus);
    }
 
    @Test
    void bonusDeveriaSerDezPorCentoParaSalarioDe10000()
    {
        BonusService service = new BonusService();
        BigDecimal bonus = service.calcularBonus(new Funcionario("Rodrigo", LocalDate.now(), new BigDecimal("10000")));

        Assertions.assertEquals(new BigDecimal("1000.00"), bonus);
    }
}
