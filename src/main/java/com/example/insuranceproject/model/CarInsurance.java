package com.example.insuranceproject.model;

import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class CarInsurance{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Kasko kasko;

    /*@Builder
    public CarInsurance(BaseOffer baseOffer) {
        super(baseOffer.getId(), baseOffer.getTitle(), baseOffer.getContent(), baseOffer.getDiscount(),
                baseOffer.getPrice(), baseOffer.getProvider(), baseOffer.getCategory());
    }*/

    public void setPrice(double price, int age, int kilometer) {
        String content = "";
        if (age < kasko.getAgeLimit()) {
            kasko.setPrice(price * ((double) (100 - kasko.getDiscount()) / 100));
            content = "Araç yaşınız küçük olduğu için orijinal fiyat (" + price + ") üzerinden %" + kasko.getDiscount() + " indirim uygulandı.";
        }
        if (kilometer < kasko.getKilometerLimit()) {
            content += " Araç kilometresi küçük olduğu için fiyat (" + kasko.getPrice() + ") üzerinden %" + kasko.getDiscount() + " indirim uygulandı.";
            kasko.setPrice(kasko.getPrice() * ((double) (100 - kasko.getDiscount()) / 100));
        }

        if (price == kasko.getPrice()) {
            kasko.setContent("Bu sigortaya indirim uygulanmadı.");
        } else {
            kasko.setContent(content);
        }
    }
}
