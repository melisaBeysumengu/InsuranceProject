package com.example.insuranceproject.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Kasko {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content = "Orijinal ücret.";

    private Integer discount;

    private double price;

    private String provider;

    private String category;

    private Integer ageLimit;

    private Integer kilometerLimit;

    private Integer driverExperienceLimit;

    private String createdAt;

    public void setPrice(double price, int age, int kilometer) {
        String content = "";
        if (age < getAgeLimit()) {
            setPrice(price * ((double) (100 - getDiscount()) / 100));
            content = "Araç yaşınız küçük olduğu için fiyat (" + price + ") üzerinden %" + getDiscount() + " indirim uygulandı.";
        }
        if (kilometer < getKilometerLimit()) {
            content += " Araç kilometresi küçük olduğu için fiyat (" + getPrice() + ") üzerinden %" + (getDiscount()/2) + " indirim uygulandı.";
            setPrice(getPrice() * ((double) (100 - (getDiscount()/2)) / 100));
        }

        if (price == getPrice()) {
            setContent("Bu sigortaya indirim uygulanmadı.");
        } else {
            setContent(content);
        }
    }

}
