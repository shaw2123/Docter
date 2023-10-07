package view;

import controller.Manager;
import controller.Validation;
import java.util.ArrayList;
import model.Doctor;

public class Menu {
       public void Menu(){
           Manager manager=new Manager();
        ArrayList<Doctor> ld = new ArrayList<>();
        //loop until user want to exit
        while (true) {
            int choice = menu();
            switch (choice) {
                case 1:
                    manager.addOrUpdateDoctor(ld);
                    break;
                case 2:
                    manager.deleteDoctor(ld);
                    break;
                case 3:
                    manager.searchDoctor(ld);
                    break;
                case 4:
                    return;
            }
        }
    }
    
    //display menu
    public int menu() {
        Validation validation = new Validation();
        System.out.println("1. Add/Update doctor");
        System.out.println("2. Delete doctor");
        System.out.println("3. Search doctor");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
        int choice = validation.checkInputIntLimit(1, 4);
        return choice;
    }
    
}
