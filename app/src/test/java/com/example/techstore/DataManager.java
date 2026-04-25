package com.example.techstore;

import java.util.ArrayList;
import java.util.List;

public class DataManager {

    public static List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();

        // === PC ===
        products.add(new Product(1, "Dell Inspiron 15", 189000, 4.5f, "PC", "Laptop Intel Core i5, 8GB RAM, 256GB SSD, écran Full HD 15.6 pouces.", R.drawable.pc1));
        products.add(new Product(2, "HP Pavilion 14", 165000, 4.3f, "PC", "Laptop léger avec processeur AMD Ryzen 5, 8GB RAM, 512GB SSD.", R.drawable.pc2));
        products.add(new Product(3, "Lenovo IdeaPad 3", 145000, 4.2f, "PC", "PC portable idéal pour les étudiants, Intel Core i3, 4GB RAM.", R.drawable.pc3));
        products.add(new Product(4, "Asus VivoBook 15", 175000, 4.4f, "PC", "Design fin et léger, Intel Core i5 11ème génération, 512GB SSD.", R.drawable.pc4));
        products.add(new Product(5, "Acer Aspire 5", 158000, 4.3f, "PC", "Performances fiables pour usage quotidien, Ryzen 7, 16GB RAM.", R.drawable.pc5));

        // === Téléphone ===
        products.add(new Product(6, "Samsung Galaxy A54", 89000, 4.6f, "Phone", "Écran Super AMOLED 6.4\", triple caméra 50MP, batterie 5000mAh.", R.drawable.phone1));
        products.add(new Product(7, "iPhone 13", 165000, 4.8f, "Phone", "Puce A15 Bionic, double caméra 12MP, autonomie exceptionnelle.", R.drawable.phone2));
        products.add(new Product(8, "Xiaomi Redmi Note 12", 52000, 4.4f, "Phone", "Écran AMOLED 6.67\", caméra 50MP, charge rapide 33W.", R.drawable.phone3));
        products.add(new Product(9, "Oppo A78", 58000, 4.3f, "Phone", "Design premium, 8GB RAM, stockage 128GB, caméra 50MP.", R.drawable.phone4));
        products.add(new Product(10, "Realme 11 Pro", 72000, 4.5f, "Phone", "Caméra 100MP, processeur Dimensity 7050, charge 67W.", R.drawable.phone5));

        // === Casque ===
        products.add(new Product(11, "Sony WH-1000XM4", 48000, 4.9f, "Headphone", "Casque sans fil avec réduction de bruit active, 30h d'autonomie.", R.drawable.headphone1));
        products.add(new Product(12, "JBL Tune 510BT", 18500, 4.4f, "Headphone", "Son puissant JBL Pure Bass, Bluetooth 5.0, 40h d'autonomie.", R.drawable.headphone2));
        products.add(new Product(13, "Samsung Galaxy Buds2", 24000, 4.5f, "Headphone", "Écouteurs True Wireless avec ANC, son immersif et compact.", R.drawable.headphone3));
        products.add(new Product(14, "Anker Soundcore Q20i", 12000, 4.3f, "Headphone", "Réduction de bruit hybride active, Hi-Res Audio, 40h autonomie.", R.drawable.headphone4));
        products.add(new Product(15, "Bose QuietComfort 45", 65000, 4.8f, "Headphone", "Référence en matière de réduction de bruit, confort exceptionnel.", R.drawable.headphone5));

        // === Clavier ===
        products.add(new Product(16, "Logitech K380", 9500, 4.5f, "Keyboard", "Clavier Bluetooth multi-appareils, compact et silencieux.", R.drawable.keyboard1));
        products.add(new Product(17, "Razer BlackWidow V3", 28000, 4.7f, "Keyboard", "Clavier mécanique gaming, switches Green, rétroéclairage RGB.", R.drawable.keyboard2));
        products.add(new Product(18, "Corsair K55 RGB", 22000, 4.4f, "Keyboard", "Clavier gaming à membrane, RGB dynamique, repose-poignets.", R.drawable.keyboard3));
        products.add(new Product(19, "HP K1500", 5500, 4.1f, "Keyboard", "Clavier filaire USB simple et fiable pour usage bureautique.", R.drawable.keyboard4));
        products.add(new Product(20, "Keychron K2", 19500, 4.6f, "Keyboard", "Clavier mécanique compact 75%, compatible Mac/Windows, Bluetooth.", R.drawable.keyboard5));

        // === Souris ===
        products.add(new Product(21, "Logitech MX Master 3", 19000, 4.8f, "Mouse", "Souris sans fil ergonomique, défilement ultra-rapide, 7 boutons.", R.drawable.mouse1));
        products.add(new Product(22, "Razer DeathAdder V2", 16500, 4.7f, "Mouse", "Capteur optique 20000 DPI, 8 boutons programmables, filaire.", R.drawable.mouse2));
        products.add(new Product(23, "HP X1500", 4500, 4.2f, "Mouse", "Souris filaire fiable, design ergonomique, plug and play.", R.drawable.mouse3));
        products.add(new Product(24, "Microsoft Arc Mouse", 14000, 4.4f, "Mouse", "Design pliable ultra-portable, Bluetooth, surface tactile.", R.drawable.mouse4));
        products.add(new Product(25, "SteelSeries Rival 3", 12000, 4.5f, "Mouse", "Souris gaming légère, capteur TrueMove Core 8500 DPI, RGB.", R.drawable.mouse5));

        // === Flash Disque ===
        products.add(new Product(26, "SanDisk Ultra 64GB", 3200, 4.6f, "USB", "Clé USB 3.0, vitesse lecture 130 Mo/s, format compact.", R.drawable.usb1));
        products.add(new Product(27, "Kingston DataTraveler 128GB", 5800, 4.5f, "USB", "USB 3.2, vitesse 200 Mo/s, coque métallique résistante.", R.drawable.usb2));
        products.add(new Product(28, "Samsung BAR Plus 32GB", 2800, 4.4f, "USB", "USB 3.1, design en métal, vitesse jusqu'à 200 Mo/s.", R.drawable.usb3));
        products.add(new Product(29, "Verbatim PinStripe 16GB", 1500, 4.1f, "USB", "Clé USB 2.0 économique, capuchon rotatif, léger et portable.", R.drawable.usb4));
        products.add(new Product(30, "Lexar JumpDrive S47 256GB", 9500, 4.7f, "USB", "Grande capacité, USB 3.1, vitesse 250 Mo/s, idéal stockage.", R.drawable.usb5));

        return products;
    }

    // Listes partagées pour le panier et les favoris
    public static List<Product> cartItems = new ArrayList<>();
    public static List<Product> favoriteItems = new ArrayList<>();
    public static List<Integer> cartQuantities = new ArrayList<>();
}