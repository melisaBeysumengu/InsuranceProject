package com.example.insuranceproject.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Dask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content = "Orijinal ücret.";

    private Integer discount;

    private Double price;

    private String provider;

    private String category;

    private String createdAt;

    private Integer houseAgeLimit;

    @OneToOne
    @JoinColumn(name="house_id")
    @JsonBackReference
    private House house;

    public void setPrice(Double price, String binaInsaTarzi, Boolean isInDangerZone, Double value) {
        String content = "";
        if(isInDangerZone){
            setPrice(price * (100 + getDiscount())/100);
            content = "Tehlikeli bölgede olduğunuz için %" + getDiscount() + " zam yapıldı. ";
        }else{
            setPrice(price * (100 + getDiscount())/100);
            content += "Tehlikeli bölgede olmadığınız için %" + getDiscount() + " indirim yapıldı. ";
        }

        if(value > 1000000){
            setPrice(getPrice() * (100 + getDiscount())/100);
            content += "Evin değeri yüksek olduğu için %" + getDiscount() + " zam yapıldı. ";
        }

        if(binaInsaTarzi.equals("Betonarme/Çelik")){
            setPrice(getPrice() * (100 - getDiscount())/100);
            content += "Ev "+ binaInsaTarzi + " olduğu için %" + getDiscount() + " indirim yapıldı.";
        }

        if(price.equals(getPrice())){
            setContent("Bu sigortaya indirim uygulanmadı.");
        }else {
            setContent(content);
        }
    }
}
