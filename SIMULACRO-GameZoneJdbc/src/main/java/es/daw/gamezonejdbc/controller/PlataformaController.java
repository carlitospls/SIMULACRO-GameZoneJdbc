package es.daw.gamezonejdbc.controller;

import es.daw.gamezonejdbc.dto.PlataformaDto;
import es.daw.gamezonejdbc.exception.PlataformaDuplicadaException;
import es.daw.gamezonejdbc.model.Plataforma;
import es.daw.gamezonejdbc.service.PlataformaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/plataformas")
@RequiredArgsConstructor
public class PlataformaController {

    private final PlataformaService plataformaService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("plataformas", plataformaService.listar());
        return "plataformas/lista";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute PlataformaDto plataformaDto, //TENGO QUE CREAR PlataformaDto EN /dto
                          BindingResult result,
                          RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "plataforma/formulario";
        }

        try {
            if (plataformaDto.getCodigo() == null) {
                Plataforma plataforma = plataformaService.crear(PlataformaDto.toModelEntity(plataformaDto));
                redirectAttributes.addFlashAttribute("mensaje",
                        "Plataforma creada con código " + plataforma.getCodigo());
            } else {
                Plataforma plataforma = plataformaService.actualizar(PlataformaDto.toModelEntity(plataformaDto));
                redirectAttributes.addFlashAttribute("mensaje",
                        "Plataforma " + plataforma.getCodigo() + " actualizada");
            }
        }
        catch (PlataformaDuplicadaException e) { //tengo que crear esta exception
            result.rejectValue("nombre", "", e.getMessage());
            return "fabricantes/formulario";
        }
        return "redirect:/fabricantes";
    }

}
