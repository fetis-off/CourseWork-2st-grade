package app.coursework.controller;

import app.coursework.dto.MaintenanceEventDto;
import app.coursework.service.maintenanceEvent.MaintenanceEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/maintenance-events")
@RequiredArgsConstructor
public class MaintenanceEventController {

    private final MaintenanceEventService service;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("maintenanceEvents", service.getAll());
        return "maintenance-events/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("maintenanceEvent", new MaintenanceEventDto());
        return "maintenance-events/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute MaintenanceEventDto dto) {
        service.create(dto);
        return "redirect:/maintenance-events";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("maintenanceEvent", service.getById(id));
        return "maintenance-events/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id, @ModelAttribute MaintenanceEventDto dto) {
        service.update(id, dto);
        return "redirect:/maintenance-events";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/maintenance-events";
    }
}
