package app.coursework.config;

import app.coursework.model.CompletedWork;
import app.coursework.model.Equipment;
import app.coursework.model.Maintenance;
import app.coursework.model.MaintenanceEvent;
import app.coursework.model.Material;
import app.coursework.model.Need;
import app.coursework.repository.CompletedWorkRepository;
import app.coursework.repository.EquipmentRepository;
import app.coursework.repository.MaintenanceEventRepository;
import app.coursework.repository.MaintenanceRepository;
import app.coursework.repository.MaterialRepository;
import app.coursework.repository.NeedRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final MaterialRepository materialRepository;
    private final EquipmentRepository equipmentRepository;
    private final MaintenanceEventRepository maintenanceEventRepository;
    private final MaintenanceRepository maintenanceRepository;
    private final NeedRepository needRepository;
    private final CompletedWorkRepository completedWorkRepository;

    @Override
    public void run(String... args) {
        // ---------- Матеріали ----------
        Material m1 = new Material();
        m1.setName("Сталь");
        m1.setPrice(500);
        m1.setType("Метал");

        Material m2 = new Material();
        m2.setName("Алюміній");
        m2.setPrice(600);
        m2.setType("Метал");

        Material m3 = new Material();
        m3.setName("Мідь");
        m3.setPrice(700);
        m3.setType("Метал");

        Material m4 = new Material();
        m4.setName("Пластик");
        m4.setPrice(200);
        m4.setType("Полімер");

        Material m5 = new Material();
        m5.setName("Гума");
        m5.setPrice(300);
        m5.setType("Полімер");

        materialRepository.saveAll(List.of(m1, m2, m3, m4, m5));

        // ---------- Обладнання ----------
        Equipment e1 = new Equipment();
        e1.setName("Шліфувальний верстат");
        e1.setPrice(15000);

        Equipment e2 = new Equipment();
        e2.setName("Токарний верстат");
        e2.setPrice(18000);

        Equipment e3 = new Equipment();
        e3.setName("Зварювальний апарат");
        e3.setPrice(12000);

        Equipment e4 = new Equipment();
        e4.setName("Прес");
        e4.setPrice(22000);
        e4.setParent(e1); // parent_id

        Equipment e5 = new Equipment();
        e5.setName("Фрезерний верстат");
        e5.setPrice(25000);
        e5.setParent(e2);

        equipmentRepository.saveAll(List.of(e1, e2, e3, e4, e5));

        // ---------- Події обслуговування ----------
        MaintenanceEvent ev1 = new MaintenanceEvent();
        ev1.setName("Заміна мастила");
        ev1.setProcedureDescription("Провести заміну мастила на всіх вузлах");

        MaintenanceEvent ev2 = new MaintenanceEvent();
        ev2.setName("Перевірка шестерень");
        ev2.setProcedureDescription("Візуальна перевірка зносу та тріщин");

        MaintenanceEvent ev3 = new MaintenanceEvent();
        ev3.setName("Очищення від пилу");
        ev3.setProcedureDescription("Очистити поверхні й повітряні фільтри");

        MaintenanceEvent ev4 = new MaintenanceEvent();
        ev4.setName("Калібрування датчиків");
        ev4.setProcedureDescription("Провести калібрування всіх сенсорів");

        MaintenanceEvent ev5 = new MaintenanceEvent();
        ev5.setName("Затягування кріплень");
        ev5.setProcedureDescription("Перевірити та затягнути всі болти");

        maintenanceEventRepository.saveAll(List.of(ev1, ev2, ev3, ev4, ev5));

        // ---------- Обслуговування ----------
        Maintenance mt1 = new Maintenance();
        mt1.setFrequency("Раз на місяць");
        mt1.setEquipment(e1);
        mt1.setMaintenanceEvent(ev1);

        Maintenance mt2 = new Maintenance();
        mt2.setFrequency("Раз на тиждень");
        mt2.setEquipment(e2);
        mt2.setMaintenanceEvent(ev2);

        Maintenance mt3 = new Maintenance();
        mt3.setFrequency("Щодня");
        mt3.setEquipment(e3);
        mt3.setMaintenanceEvent(ev3);

        Maintenance mt4 = new Maintenance();
        mt4.setFrequency("Раз на квартал");
        mt4.setEquipment(e4);
        mt4.setMaintenanceEvent(ev4);

        Maintenance mt5 = new Maintenance();
        mt5.setFrequency("Раз на півроку");
        mt5.setEquipment(e5);
        mt5.setMaintenanceEvent(ev5);

        maintenanceRepository.saveAll(List.of(mt1, mt2, mt3, mt4, mt5));

        // ---------- Потреби матеріалів ----------
        Need n1 = new Need();
        n1.setQuantity(3);
        n1.setMaterial(m1);
        n1.setMaintenance(mt1);

        Need n2 = new Need();
        n2.setQuantity(2);
        n2.setMaterial(m2);
        n2.setMaintenance(mt2);

        Need n3 = new Need();
        n3.setQuantity(4);
        n3.setMaterial(m3);
        n3.setMaintenance(mt3);

        Need n4 = new Need();
        n4.setQuantity(5);
        n4.setMaterial(m4);
        n4.setMaintenance(mt4);

        Need n5 = new Need();
        n5.setQuantity(1);
        n5.setMaterial(m5);
        n5.setMaintenance(mt5);

        needRepository.saveAll(List.of(n1, n2, n3, n4, n5));

        // ---------- Виконані роботи ----------
        CompletedWork cw1 = new CompletedWork();
        cw1.setExecutionDate(LocalDate.now().minusDays(5));
        cw1.setExecutionTime(LocalTime.of(10, 0));
        cw1.setMaintenance(mt1);

        CompletedWork cw2 = new CompletedWork();
        cw2.setExecutionDate(LocalDate.now().minusDays(3));
        cw2.setExecutionTime(LocalTime.of(11, 30));
        cw2.setMaintenance(mt2);

        CompletedWork cw3 = new CompletedWork();
        cw3.setExecutionDate(LocalDate.now().minusDays(2));
        cw3.setExecutionTime(LocalTime.of(8, 15));
        cw3.setMaintenance(mt3);

        CompletedWork cw4 = new CompletedWork();
        cw4.setExecutionDate(LocalDate.now().minusDays(1));
        cw4.setExecutionTime(LocalTime.of(14, 0));
        cw4.setMaintenance(mt4);

        CompletedWork cw5 = new CompletedWork();
        cw5.setExecutionDate(LocalDate.now());
        cw5.setExecutionTime(LocalTime.of(9, 45));
        cw5.setMaintenance(mt5);

        completedWorkRepository.saveAll(List.of(cw1, cw2, cw3, cw4, cw5));
    }
}
