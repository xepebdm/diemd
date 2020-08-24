package br.com.diem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Não foi localizado Itinerário com os dados informados!")
public class ItinerarioNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 6339517305692772375L;

}
