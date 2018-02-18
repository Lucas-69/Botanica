package ar.edu.um.ingenieria.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.edu.um.ingenieria.domain.Usuario;
import ar.edu.um.ingenieria.repository.TemaRepository;
import ar.edu.um.ingenieria.repository.UsuarioRepository;


@Controller
@RequestMapping("/ventas")
@Secured("ROLE_VENDEDOR")
public class VentaController {
	
	private static final Logger logger = LoggerFactory.getLogger(VentaController.class);
			
	@GetMapping("/")
	public String indexPage(@AuthenticationPrincipal Usuario sesion,Model model) {
		logger.info("carga pagina de inicio controler");
		model.addAttribute("sesion", sesion);
		return "ventas"; 
	}
}
