package com.involves.selecao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.involves.selecao.alerta.Alerta;
import com.involves.selecao.service.BuscaAlertasService;
import com.involves.selecao.service.ProcessadorAlertas;
import com.involves.selecao.service.ServiceException;

@RestController
@RequestMapping("/alertas")
public class AlertaController {

	@Autowired
	private BuscaAlertasService buscaAlertasService;
	
	@Autowired
	private ProcessadorAlertas processador;
	
	@GetMapping
    public List<Alerta> alertas() {
		return buscaAlertasService.buscarTodos();
    }
	
	@GetMapping("/processar")
    public ResponseEntity<String> processar() {
		try {
			processador.processa();
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
}
