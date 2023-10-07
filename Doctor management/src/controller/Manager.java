package controller;

import controller.Validation;
import model.Doctor;
import java.util.ArrayList;

public class Manager {
 Validation validation = new Validation();
 
    //allow user add or update doctor
    public void addOrUpdateDoctor(ArrayList<Doctor> ld) {
        System.out.print("Enter code: ");
        String code = validation.checkInputString();
        //search for existing doctor with the code
        Doctor existingDoctor = getDoctorByCode(ld, code);
        //check if doctor already exists
        if (existingDoctor != null) {
            System.out.println("Doctor with code " + code + " already exist");
            System.out.println("Updating doctor information:");
            updateDoctor(existingDoctor);
        } else {
            System.out.println("Doctor with code " + code + " not existed");
            System.out.println("Adding new doctor:");
            addDoctor(ld, code);
        }
    }

    //allow user add doctor
    public void addDoctor(ArrayList<Doctor> ld, String code) {
        System.out.print("Enter name: ");
        String name = validation.checkInputString();
        System.out.print("Enter specialization: ");
        String specialization = validation.checkInputString();
        System.out.print("Enter availability: ");
        int availability = validation.checkInputInt();
        //check worker duplicate
        if (!validation.checkDuplicate(ld, code, name, specialization, availability)) {
            System.err.println("Duplicate.");
            return;
        }
        ld.add(new Doctor(code, name, specialization, availability));
        System.err.println("Add successful.");
    }

    //allow user update doctor
    public void updateDoctor(Doctor doctor) {
        System.out.print("Enter name: ");
        String name = validation.checkInputString();
        System.out.print("Enter specialization: ");
        String specialization = validation.checkInputString();
        System.out.print("Enter availability: ");
        int availability = validation.checkInputInt();
        //check user change information or not
        if (!validation.checkChangeInfo(doctor, doctor.getCode(), name, specialization, availability)) {
            System.err.println("No change");
            return;
        }
        doctor.setName(name);
        doctor.setSpecialization(specialization);
        doctor.setAvailability(availability);
        System.err.println("Update successful");
    }

    //allow user delete doctor
    public void deleteDoctor(ArrayList<Doctor> ld) {
        System.out.print("Enter code: ");
        String code = validation.checkInputString();
        Doctor doctor = getDoctorByCode(ld, code);
        if (doctor == null) {
            System.err.println("Not found doctor.");
            return;
        } else {
            ld.remove(doctor);
        }
        System.err.println("Delete successful.");
    }

    //allow user search doctor
    public void searchDoctor(ArrayList<Doctor> ld) {
        System.out.print("Enter name: ");
        String nameSearch = validation.checkInputString();
        ArrayList<Doctor> listFoundByName = listFoundByName(ld, nameSearch);
        if (listFoundByName.isEmpty()) {
            System.err.println("List empty.");
        } else {
            System.out.printf("%-10s%-15s%-25s%-20s\n", "Code", "Name",
                    "Specialization", "Availability");
            for (Doctor doctor : listFoundByName) {
                System.out.printf("%-10s%-15s%-25s%-20d\n", doctor.getCode(),
                        doctor.getName(), doctor.getSpecialization(),
                        doctor.getAvailability());
            }
        }
    }

    //get doctor by code
    public Doctor getDoctorByCode(ArrayList<Doctor> ld, String code) {
        for (Doctor doctor : ld) {
            if (doctor.getCode().equalsIgnoreCase(code)) {
                return doctor;
            }
        }
        return null;
    }

    //get list found by name
    public ArrayList<Doctor> listFoundByName(ArrayList<Doctor> ld, String name) {
        ArrayList<Doctor> listFoundByName = new ArrayList<>();
        for (Doctor doctor : ld) {
            if (doctor.getName().contains(name)) {
                listFoundByName.add(doctor);
            }
        }
        return listFoundByName;
    }
}
