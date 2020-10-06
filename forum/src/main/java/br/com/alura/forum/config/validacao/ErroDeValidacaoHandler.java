package br.com.alura.forum.config.validacao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // vai tratar as exceções em qualquer RESTCONTROLLER
public class ErroDeValidacaoHandler
{
	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST) // se não fizer isso, ele devolve 200 na resposta
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroDeFormularioDto> handle(MethodArgumentNotValidException exception)
	{
		List<ErroDeFormularioDto> dto = new ArrayList<ErroDeFormularioDto>();
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		
		for (FieldError fieldError : fieldErrors)
		{
			ErroDeFormularioDto erro = new ErroDeFormularioDto(fieldError.getField(), messageSource.getMessage(fieldError, LocaleContextHolder.getLocale()));
			dto.add(erro);
		}
		
		return dto;
	}
}
