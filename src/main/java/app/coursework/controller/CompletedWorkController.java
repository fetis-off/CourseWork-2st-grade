package app.coursework.controller;

import app.coursework.dto.CompletedWorkDto;
import app.coursework.service.completedWork.CompletedWorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/completed-works")
@RequiredArgsConstructor
public class CompletedWorkController {
    private final CompletedWorkService service;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("completedWorkList", service.getAll());
        return "completed-work/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("completedWork", new CompletedWorkDto());
        return "completed-work/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute CompletedWorkDto dto) {
        service.create(dto);
        return "redirect:/completed-work";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("completedWork", service.getById(id));
        return "completed-work/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id, @ModelAttribute CompletedWorkDto dto) {
        service.update(id, dto);
        return "redirect:/completed-work";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/completed-work";
    }
}

