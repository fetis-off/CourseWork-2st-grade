package app.coursework.controller;

import app.coursework.dto.MaterialDto;
import app.coursework.service.material.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/materials")
@RequiredArgsConstructor
public class MaterialController {
    private final MaterialService materialService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("materials", materialService.getAll());
        return "materials/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("material", new MaterialDto());
        return "materials/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute MaterialDto dto) {
        materialService.create(dto);
        return "redirect:/materials";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("material", materialService.getById(id));
        return "materials/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id, @ModelAttribute MaterialDto dto) {
        materialService.update(id, dto);
        return "redirect:/materials";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        materialService.delete(id);
        return "redirect:/materials";
    }
}
