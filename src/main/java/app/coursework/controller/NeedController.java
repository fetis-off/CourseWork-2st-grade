package app.coursework.controller;

import app.coursework.dto.NeedDto;
import app.coursework.service.need.NeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/needs")
@RequiredArgsConstructor
public class NeedController {
    private final NeedService service;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("needs", service.getAll());
        return "needs/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("need", new NeedDto());
        return "needs/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute NeedDto dto) {
        service.create(dto);
        return "redirect:/needs";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("need", service.getById(id));
        return "needs/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id, @ModelAttribute NeedDto dto) {
        service.update(id, dto);
        return "redirect:/needs";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/needs";
    }
}

