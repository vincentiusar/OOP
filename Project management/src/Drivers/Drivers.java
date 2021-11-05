package Drivers;

import Employee.Employee;
import java.util.Date;
import java.util.Scanner;
import Project.Project;
import Manager.Manager;
import CEO.CEO;

public class Drivers {

    static Project P[] = new Project[100];
    static Employee E[] = new Employee[1000];
    static Manager M[] = new Manager[100];
    static CEO C[] = new CEO[100];
    static int nP = 0, nE = 0, nM = 0, nC = 0;
    
    static void menu() {
        System.out.println("1. Tambahkan Pegawai");
        System.out.println("2. Tambahkan Project");
        System.out.println("3. Lihat data Pegawai");
        System.out.println("4. Lihat Project dan subProject");
        System.out.println("5. Cek status Project");
        System.out.println("0. Exit");
    }
    
    static boolean findEmployee(Employee E[], String str) {
        // cek apakah employee sudah terdaftar dengan ID
        for (int i = 0; i < nE; i++) {
            if (E[i].getId().equals(str)) return true;
        }
        return false;
    }
    
    static boolean findProject(Project P[], String str) {
        // cek apakah project sudah terdaftar dengan ID
        for (int i = 0; i < nP; i++) {
            if (P[i].getId().equals(str)) return true;
        }
        return false;
    }
      
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        System.out.println("SYSTEM STARTED");
        System.out.println("SELAMAT DATANG");
        while (true) {
            menu();
            int choose = cin.nextInt();
            String id, nama, jabatan, divisi, id_cus, nama_cus;
            Date start, end;
            int n, k;
            switch (choose) {
                case 1 :
                    System.out.println("Pegawai? Manager?");
                    cin.nextLine();
                    jabatan = cin.nextLine();
                    do {
                        System.out.println("ID : ");
                        id = cin.nextLine();
                    } while (findEmployee(E, id));
                    
                    System.out.println("Nama : ");
                    nama = cin.nextLine();
                    
                    while (!jabatan.equals("Pegawai") && !jabatan.equals("Manager")) {
                        jabatan = cin.nextLine();
                    }
                    if (jabatan.equals("Pegawai")) {
                        System.out.println("Masukkan divisinya");
                        divisi = cin.nextLine();
                        E[nE++] = new Employee(id, nama, jabatan);
                        M[nM++] = new Manager(id, nama, jabatan, divisi);
                    } else {
                        System.out.println("Masukkan ketua divisinya");
                        divisi = cin.nextLine();
                        C[nC++] = new CEO(id, nama, jabatan, divisi);
                        E[nE++] = new Employee(id, nama, jabatan);
                    }
                    break;
                case 2 :
                    do {
                        System.out.println("Masukkan ID project : ");
                        cin.nextLine();
                        id = cin.nextLine();
                        // lakukan sampai project belum ada
                    } while (findProject(P, id));
                    System.out.println("Masukkan Nama project : ");
                    nama = cin.nextLine();
                    
                    System.out.println("Masukkan ID customer : ");
                    id_cus = cin.nextLine();
                    
                    System.out.println("Masukkan nama customer : ");
                    nama_cus = cin.nextLine();
                    
                    System.out.println("Mulai project (yyyy-mm-dd)");
                    id = cin.nextLine();
                    start = new Date(Integer.valueOf(id.substring(0, 4)), Integer.valueOf(id.substring(5, 7)), Integer.valueOf(id.substring(8, 10)));
                    
                    System.out.println("End project (dd-mm-yyyy)");
                    id = cin.nextLine();
                    end = new Date(Integer.valueOf(id.substring(7, 10)), Integer.valueOf(id.substring(3, 4)), Integer.valueOf(id.substring(0, 1)));
            
                    System.out.println("Jumlah subProject :");
                    n = cin.nextInt();
                    
                    System.out.println("Jumlah Pekerja :");
                    k = cin.nextInt();
                    
                    P[nP] = new Project(id_cus, nama_cus, E, id, nama, start, end, k, n, nE, nP);
                    break;
                case 3 :
                    for (int i = 0; i < nE; i++) {
                        System.out.println("["+(i+1)+"]");
                        System.out.println(E[i].getId());
                        System.out.println(E[i].getNama());
                        System.out.println(E[i].getJabatan());
                    }
                    break;
                case 4 :
                    for (int i = 0; i < nP; i++) {
                        P[i].showInfo();
                    }
                    break;
                case 5 :
                    System.out.println("not yet");
                    break;
                default : 
                    return;
            }
        }
    }
}
