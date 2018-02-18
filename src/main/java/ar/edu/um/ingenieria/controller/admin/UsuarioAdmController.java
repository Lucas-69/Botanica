package ar.edu.um.ingenieria.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.um.ingenieria.manager.UsuarioManager;
import ar.edu.um.ingenieria.service.impl.RolServiceImpl;

@Controller
@RequestMapping("/admin")

public class UsuarioAdmController {
	private static final Logger logger = LoggerFactory.getLogger(UsuarioAdmController.class);

	@Autowired
	private UsuarioManager usuarioManager;
	
	@Autowired
	private RolServiceImpl rolServiceImpl;
	
	@RequestMapping(value= "/usuarios", method = RequestMethod.GET)
	public String listarUsuarios(Model model) {
		logger.info("AdminController");
		model.addAttribute("usuarios", usuarioManager.showAll());
		return "/admin/usuarios";
	}
	
	@GetMapping("/usuarioeditar/{id}")
    public String show(@PathVariable Integer id, Model model) {
		model.addAttribute("usuario", usuarioManager.findById(id));
		model.addAttribute("rol", rolServiceImpl.findWithOutAdmin());
		return "/admin/usuarioeditar";
    }
	
	@GetMapping("/usuarioborrar/{id}")
    public String borrar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
		usuarioManager.delete(id);
		redirectAttributes.addFlashAttribute("mensaje", "usuario borrado");
		redirectAttributes.addFlashAttribute("estilo", "alert-warning");
		return "redirect:/admin/usuarios";
    }
}
