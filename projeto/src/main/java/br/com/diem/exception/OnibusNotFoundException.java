package br.com.diem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "N�o foi localizado Linha de �nibus com os dados informados!")
public class OnibusNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 5778126708964916862L;

}
