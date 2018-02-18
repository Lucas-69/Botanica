package ar.edu.um.ingenieria.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ar.edu.um.ingenieria.dto.RolDTO;
import ar.edu.um.ingenieria.dto.UsuarioDTO;
import ar.edu.um.ingenieria.editor.RolEditor;
import ar.edu.um.ingenieria.manager.RolManager;
import ar.edu.um.ingenieria.manager.UsuarioManager;
import ar.edu.um.ingenieria.service.impl.RolServiceImpl;


@Controller
@RequestMapping("/")
public class RegistroController {
	private static final Logger logger = LoggerFactory.getLogger(RegistroController.class);
	
	@Autowired
	private RolManager rolManager;
	
	@Autowired
	private UsuarioManager usuarioManager;
	
	@Autowired
	private RolServiceImpl rolServiceImpl;
	
	@RequestMapping(value = "/registro", method = RequestMethod.GET)
	public String registracion(Model model) {
		logger.info("ControladorRegistro");
		UsuarioDTO usuario = new UsuarioDTO();
		model.addAttribute("usuario", usuario);
		model.addAttribute("rol", rolServiceImpl.findWithOutAdmin());
		return "registro";
	}

	@RequestMapping(value = "/registro", method = RequestMethod.POST)
	public String formNuevo(@Valid @ModelAttribute("usuario") UsuarioDTO usuarioDTO, BindingResult result, Model model) {
		if (!result.hasErrors()) {
			usuarioManager.create(usuarioDTO);
			return "iniciar";
		}
		model.addAttribute("rol", rolServiceImpl.findWithOutAdmin());
		return "registro";
	}

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(RolDTO.class, "rol", new RolEditor(this.rolManager));
	}

}
