package app.coursework.controller;

import app.coursework.dto.MaintenanceDto;
import app.coursework.service.equipment.EquipmentServiceImpl;
import app.coursework.service.maintenance.MaintenanceService;
import app.coursework.service.maintenanceEvent.MaintenanceEventServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/maintenance")
@RequiredArgsConstructor
public class MaintenanceController {

    private final MaintenanceService service;
    private final EquipmentServiceImpl equipmentServiceImpl;
    private final MaintenanceEventServiceImpl maintenanceEventServiceImpl;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("maintenances", service.getAll());
        return "maintenance/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("maintenance", new MaintenanceDto());
        model.addAttribute("equipments", equipmentServiceImpl.getAll());
        model.addAttribute("maintenanceEvents", maintenanceEventServiceImpl.getAll());
        return "maintenance/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute MaintenanceDto dto) {
        service.create(dto);
        return "redirect:/maintenance";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("maintenance", service.getById(id));
        model.addAttribute("equipments", equipmentServiceImpl.getAll());
        model.addAttribute("maintenanceEvents", maintenanceEventServiceImpl.getAll());
        return "maintenance/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id, @ModelAttribute MaintenanceDto dto) {
        service.update(id, dto);
        return "redirect:/maintenance";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/maintenance";
    }
}

