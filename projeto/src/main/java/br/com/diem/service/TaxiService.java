package br.com.diem.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.diem.model.TaxiModel;

@Service
public class TaxiService {

	private File pontosTaxi = new File("pontosTaxi.txt");
	
	private StringBuilder pontos = new StringBuilder();
	
	
	/**
	 * Salva o novo Ponto de Táxi informado e retorna o valor
	 * <p>O arquivo é salvo dentro do projeto com o nome pontosTaxi
	 * 
	 * @param taxi
	 * @return ponto
	 */
	public String save(TaxiModel taxi) {
		
		try {
			
			FileWriter myWriter = new FileWriter(pontosTaxi, true);
			
			//FORMATO DO TEXTO SALVO NO ARQUIVO ESTÁ DENTRO DO toString DA CLASSE MODEL
			myWriter.append(taxi.toString());
			myWriter.append("\n");
			myWriter.close();
			
			pontos.append(taxi);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return taxi.toString();
	}
	
	
	/**
	 * Retorna todos os pontos de Táxi salvos no arquivo
	 * @return pontosSalvosNoArquivo
	 */
	public String getAll() {
		
		pontos = new StringBuilder();
		
		try {
			Scanner myReader = new Scanner(pontosTaxi);

			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				pontos.append(data);
			}

			myReader.close();
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return pontos.toString();
	}
}
