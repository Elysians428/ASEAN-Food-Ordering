/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AseanFoodOrderingSystem;

/**
 *
 * @author USER
 */

interface OperasiStok {
    void tambahStok(int jumlah); 
}
class MenuDaftar {
  
    private int idMakanan;
    private String namaMakanan;

    public MenuDaftar(int idMakanan, String namaMakanan) {
        this.idMakanan = idMakanan;
        this.namaMakanan = namaMakanan;
    }


    public int getIdMakanan() { return idMakanan; }
    public String getNamaMakanan() { return namaMakanan; }

    
    public void cetakInfo() {
        System.out.println("Menu: " + namaMakanan);
    }
}


class MenuVarian extends MenuDaftar implements OperasiStok {
    private String ukuran;
    private double harga;
    private int stok;

    public MenuVarian(int idMakanan, String namaMakanan, String ukuran, double harga, int stok) {
        super(idMakanan, namaMakanan); 
        this.ukuran = ukuran;
        this.harga = harga;
        this.stok = stok;
    }

    public double getHarga() { return harga; }
    public int getStok() { return stok; }

 
    @Override
    public void tambahStok(int jumlah) {
        this.stok += jumlah;
    }

    @Override
    public void cetakInfo() {
        super.cetakInfo();
        System.out.println("Ukuran: " + ukuran + ", Harga: " + harga);
    }
    public double hitungTotal(int jumlah) {
        return this.harga * jumlah;
    }

    public double hitungTotal(int jumlah, double diskon) {
        double hargaNormal = this.harga * jumlah;
        return hargaNormal - (hargaNormal * diskon);
    }
}
public class ModelRestoran {public static void prosesKonversiMenu(MenuDaftar menuSembarang) {
        // SYARAT 8: Memanfaatkan operator instanceof dan casting
        if (menuSembarang instanceof MenuVarian) {
            MenuVarian varian = (MenuVarian) menuSembarang; // Casting dari Superclass ke Subclass
            System.out.println("Casting Berhasil! Stok saat ini: " + varian.getStok());
        }
    }
    
}
