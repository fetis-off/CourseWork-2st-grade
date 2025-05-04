package app.coursework.controller;

import app.coursework.dto.EquipmentDto;
import app.coursework.service.equipment.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/equipments")
@RequiredArgsConstructor
public class EquipmentController {

    private final EquipmentService equipmentService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("equipmentList", equipmentService.getAll());
        return "equipments/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("equipment", new EquipmentDto());
        return "equipments/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute EquipmentDto dto) {
        equipmentService.create(dto);
        return "redirect:/equipments";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("equipment", equipmentService.getById(id));
        return "equipments/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id, @ModelAttribute EquipmentDto dto) {
        equipmentService.update(id, dto);
        return "redirect:/equipments";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        equipmentService.delete(id);
        return "redirect:/equipments";
    }
}
