package com.spring.jpa.h2.drone.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.spring.jpa.h2.drone.dto.DroneModel;
import com.spring.jpa.h2.drone.dto.DroneState;
import com.spring.jpa.h2.drone.dto.request.LoadDroneDTO;
import com.spring.jpa.h2.drone.dto.response.DroneBatteryLevelDTO;
import com.spring.jpa.h2.drone.mapping.DroneMapper;
import com.spring.jpa.h2.drone.model.ActiveDrone;
import com.spring.jpa.h2.drone.model.Drone;
import com.spring.jpa.h2.drone.model.DroneLog;
import com.spring.jpa.h2.drone.model.Medication;
import com.spring.jpa.h2.drone.repository.ActiveDroneRepository;
import com.spring.jpa.h2.drone.repository.DroneLogRepository;
import com.spring.jpa.h2.drone.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.spring.jpa.h2.drone.repository.DroneRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class DroneWithMedicationController {


    private final DroneRepository droneRepository;
    private final MedicationRepository medicationRepository;
    private final ActiveDroneRepository activeDroneRepository;
    private final DroneLogRepository droneLogRepository;
    private final DroneMapper mapper;

    @Autowired
    public DroneWithMedicationController(DroneRepository droneRepository, MedicationRepository medicationRepository, ActiveDroneRepository activeDroneRepository, DroneLogRepository droneLogRepository, DroneMapper mapper) {
        this.droneRepository = droneRepository;
        this.medicationRepository = medicationRepository;
        this.activeDroneRepository = activeDroneRepository;
        this.droneLogRepository = droneLogRepository;
        this.mapper = mapper;
    }

    @GetMapping("/medication-items-by-drone")
    public ResponseEntity<List<Medication>> getMedicationItemsByDrone(@RequestParam String serialNumber) {
        try {
            Optional<List<ActiveDrone>> activeDrone = activeDroneRepository.findBySerialNumber(serialNumber);
            return activeDrone.map(activeDrones -> new ResponseEntity<>(mapper.mapActiveDroneEntityToMedicationDTOResponse(activeDrones), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/available-drones-for-loading")
    public ResponseEntity<List<Drone>> getAvailableDronesForLoading() {
        try {
            Optional<List<Drone>> drones = droneRepository.findDistinctByStateAndBatteryCapacityGreaterThanAndActiveDrones_Empty(DroneState.IDLE.droneState, 24);
            return drones.map(droneList -> new ResponseEntity<>(droneList, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/check-drone-battery-level")
    public ResponseEntity<DroneBatteryLevelDTO> getDroneBatteryLevel(@RequestParam String serialNumber) {
        try {
            Optional<Drone> drone = droneRepository.findBySerialNumber(serialNumber);
            return drone.map(value -> new ResponseEntity<>(new DroneBatteryLevelDTO(value.getBatteryCapacity()), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register-drone")
    public ResponseEntity<Drone> registerDrone(@RequestBody Drone request) {
        try {

            boolean modelResult = false;
            boolean stateResult = false;
            for (DroneModel model : DroneModel.values()) {
                if (request.getModel().trim().equalsIgnoreCase(model.droneModel)) {
                    modelResult = true;
                    break;
                }
            }
            for (DroneState state : DroneState.values()) {
                if (request.getState().trim().equalsIgnoreCase(state.droneState)) {
                    stateResult = true;
                    break;
                }
            }
            if (modelResult && stateResult) {

                Drone drone = droneRepository
                        .save(new Drone(request.getModel(), request.getWeightLimit(), request.getBatteryCapacity(), request.getState()));
                return new ResponseEntity<>(drone, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/load-drone")
    public ResponseEntity<Drone> loadDrone(@RequestBody LoadDroneDTO request) {
        try {

            int totalRequestedWeight = 0;
            Optional<Drone> requestedDrone = droneRepository.findBySerialNumber(request.getDroneSerialNumber());
            if (requestedDrone.isPresent() && requestedDrone.get().getBatteryCapacity() < 25) {
                return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
            }

            //save mocked data
            List<Medication> preDefinedMedications = new ArrayList<>();
            preDefinedMedications.add(new Medication("Skin Care", 50, "https://img.freepik.com/free-photo/side-view-circle-chocolate-candies-black-table-jpg_140725-12879.jpg?w=1060&t=st=1664823741~exp=1664824341~hmac=7e367ed813ea3019995ce54dd39a1d314daf24bcd7b5d54f2316b6684a7310ae"));
            preDefinedMedications.add(new Medication("Body Lotion", 50, "https://img.freepik.com/free-photo/side-view-circle-chocolate-candies-black-table-jpg_140725-12879.jpg?w=1060&t=st=1664823741~exp=1664824341~hmac=7e367ed813ea3019995ce54dd39a1d314daf24bcd7b5d54f2316b6684a7310ae"));
            preDefinedMedications.add(new Medication("cold & flu tabs", 100, "https://img.freepik.com/free-photo/side-view-circle-chocolate-candies-black-table-jpg_140725-12879.jpg?w=1060&t=st=1664823741~exp=1664824341~hmac=7e367ed813ea3019995ce54dd39a1d314daf24bcd7b5d54f2316b6684a7310ae"));
            preDefinedMedications.add(new Medication("supplements", 400, "https://img.freepik.com/free-photo/side-view-circle-chocolate-candies-black-table-jpg_140725-12879.jpg?w=1060&t=st=1664823741~exp=1664824341~hmac=7e367ed813ea3019995ce54dd39a1d314daf24bcd7b5d54f2316b6684a7310ae"));
            medicationRepository.saveAll(preDefinedMedications);

            List<Medication> items = medicationRepository.findByCodeIn(request.getMedicationItemsCodes());
            List<ActiveDrone> activeDroneLoadedWithItems = new ArrayList<>();
            List<DroneLog> droneLogs = new ArrayList<>();
            for (Medication item : items) {

                totalRequestedWeight += item.getWeight();

                if (totalRequestedWeight > requestedDrone.get().getWeightLimit())
                    return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);

                activeDroneLoadedWithItems.add(new ActiveDrone(request.getDroneSerialNumber(), item.getCode(), new Date(), item.getWeight()));
                droneLogs.add(new DroneLog(request.getDroneSerialNumber(), item.getCode(), new Date(), null));

            }
            if (activeDroneLoadedWithItems.size() == 0)
                return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);

            requestedDrone.get().setCurrentLoadedWeight(totalRequestedWeight);
            droneRepository.save(requestedDrone.get());
            activeDroneRepository.saveAll(activeDroneLoadedWithItems);
            droneLogRepository.saveAll(droneLogs);
            return new ResponseEntity<>(null, HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Scheduled(fixedDelay = 30000)
    public void scheduleFixedDelayTask() {

        for (Drone drone : droneRepository.findAll()) {
            System.out.println(
                    " drone - with - serialNo = " + drone.getSerialNumber() + " with battery level = " + drone.getBatteryCapacity());
        }
    }

}
