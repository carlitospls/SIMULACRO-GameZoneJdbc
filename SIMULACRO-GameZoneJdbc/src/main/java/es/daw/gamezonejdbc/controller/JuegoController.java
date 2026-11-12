package es.daw.gamezonejdbc.controller;

import es.daw.gamezonejdbc.dto.JuegoForm;
import es.daw.gamezonejdbc.model.Juego;
import es.daw.gamezonejdbc.service.PlataformaService;
import es.daw.gamezonejdbc.service.JuegoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// URLs: GET /juegos | GET /juegos/nuevo | GET /juegos/editar/{codigo}
//       POST /juegos/guardar | POST /juegos/borrar/{codigo}
@Controller
@RequestMapping("/juegos")
@RequiredArgsConstructor
public class JuegoController {

    private final JuegoService juegoService;
    private final PlataformaService plataformaService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("juegos", juegoService.listar());
        return "juegos/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("juegoForm", new JuegoForm());
        model.addAttribute("plataformas", plataformaService.listar());
        return "juegos/formulario";
    }

    @GetMapping("/editar/{codigo}")
    public String editar(@PathVariable Integer codigo, Model model) {
        Juego juego = juegoService.buscarPorCodigo(codigo);
        model.addAttribute("juegoForm", JuegoForm.toDto(juego));
        model.addAttribute("plataformas", plataformaService.listar());
        return "juegos/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("juegoForm") JuegoForm juegoForm,
                          BindingResult result,
                          Model model,
                          RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("plataformas", plataformaService.listar());
            return "juegos/formulario";
        }

        if (juegoForm.getCodigo() == null) {
            Juego creado = juegoService.crear(juegoForm.toModelEntity());
            redirectAttributes.addFlashAttribute("mensaje",
                    "Juego creado con código " + creado.getCodigo());
        } else {
            juegoService.actualizar(juegoForm.toModelEntity());
            redirectAttributes.addFlashAttribute("mensaje",
                    "Juego " + juegoForm.getCodigo() + " actualizado");
        }
        return "redirect:/juegos";
    }

    @PostMapping("/borrar/{codigo}")
    public String borrar(@PathVariable Integer codigo, RedirectAttributes redirectAttributes) {
        juegoService.borrar(codigo);
        redirectAttributes.addFlashAttribute("mensaje", "Juego " + codigo + " borrado");
        return "redirect:/juegos";
    }
}
